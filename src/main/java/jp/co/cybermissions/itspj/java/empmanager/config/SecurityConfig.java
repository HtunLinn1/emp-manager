package jp.co.cybermissions.itspj.java.empmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Securityの機能を有効にするための設定クラス
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // password encoder
    PasswordEncoder enc = passwordEncoder();

    auth.inMemoryAuthentication() //
        .withUser("user").password(enc.encode("user")).roles("USER") //
        .and() //
        .withUser("admin").password(enc.encode("admin")).roles("ADMIN");
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // セキュリティ設定を指定
    web.ignoring().antMatchers("/h2-console/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests() //
        .antMatchers("/login").permitAll()//
        .anyRequest().authenticated();

    http.formLogin().loginProcessingUrl("/login") //
        .defaultSuccessUrl("/emp");

    http.logout().logoutUrl("/logout") //
        .logoutSuccessUrl("/login?logout");
  }
}
