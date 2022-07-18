package com.xue.repository.dao;

import com.xue.entity.model.ACUser;
import com.xue.entity.model.LeagsoftUser;
import com.xue.entity.model.NetdistUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//填充staff_tmp表


@Repository
@Mapper
public interface StaffTmpMapper {

    //根据ac_user填充ac_name，ac_ip
    public void saveAC(ACUser acUser);

    List<ACUser> queryACUser();

    int queryACOne(ACUser acUser);

    //根据leagsoft_user填充leagsoft_name,leagsoft_ip
    public void saveLeagsoft(LeagsoftUser leagsoftUser);

    List<LeagsoftUser> queryLeagsoft();

    int queryLeagsoftOne(LeagsoftUser leagsoftUser);

    //根据netdisk_user填充netdisk_name
    public void saveNetdisk(NetdistUser netdistUser);

    List<NetdistUser> queryNetdisk();

    int queryNetdiskOne(NetdistUser netdistUser);
}
