package me.dong.security;

import lombok.RequiredArgsConstructor;
import me.dong.github.GithubClient;
import me.dong.github.GithubUser;
import me.dong.user.User;
import me.dong.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;


@Configuration
@EnableOAuth2Sso
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/posts/new").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/posts/{id}").permitAll()
                .antMatchers("/posts/**").hasRole("ADMIN")
                .antMatchers("/categories/**").hasRole("ADMIN")
                .antMatchers("/", "/js/**", "/vendor/**", "/codemirror/**", "/markdown/**", "/login/**", "/css/**", "/img/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .headers().frameOptions().sameOrigin();
    }

    @Bean
    public AuthoritiesExtractor authoritiesExtractor(){
        return map -> {
            String userName = (String) map.get("login");
            if("opklnm102".contains(userName)){
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER, ROLE_ADMIN");
            }else {
                return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
            }
        };
    }

    @Bean
    public PrincipalExtractor principalExtractor(GithubClient githubClient, UserRepository userRepository) {
        return map -> {
            String githubLogin = (String) map.get("login");
            User loginUser = userRepository.findByGithub(githubLogin);
            if (loginUser == null) {
                logger.info("Initialize user with githubId {}", githubLogin);
                GithubUser user = githubClient.getUser(githubLogin);  // 실질적으로 필요 없다. 나중에 github api를 간단하게 사용하기 위해 넣었음
                loginUser = new User(user.getEmail(), user.getName(), githubLogin, user.getAvatar());
                userRepository.save(loginUser);
            }
            return loginUser;
        };
    }
}
