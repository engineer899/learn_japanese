package ink.zxu.learn_japanese.service;

import ink.zxu.learn_japanese.config.UploadConfigure;
import ink.zxu.learn_japanese.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author 张伟
 * @date 2019/9/26 20:22
 */
@Service
public class UploadService {

    @Resource
    private UploadConfigure uploadConfigure;


    public String imageUpload(MultipartFile file){
        //基础路径  E:/springboot-upload/image/
        String basePath = uploadConfigure.getImageBasePath();
        //获取文件保存路径 \20180608\113339\
        String folder = FileUtils.getFolder();
        // 获取前缀为"FL_" 长度为20 的文件名  FL_eUljOejPseMeDg86h.png
        String fileName = FileUtils.getFileName("FL_") + FileUtils.getFileNameSub(file.getOriginalFilename());
        try {
            // E:\springboot-upload\image\20180608\113339
            Path filePath = Files.createDirectories(Paths.get(basePath, folder));
//            log.info("path01-->{}", filePath);

            //写入文件  E:\springboot-upload\image\20180608\113339\FL_eUljOejPseMeDg86h.png
            Path fullPath = Paths.get(basePath, folder, fileName);
//            log.info("fullPath-->{}", fullPath);
            // E:\springboot-upload\image\20180608\113339\FL_eUljOejPseMeDg86h.png
            Files.write(fullPath, file.getBytes(), StandardOpenOption.CREATE);

            String urlSuffix=folder+fileName;

            String url=uploadConfigure.getServerPrefix()+"/image"+urlSuffix.replaceAll("\\\\","/");


            System.out.println(url);

            return url;

        } catch (Exception e) {
//            Path path = Paths.get(basePath, folder);
////            log.error("写入文件异常,删除文件。。。。", e);
//            try {
//                Files.deleteIfExists(path);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
            e.printStackTrace();
            return null;
        }
    }



    public String courseUpload(MultipartFile file){
        //基础路径  E:/springboot-upload/course/
        String basePath = uploadConfigure.getCourseBasePath();
        //获取文件保存路径 \20180608\113339\
        String folder = FileUtils.getFolder();
        // 获取前缀为"FL_" 长度为20 的文件名  FL_eUljOejPseMeDg86h.png
        String fileName = FileUtils.getFileName("CL_") + FileUtils.getFileNameSub(file.getOriginalFilename());
        try {
            // E:\springboot-upload\course\20180608\113339
            Path filePath = Files.createDirectories(Paths.get(basePath, folder));
//            log.info("path01-->{}", filePath);

            //写入文件  E:\springboot-upload\course\20180608\113339\FL_eUljOejPseMeDg86h.png
            Path fullPath = Paths.get(basePath, folder, fileName);
//            log.info("fullPath-->{}", fullPath);
            // E:\springboot-upload\course\20180608\113339\FL_eUljOejPseMeDg86h.png
            Files.write(fullPath, file.getBytes(), StandardOpenOption.CREATE);

            String urlSuffix=folder+fileName;

            String url=uploadConfigure.getServerPrefix()+"/course"+urlSuffix.replaceAll("\\\\","/");


            System.out.println(url);

            return url;

        } catch (Exception e) {
//            Path path = Paths.get(basePath, folder);
////            log.error("写入文件异常,删除文件。。。。", e);
//            try {
//                Files.deleteIfExists(path);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
            e.printStackTrace();
            return null;
        }
    }

    public String videoUpload(MultipartFile file){
        //基础路径  E:/springboot-upload/course/
        String basePath = uploadConfigure.getVideoBasePath();
        //获取文件保存路径 \20180608\113339\
        String folder = FileUtils.getFolder();
        // 获取前缀为"FL_" 长度为20 的文件名  FL_eUljOejPseMeDg86h.png
        String fileName = FileUtils.getFileName("VL_") + FileUtils.getFileNameSub(file.getOriginalFilename());
        try {
            // E:\springboot-upload\course\20180608\113339
            Path filePath = Files.createDirectories(Paths.get(basePath, folder));
//            log.info("path01-->{}", filePath);

            //写入文件  E:\springboot-upload\course\20180608\113339\FL_eUljOejPseMeDg86h.png
            Path fullPath = Paths.get(basePath, folder, fileName);
//            log.info("fullPath-->{}", fullPath);
            // E:\springboot-upload\course\20180608\113339\FL_eUljOejPseMeDg86h.png
            Files.write(fullPath, file.getBytes(), StandardOpenOption.CREATE);

            String urlSuffix=folder+fileName;

            String url=uploadConfigure.getServerPrefix()+"/video"+urlSuffix.replaceAll("\\\\","/");


            System.out.println(url);

            return url;

        } catch (Exception e) {
//            Path path = Paths.get(basePath, folder);
////            log.error("写入文件异常,删除文件。。。。", e);
//            try {
//                Files.deleteIfExists(path);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
            e.printStackTrace();
            return null;
        }
    }


}
