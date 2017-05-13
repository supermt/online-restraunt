/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: JwtUserDetailsImpl.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.serviceImpl 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月10日 下午4:35:13 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.uestc.msstudio.panorama.model.JwtUserFactory;
import edu.uestc.msstudio.panorama.model.User;
import edu.uestc.msstudio.panorama.repo.UserRepository;

/** 
 * @ClassName: JwtUserDetailsImpl 
 * @Description: TODO
 * @author: MT 
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}

