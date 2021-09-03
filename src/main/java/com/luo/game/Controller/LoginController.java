package com.luo.game.Controller;


public interface LoginController {
    public Boolean Login(String id,String password);
    public String Encryption(String password);
}
