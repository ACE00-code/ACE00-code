package com.xue.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xue.service.LeagsoftUserService;

@Controller
public class LeagsoftUserController {
	
	@Autowired
	private LeagsoftUserService userService;
	
	/**
	 * 上传excel页面
	 * 后期修改，测试使用
	 */
	@RequestMapping("/index")
	public String index(){
		
		return "index";
	}

	/**
	 * 后期修改路径，测试使用
	 */
	@RequestMapping("/import1")
	@ResponseBody
	public String LeagsoftUserImport(@RequestParam(value="filename")MultipartFile file,HttpSession session){
		

		int result = 0;
		
		try {
			result = userService.addUser(file);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		if(result > 0){
			return "联软用户信息表文件数据导入成功！";
		}else{
			return "联软用户信息表文件数据导入失败！";
		}
		
		
		
		
		
	}
	

}
