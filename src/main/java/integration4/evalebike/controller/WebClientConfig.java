package integration4.evalebike.controller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://testbench.raoul.dev")
                .defaultHeader("X-Api-Key", "9e8dffd7-f6e1-45b4-b4aa-69fd257ca200")
                .build();
    }
}
