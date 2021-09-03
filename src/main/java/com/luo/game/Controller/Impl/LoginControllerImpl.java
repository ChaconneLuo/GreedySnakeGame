package com.luo.game.Controller.Impl;

import com.luo.game.Controller.LoginController;
import com.luo.game.Entity.UserInformation;
import com.luo.game.Services.Impl.UserServicesImpl;
import com.luo.game.Services.UserServices;
import lombok.SneakyThrows;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class LoginControllerImpl implements LoginController {

    UserServices userServices;

    public LoginControllerImpl() {
        userServices = new UserServicesImpl();
    }

    @Override
    public Boolean Login(String name, String password) {
        UserInformation userInformation = new UserInformation(name, Encryption(password));
        Integer login = userServices.Login(userInformation);
        if (login == 0)  //成功
        {
            return true;
        } else if (login == 1) //密码错误
        {
            return false;
        } else if (login == 2) //注册成功
        {
            return true;
        }
        return false;
    }

    /**
     * 预留的加密方法
     *
     * @param password 明文密码
     * @return 加密过后的密码
     */
    @SneakyThrows
    @Override
    public String Encryption(String password) {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(new BigInteger(1, messageDigest.digest())
                .toString(16)
                .getBytes(StandardCharsets.UTF_8));
    }
}
