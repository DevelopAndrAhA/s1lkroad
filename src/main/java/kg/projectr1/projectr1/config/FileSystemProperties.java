package kg.projectr1.projectr1.config;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "file-system")
public class FileSystemProperties {
    private String root;
}
