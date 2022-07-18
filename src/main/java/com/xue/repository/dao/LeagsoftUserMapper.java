package com.xue.repository.dao;

import com.xue.entity.model.LeagsoftUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LeagsoftUserMapper {
    int addUser(LeagsoftUser user);
}