package com.yuki.utils;

import cn.hutool.Hutool;
import com.yuki.domain.Const.SysConst;
import org.springframework.web.multipart.MultipartFile;
import cn.hutool.core.io.FileUtil;

import java.io.*;
import java.util.UUID;

public class MultipartFileToFile {

    /**
     * 传入MultipartFile类型的文件,将它保存到指定目录
     *
     * @param file    传入的文件
     * @param tarPath 文件要保存的路径
     * @return
     */
    public static String saveMultipartFile(MultipartFile file, String tarPath, String fileName) {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            return null;
        }

        System.out.println(">>>>>>>>>>>>>>>>>");
        System.out.println("tagPath: "+tarPath);
        System.out.println("fileName: "+fileName);
        System.out.println(">>>>>>>>>>>>>>>>>");

        /*获取文件原名称*/ //留学生ちゃん.zip
        String originalFilename = file.getOriginalFilename();
        /*获取文件格式*/ // .zip
        String fileFormat = originalFilename.substring(originalFilename.lastIndexOf("."));
        if(!fileFormat.equals(".zip")){
            return null;
        }

        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        toFile = new File(tarPath + File.separator + fileName + fileFormat);
        System.out.println("文件的绝对路径: ");
        String real_file_path = toFile.getAbsolutePath();
        System.out.println(real_file_path);
        String absolutePath = null;
        try {
            absolutePath = toFile.getCanonicalPath();

            /*判断路径中的文件夹是否存在，如果不存在，先创建文件夹*/
            String dirPath = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator));
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            InputStream ins = file.getInputStream();

            inputStreamToFile(ins, toFile);
            ins.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /*下面为解压文件*/
        unzip_hutool(real_file_path,tarPath);
        boolean delete = toFile.delete();
        if (delete){
            System.out.println("删除成功");
        }else{
            System.out.println("删除失败");
        }

        int index = tarPath.indexOf("libraryServer/");
        System.out.println(tarPath.substring(index+14));
        return tarPath.substring(index+14);
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除本地临时文件
     *
     * @param file
     */
    public static void deleteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }

    public static void unzip_hutool(String zipFile, String outDir) {
        cn.hutool.core.util.ZipUtil.unzip(zipFile, outDir);
    }

}

