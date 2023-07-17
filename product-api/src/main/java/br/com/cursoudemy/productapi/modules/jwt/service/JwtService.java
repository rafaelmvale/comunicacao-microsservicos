package br.com.cursoudemy.productapi.modules.jwt.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.config.exception.AuthenticationException;
import br.com.cursoudemy.productapi.modules.jwt.dto.JwtResponse;

@Service
public class JwtService {
  private static final String BEARER = "bearer ";
  @Value("${app-config.secrets.api-secret}")
  private String apiSecret;

  public void validateAuthorization(String token) {
    try {
      var accessToken = extractToken(token);
      var claims = Jwts
        .parserBuilder()
        .setSigningKey(Keys.hmacShaKeyFor(apiSecret.getBytes()))
        .build()
        .parseClaimsJws(accessToken)
        .getBody();

        var user = JwtResponse.getUser(claims);
        if(isEmpty(user) || isEmpty(user.getId())) {
          throw new AuthenticationException("The user is not valid");
        }

    } catch (Exception e) {
      e.printStackTrace();
      throw new AuthenticationException("Error while trying to proccess the Access token");
    }
  }

  private String extractToken(String token) {
    if(isEmpty(token)) {
     throw new AuthenticationException("The access token was not informed");
    }
    if(token.toLowerCase().contains(BEARER)) {
      return token.replace(BEARER, Strings.EMPTY);
    }
    return token;
  }
}
