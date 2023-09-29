package ovh.major.githubclient;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Log4j2
@EnableFeignClients
public class GithubclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubclientApplication.class, args);
    }

}
