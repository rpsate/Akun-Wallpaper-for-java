package com.rpsate.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rpsate.pojo.Image;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ImageDao extends BaseMapper<Image> {
    public default IPage<Image> search(IPage page, String key) {
        LambdaQueryWrapper<Image> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(Image::getName, key);
        return selectPage(page, lambdaQueryWrapper);
    }
}
