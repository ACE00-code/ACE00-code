package com.xue.repository.dao;

import com.xue.entity.model.NetdistUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NetdiskUserMapper {
    int addUser(NetdistUser user);
    void clearAll();
}