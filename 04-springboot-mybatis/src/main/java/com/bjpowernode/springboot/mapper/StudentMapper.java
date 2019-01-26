package com.bjpowernode.springboot.mapper;

import com.bjpowernode.springboot.model.Student;

//在spring中开发的时候，我们没有添加@Mapper,是因为spring的xml里面对该包进行了扫描
//@Mapper //该注解将StudentMapper变成Spring容器中的一个bean
public interface StudentMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}