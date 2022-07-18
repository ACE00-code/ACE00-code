package com.xue.service;

public interface StaffTmpService {

    //根据ac_user填充ac_name，ac_ip
    public void saveAC();

    //根据leagsoft_user填充leagsoft_name,leagsoft_ip
    public void saveLeagsoft();

    //根据netdisk_user填充netdisk_name
    public void saveNetdisk();
}
