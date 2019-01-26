package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.mapper.StudentMapper;
import com.bjpowernode.springboot.model.Student;
import com.bjpowernode.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName:StudentServiceImpl
 * package:com.bjpowernode.springboot.service.impl
 * Descrption:
 *
 * @Date:2018/7/24 15:38
 * @Author:724班
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student getStudentById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Transactional //该方法是有事务管理的，是spring提供的事务管理
    @Override
    public int updateStudent(Student student) {

        //1、更新学生
        int update = studentMapper.updateByPrimaryKeySelective(student);

        //2、抛出异常，Spring事务管理是当方法抛出异常后，前面的操作都进行回滚
        int a = 10 / 0;

        return update;
    }
}
