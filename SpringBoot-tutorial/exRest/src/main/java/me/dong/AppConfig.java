package me.dong;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import net.sf.log4jdbc.Log4jdbcProxyDataSource;

/*
 * DataSource가 DI 컨테이너에 자동으로 등록되었지만
 * Log4JDBC를 사용하려면 DataSource를 명시적으로 정의해야함
 * -> EnableAutoConfiguration의 자동등록 기능은 무시
 */
@Configuration
public class AppConfig {
	
	@Autowired
	DataSourceProperties dataSourceProperties;  //설정한 속성 주입클래스
	DataSource dataSource;

	//스프링 데이터가 제공하는 데이터소스용 팩톨리 클래스를 사용하여 DataSource인스턴스 생성
	@Bean
	DataSource realDataSource() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.dataSourceProperties.getClassLoader())
				.url(this.dataSourceProperties.getUrl())
				.username(this.dataSourceProperties.getUsername())
				.password(this.dataSourceProperties.getPassword());
		this.dataSource = factory.build();
		return this.dataSource;
	}

	//Log4jdbcProxyDataSource로 DataSource를 래핑.
	//DataSource에 구현된 각각의 처리에 로깅 처리를 끼워넣는다.
	@Bean
	@Primary
	DataSource dataSource() {
		return new Log4jdbcProxyDataSource(this.dataSource);
	}
}
