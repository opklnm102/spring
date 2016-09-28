package me.dong.config;

import me.dong.util.JsonStringConverter;
import me.dong.util.StringConverter;
import me.dong.util.XmlStringConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiConfig {

    @Bean
    public StringConverter jsonStringConverter(){
        return new JsonStringConverter();
    }

    @Bean
    public StringConverter xmlStringConverter(){
        return new XmlStringConverter();
    }
}
