package com.yuki.utils;

import org.aspectj.weaver.ast.Or;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class BookUtils {
//    public static void findFolder(File file, String filepath , List<String> allFiles){
//        if(file.isDirectory()){
//            allFiles.add(filepath+file.getName());
//            File[] files= file.listFiles();
//            for(File f:files){
//                findFolder(f,filepath,allFiles);
//            }
//        }else{
//            System.out.println(filepath+file.getName());
//            allFiles.add(filepath+file.getName());
//        }
//    }

    public static String GetBookCoverByName(String bookName) {
        String defUrl = "D:/code/Project/java/前后端分离-漫画系统/libraryServer/";
        String path = "http://127.0.0.1:7777/api/images/" + bookName + "/";
        File file_dir = new File(defUrl + bookName);
        File[] files = file_dir.listFiles();
        return path + files[0].getName();
    }

    public static List<String> GetBookDetailByName(String bookName, Integer current) {
        String defUrl = "D:/code/Project/java/前后端分离-漫画系统/libraryServer/";
        String path = "http://127.0.0.1:7777/api/images/" + bookName + "/";
        File file_dir = new File(defUrl + bookName);
        File[] files = file_dir.listFiles();
        List<String> bookUrls = new ArrayList<>();
        int i = 0;
        int array_begin = 0;
        int array_end = 20;
        if (files.length <= 20){
            File[] sort = sort(files);
            for (File file : sort) {
                bookUrls.add(path+file.getName());
            }
        }else{
            for (i=1;i<current;i++){
                array_begin+=20;
                array_end+=20;
                if (array_end > files.length){
                    array_end = files.length;
                }
            }
            files = Arrays.copyOfRange(files, array_begin, array_end);
            File[] sort = sort(files);
            System.out.println("用户传过来了>>"+current);
            for (File file : sort) {
                bookUrls.add(path+file.getName());
            }
        }

        return bookUrls;
    }

    public static File[] sort(File[] s){
        //中间值
        File temp =null;
        //外循环:我认为最小的数,从0~长度-1
        for(int j = 0; j<s.length-1;j++){
            //最小值:假设第一个数就是最小的
            String min = s[j].getName();
            //记录最小数的下标的
            int minIndex=j;
            //内循环:拿我认为的最小的数和后面的数一个个进行比较
            for(int k=j+1;k<s.length;k++){
                //找到最小值
                if (Integer.parseInt(min.substring(0,min.indexOf(".")))>Integer.parseInt(s[k].getName().substring(0,s[k].getName().indexOf(".")))){
                    //修改最小
                    min=s[k].getName();
                    minIndex=k;
                }
            }
            //当退出内层循环就找到这次的最小值
            //交换位置
            temp = s[j];
            s[j]=s[minIndex];
            s[minIndex]=temp;
        }
        return s;
    }

    public static Integer GetBookCount(String bookName){
        String defUrl = "D:/code/Project/java/前后端分离-漫画系统/libraryServer/";
        String path = "http://127.0.0.1:7777/api/images/" + bookName + "/";
        File file_dir = new File(defUrl + bookName);
        File[] files = file_dir.listFiles();
        return files.length;
    }

}
