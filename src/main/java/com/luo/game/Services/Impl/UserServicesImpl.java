package com.luo.game.Services.Impl;

import com.luo.game.Utils.MapperUtils;
import com.luo.game.Entity.UserInformation;
import com.luo.game.Mapper.UserMapper;
import com.luo.game.Services.UserServices;

public class UserServicesImpl implements UserServices {

    UserMapper userMapper;

    public UserServicesImpl() {
        userMapper = MapperUtils.getMapper(UserMapper.class);
    }

    @Override
    public Integer Login(UserInformation userInformation) {
        UserInformation auth = userMapper.Auth(userInformation);
        if (auth != null) {
            return 0;
        } else {
            if (userMapper.Search(userInformation) != null) {
                return 1;
            } else {
                userMapper.SignUp(userInformation);
                return 2;
            }
        }
    }

}
