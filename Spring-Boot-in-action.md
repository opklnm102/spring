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
      SpringApplication.run(MyApplication.class, args);
  }
}
```

> ### @SpringBootApplication
유용한 Annotation 3개를 묶은 것
* Spring의 `@Configuration` - Java기반 구성 클래스로 지정
* Spring의 `@ComponentScan` - 컴포넌트 클래스들을 자동으로 검색하여 SpringApplicationContext에 Bean으로 등록
* Spring Boot의 `@EnableAutoConfiguration` - Spring Boot의 자동 구성

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
