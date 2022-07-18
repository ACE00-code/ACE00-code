package com.xue.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yuyilin
 * @version 1.0
 * @description: 拓邦网盘用户表
 * @date 2022/5/18 14:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetdistUser {

    private String sapid;

    private String name;

    private String email;

    private String phone;

    private String datacenter;

    private String creattime;

    private String group;

    private String lastlogtime;

    private String state;

}
