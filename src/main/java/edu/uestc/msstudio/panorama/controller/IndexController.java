/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: IndexController.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.controller 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月10日 下午3:23:45 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.uestc.msstudio.panorama.model.User;
import edu.uestc.msstudio.panorama.repo.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: IndexController
 * @Description: TODO
 * @author: MT
 * @date: 2017年5月10日 下午3:23:45
 */
@RestController
@Api
public class IndexController {
    @Autowired
    private UserRepository userDao;

    @ApiOperation(value = "获取响应情况", notes = "正常情况下将会返回 Pong，如果未能正常返回请联系运维人员")
    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }
    @GetMapping("/ping/user")
    public User testUser(@RequestParam String username) {
        return userDao.findByUsername(username);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/admintest")
    public String roleTest() {
        return "you are a admin";
    }
}
