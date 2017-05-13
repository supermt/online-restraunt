/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: User.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.model.annotation 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午12:21:26 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.model.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @ClassName: User
 * @Description: TODO
 * @author: MT
 */
@PreAuthorize("hasRole('ROLE_USER')")
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface LoginedUser {
}
