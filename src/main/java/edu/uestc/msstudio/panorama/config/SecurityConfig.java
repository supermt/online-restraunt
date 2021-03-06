/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: SecurityConfig.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.config 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月10日 下午3:26:05 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import edu.uestc.msstudio.panorama.config.filters.JwtAuthenticationTokenFilter;

/**
 * @ClassName: SecurityConfig
 * @Description: TODO
 * @author: MT
 * @date: 2017年5月10日 下午3:26:05
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureAuthentication(
            AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }
    // 装载BCrypt密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(HttpMethod.GET, "/", "/*.html", "/favicon.ico",
                        "/**/*.html", "/**/*.css", "/**/*.js", "/swagger**/**",
                        "/configuration/**")
                .permitAll().antMatchers(HttpMethod.GET, "/book/**").permitAll()
                .antMatchers(HttpMethod.GET, "/comment/**").permitAll()
                .antMatchers("/v2/**").permitAll().antMatchers("/image/**")
                .permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/auth/**").permitAll()
                // 某些测试服务器状态的路径应该打开
                .antMatchers("/ping/**").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(),
                UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean()
            throws Exception {
        return new JwtAuthenticationTokenFilter();
    }
}
