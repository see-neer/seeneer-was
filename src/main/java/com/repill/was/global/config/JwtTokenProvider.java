package com.repill.was.global.config;

import com.repill.was.member.entity.member.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Duration;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    private static final String ROLES_KEY = "roles";

    private String secretKey;

    private Long tokenValidMilliseconds;
    private final MemberRepository memberRepository;

    public JwtTokenProvider(@Value("${spring.jwt.secret}") String secretKey, @Value("${spring.jwt.valid-duration}") Duration validDuration, MemberRepository memberRepository) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.tokenValidMilliseconds = validDuration.toMillis();
        this.memberRepository = memberRepository;
    }


    public String createToken(String userId, String role) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put(ROLES_KEY, role);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createRefreshToken() {
        Claims claims = Jwts.claims().setSubject(UUID.randomUUID().toString());
        Date now = new Date();
        Integer twoWeeks = 14;
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMilliseconds * twoWeeks))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUserId(String jwtToken) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody().getSubject();
    }

    public String getUserRoles(String jwtToken) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken).getBody().get(ROLES_KEY, String.class);
    }

    public boolean validateToken(String jwtToken) {
        Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
        return true;
    }
}

