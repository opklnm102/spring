package me.dong;

import org.dom4j.tree.AbstractEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EntityScan(basePackageClasses = AbstractEntity.class)
@EnableJpaAuditing  //@CreatedBy, @LastModifiedBy를 사용하기 위한 설정
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
        System.out.println("Boot Run!!");
    }

    //	@CreatedBy, @LastModifiedBy를 사용하기 위한 설정
    @Bean
    public AuditorAware<Long> auditorProvider() {
//        return new SpringSecurityAuditorAware();
        return null;
    }
}
