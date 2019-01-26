package com.bjpowernode.springboot.controller;

import com.bjpowernode.springboot.model.Student;
import com.bjpowernode.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:StudentController
 * package:com.bjpowernode.springboot.controller
 * Descrption:
 *
 * @Date:2018/7/24 15:42
 * @Author:724班
 */
@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/boot/student")
    public @ResponseBody Object student() {

        Student student = studentService.getStudentById(1);

        return student;
    }

    /**
     * 测试事务管理
     *
     * @return
     */
    @GetMapping("/boot/update")
    public @ResponseBody Object updateStudent() {

        Student student = new Student();
        student.setId(1);
        student.setAge(18);
        student.setName("张三丰");

        int update = studentService.updateStudent(student);

        return update;
    }
}
