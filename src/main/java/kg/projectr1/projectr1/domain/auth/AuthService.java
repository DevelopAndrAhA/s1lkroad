package kg.projectr1.projectr1.domain.auth;


import kg.projectr1.projectr1.domain.auth.dto.AuthRequest;
import kg.projectr1.projectr1.domain.auth.dto.AuthResponse;
import kg.projectr1.projectr1.domain.auth.dto.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse authenticate(AuthRequest request);
}
