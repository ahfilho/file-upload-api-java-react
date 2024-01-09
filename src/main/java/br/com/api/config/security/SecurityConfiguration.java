package br.com.api.config.security;

import br.com.api.auth.JWTAuthenticationFilter;
import br.com.api.auth.JWTTokenHelper;
import br.com.api.service.CustomUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserService userService;

    @Autowired
    private JWTTokenHelper jWTTokenHelper;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("Arlindo").password(passwordEncoder().encode("test@123"))
                .authorities("USER", "ADMIN");

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint).and()
                .authorizeRequests((request -> request.antMatchers("/localhost:3000/**", "/localhost:9090/**").permitAll()
                        .antMatchers("/user/auth/login").permitAll()
                        .antMatchers("/client").permitAll()
                        .antMatchers("/client/{id}").permitAll()
                        .antMatchers("/client/search/").permitAll()
                        .antMatchers("/client/search/{cpf}").permitAll()
                        .antMatchers("/client/find/{id}").permitAll()
                        .antMatchers("/client/search/cpf").permitAll()
                        .antMatchers("/ssd").permitAll()
                        .antMatchers("/ssd/{id}").permitAll()
                        .antMatchers("/files/{id}").permitAll()
                        .antMatchers("/ssd/files/download").permitAll()
                        .antMatchers("/ssd/files/{id}").permitAll()
                        .antMatchers("/ssd/sale/day").permitAll()
                        .antMatchers("/cpu").permitAll()
                        .antMatchers("/cpu/{id}").permitAll()
                        .antMatchers("/ssd/redirect/{id}").permitAll()
                        .antMatchers("/files/ssd/{id}").permitAll()
                        .antMatchers("/files/cpu/{id}").permitAll()
                        .antMatchers("/files/ram/{id}").permitAll()
                        .antMatchers("/ram/redirect/{id}").permitAll()

                        .antMatchers("/ram").permitAll()
                        .antMatchers("/ram/{id}").permitAll()
                        .antMatchers("/ram/files/{id}").permitAll()

                        .antMatchers("/user/users").permitAll()

                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated()))
                .addFilterBefore(new JWTAuthenticationFilter(userService, jWTTokenHelper),
                        UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable().cors().and().headers().frameOptions().disable();
//                .authorizeRequests((request) -> request.antMatchers("/h2-console/**", "/api/v1/auth/login").permitAll()
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // Origem permitida
        configuration.addAllowedMethod("*"); // Todos os métodos HTTP permitidos
        configuration.addAllowedHeader("*"); // Todos os cabeçalhos permitidos

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
