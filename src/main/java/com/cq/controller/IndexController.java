package com.cq.controller;

import com.cq.util.ResizeImage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by cheng on 2018/10/9.
 */
@Controller
public class IndexController {


    @RequestMapping(value = "/api/index/2", method = RequestMethod.GET)
    public String index(Model model) {
        List<String> nameList = new ArrayList<>();
        String outputFolder = "/home/ubuntu/mydata/images/sl";
        String fileName = "/home/ubuntu/mydata/images";
//        String outputFolder = "C:\\Users\\cheng\\Pictures\\pixiv\\sl";
//        String fileName = "C:\\Users\\cheng\\Pictures\\pixiv";
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
                nameList.add(entry.getKey());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        model.addAttribute("nameList",nameList);
        return "index2";
    }

    @RequestMapping(value = "/api/index/1", method = RequestMethod.GET)
    public String index2(Model model) {
        return "index1";
    }
}
