/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: UserController.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.controller 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月14日 上午12:04:37 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.uestc.msstudio.panorama.model.User;
import edu.uestc.msstudio.panorama.model.UserRole;
import edu.uestc.msstudio.panorama.model.annotation.LoginedAdmin;
import edu.uestc.msstudio.panorama.model.annotation.LoginedUser;
import edu.uestc.msstudio.panorama.repo.UserRepository;
import edu.uestc.msstudio.panorama.repo.UserRoleRepository;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: UserController
 * @Description: TODO
 * @author: MT
 */
@RestController
@RequestMapping("/user")
public class HomeController {
    @Autowired
    private UserRepository userDao;
    @Autowired
    private UserRoleRepository roleDao;

    @LoginedUser
    @ApiOperation("This is the info for a authorized user")
    @GetMapping("/")
    public HttpEntity<User> me() {
        UserDetails detail = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return ResponseEntity.ok(userDao.findByUsername(detail.getUsername()));
    }
    @PreAuthorize("#input.username == authentication.principal.username")
    @ApiOperation("This is the way to update a authorized user , but you can not update its password or roles")
    @PutMapping("/")
    public HttpEntity<?> update(@RequestBody User input) {
        if (input.getUserid() == null) {
            return ResponseEntity.badRequest()
                    .body("The entity input has no userID");
        }
        User target = userDao.findOne(input.getUserid());
        if (input.getEmail() != null)
            target.setEmail(input.getEmail());
        if (input.getGender() != null)
            target.setGender(input.getGender());
        if (input.getHometown() != null)
            target.setHometown(input.getHometown());
        if (input.getResidence() != null)
            target.setResidence(input.getResidence());
        User result = userDao.save(target);
        return ResponseEntity.ok(result);
    }
    @LoginedAdmin
    @PutMapping("/upgrade")
    public HttpEntity<?> upgrade(@RequestBody List<UserRole> roles,
            @RequestParam Long userId) {
        User target = userDao.findOne(userId);
        if (target == null) {
            return ResponseEntity.status(404)
                    .body("The User you request DOES NOT EXISTS");
        }
        List<UserRole> targetList = new ArrayList<>();
        for (UserRole role : roles) {
            role = roleDao.findByRole(role.getRole());
            if (role == null) {
                return ResponseEntity.status(400)
                        .body("The input list of roles contains unknown roles");
            }
            targetList.add(role);
        }
        target.setRoles(targetList);
        return ResponseEntity.ok(userDao.save(target));
    }
    @LoginedAdmin
    @GetMapping("/list")
    public HttpEntity<?> getAllUsers(
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "1") int num,
            @RequestParam(defaultValue = "true") boolean sort) {
        Pageable pageable;
        if (sort)
            pageable = new PageRequest(num - 1, size, Sort.Direction.ASC,
                    "userid");
        else
            pageable = new PageRequest(num - 1, size, Sort.Direction.DESC,
                    "userid");
        Page<User> resultPage = userDao.findAll(pageable);
        if (resultPage.getContent().size() == 0)
            return ResponseEntity.status(404).body(resultPage);
        return ResponseEntity.ok(resultPage);
    }
}
