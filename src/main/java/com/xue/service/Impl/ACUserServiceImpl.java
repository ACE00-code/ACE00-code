package com.xue.service.Impl;

import com.xue.entity.model.ACUser;
import com.xue.repository.dao.ACUserMapper;
import com.xue.service.ACUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class ACUserServiceImpl implements ACUserService {

	@Autowired
	private ACUserMapper acUserMapper;

	@Transactional
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
		acUserMapper.clearAll();

		while ((line= reader.readLine())!=null){
			ACUser acUser=new ACUser();
			String item[] = line.split(",");

			acUser.setAcIp(item[0].trim());
			acUser.setAcName(item[1].trim());
			acUser.setGroup(item[2]);

			userList.add(acUser);

		}

		for(ACUser userInfo:userList){
				result = acUserMapper.addUser(userInfo);
			}
		return result;
	}


}
