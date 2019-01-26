package com.bjpowernode.seckill.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpowernode.seckill.commons.CommonsConstants;
import com.bjpowernode.seckill.commons.CommonsReturnObject;
import com.bjpowernode.seckill.model.Goods;
import com.bjpowernode.seckill.model.Users;
import com.bjpowernode.seckill.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName:GoodsContrroller
 * package:com.bjpowernode.seckill.controller
 * Descrption:
 *
 * @Date:2018/8/7 16:45
 * @Author:724班
 */
@Controller
public class GoodsController {

    //引用远程dubbo服务
    @Reference
    private GoodsService goodsService;

    /**
     * 秒杀商品列表页
     *
     * @param model
     * @return
     */
    @GetMapping("/seckill/goods")
    public String goods(Model model) {

        List<Goods> goodsList = goodsService.getAllSeckillGoods();

        model.addAttribute("goodsList", goodsList);

        return "goods";
    }

    /**
     * 秒杀商品详情页
     *
     * @param id
     * @return
     */
    @GetMapping("/seckill/goods/{id}")
    public String detail(HttpSession session, Model model, @PathVariable("id") Integer id) {

        //模拟给用户登录一下，用户只要点击进入了商品详情页，就给用户登录
        Users users = new Users();
        users.setUid(888888888);
        session.setAttribute(CommonsConstants.SESSION_USER, users);

        Goods goods = goodsService.getSeckillGoodsById(id);

        model.addAttribute("goods", goods);

        model.addAttribute("nowTime", System.currentTimeMillis());

        return "detail";
    }

    @PostMapping("/seckill/goods/random/{id}")
    public @ResponseBody CommonsReturnObject random(@PathVariable("id") Integer id) {

        CommonsReturnObject commonsReturnObject = new CommonsReturnObject();

        Goods goods = goodsService.getSeckillGoodsById(id);

        //判断什么情况下可以给前台返回商品秒杀唯一标志(秒杀真正开始了，才可以给前台返回秒杀唯一标志)

        //秒杀开始时间
        long startTime = goods.getStarttime().getTime();
        //秒杀结束时间
        long endTime = goods.getEndtime().getTime();

        //当前时间
        long nowTime = System.currentTimeMillis();

        if (nowTime < startTime) {
            //秒杀未开始, 不能返回秒杀唯一标志
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("秒杀未开始");

        } else if (nowTime > endTime) {
            //秒杀已结束，不能返回秒杀唯一标志
            commonsReturnObject.setErrorCode(CommonsConstants.ONE);
            commonsReturnObject.setErrorMessage("秒杀已结束");

        } else {
            //秒杀真正开始了，可以返回秒杀唯一标志
            commonsReturnObject.setErrorCode(CommonsConstants.ZERO);
            commonsReturnObject.setErrorMessage("秒杀已开始");
            //秒杀唯一标志
            commonsReturnObject.setData(goods.getRandomname());
        }
        return commonsReturnObject;
    }

    /**
     * 执行秒杀请求
     *
     * @param id
     * @param random
     * @return
     */
    @PostMapping("/seckill/goods/{id}/{random}")
    public @ResponseBody CommonsReturnObject execSeckill(HttpSession session, @PathVariable("id") Integer id,
                                                         @PathVariable("random") String random) {

        //controller就是做一些参数的合法性验证，逻辑控制、跳转

        Users users = (Users)session.getAttribute(CommonsConstants.SESSION_USER);

        ExecutorService executorService = Executors.newFixedThreadPool(16);

        for (int i=0; i<50000; i++) {
            int uid = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    //核心业务逻辑是 service层 实现
                    CommonsReturnObject commonsReturnObject =  goodsService.execSeckill(uid, id, random);
                    System.out.println(commonsReturnObject.getErrorMessage());
                }
            });
        }

        //页面上的用户点击的请求

        //核心业务逻辑是 service层 实现
        CommonsReturnObject commonsReturnObject =  goodsService.execSeckill(users.getUid(), id, random);

        return commonsReturnObject;
    }

    /**
     * 查询最终秒杀结果
     *
     * @param id
     * @return
     */
    @PostMapping("/seckill/result/{id}")
    public @ResponseBody CommonsReturnObject queryResult(HttpSession session, @PathVariable("id") Integer id) {

        Users users = (Users)session.getAttribute(CommonsConstants.SESSION_USER);

        return goodsService.querySeckillResult(users.getUid(), id);
    }
}
