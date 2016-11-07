package me.dong;

import me.dong.security.GitProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.dialect.springdata.SpringDataDialect;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
/*
JPA에서 JAVA8의 LocalDateTime을 지원하지 않는다.
Jsr310JpaConverters에 static class로 LocalDateTimeConverter가 있다.
 */
@EntityScan(basePackageClasses = {SpringBootCleanBlogApplication.class, Jsr310JpaConverters.class})
@EnableCaching  // 캐시 설정
@EnableConfigurationProperties({GitProperties.class})
public class SpringBootCleanBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCleanBlogApplication.class, args);
        System.out.println("Boot Run!!");
    }

    // paging lib config
    @Bean
    public SpringDataDialect springDataDialect(){
        return new SpringDataDialect();
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer(){
        return cm -> {
            cm.createCache("blog.category", initConfiguration(TEN_SECONDS));
            cm.createCache("github.user", initConfiguration(Duration.ONE_HOUR));
        };
    }

    public static final Duration TEN_SECONDS = new Duration(TimeUnit.SECONDS, 10);

    private MutableConfiguration<Object, Object> initConfiguration(Duration duration){
        return new MutableConfiguration<>()
                .setStatisticsEnabled(true)  // jconsole로 해당 캐시의 통계 확인.
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(duration));
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
