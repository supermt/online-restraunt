/**   
 * Copyright © 2017 微软创新工作室. All rights reserved.
 * 
 * @Title: MongoImageUtils.java 
 * @Prject: panorama
 * @Package: edu.uestc.msstudio.panorama.common.utils 
 * @Description: TODO
 * @author: MT   
 * @date: 2017年5月13日 下午3:13:19 
 * @version: V1.0   
 */
package edu.uestc.msstudio.panorama.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

/**
 * @ClassName: MongoImageUtils
 * @Description: TODO
 * @author: MT
 */
public class MongoImageUtils {
    
    @Value(value = "mongo.gridname.default")
    public static String gridName = "default";
    
    public static void savePic(MongoTemplate mongoTemplate, File picFile,
            String imagename) {
        try {
            DB db = mongoTemplate.getDb();
            GridFS fs = new GridFS(db, gridName);
            GridFSInputFile inputFile = fs.createFile(picFile);
            inputFile.setFilename(imagename);
            inputFile.setContentType(
                    imagename.substring(imagename.lastIndexOf(".")));
            inputFile.save();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<GridFSDBFile> findPic(MongoTemplate mongoTemplate,
            String imagename) {
        DB db = mongoTemplate.getDb();
        GridFS fs = new GridFS(db, gridName);
        List<GridFSDBFile> file = fs.find(imagename);
        return file;
    }
    public static void deletePic(MongoTemplate mongoTemplate, String filename) {
        DB db = mongoTemplate.getDb();
        GridFS fs = new GridFS(db, gridName);
        GridFSDBFile file = fs.findOne(filename);
        fs.remove(file);
    }
}
