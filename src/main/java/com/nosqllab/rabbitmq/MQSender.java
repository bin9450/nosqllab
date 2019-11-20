package com.nosqllab.rabbitmq;

import com.nosqllab.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Pan
 * @Date: 2019/11/20 19:12
 * @Description:
 **/
@Service
public class MQSender {
    private static Logger log = LoggerFactory.getLogger(MQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendMessage(SelectCourseMessage selectCourseMessage){
        String msg = RedisService.beanToString(selectCourseMessage);
        log.info("send message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.QUEUE, msg);
    }

}
