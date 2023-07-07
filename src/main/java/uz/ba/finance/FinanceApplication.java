package uz.ba.finance;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uz.ba.finance.dto.user.UserCreateDTO;
import uz.ba.finance.service.UserService;

@SpringBootApplication
@RequiredArgsConstructor
public class FinanceApplication implements CommandLineRunner {

    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(FinanceApplication.class, args);
    }

    public void run(String... args) {
        userService.register(new UserCreateDTO(
                "Bunyod",
                "bunyod",
                "bunyod"
        ));
    }
}
