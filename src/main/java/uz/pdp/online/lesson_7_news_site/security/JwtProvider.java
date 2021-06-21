package uz.pdp.online.lesson_7_news_site.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.online.lesson_7_news_site.entity.Lavozim;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider { // tokenlarni generate va validate qilamiz

    private static final long expireTime = 1000 * 60 * 60 * 24;
    private static final String secretKey = "maxfiySo'zHechKimBilmasin";

    public String generateToken(String username, Lavozim lavozim) {
        Date expireDate = new Date(System.currentTimeMillis() + expireTime);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .claim("roles", lavozim.getName())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }

    public String getUsernameFromToken(String token) {
        try {
            String email = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return email;
        } catch (Exception e) {
            return null;
        }
    }
}
