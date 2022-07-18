package com.xue.entity.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagsoftUser {

    private String sapId;

    private String deviceName;

    private String leagsoftIp;

    private String mac;

    private String macManufacturers;

    private String deviceType;

    private String department;

    private String leagsoftName;

}