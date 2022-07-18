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
public class InitNetdiskUser {

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
        stat.executeUpdate("CREATE TABLE if not exists netdisk_user("
                + "sapId int NOT NULL auto_increment,"
                + "name varchar(32) DEFAULT NULL COMMENT '用户名',"
                + "email varchar(32) DEFAULT NULL COMMENT '邮箱',"
                + "phone varchar(32) DEFAULT NULL COMMENT '电话号码',"
                + "datacenter varchar(32) DEFAULT NULL COMMENT '数据中心',"
                + "creattime varchar(32) DEFAULT NULL COMMENT '创建时间',"
                + "team varchar(128) DEFAULT NULL COMMENT '所属组织',"
                + "lastlogtime varchar(32) DEFAULT NULL COMMENT '最近登录时间',"
                + "state varchar(32) DEFAULT NULL COMMENT '账户状态',"
                + "PRIMARY KEY (sapid)"
                + ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='netdisk_user';"
        );

        // 释放资源
        stat.close();
        conn.close();
    }
}
