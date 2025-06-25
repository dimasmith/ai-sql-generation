package levi9.meetup.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AiApplication {

  public static void main(String[] args) {
    SpringApplication.run(AiApplication.class, args);
  }
}
