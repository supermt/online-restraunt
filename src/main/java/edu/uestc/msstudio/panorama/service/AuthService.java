/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: AuthService.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.service 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月10日 下午5:37:13 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.service;

import edu.uestc.msstudio.panorama.model.User;

/**
 * @ClassName: AuthService
 * @Description: TODO
 * @author: MT
 */
public interface AuthService {
    User register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}
