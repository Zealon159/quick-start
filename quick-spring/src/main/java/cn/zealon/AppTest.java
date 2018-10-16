package cn.zealon;

import cn.zealon.bean.BizService;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @auther: Zealon
 * @Date: 2018-10-16 16:14
 */
public class AppTest {
    public static void main(String[] args){
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BizService service = (BizService) context.getBean("bizService");
        service.sayHello("ai ya ~ ");
        context.close();
    }
}
