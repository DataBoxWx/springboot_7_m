package com.bjpowernode.springboot.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.springboot.model.Student;
import com.bjpowernode.springboot.service.StudentService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:StudentController
 * package:com.bjpowernode.springboot.controller
 * Descrption:
 *
 * @Date:2018/7/26 15:32
 * @Author:724班
 */
@RestController
public class StudentController {

    //引用远程dubbo服务
    @Reference(timeout = 12000) // 等价于 <dubbo:reference id="xxx" interface="xxx.xx.xx">
    private StudentService studentService;

    @RequestMapping("/boot/student/{id}")
    public Object student(@PathVariable("id") Integer id)  {

        return studentService.getStudentById(id);

    }

    @RequestMapping("/boot/update")
    public Object updateStudent() {

        Student student = new Student();
        student.setId(1);
        student.setAge(18);
        student.setName("张三丰");

        int update = studentService.updateStudent(student);

        return update;
    }
}