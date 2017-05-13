/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: JwtUserFactory.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.model 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月10日 下午4:25:28 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.model.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import edu.uestc.msstudio.panorama.model.User;
import edu.uestc.msstudio.panorama.model.UserRole;

/**
 * @ClassName: JwtUserFactory
 * @Description: TODO
 * @author: MT
 */
public final class JwtUserFactory {
    private JwtUserFactory() {
    }
    public static JwtUser create(User user) {
        return new JwtUser(user.getUserid(), user.getUsername(),
                user.getPassword(), user.getEmail(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getLastPasswordResetDate());
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(
            List<UserRole> authorities) {
        List<String> roledesc = new ArrayList<>();
        for (UserRole role : authorities) {
            roledesc.add(role.getRole());
        }
        return roledesc.stream().map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
