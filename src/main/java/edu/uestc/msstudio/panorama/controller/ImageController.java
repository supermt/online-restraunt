/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: ImageController.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.controller 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月13日 下午3:08:03 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;

import edu.uestc.msstudio.panorama.common.utils.MongoImageUtils;
import edu.uestc.msstudio.panorama.model.ImageInfo;
import edu.uestc.msstudio.panorama.repo.ImageRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: ImageController
 * @Description: TODO
 * @author: MT
 */
@Api("The main interface of a image")
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ImageRepository imageDao;
    
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("This is the API to create a new Image")
    @PostMapping("/")
    public HttpEntity<?> createImage(@RequestParam MultipartFile file) {
        String imagename = UUID.randomUUID() + file.getOriginalFilename();
        try {
            File targetFile = File.createTempFile("tmp", null);
            file.transferTo(targetFile);
            System.out.println(targetFile.length());
            MongoImageUtils.savePic(mongoTemplate, targetFile, imagename);
            targetFile.delete();
            ImageInfo result = new ImageInfo();
            result.setEnable(true);
            result.setImagename(imagename);
            imageDao.save(result);
            return ResponseEntity.ok(result);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Get Target File By id")
    @GetMapping("/{id}")
    public HttpEntity<?> getTargetImage(@PathVariable Long id) {
        ImageInfo target = imageDao.findOne(id);
        try {
            if (target.getEnable() == null || target.getEnable() == false) {
                return ResponseEntity.notFound().build();
            }
            GridFSDBFile targetImage = MongoImageUtils
                    .findPic(mongoTemplate, target.getImagename()).get(0);
            return ResponseEntity.ok(targetImage.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Enable a picture")
    @PutMapping("/{id}")
    public HttpEntity<?> enablePicture(@PathVariable Long id) {
        try {
            ImageInfo target = imageDao.findOne(id);
            target.setEnable(true);
            imageDao.save(target);
            return ResponseEntity.ok(target);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation("Disable a picture")
    @DeleteMapping("/{id}")
    public HttpEntity<?> disablePicture(@PathVariable Long id) {
        try {
            ImageInfo target = imageDao.findOne(id);
            target.setEnable(false);
            imageDao.save(target);
            return ResponseEntity.ok(target);
        } catch (NullPointerException e) {
            return ResponseEntity.notFound().build();
        }
    }
    // @ApiOperation("Get Target File By its Name")
    // @GetMapping("/name/")
    // public void getTargetImageByName(@RequestParam String name,
    // HttpServletResponse response) {
    // try {
    // GridFSDBFile targetImage = MongoImageUtils
    // .findPic(mongoTemplate, name).get(0);
    // OutputStream os = response.getOutputStream();
    // targetImage.writeTo(os);
    // os.flush();
    // } catch (Exception e) {
    // e.printStackTrace();
    // response.setStatus(404);
    // }
    // }
}
