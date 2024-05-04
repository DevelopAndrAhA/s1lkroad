package kg.projectr1.projectr1.domain.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDto {
    private long id;
    private String name;
    private String email;
    private String[] roles;
}
