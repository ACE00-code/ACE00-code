package com.xue.controller;

import com.xue.service.ACUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class ACUserController {

    @Autowired
    private ACUserService acUserService;

//    @RequestMapping("/index")
//    public String index(){
//
//        return "index";
//    }

    /**
     * 后期修改路径，测试使用
     */
    @RequestMapping("/import2")
    @ResponseBody
    public String acUserImport(@RequestParam(value="filename") MultipartFile file, HttpSession session){

//		String fileName = file.getOriginalFilename();

        int result = 0;

        try {
            result = acUserService.addUser(file);
        } catch (Exception e) {

            e.printStackTrace();
        }

        if(result > 0){
            return "AC用户清单数据导入成功！";
        }else{
            return "AC用户清单数据导入失败！";
        }

    }

}
