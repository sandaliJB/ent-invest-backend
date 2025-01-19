package com.enterprise.ent_invest_backend.Jwt;

import com.enterprise.ent_invest_backend.Model.User;
import com.enterprise.ent_invest_backend.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    private final UserRepository userRepository;

    // Constructor-based dependency injection
    public JwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Extract the username (subject) from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract the expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extract any specific claim using a resolver function
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Extract all claims with clock skew allowance
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .setAllowedClockSkewSeconds(60) // Allow 60 seconds clock skew
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Check if the token is expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Validate the token against user details and check expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Generate a token with additional claims
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        User user = userRepository.findByUserEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User with email " + email + " not found"));

        // Add user-specific claims
        claims.put("userId", user.getUserId());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("role", user.getUserRole());

        return createToken(claims, email);
    }

    // Create the JWT token
    private String createToken(Map<String, Object> claims, String email) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24); // 24 hours expiration

        // Debugging token times
        System.out.println("Token Issued At: " + issuedAt);
        System.out.println("Token Expiration: " + expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Get the signing key from the secret
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
