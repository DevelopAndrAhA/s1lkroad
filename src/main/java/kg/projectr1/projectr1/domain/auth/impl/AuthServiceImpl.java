package kg.projectr1.projectr1.domain.auth.impl;

import kg.projectr1.projectr1.domain.auth.AuthService;
import kg.projectr1.projectr1.domain.auth.dto.AuthRequest;
import kg.projectr1.projectr1.domain.auth.dto.AuthResponse;
import kg.projectr1.projectr1.domain.auth.dto.RegisterRequest;
import kg.projectr1.projectr1.domain.user.Role;
import kg.projectr1.projectr1.domain.user.User;
import kg.projectr1.projectr1.domain.user.UserRepository;
import kg.projectr1.projectr1.domain.user.dto.UserResponseDto;
import kg.projectr1.projectr1.security.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.User)
                .build();
        user = userRepository.save(user);
        var token = jwtService.generateToken(user);
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(new String[]{user.getRole().name()})
                .build();

        return AuthResponse.builder()
                .token(token)
                .user(userResponseDto)
                .build();
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
        );

        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(new String[]{user.getRole().name()})
                .build();
        var token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .user(userResponseDto)
                .build();
    }
}
