package kg.projectr1.projectr1.security.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String extractUsername(String token);

    String generateToken(
            UserDetails userDetails
    );
    String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    );

    boolean isTokenValid( String token, UserDetails userDetails);

}