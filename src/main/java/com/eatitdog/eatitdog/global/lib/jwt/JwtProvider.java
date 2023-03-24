package com.eatitdog.eatitdog.global.lib.jwt;

import com.eatitdog.eatitdog.domain.user.domain.User;
import com.eatitdog.eatitdog.domain.user.domain.repository.UserRepository;
import com.eatitdog.eatitdog.domain.user.exception.UserNotFoundException;
import com.eatitdog.eatitdog.global.exception.global.InvalidTokenException;
import com.eatitdog.eatitdog.global.lib.encrypt.Encrypt;
import com.eatitdog.eatitdog.global.properties.JwtConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final Encrypt encrypt;
    private final UserRepository userRepository;
    private final JwtConfiguration jwtConfiguration;

    private static final String REQUEST_HEADER = "Authorization";

    public String createToken(User user, JwtType jwtType) {

        Date nowDate = new Date();
        Calendar expiredDate = Calendar.getInstance();
        expiredDate.setTime(nowDate);

        String secretKey = "";

        switch(jwtType) {
            case ACCESS:
                expiredDate.add(Calendar.DATE, 3);
                secretKey = jwtConfiguration.getAccessKey();
            case REFRESH:
                expiredDate.add(Calendar.DATE, 20);
                secretKey = jwtConfiguration.getRefreshKey();
        }

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, encrypt.getSignatureAlgorithm().getValue());

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", encrypt.getSignatureAlgorithm());

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("id", user.getId());

        JwtBuilder builder = Jwts.builder().setHeaderParams(headerMap)
                .setClaims(claimsMap)
                .setExpiration(expiredDate.getTime())
                .signWith(encrypt.getSignatureAlgorithm(), signingKey);

        return builder.compact();
    }

    public User validateToken(String token) {

        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtConfiguration.getAccessKey())).parseClaimsJws(token).getBody();

        return userRepository.findById(claims.get("id", Long.class))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public String refresh(String refreshToken) {

        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtConfiguration.getRefreshKey())).parseClaimsJws(refreshToken).getBody();
        User user = userRepository.findById(claims.get("id", Long.class))
                .orElseThrow(() -> InvalidTokenException.EXCEPTION);

        return createToken(user, JwtType.ACCESS);
    }

    public String extract(HttpServletRequest request, String type) {

        Enumeration<String> headers = request.getHeaders(REQUEST_HEADER);

        while(headers.hasMoreElements()) {
            String value = headers.nextElement();
            if(value.toLowerCase().startsWith(type.toLowerCase())) {
                return value.substring(type.length()).trim();
            }
        }

        return null;
    }
}
