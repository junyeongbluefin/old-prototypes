package junyeong.yu.statemachine;

import junyeong.yu.statemachine.bootstrap.ConfiguredBootstrap;
import junyeong.yu.statemachine.bootstrap.ManualBootstrap;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@AllArgsConstructor
public class StatemachineApplication {

    private final ConfiguredBootstrap configuredBootstrap;

    public static void main(String[] args) {
        SpringApplication.run(StatemachineApplication.class, args);
    }

    @PostConstruct
    private void after() {
        System.out.println("=== 1. Manual ===");
        new ManualBootstrap().init();

        System.out.println("=== 2. Configured ===");
        configuredBootstrap.init();
    }
}
