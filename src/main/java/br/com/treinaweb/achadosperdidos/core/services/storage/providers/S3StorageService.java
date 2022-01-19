package br.com.treinaweb.achadosperdidos.core.services.storage.providers;

import java.io.IOException;
import java.util.UUID;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.treinaweb.achadosperdidos.core.models.Imagem;
import br.com.treinaweb.achadosperdidos.core.repositories.ImagemRepository;
import br.com.treinaweb.achadosperdidos.core.services.storage.adapters.StorageService;
import br.com.treinaweb.achadosperdidos.core.services.storage.exceptions.StorageServiceException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Service
@Profile("prod")
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "br.com.treinaweb.achadosperdidos.s3")
public class S3StorageService implements StorageService {

    private Regions region;
    private String accessKey;
    private String secretKey;
    private String bucketName;

    private final ImagemRepository imagemRepository;

    @Override
    public Imagem save(MultipartFile file) {
        try {
            return trySave(file);
        } catch (IOException exception) {
            throw new StorageServiceException(exception.getLocalizedMessage());
        }
    }

    private Imagem trySave(MultipartFile file) throws IOException {
        var s3Client = getS3Client();
        createBucketIfNotExists(s3Client);
        var putObjectRequest = getPutObjectRequest(file);
        s3Client.putObject(putObjectRequest);
        var imagem = buildImagem(file, s3Client, putObjectRequest);
        return imagemRepository.save(imagem);
    }

    private Imagem buildImagem(MultipartFile file, AmazonS3 s3Client, PutObjectRequest putObjectRequest) {
        return Imagem.builder()
            .filename(putObjectRequest.getKey())
            .contentLength(file.getSize())
            .contentType(file.getContentType())
            .url(s3Client.getUrl(bucketName, putObjectRequest.getKey()).toString())
            .build();
    }

    private PutObjectRequest getPutObjectRequest(MultipartFile file) throws IOException {
        return new PutObjectRequest(
            bucketName,
            genareteFilename(file),
            file.getInputStream(),
            getObjectMetadata(file)
        ).withCannedAcl(CannedAccessControlList.PublicRead);
    }

    private ObjectMetadata getObjectMetadata(MultipartFile file) {
        var objectMetada = new ObjectMetadata();
        objectMetada.setContentType(file.getContentType());
        objectMetada.setContentLength(file.getSize());
        return objectMetada;
    }

    private void createBucketIfNotExists(AmazonS3 s3Client) {
        if (!s3Client.doesBucketExistV2(bucketName)) {
            s3Client.createBucket(bucketName);
        }
    }

    private AmazonS3 getS3Client() {
        return AmazonS3ClientBuilder
            .standard()
            .withCredentials(getS3CredentialsProvider())
            .withRegion(region)
            .build();
    }

    private AWSStaticCredentialsProvider getS3CredentialsProvider() {
        var s3Credentials = new BasicAWSCredentials(accessKey, secretKey);
        return new AWSStaticCredentialsProvider(s3Credentials);
    }

    private String genareteFilename(MultipartFile file) {
        var originalFilename = file.getOriginalFilename();
        var ext = originalFilename.split("\\.")[1];
        return UUID.randomUUID().toString() + "." + ext;
    }

}
