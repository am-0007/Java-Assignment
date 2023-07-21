package internsathi.javaAssignment.security.token;

import internsathi.javaAssignment.Enum.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class JwtTokenService {

    private final UserDetailsService userDetailsService;

    @Value("secretKeasdfjh32rjhasdf09yjklasdfjh134hasdflpvasdfpy")
    private String secretKey;

    @Value("10000")
    private int expireIn;

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    public String generateToken(String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        List<Role> roles = new ArrayList<>();
        userDetails.getAuthorities().forEach(
                grantedAuthority -> roles.add(Role.valueOf(grantedAuthority.getAuthority()))
        );
        Map<String, Object> authority = new HashMap<>();
        authority.put("Role", roles);
        return  Jwts.builder()
                .setSubject(username)
                .setClaims(authority)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .signWith(getSignInKey(), SIGNATURE_ALGORITHM)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Date generateExpirationDate() {
        return new Date(new Date().getTime() + expireIn * 1000L);
    }
    public String getToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring("Bearer ".length());
        }
        return null;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            assert claims != null;
            username = claims.getSubject();
        } catch (Exception exception) {
            return null;
        }
        return username;
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
        return claims;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username != null &&
                        username.equals(userDetails.getUsername()) &&
                        !isTokenExpired(token)
                );
    }

    private boolean isTokenExpired(String token) {
        Date expireDate = getExpirationDate(token);
        return expireDate.before(new Date());
    }

    private Date getExpirationDate(String token) {
        Date expiredDate;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            assert claims != null;
            expiredDate = claims.getExpiration();
        } catch (Exception exception) {
            expiredDate = null;
        }
        return expiredDate;
    }
}
