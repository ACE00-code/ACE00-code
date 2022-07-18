package com.xue.service.Impl;

import com.xue.entity.model.ACUser;
import com.xue.entity.model.LeagsoftUser;
import com.xue.repository.dao.ACUserMapper;
import com.xue.repository.dao.LeagsoftUserMapper;
import com.xue.service.ACUserService;
import com.xue.service.LeagsoftUserService;
import com.xue.transcation.MyException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ACUserServiceImpl implements ACUserService {

	@Autowired
	private ACUserMapper acUserMapper;

	@Value(value = "${spring.datasource.driver-class-name}")
	private String driver;

	@Value(value = "${spring.datasource.url}")
	private String url;

	@Value(value = "${spring.datasource.username}")
	private String userName;

	@Value(value = "${spring.datasource.password}")
	private String password;

	@Override
	public int addUser(MultipartFile file) throws Exception {

		int result = 0;
		InputStream inputStream = file.getInputStream();

		InputStreamReader is = new InputStreamReader(inputStream, "GBK");
		BufferedReader reader = new BufferedReader(is);
		reader.readLine();
		String line=null;
		List<ACUser> userList = new ArrayList<>();

		//清库
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url, userName, password);
		Statement stat = conn.createStatement();
		String sql="delete from ac_user where ac_user.sapId IS NOT NULL";
		stat.executeUpdate(sql);
		// 释放资源
		stat.close();
		conn.close();

		while ((line= reader.readLine())!=null){
			ACUser acUser=new ACUser();
			String item[] = line.split(",");

			acUser.setAcIp(item[0]);
			acUser.setAcName(item[1]);
			acUser.setGroup(item[2]);

			userList.add(acUser);

		}

		for(ACUser userInfo:userList){
				result = acUserMapper.addUser(userInfo);
			}
		return result;
	}


}
