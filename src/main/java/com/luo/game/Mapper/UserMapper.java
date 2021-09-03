package com.luo.game.Mapper;

import com.luo.game.Entity.UserInformation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public void SignUp(UserInformation userInformation);
    public UserInformation Auth(UserInformation userInformation);
    public UserInformation Search(UserInformation userInformation);
}
