/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: JWTUser.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.model 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月10日 下午4:21:21 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.model;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @ClassName: JWTUser
 * @Description: TODO
 * @author: MT
 */
public class JwtUser implements UserDetails {
    private static final long serialVersionUID = 7786912529509884011L;
    private final Long id;
    private final String username;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final Date lastPasswordResetDate;

    public JwtUser(Long id, String username, String password, String email,
            Collection<? extends GrantedAuthority> authorities,
            Date lastPasswordResetDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
    // 返回分配给用户的角色列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @JsonIgnore
    public Long getId() {
        return id;
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    // 账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    // 账户是否未锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    // 密码是否未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    // 账户是否激活
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }
    // 这个是自定义的，返回上次密码重置日期
    @JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }
    @JsonIgnore
    public String getEmail() {
        return email;
    }
}
