package com.xue.service.Impl;

import com.xue.entity.model.NetdistUser;
import com.xue.repository.dao.NetdiskUserMapper;
import com.xue.service.NetdiskUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class NetdiskUserServiceImpl implements NetdiskUserService {

	@Autowired
	private NetdiskUserMapper netdiskUserMapper;

	@Transactional
	@Override
	public int addUser(MultipartFile file) throws Exception {

		int result = 0;
		InputStream inputStream = file.getInputStream();

		InputStreamReader is = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(is);
		reader.readLine();
		reader.readLine();
		String line=null;

		//清库
		netdiskUserMapper.clearAll();

		List<NetdistUser> userList = new ArrayList<>();
		while ((line= reader.readLine())!=null){
			NetdistUser netdistUser=new NetdistUser();
			String item[] = line.split(",");

			netdistUser.setName(item[0].trim());
			netdistUser.setEmail(item[1]);
			netdistUser.setPhone(item[2]);
			netdistUser.setDatacenter(item[3]);
			netdistUser.setCreattime(item[4]);
			netdistUser.setGroup(item[5]);
			netdistUser.setLastlogtime(item[6]);
			netdistUser.setState(item[7]);

			userList.add(netdistUser);

		}

		for(NetdistUser userInfo:userList){
				result = netdiskUserMapper.addUser(userInfo);
			}
		return result;
	}


}
