package com.company.readnovel.utils;


import com.company.readnovel.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

@Component
public class JwtUtils {
    private JwtBuilder jwtBuilder;
    private DateUtils dateUtils;
    private JwtParser jwtParser;
    private byte[] apiSecretByteArray;

    public JwtUtils(JwtBuilder jwtBuilder, DateUtils dateUtils, JwtParser jwtParser) {
        this.jwtBuilder = jwtBuilder;
        this.dateUtils = dateUtils;
        this.jwtParser = jwtParser;
        this.apiSecretByteArray = getApiSecretByteArray();
    }

    public String signToken(User user) {
        var currentDate = dateUtils.getCurrentDate();
        var futureDate = dateUtils.getCurrentIncreasedDate();

        var signatureAlgorithm = SignatureAlgorithm.HS256;
        var signingKey = new SecretKeySpec(apiSecretByteArray, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        var builder = jwtBuilder
                .setIssuedAt(currentDate)
                .setSubject(user.getUsername())
                .claim("authorities", user.getAuthorities())
                .claim("email", user.email)
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(futureDate);
        var token = builder.compact();
        return token;
    }


    public Claims getDecodedToken(String token) {
        var jwtClaims = jwtParser
                .setSigningKey(apiSecretByteArray)
                .parseClaimsJws(token);

        var claims = jwtClaims.getBody();
        return claims;
    }

    private byte[] getApiSecretByteArray() {
        var apiSecret = System.getenv("API_SECRET");
        var apiSecretByteArray = DatatypeConverter.parseBase64Binary(apiSecret);
        return apiSecretByteArray;
    }
}
