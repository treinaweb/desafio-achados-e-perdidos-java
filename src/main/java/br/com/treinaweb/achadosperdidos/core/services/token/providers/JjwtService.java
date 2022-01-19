package br.com.treinaweb.achadosperdidos.core.services.token.providers;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import br.com.treinaweb.achadosperdidos.core.services.token.adapters.TokenService;
import br.com.treinaweb.achadosperdidos.core.services.token.exceptions.TokenServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;

@Setter
@Service
@ConfigurationProperties(prefix = "br.com.treinaweb.achadosperdidos.token")
public class JjwtService implements TokenService {

    private String accessKey;
    private int accessExpiration;
    private String refreshKey;
    private int refreshExpiration;

    @Override
    public String generateAccessToken(String subject) {
        return generateToken(accessKey, accessExpiration, subject);
    }

    @Override
    public String getSubjectFromAccessToken(String accessToken) {
        return getClaims(accessToken, accessKey).getSubject();
    }

    @Override
    public String genarateRefreshToken(String subject) {
        return generateToken(refreshKey, refreshExpiration, subject);
    }

    @Override
    public String getSubjectFromRefreshToken(String refreshToken) {
        return getClaims(refreshToken, refreshKey).getSubject();
    }

    private String generateToken(String signKey, int expirationTime, String subject) {
        var issuedAt = Instant.now();
        var expiration = issuedAt.plusSeconds(expirationTime);
        return Jwts.builder()
            .setClaims(new HashMap<String, Object>())
            .setSubject(subject)
            .setIssuedAt(new Date(issuedAt.toEpochMilli()))
            .setExpiration(new Date(expiration.toEpochMilli()))
            .signWith(SignatureAlgorithm.HS512, signKey)
            .compact();
    }

    private Claims getClaims(String token, String signKey) {
        try {
            return tryGetClaims(token, signKey);
        } catch (JwtException exception) {
            throw new TokenServiceException(exception.getLocalizedMessage());
        }
    }

    private Claims tryGetClaims(String token, String signKey) {
        return Jwts.parser()
            .setSigningKey(signKey)
            .parseClaimsJws(token)
            .getBody();
    }

}
