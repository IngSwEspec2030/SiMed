package com.sw.ingenieria.simed.service;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sw.ingenieria.simed.exeptions.JWTException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Johan Céspedes Ortega at PUJ
 * @project SiMed
 * @date 07/09/2020
 */
@Service
public class JWTService {
    private static final String PREFIX = "Bearer ";

    @Value("${jwt.token.expiration-minutes}")
    private int jwtExpirationMinutes;

    private static final String JWT_SECRET = "MwTeTnnSkhWQt8utGETcmHIGIsOzQZLsXVrpyDG1";

    private static final String JWT_ISSUER = "simed";

    public String generateToken(final String username) throws JWTException {
        try {
            return JWT.create()
                    .withIssuer(JWT_ISSUER)
                    .withIssuedAt(new Date())
                    .withSubject(username) //Token Content
                    .withExpiresAt(addMinutes(jwtExpirationMinutes))
                    .sign(buildAlgorithm(JWT_SECRET));
        } catch (IllegalArgumentException | JWTCreationException e) {
            throw new JWTException(e.getMessage(), e);
        }
    }

    public String validate(final String token) throws JWTException {
        try {
            final JWTVerifier verifier = JWT.require(buildAlgorithm(JWT_SECRET))
                    .build();
            final DecodedJWT decoded = verifier.verify(token);
            return decoded.getSubject();
        } catch (JWTVerificationException e) {
            throw new JWTException(e.getMessage(), e);
        }
    }

    private Algorithm buildAlgorithm(String secret) {
        return Algorithm.HMAC512(secret);
    }

    private Date addMinutes(final int minutes) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    public String getToken(String header){
        return header.split(PREFIX)[1];
    }
}

