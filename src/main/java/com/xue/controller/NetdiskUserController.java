package com.xue.controller;

import com.xue.service.LeagsoftUserService;
import com.xue.service.NetdiskUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


@Controller
public class NetdiskUserController {
	
	@Autowired
	private NetdiskUserService userService;

	/**
	 * 后期修改路径，测试使用
	 */
	@RequestMapping("/import3")
	@ResponseBody
	public String NetdiskUserImport(@RequestParam(value="filename")MultipartFile file,HttpSession session){
		

		int result = 0;
		
		try {
			result = userService.addUser(file);
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		if(result > 0){
			return "拓邦云盘用户信息表文件数据导入成功！";
		}else{
			return "拓邦云盘用户信息表文件数据导入失败！";
		}
		
		
		
		
		
	}
	

}
