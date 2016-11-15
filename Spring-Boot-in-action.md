# 스프링부트 코딩 공작소 정리

## Spring Boot의 핵심

### 자동 구성
많은 애플리케이션에 공통으로 필요한 애플리케이션 기능을 자동으로 구성

### Starter Dependence
어떤 기능이 필요한지 알려주면 필요한 라이브러리를 빌드에 추가한다는 것을 보장

### CLI(명령줄 인터페이스
애플리케이션 코드만 작성해도 완전한 애플리케이션을 개발할 수 있지만, 기존 프로젝트 빌드 방식에는 필요 없는 기능


### Actuator
애플리케이션을 실행할 때 내부에서 어떤 일이 일어나는지 이해할 수 있다.
* 스프링 애플리케이션 컨텍스트에 구성된 빈
* 스프링 부트의 자동구성으로 구성된 것
* 애플리케이션에서 사용할 수 있는 환경변수, 시스템 프로퍼티, 구성 프로퍼티, 명령줄 인자
* 애플리케이션에서 구동 중인 스레드의 현재 상태
* 최근에 처리된 HTTP 요청 정보
* 메모리 사량, 가비지 컬렉션, 웹 요청, 데이터 소스 사용량 등 다양한 메트릭
> 이러한 정보를 웹 엔드포인트와 셸 인터페이스를 사용하여 보여준다.

## Spring Boot CLI 설치
* 다운로드한 배포 버전 설치
* SDKMAN
* OSX homebrew
* OSX mac port

## Spring Initializer로 프로젝트 구성
* [웹 기반 인터페이스](http://start.spring.io/)
  * zip 다운로드
* Spring Tool Suite(STS)
  * 내부에서 생성
* IntelliJ IDEA
  * 내부에서 생성
* SpringBoot CLI
  * `spring init` - 기본 설정
  * `spring init -dweb, jpa, security` - 의존성 설정
  * `spring init -dweb, jpa, security --build gradle` - jar 생성(gradle build)
  * `spring init -dweb, jpa, security --build gradle -p war myapp` - WAR 생성(gradle build). myapp 디렉토리에 풀기
  * `spring init -dweb, jpa, security --build gradle -p jar -x` - jar 현재 디렉토리에 풀기
  * `spring help init` - 사용할 수 있는 매개변수 확인
  * `spring init --list` - Spring Initializer가 지원하는 기능 모두 확인

## Spring Boot 프로젝트 구조
* `build.gradle` - gradle build 명세(maven이면 `pom.xml`)
* `gradlew` - gradle wrapper. 시스템에 gradle을 설치하지 않았으면 `gradle`명령 대신에 이 스크립트 사용
* `xxxApplication.java` - 애플리케이션을 시작하는 `main()`가 있는 클래스
* `application.properties` - 필요한 구성 프로퍼티를 추가하는 빈 프로퍼티 파일
* `xxxApplicationTest.java` - Spring Boot 자동구성을 이용하여 Spring 애플리케이션 컨텍스트를 로드하도록 구성한 빈 JUnit 테스트 클래스
* `static folder` - `JavaScript`, `CSS`, `Image`등 정적 콘텐츠가 존재
* `templates folder` - 모델 데이터를 랜더링할 템플릿 존재

## Spring BootStraping
```Java
@SpringBootApplication  // 컴포넌트 검색과 자동 구성 활성화
public class MyApplication{
  public static void main(String[] args) {
      // application bootstrap
      // 명령줄에서 실행가능한 JAR파일로 App실행
      SpringApplication.run(MyApplication.class, args);
  }
}
```

> ### @SpringBootApplication
유용한 Annotation 3개를 묶은 것
* Spring의 `@Configuration` - Java기반 구성 클래스로 지정
* Spring의 `@ComponentScan` - 컴포넌트 클래스들을 자동으로 검색하여 SpringApplicationContext에 Bean으로 등록
* Spring Boot의 `@EnableAutoConfiguration` - Spring Boot의 자동 구성

> ### Gradle로 Application 실행
```sh
$gradle bootRun  // bootRun task 실행(spring boot gradle plug in에 포함된 task)
// or
$gradle build  // build task 실행
$java -jar build/libs/xxx-0.0.1-SNAPSHOT.jar
```

## Spring Boot Application Test
```Java
@RunWith(SpringJUnit4ClassRunner.class)
// Spring Boot로 Context Load
@SpringApplicationConfiguration(classes = MyApplication.class)
@WebAppConfiguration
public class MyApplicationTests{

  /**
  * context load test
  * MyApplication에 정의된 구성에 문제가 없다면 테스트는 성공
  */
  @Test
  public void contextLoads(){
  }
}
```

## Spring Boot Build 파헤치기
* 빌드 플러그인의 주기능은 **실행 가능한 JAR로 패키징**
* `Gradle`, `Maven` 중 선택

```gradle
// Gradle Build Script
buildscript {
	ext {
		springBootVersion = '1.4.1.RELEASE'
	}
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")  // 스프링부트 플러그인 의존성
	}
}

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'spring-boot'  // 스프링부트 플러그인 적용

jar {
	baseName = 'nine_tique'
	version = '0.0.1-SNAPSHOT'
}
sourceCompatibility = 1.8
targetCompatibility = 1.8

repositories {
	mavenCentral()
}


dependencies {
  testCompile('org.springframework.boot:spring-boot-starter-test')  // 스타터 의존성
  compile('org.springframework.boot:spring-boot-starter-web')
}
```

## 스타터 의존성
* 복잡한 프로젝트 의존성을 해결
* 스프링부트 버전에 따라 의존성 버전이 전이적으로 결정
* 전의적 의존성 확인
  * 의존성을 트리형태로 확인
  * `$gradle dependencies` or `$mvn dependency:tree`
* 스타터의 전이적 의존성 오버라이드
  * `Gradle`은 최신버전의 의존성 선호
  * `Maven`은 항상 더 가까운 의존성 선호
  * 선택적으로 오버라이드 - **잘 동작하는지에 대한 테스트 필요**
    * 전이적 의존성 제외
    * 의존성 추가
  * 새로운 버전에서 버그를 고칠 때 같은 특별한 상황에서만 전이적 의존성을 오버라이드하는 것이 좋다

```gradle
compile('org.springframework.boot:spring-boot-starter-web'){
  exclude group: 'com.fasterxml.jackson.core'  // 의존성 제외
}
compile('com.fasterxml.jackson.core:jackson-databind:2.3.1')  // 특정버전 의존성 추가
```

## Auto Configuration 사용하기
* 스프링 구성을 적용해야 할지 말지를 결정하는 요인들을 판단하는 런타임(더 정확히는 Application이 시작되는 시점) 과정
  * 클래스패스에 `JdbcTemplate`이 있고 `DataSource`빈이 있다면 JdbcTemplate빈을 자동 구성
  * 클래스패스에 `Thymeleaf`가 있다면 Thymeleaf 템플릿 리졸버, 뷰 리졸버, 템플릿 엔진을 구성
  * 클래스패스에 ``스프링 시큐리티``가 있다면 아주 기본적인 웹 보안을 구성

### Conditional Configuration을 사용하여 자신만의 조건 구성하기
* 스프링 4.0에서 추가된 기능
* `Condition` 인터페이스를 상속받고 `matches()`를 오버라이드

```java
public class JdbcTemplateCondition implements Condition{

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try{
            // JdbcTemplate이 있을 때만 통과
            context.getClassLoader().loadClass("org.springframework.jdbc.core.JdbcTemplate");
            return true;
        }catch (Exception e){
            return false;
        }
    }
}

// JdbcTemplate때만 Bean을 생성
@Conditional(JdbcTemplateCondition.class)
@Bean
public MyService myService(){
    //Todo
}
```

> #### Auto Configuration에서 사용하는 Condition 어노테이션
| Condition 어노테이션 | 구성을 적용하는 조건 |
|:--|:--|
| @ConditionalOnBean | 대상 빈을 구성함 |
| @ConditionalOnMissingBean | 대상 빈을 아직 구성하지 않음 |
| @ConditionalOnClass | 대상 클래스가 클래스패스에 있음 |
| @ConditionalOnMissingClass | 대상 클래스가 클래스패스에 없음 |
| @ConditionalOnExpression | 스프링 표현식 언어(SpEL)가 True |
| @ConditionalOnJava | 자바 버전이 특정 버전 또는 버전 범위에 맞음 |
| @ConditionalOnJndi | JNDI InitialContext가 사용 가능하고, 선택적으로 지정한 JNDI위치가 있음 |
| @ConditionalOnProperty | 지정한 구성 프로퍼티가 기대하는 값을 가짐 |
| @ConditionalOnResource | 지정한 리소스가 클래스패스에 있음 |
| @ConditionalOnWebApplication | 애플리케이션이 웹 애플리케이션임 |
| @ConditionalOnNotWebApplication | 애플리케이션이 웹 애플리케이션이 아님 |

```java
// 다른 Configuration클래스에서 추가적으로 구성을 가져오면 자체적으로 빈을 정의
//클래스패스에 DataSource, EmbeddedDatabaseType가 없으면 제공하는 구성 무시
@Configuration
@ConditionalOnClass({ DataSource.class, EmbeddedDatabaseType.class })  
@EnableConfigurationProperties(DataSourceProperties.class)
@Import({ Registrar.class, DataSourcePoolMetadataProvidersConfiguration.class })
public class DataSourceAutoConfiguration {
    ...
}
```

## Auto Configuration 오버라이드
* 자동구성이 적합하지 않을 경우 사용
  * ex. 보안

### 사용자 정의 보안 구성
```java
// 보안 설정
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").access("hasRole('READER')")  // READER 권한 필요
                .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/login")  // 로그인 폼 경로 설정
                .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {  // 사용자정의 UserDetailsService
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return readerRepository.findOne(username);
            }
        });
    }
}

// Application 설정
@SpringBootApplication
public class ReadingListApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(ReadingListApplication.class, args);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // login 경로를 login 템플릿으로 매핑
       registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
       // Reader 타입의 객체가 컨트롤러 매개변수가 있을 때 처리할 리졸버 설정
        argumentResolvers.add(new ReaderHandlerMethodArgumentResolver());
    }
}

public class ReaderHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Reader.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Authentication auth = (Authentication) webRequest.getUserPrincipal();
        return auth != null && auth.getPrincipal() instanceof Reader ? auth.getPrincipal() : null;
    }
}


// 권한을 관리하는 JPA Entity
@Entity
public class Reader implements UserDetails {

    @Id
    private String username;
    private String fullname;
    private String password;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // UserDetails 메소드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  // READER 권한 부여
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_READER"));
    }

    @Override
    public boolean isAccountNonExpired() {  // 계정이 만료되지 않았다는 것을 반환
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  // 계정이 잠겨있지 않다는 것을 반환
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  // 자격이 유효하다는 것을 반환
        return true;
    }

    @Override
    public boolean isEnabled() {  // 계정이 활성화되어 있다는 것을 반환
        return true;
    }
}
```

> 사용자에게 부여한 권한 자체도 Entity
별도의 DB Table로 관리
계정 만료 여부, 잠김 여부, 활성화 여부를 나타내는 Boolean값도 DB에서 관리하는 필드


## Application Property 구성
* [문서](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html) 참고해서 설정
* 선택적이므로 내용 없어도 무방

### Property 설정 방법(우선순위 배치)
1. 명령줄 인자
2. java:comp/env에서 얻을 수 있는 JNDI 속성
3. JVM 시스템 프로퍼티
4. OS의 환경변수
5. `random.*`로 시작하는 프로퍼티 때문에 무작위로 생성된 값(${random.long}처럼 다른 프로퍼티를 설정할 때 참조)
6. 애플리케이션 외부에 있는 `application.properties`나 `application.yml` 파일
7. 애플리케이션 내부에 패키징된 `application.properties`나 `application.yml` 파일
8. `@PropertySource`로 지정된 프로퍼티 소스
9. 기본 프로퍼티

> #### application.properties, application.yml는 어디에나 배치 가능(우선순위 배치, yml이 우선)
1. 외부적으로 애플리케이션이 작동하는 디렉토리의 /config 하위 디렉토리
2. 외부적으로 애플리케이션이 작동하는 디렉토리
3. 내부적으로 config 패키지
4. 내부적으로 클래스패스의 루트

### 탬플릿 캐싱 비활성화
* 명령줄인자 - `$java -jar build/libs/xxx-0.0.1-SNAPSHOT.jar --spring.thymeleaf.cache=false`
* 환경변수 - `$export spring_thymeleaf_cache=false`

### 내장 서버 구성
* 포트 변경
  * `server.port=8000`
* HTTPS 사용

```sh
// 1.JDK의 keytool로 키스토어 생성(패스워드 기억!!)
$keytool -keystore mykeys.jks -genkey -alias tomcat -keyalg RSA

// 2.application.properties 파일 설정
server.port=8000
server.ssl.key-store= file://path/to/mykeys.jks
server.ssl.key-store-password= keystore 생성시 입력한 password
server.ssl.key-password= keystore 생성시 입력한 password
```

### 로깅 구성
* 기본적으로 INFO 레벨 로깅에 [logback](http://logback.qos.ch) 사용
* 로깅 구성을 완전히 제어하려면 클래스패스 root(src/main/resources)에 `logback.xml`파일을 생성
  * logback 문서 참고
* 설정으로 로깅 구성
```
logging.level.root= WARN  // root logging level WARN으로 설정
logging.level.org.springframework.security= DEBUG  // security logging level DEBUG로 설정
// 로그를 /var/logs/BookWorm.log에 기록, W권한 필요, 기본적으로 10MB마다 로그파일 교체
logging.path= /var/logs  
logging.file= BookWorm.log
// 로깅 구성을 완전히 제어하고 싶지만 파일 이름 변경, 두 런타임 프로파일에서 서로 다른 로깅구성시 유용
logging.config.classpath= logging-config.xml
```

### 프로파일 구성
* 서로 다른 런타임에 배포할 때 세부구성을 다르게 할 수 있다

```java
@Profile("production")  //production 프로파일을 활성화시켰을 경우 아래 설정이 적용
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  ...
}
```

```sh
// 명령줄에서 production 프로파일 활성화
$java -jar build/libs/xxx-0.0.1-SNAPSHOT.jar --spring.profiles.active=production

// application.properties 파일에서 활성화
spring.profiles.active=production
```
* 프로파일에 특화된 Property 파일 설정
  * `application-{profile}.properties`
  * ex. application-development.properties - 개발용 구성(콘솔에 DEBUG level 로깅)

## 통합 테스트를 위한 자동구성
* Spring이 수행하는 가장 핵심적인 작업 -> Application을 구성하는 컴포너느를 모두 연결하는 것
  * 어떤 기반이든 연결명세를 읽어 `Application Context`안에 `Bean`객체를 생성, 의존 관계에 따라 **주입**
* 통합 테스트시 출시 환경에서 실행할 때와 동일한 방식으로 테스트 대상 `Bean`을 연결하는 것이 중요
```java
// SpringJUnit4ClassRunner를 이용한 스프링 통합 테스트
@RunWith(SpringJUnit4ClassRunner.class)  // 스프링 통합 테스트 활성화
//@ContextConfiguration(classes = AddressBookConfiguration.class)  // SpringBoot의 기능을 완전히 로드 못함
@SpringApplicationConfiguration(classes = AddressBookConfiguration.class)  // SpringBoot Application Context load
public class AddressServiceTests{

  @Autowired
  private AddressService addressService;  // AddressService 주입

  @Test
  public void testService(){  // AddressService Test
    Address address = addressService.findByLastName("Sherman");
    assertEquals("P", address.getFirstName());
  }
}
```

## Web Application Test
* HTTP 요청을 제대로 처리했는지 검증
  * Spring Mock MVC
  * Web 통합 Test

### Spring Mock MVC
* 서블릿 컨테이너에서 Controller를 실행하지 않고도 컨트롤러에 HTTP 요청을 할 수 있다
  * 실제 컨테이너에서 실행하지 않고 흉내 낸다
* `MockMvcBuilders`를 사용해 설정
  * `standaloneSetup()` - 수동으로 생성하고 구성한 컨트롤러 1개 이상을 서비스할 MockMvc를 만든다, 유닛 테스트처럼 사용
  * `webAppContextSetup()` - SpringContext를 사용하여 MockMvc를 만든다, 통합 테스트에 사용

```java
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductListApplication.class)
@WebAppConfiguration  // Web Context Test 활성화
public class MockMvcWebTests {

    @Autowired
    private WebApplicationContext webApplicationContext;  // WebApplicationContext 주입

    private MockMvc mockMvc;

    @Before  // @Test 메소드전에 실행
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders  // MockMvc 설정
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void homePage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))  // GET 요청
                .andExpect(MockMvcResultMatchers.status().isOk())  // HTTP 200 응답코드 검증
                .andExpect(MockMvcResultMatchers.view().name("ProductList"))  // 응답 View 이름 검증
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))  // 응답 Model 속성 검증
                .andExpect(MockMvcResultMatchers.model().attribute("products", Matchers.is(Matchers.empty())));  // 속성 내용 검증
    }

    @Test
    public void postBook() throws Exception {
        // 상품 등록 요청 검증  
        mockMvc.perform(MockMvcRequestBuilders.post("/")  // POST 요청
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)  // ContentType 설정
                .param("name", "PRODUCT NAME")  // 전송할 폼을 시뮬레이션하는 필드들 설정
                .param("price", "PRODUCT PRICE"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())  // 요청 실행시 "/"로 리다이렉트되는지 검증
                .andExpect(MockMvcResultMatchers.header().string("Location", "/"));

        Product expectedProduct = new Product();  // 생성할 상품 정보 설정
        expectedProduct.setId(1L);
        expectedProduct.setName("aa");
        expectedProduct.setPrice(35000);

        mockMvc.perform(MockMvcRequestBuilders.get("/"))  // GET 요청
        .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("products"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.model().attribute("products", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.model().attribute("products",
                        Matchers.contains(Matchers.samePropertyValuesAs(expectedProduct))));
    }
}
```

> ### 명령어로 Test 수행
```sh
$gradle test  // Gradle
$mvn test  // Maven
```

### Web 보안 Test
* `testCompile('org.springframework.security:spring-security-test')` 추가
* Spring Security Configuration 설정

```java
@Before  // @Test 메소드전에 실행
public void setupMockMvc() {
    mockMvc = MockMvcBuilders  // MockMvc 설정
            .webAppContextSetup(webApplicationContext)
            .apply(springSecurity())  // Spring Security를 활성화하는 MockMvc 구성자 반환
            .build();
}

@Test
public void homePage_unauthenticatedUser() throws Exception {
    // 인증된 요청이 아니면 Login Page로 리다이렉트
    mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.header().string("Location", "http://127.0.0.1/login"));
}
```
* 인증된 요청 수행 어노테이션
  * `@WithMockUser` - 지정한 사용자 이름, 패스워드, 권한으로 `UserDetails를 생성`한 후 SecurityContext를 로드, **보통 이걸 사용**
  ```java
  @Test
  @WithMockUser(username = "user1", password = "pass1", roles = "USER")
  public void homePage_authenticatedUser() throws Exception {
      ...
    }
  ```
  * `@WithUserDetails` - 지정한 사용자 이름으로 `UserDetails를 조회`하여 SecurityContext를 로드, **커스텀 권한 이용시 사용**
  ```java
  @Test
  @WithUserDetails("user1")  // user1을 사용자로 사용
  public void homePage_authenticatedUser() throws Exception {
      Reader expectedReader = new Reader();  // 반환할 Reader 생성
      expectedReader.setUsername("user1");
      expectedReader.setPassword("password");

      mockMvc.perform(MockMvcRequestBuilders.get("/"))  // GET 요청
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.view().name("readingList"))
              .andExpect(MockMvcResultMatchers.model().attribute("reader", Matchers.samePropertyValuesAs(expectedReader)))
              .andExpect(MockMvcResultMatchers.model().attribute("books", Matchers.hasSize(0)))
              .andExpect(MockMvcResultMatchers.model().attribute("amazonId", "aaaa"));
  }
  ```

## 실행중인 Application Test
* Web Application은 실제로 작동하는 것으로 Test하는 것이 가장 좋다
  * Mock Test보다 사용자가 실제로 어떻게 사용하는지를 더 잘보여주기 때문
* `@WebIntergrationTest` -> 1.4에서 `@SpringBootTest`에 `webEnvironment`필드를 설정하는 방식으로 변경
  * Test를 진행하는 동안 실제와 동일한 메커니즘으로 Application을 실행
  * TestApplicationContext를 생성하면서 내장 서블릿 컨테이너도 시작
  * Application을 내장 컨테이너와 함께 실행 -> 실제 HTTP요청을 검증

```java
@RunWith(SpringJUnit4ClassRunner.class)
// webEnvironment속성 -> 서버에서 테스트 실행
// webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT -> 임의의 포트 사용
// webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT -> 정의된 포트 사용
@SpringBootTest(classes = ProductListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SimpleWebTests {

    @Value("${local.server.port}")  // Property 주입
    private int port;

    @Test(expected = HttpClientErrorException.class)
    public void pageNotFound(){
        try{
            RestTemplate restTemplate = new RestTemplate();
            // GET 요청
            restTemplate.getForObject("http://127.0.0.1:{port}/bogusPage", String.class, port);
            fail("Should result in HTTP 404");
        }catch (HttpClientErrorException e){
            assertEquals(HttpStatus.NOT_FOUND, e.getStackTrace());  // HTTP 404인지 검사
            throw e;
        }
    }
}
```

### [Selenium](http://www.seleniumhq.org/)으로 HTML 페이지 Test
* `RestTemplate`은 간단한 요청 수행, REST ENDPOINT를 Test에 아주 적합
* 페이지의 Contents 검증, 페이지 자체에 작동을 수행하기에는 불편 -> `Selenium` 이용
* Gradle dependency 추가 - `testCompile group: 'org.seleniumhq.selenium', name: 'selenium-java', version: '3.0.1'`
```java
@RunWith(SpringJUnit4ClassRunner.class)
// webEnvironment속성 -> 서버에서 테스트 실행
// webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT -> 임의의 포트 사용
// webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT -> 정의된 포트 사용
@SpringBootTest(classes = ProductListApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerWebTests {

    private static FirefoxDriver browser;

    @Value("${local.server.port}")  // 포트 주입입
    private int port;

    @BeforeClass
    public static void openBrowser(){
        browser = new FirefoxDriver();
        // FirefoxDriver 설정
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void closeBrowser(){
        browser.quit();  // Web Browser 종료
    }

    @Test
    public void addBookToEmptyList(){
        String baseUrl = "http://127.0.0.1:" + port;

        browser.get(baseUrl);  // 메인 페이지 조회

        assertEquals("You have no books in your book list", browser.findElementByTagName("div").getText());  // 빈 책 목록 검증

        browser.findElementByName("title").sendKeys("BOOK TITLE");
        browser.findElementByName("price").sendKeys("BOOK PRICE");
        browser.findElementByName("form").submit();  // 폼에 데이터를 추가하고 전송

        WebElement dl = browser.findElementByCssSelector("dt.bookHeadline");  // <dt>를 찾아 검증
        assertEquals("BOOK TITLE by BOOK PRICE", dl.getText());
        WebElement dt = browser.findElementByCssSelector("dd.bookDescription");  // <dd>를 찾아 검증
        assertEquals("DESCRIPTION", dt.getText());  // 목록에 새책이 있는지 검증
    }
}
```

## Spring Boot 배포

### 순서
1. 방법 선택
2. 방법에 따라 패키징 파일(`war`, `jar`) 생성
3. 출시용 프로파일 생성
4. DB 마이그레이션 활성화
  * `Flyway`
  * `Liquibase`

### 방법
| 배포 아티팩트 | 생성자 | 배포 대상 환경 |
|:--:|:--:|:--:|
| 로 그루비 소스 | 개발자가 직접 작성 | 클라우드(Cloud Foundry), 컨테이너(Docker) |
| JAR | Maven, Gradle, SpringBoot CLI | 클라우드(Cloud Foundry, Heroku), 컨테이너(Docker) |
| WAR | Maven, Gradle | Java Application Server, 클라우드(Cloud Foundry) |

### WAR파일 생성
1. plugin 추가, `jar`구성을 `war`로 수정
```sh
apply plugin: 'war'

war {  // jar -> war
  baseName= 'readinglist'
  version= '0.0.1-SNAPSHOT'
}
```
2. 서블릿 초기화 클래스 추가
```java
/*
DispatcherServlet 구성
SpringApplicationContext에 있는 Filter, Servlet, ServletContextInitializer 타입의 Bean을 검색, 서블릿 컨테이너에 바인드
 */
public class ProductListServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // Spring 환경 구성 클래스 지정
        return builder.sources(ProductListApplication.class);
    }
}
```
3. `$gradle build` or `$mvn package`
