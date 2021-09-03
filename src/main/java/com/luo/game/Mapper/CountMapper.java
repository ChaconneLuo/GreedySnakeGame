package com.luo.game.Mapper;

import com.luo.game.Entity.UserInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CountMapper {
    public Integer UpdateScore(@Param("name") String name,@Param("Score") Integer Score);
    public Integer getMaxScore(String name);
}
