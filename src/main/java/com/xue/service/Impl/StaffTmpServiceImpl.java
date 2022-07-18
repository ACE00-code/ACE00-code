package com.xue.service.Impl;


import com.xue.entity.model.ACUser;
import com.xue.entity.model.LeagsoftUser;
import com.xue.entity.model.NetdistUser;
import com.xue.repository.dao.StaffTmpMapper;
import com.xue.service.StaffTmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class StaffTmpServiceImpl implements StaffTmpService {

    @Autowired
    private StaffTmpMapper staffTmpMapper;

    @Override
    public void saveAC() {
        List<ACUser> acUserList=new ArrayList<>();
        acUserList=staffTmpMapper.queryACUser();
        System.out.println("ac:"+acUserList.size());
        for (ACUser acUser:acUserList){
            if (staffTmpMapper.queryACOne(acUser)==1){
                staffTmpMapper.saveAC(acUser);
            }

        }
    }

    @Override
    public void saveLeagsoft() {
        List<LeagsoftUser> leagsoftUsers=new ArrayList<>();
        leagsoftUsers=staffTmpMapper.queryLeagsoft();
        System.out.println("lea"+leagsoftUsers.size());
        for (LeagsoftUser leagsoftUser:leagsoftUsers){
            if (staffTmpMapper.queryLeagsoftOne(leagsoftUser)==1)
                staffTmpMapper.saveLeagsoft(leagsoftUser);
        }
    }

    @Override
    public void saveNetdisk() {
        List<NetdistUser> netdistUsers=new ArrayList<>();
        netdistUsers=staffTmpMapper.queryNetdisk();
        System.out.println("net:"+netdistUsers.size());
        for (NetdistUser netdistUser:netdistUsers){
            System.out.println(netdistUser.toString());
            if (staffTmpMapper.queryNetdiskOne(netdistUser)==1)
//                System.out.println(netdistUser.toString());
                staffTmpMapper.saveNetdisk(netdistUser);
        }
    }
}
