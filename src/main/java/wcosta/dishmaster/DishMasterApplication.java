package wcosta.dishmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class DishMasterApplication {

  public static void main(String[] args) {
    SpringApplication.run(DishMasterApplication.class, args);
  }

  @Bean
  public CorsWebFilter corsConfigurationSource() {
    CorsConfiguration conf = new CorsConfiguration();
    conf.setAllowCredentials(true);
    conf.addAllowedOrigin("http://localhost:4200");
    conf.addAllowedHeader("*");
    conf.addAllowedMethod("*");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", conf);

    return new CorsWebFilter(source);
  }
}
