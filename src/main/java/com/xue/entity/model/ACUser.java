package com.xue.entity.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ACUser {
    private String sapId;

    private String acIp;

    private String acName;

    private String group;


}
