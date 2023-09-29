package ovh.major.githubclient.domain.configuration;

import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class FeignConfiguration {

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }

}