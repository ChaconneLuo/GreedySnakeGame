package com.luo.game.Controller.Impl;

import com.luo.game.Controller.CountController;
import com.luo.game.Services.CountServices;
import com.luo.game.Services.Impl.CountServicesImpl;

public class CountControllerImpl implements CountController {

    CountServices countServices;

    public CountControllerImpl()
    {
        countServices = new CountServicesImpl();
    }

    @Override
    public Integer getMaxCount(String name) {
        return countServices.GetMaxScore(name);
    }

    @Override
    public Integer setMaxCount(String name, Integer Score) {
        countServices.UpdateMaxScore(name,Score);
        return countServices.GetMaxScore(name);
    }

}
