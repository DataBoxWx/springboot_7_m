package com.bjpowernode.p2p.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bjpowernode.p2p.admin.model.SlideShow;

/**
 * 轮播图相关处理Mapper
 * 
 * @author yanglijun
 *
 */
@Mapper
public interface SlideShowMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insertSelective(SlideShow slideShow);

    SlideShow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SlideShow record);

    List<SlideShow> getSlideShowByPage(Map<String, Object> paramMap);
    
    int getSlideShowByTotal(Map<String, Object> paramMap);
}