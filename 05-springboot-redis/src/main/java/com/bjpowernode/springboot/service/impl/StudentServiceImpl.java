package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.mapper.StudentMapper;
import com.bjpowernode.springboot.model.Student;
import com.bjpowernode.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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

    /**
     * redisTemplate这个bean是springboot自动配置好的，我们直接注入即可使用
     */
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 线程锁实现 synchronized
     *
     * @param id
     * @return
     */
    @Override
    public /*synchronized*/ Student getStudentById(Integer id) {

        //设置key的序列化方式采用string的方式，可读性比较好
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //取redis
        Student student = (Student) redisTemplate.opsForValue().get("studentKey");

        if (null == student) {
            //双重检测锁 实现
            synchronized (this) {
                //再取一遍redis
                student = (Student) redisTemplate.opsForValue().get("studentKey");

                if (null == student) {

                    //查询一下数据库
                    student = studentMapper.selectByPrimaryKey(id);
                    redisTemplate.opsForValue().set("studentKey", student);

                    System.out.println("查询数据库......");
                } else {
                    System.out.println("查询缓存......");
                }
            }
        } else {
            System.out.println("查询缓存......");
        }
        return student;
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
