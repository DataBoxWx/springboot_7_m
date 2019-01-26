//JS的模块化开发方式
var seckillObj = {

    path : "",

    timeFlag : "",

    url : {

        //获取商品秒杀唯一标识的url
        randomURL : function () {
            return seckillObj.path + "/seckill/goods/random";
        },

        //执行秒杀的url
        seckillURL : function () {
            return seckillObj.path + "/seckill/goods";
        },

        //查询最终秒杀结果的url
        querySeckill : function () {
            return seckillObj.path + "/seckill/result";
        }
    },

    func : {
        //1、秒杀信息初始化函数
        initDetail : function (id, nowTime, startTime, endTime) {

            if (nowTime < startTime) {
                //秒杀未开始，显示秒杀倒计时

                var killTime = new Date(startTime + 1000);//防止时间偏移

                $("#seckillTip").countdown(killTime, function (event) {
                    //时间格式
                    var format = event.strftime('距秒杀开始还有: %D天 %H时 %M分 %S秒');
                    $("#seckillTip").html("<span style='color:red;'>"+format+"</span>");

                }).on('finish.countdown', function () {
                    //倒计时完成后回调事件，可以执行秒杀
                    seckillObj.func.startSeckill(id);
                });

            } else if (nowTime > endTime) {
                //秒杀已结束，直接显示秒杀已经结束
                $("#seckillTip").html("<span style='color:red;'>来晚了，秒杀已结束~</span>");

            } else {
                //秒杀已开始，可以开始秒杀，显示立即秒杀按钮
                seckillObj.func.startSeckill(id);
            }
        },

        //2、秒杀开始处理函数
        startSeckill : function (id) {

            //时间已经到了，可以秒杀了，这个时候，我们通过获取一个商品的唯一标识，如果能获取到则可以秒杀，不能获取到，则不能秒杀

            //你去进行秒杀的时候，你必须给我传一个商品的唯一标志，否则不能秒杀，我们称为秒杀接口地址暴露

            //秒杀地址： http://localhost:8080/24-seckill-web/seckill/goods/2/49020387-0a86-4f3f-9e13-463688ad4ef1

            //去获取商品秒杀的唯一标志
            $.ajax({
                // /seckill/goods/random/1
                url : seckillObj.url.randomURL() + "/" + id,
                type : "POST",
                dataType : "json",
                success : function (responseMessage) {
                    if (responseMessage.errorCode == "0") {

                        var random = responseMessage.data;
                        if (random) {
                            //可以秒杀的，在页面显示立即秒杀按钮
                            $("#seckillTip").html("<button type='button' id='seckillButton'>立即秒杀</button>");
                            $("#seckillButton").click(function () {
                                //秒杀按钮被点击后，第一行代码就是让秒杀按钮失效，避免用户多次点击（双击）,减少对后台的请求
                                $("#seckillButton").attr("disabled", true);
                                seckillObj.func.execSeckill(id, random);
                            });
                        }
                    } else {
                        $("#seckillTip").html("<span style='color:red;'>"+responseMessage.errorMessage+"</span>");
                    }
                }
            })
        },

        //点击秒杀按钮，执行秒杀处理
        execSeckill : function (id, random) {

            //秒杀真正开始，才会暴露秒杀地址
            $.ajax({
                url : seckillObj.url.seckillURL() + "/" + id + "/" + random,
                type: "POST",
                dataType : "json",
                success : function (responseMessage) {

                    //秒杀请求成功，返回的是一个中间结果，因为此时消息还没有消费，还没有真正下单，不是最终秒杀结果
                    if (responseMessage.errorCode == "0") {
                        //秒杀成功，现在页面给用户做提示（中间结果）最好是做一个页面效果，比如把页面蒙住，不能点，倒计时
                        $("#seckillTip").html("<span style='color:red;'>" + responseMessage.errorMessage + "</span>");

                        //3秒去查一次最终结果
                        seckillObj.timeFlag = window.setInterval(function() {
                            seckillObj.func.queryResult(id);
                        }, 3000);

                    } else {
                        $("#seckillTip").html("<span style='color:red;'>" + responseMessage.errorMessage + "</span>");
                    }
                }
            })
        },

        //查询最终秒杀结果
        queryResult : function (id) {
            $.ajax({
                url : seckillObj.url.querySeckill() + "/" + id,
                type : "POST",
                dataType : "json",
                success : function (responseMessage) {
                    if (responseMessage.errorCode == "0") {
                        //秒杀成功的, 可以 1、直接跳转到支付页支付，2、先把结果展示一下，然后三秒后自动跳转到支付页支付，3、让用户自己点击去支付
                        $("#seckillTip").html("<a style='color:red;'>秒杀成功，请立即支付。<a href='http://www.alipay.com' target='_blank'>去支付</a></span>");

                        //查到结果了，就应该把页面的3秒轮询去掉，不要反复去查
                        window.clearInterval(seckillObj.timeFlag);

                    } else if (responseMessage.errorCode == "1") {
                        //秒杀失败
                        $("#seckillTip").html("<span style='color:red;'>" +responseMessage.errorMessage+ "</span>");

                        //查到结果了，就应该把页面的3秒轮询去掉，不要反复去查
                        window.clearInterval(seckillObj.timeFlag);

                    } //else {
                        //还没有查询到结果
                        //不用操作,等下一次查询
                    //}
                }
            })
        }
    }
}