package com.cq.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;



//将图片转换为略缩图
public class ResizeImage {
    /**
     * 根据原图获取缩略图
     *
     * @param im       源图片集
     * @param toWidth  目标宽
     * @param toHeight 目标高
     * @return
     */
    public BufferedImage zoomImage(BufferedImage im, int toWidth, int toHeight) {
        BufferedImage result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);
        result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
        return result;
    }

    /**
     * 获取指定路径的文件
     *
     * @param ImgLists 文件集合
     * @param type    文件类型
     * @return
     * @throws IOException
     */
    public Map<String,BufferedImage> getImageList(String ImgLists, String[] type) throws IOException {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        Map<String,BufferedImage> maps = new HashMap<>();
        for (String s : type) {
            map.put(s, true);
        }
        BufferedImage imageList = null;
        File folder = new File(ImgLists);
        File[] files = folder.listFiles();
        for (File file:files) {
            if(file.isDirectory()){
                continue;
            }
            String fileName = file.getName();
//            System.out.println(fileName);
            try {
                if (file.length() != 0 && map.get(getExtension(file.getName())) != null) {
                    imageList = ImageIO.read(file);
                }
            } catch (Exception e) {
                imageList = null;
            }
            maps.put(fileName,imageList);
        }

        return maps;
    }

    /**
     * 把图片写到磁盘上
     *
     * @param im
     * @param path     eg: C://home// 图片写入的文件夹地址
     * @param fileName DCM1987.jpg  写入图片的名字
     * @date 2017年5月7日10:48:27
     */

    public boolean writeToDisk(BufferedImage im, String path, String fileName) {
        File f = new File(path + fileName);
        String fileType = getExtension(fileName);
        if (fileType == null)
            return false;
        try {
            ImageIO.write(im, fileType, f);
            im.flush();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 缩略图存储
     *
     * @param path         缩略图名称
     * @param im           图片集
     * @param fileFullPath 路径前缀
     * @return
     * @throws IOException
     */
    public boolean writeHighQuality(String path, BufferedImage im, String fileFullPath) throws IOException {
        //return true;
        FileOutputStream newimage = null;
        try {
            // 输出到文件流
            newimage = new FileOutputStream(fileFullPath +File.separator+ path);
            ImageIO.write(im, "jpeg", newimage);
            return true;
        } catch (Exception e) {
            return false;
        }finally {
            newimage.close();
        }
    }

    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    public String getExtension(String fileName) {
        try {
            return fileName.split("\\.")[fileName.split("\\.").length - 1];
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String outputFolder = "C:\\Users\\cheng\\Pictures\\pixiv\\sl";
        String fileName = "C:\\Users\\cheng\\Pictures\\pixiv";
        ResizeImage r = new ResizeImage();
        int toWidth = 150;
        int toHeight = 230;
        Map<String,BufferedImage> maps = new HashMap<>();
        try {
            maps= r.getImageList(fileName, new String[]{"jpg", "png", "gif"});
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Map.Entry<String,BufferedImage> entry:maps.entrySet()){
            try {
                r.writeHighQuality(entry.getKey(), r.zoomImage(entry.getValue(), toWidth, toHeight), outputFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}


