package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.Student;

/**
 * ClassName:StudentService
 * package:com.bjpowernode.springboot.service
 * Descrption:
 *
 * @Date:2018/7/24 15:38
 * @Author:724班
 */
public interface StudentService {

    public Student getStudentById(Integer id);

    public int updateStudent(Student student);

}
