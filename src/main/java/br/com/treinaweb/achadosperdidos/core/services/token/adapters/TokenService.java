package br.com.treinaweb.achadosperdidos.core.services.token.adapters;

public interface TokenService {

    String generateAccessToken(String subject);

    String getSubjectFromAccessToken(String accessToken);

    String genarateRefreshToken(String subject);

    String getSubjectFromRefreshToken(String refreshToken);

}
