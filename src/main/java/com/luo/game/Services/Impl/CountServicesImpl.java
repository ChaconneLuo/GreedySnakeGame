package com.luo.game.Services.Impl;

import com.luo.game.Utils.MapperUtils;
import com.luo.game.Mapper.CountMapper;
import com.luo.game.Services.CountServices;


public class CountServicesImpl implements CountServices {

    CountMapper countMapper;

    public CountServicesImpl() {
        countMapper = MapperUtils.getMapper(CountMapper.class);
    }

    @Override
    public Integer UpdateMaxScore(String name, Integer Score) {
        if(countMapper.getMaxScore(name) < Score)
        {
            countMapper.UpdateScore(name,Score);
        }
        return countMapper.getMaxScore(name);
    }

    @Override
    public Integer GetMaxScore(String name) {
        return countMapper.getMaxScore(name);
    }
}
