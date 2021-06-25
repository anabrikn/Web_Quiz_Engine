package engine.config;

import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private BCryptPasswordEncoder encoder;

    @Autowired
    public WebSecurityConfig(UserService userService, BCryptPasswordEncoder encoder) {
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationEntryPoint() {

                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response,
                                         AuthenticationException authException) throws IOException, ServletException {
                        if (authException != null) {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().print("Unauthorizated....");
                        }
                    }
                })
                //(req, resp, e) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED)
                .and()
                .authorizeRequests()
                .antMatchers("/api/quizzes", "/api/quizzes/**").authenticated()
                .antMatchers("/api", "/api/register", "/actuator/shutdown").permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);;

                /*
                http
                .authorizeRequests()
                    .antMatchers("/", "/registration").permitAll() // для главной страницы доступ разрешен
                    .anyRequest().authenticated() // для всех остальных требуется авторизация
                .and()
                    .formLogin()
                    .loginPage("/login") // указываем что на этом маппинге находится форма лог-ин
                    .permitAll() // разрешаем доступ всем
                .and()
                    .logout()
                    .permitAll();
                 */
                //.and()
                //.formLogin()
                //.loginPage("/login")
                //.permitAll()
                //.and()
                //.logout()
                //.permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(encoder);
    }
}
