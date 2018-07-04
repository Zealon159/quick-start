package cn.zealon.activemq.p2p.controller;

import cn.zealon.activemq.p2p.service.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 生产者控制器类
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderProducerService producerService;

    /**
     * 新增订单
     * @param queue 队列名称
     * @param message 消息内容
     * @param messageNum 数量
     * @return
     */
    @PostMapping("add")
    public Object addOrder(String queue,String message,int messageNum){
        if(messageNum==0){
            messageNum = 1;
        }

        for(int i=0;i<messageNum;i++) {
            producerService.sendTextMessage(queue, message+i);
        }

        return "ok";
    }
}
