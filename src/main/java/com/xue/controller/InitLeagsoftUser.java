package com.xue.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 创建leagsoft_user表
 */

@Repository
public class InitLeagsoftUser {

    @Value(value = "${spring.datasource.driver-class-name}")
    private String driver;

    @Value(value = "${spring.datasource.url}")
    private String url;

    @Value(value = "${spring.datasource.username}")
    private String userName;

    @Value(value = "${spring.datasource.password}")
    private String password;

    @PostConstruct
    public void init() throws SQLException, ClassNotFoundException {
        //连接数据库
        Class.forName(driver);
        //测试url中是否包含useSSL字段，没有则添加设该字段且禁用
        if (url.indexOf("?") == -1) {
            url = url + "?useSSL=false";
        } else if (url.indexOf("useSSL=false") == -1 || url.indexOf("useSSL=true") == -1) {
            url = url + "&useSSL=false";
        }
        Connection conn = DriverManager.getConnection(url, userName, password);
        Statement stat = conn.createStatement();

        // 先判断是否纯在表名，有则先删除表在创建表
        stat.executeUpdate("CREATE TABLE if not exists leagsoft_user("
                + "sapId int NOT NULL auto_increment,"
                + "deviceName varchar(100) DEFAULT NULL COMMENT '设备名称',"
                + "leagsoftIp varchar(32) DEFAULT NULL COMMENT '设备ip',"
                + "mac varchar(50) DEFAULT NULL COMMENT 'mac地址',"
                + "macManufacturers varchar(50) DEFAULT NULL COMMENT 'mac厂家',"
                + "deviceType varchar(32) DEFAULT NULL COMMENT '设备类型',"
                + "department varchar(100) DEFAULT NULL COMMENT '部门名称',"
                + "leagsoftName varchar(32) DEFAULT NULL COMMENT '用户姓名',"
                + "PRIMARY KEY (sapId)"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='leagsoft_user';"
        );

        // 释放资源
        stat.close();
        conn.close();
    }
}

