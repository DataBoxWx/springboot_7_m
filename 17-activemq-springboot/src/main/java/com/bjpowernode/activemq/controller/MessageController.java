package com.bjpowernode.activemq.controller;

import com.bjpowernode.activemq.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:MessageController
 * package:com.bjpowernode.activemq.controller
 * Descrption:
 *
 * @Date:2018/8/4 14:38
 * @Author:724班
 */
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 发送消息
     *
     * @return
     */
    @GetMapping("/sms/send")
    public String send() {

        messageService.sendMessage();

        return "OK";
    }

    /**
     * 接收消息
     *
     * @return
     */
    @GetMapping("/sms/receive")
    public String receive() {

        return messageService.receiveMessage();

    }
}