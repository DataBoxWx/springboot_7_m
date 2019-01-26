package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.mapper.StudentMapper;
import com.bjpowernode.springboot.model.Student;
import com.bjpowernode.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:StudentServiceImpl
 * package:com.bjpowernode.springboot.service.impl
 * Descrption:
 *
 * @Date:2018/7/26 15:20
 * @Author:724班
 */
@Service //spring提供的注解，主要是把该实现类变成spring容器中的一个bean、
@com.alibaba.dubbo.config.annotation.Service(timeout = 10000, retries = 0) //dubbo注解，等价于 <dubbo:service interface="XXX" ref="xxxImpl">
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStudent(Student student) {
        return studentMapper.updateByPrimaryKeySelective(student);
    }
}