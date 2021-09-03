package com.luo.game.Entity;

import lombok.Data;

@Data
public class UserInformation {
    private Integer Id;
    private String Name;
    private String Password;

    public UserInformation(String name, String password) {
        Name = name;
        Password = password;
    }

}
