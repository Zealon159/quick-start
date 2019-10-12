package cn.zealon.rabbit.p2p;

import cn.zealon.rabbit.common.JsonSerilizable;
import com.alibaba.fastjson.JSONArray;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Map;

/**
 * @author: tangyl
 * @since: 2019/10/11
 */
@Component
public class Receiving {

    @RabbitListener(queues = "myQueue")
    public void processMessage(Message message) {
        byte[] body = message.getBody();
        if (body != null){
            try {
                Map<String,Object> retMap = JsonSerilizable.deserilizableForMapFromFile(new String(body), Object.class);
                JSONArray data = (JSONArray) retMap.get("data");
                System.out.println("receiving: msg="+retMap.get("msg")+"，data="+data.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
