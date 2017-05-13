/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: UserTokenUtils.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.common.utils 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午4:01:23 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.common.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import edu.uestc.msstudio.panorama.model.User;
import edu.uestc.msstudio.panorama.repo.UserRepository;

/**
 * @ClassName: UserTokenUtils
 * @Description: TODO
 * @author: MT
 */
public class UserTokenUtils {
    public static User getUserFromToken(UserRepository userDao) {
        UserDetails detail = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User author = userDao.findByUsername(detail.getUsername());
        return author;
    }
}
