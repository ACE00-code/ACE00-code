package com.xue.service.Impl;

import com.xue.entity.model.LeagsoftUser;
import com.xue.repository.dao.LeagsoftUserMapper;
import com.xue.service.LeagsoftUserService;
import com.xue.transcation.MyException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
@Service
public class LeagsoftUserServiceImpl implements LeagsoftUserService {
	
	@Autowired
	private LeagsoftUserMapper userMapper;

//	@Value(value = "${spring.datasource.driver-class-name}")
//	private String driver;
//
//	@Value(value = "${spring.datasource.url}")
//	private String url;
//
//	@Value(value = "${spring.datasource.username}")
//	private String userName;
//
//	@Value(value = "${spring.datasource.password}")
//	private String password;

	@Transactional
	@Override
	public int addUser(MultipartFile file) throws Exception{
		
		int result = 0;
		
		List<LeagsoftUser> userList = new ArrayList<>();
		
		String fileName = file.getOriginalFilename();
		
		String suffix = fileName.substring(fileName.lastIndexOf(".")+1);

		InputStream ins = file.getInputStream();
		
		Workbook wb = null;

//		//清库
//		Class.forName(driver);
//		Connection conn = DriverManager.getConnection(url, userName, password);
//		Statement stat = conn.createStatement();
//		String sql="delete from leagsoft_user where leagsoft_user.sapId IS NOT NULL";
//		stat.executeUpdate(sql);
//		// 释放资源
//		stat.close();
//		conn.close();

		//清库
		userMapper.clearAll();

		if(suffix.equals("xlsx")){
			
			wb = new XSSFWorkbook(ins);
			
		}else{
			wb = new HSSFWorkbook(ins);
		}
		
		Sheet sheet = wb.getSheetAt(0);
		

		if(null != sheet){
			
			for(int line = 1; line <= sheet.getLastRowNum();line++){

				LeagsoftUser user = new LeagsoftUser();
				
				Row row = sheet.getRow(line);
				
				if(null == row){
					continue;
				}
				
				if(1 != row.getCell(0).getCellType()){
					throw new MyException("单元格类型不是文本类型！");
				}
				
				String deviceName = row.getCell(0).getStringCellValue();
				
				row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);

				String ip = row.getCell(1).getStringCellValue();
				String macAddr = row.getCell(2).getStringCellValue();
				String macManufactor = row.getCell(3).getStringCellValue();
				String deviceType = row.getCell(4).getStringCellValue();
				String departmentName = row.getCell(5).getStringCellValue();
				String userName = row.getCell(6).getStringCellValue();

				user.setDeviceName(deviceName);
				user.setLeagsoftIp(ip.trim());
				user.setMac(macAddr);
				user.setMacManufacturers(macManufactor);
				user.setDeviceType(deviceType);
				user.setDepartment(departmentName);
				user.setLeagsoftName(userName.trim());

				userList.add(user);

			}
			
			for(LeagsoftUser userInfo:userList){
				result = userMapper.addUser(userInfo);
			}
		}
		return result;
	}
	
	

}
