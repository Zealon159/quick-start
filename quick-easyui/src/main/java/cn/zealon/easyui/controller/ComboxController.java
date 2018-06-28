package cn.zealon.easyui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther: Zealon
 * @Date: 2018-06-28 10:28
 */
@Controller
public class ComboxController {

    @RequestMapping("combox")
    public String index(){
        return "form/combox";
    }

    @ResponseBody
    @RequestMapping("combox/data")
    public String comboData(){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i=1;i<=20;i++){
            if(i>1){
                sb.append(",");
            }
            sb.append("{\"id\":\"黑足猫"+i+"\",\"text\":\"黑足猫"+i+"\"}");
        }
        sb.append("]");
        return sb.toString();
    }

    @ResponseBody
    @RequestMapping("combox/datachild/{id}")
    public String comboChildData(@PathVariable("id") String id){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for(int i=1;i<=5;i++){
            if(i>1){
                sb.append(",");
            }
            sb.append("{\"id\":\""+id+"的儿子小黑"+i+"\",\"text\":\""+id+"的儿子小黑"+i+"\"}");
        }
        sb.append("]");
        return sb.toString();
    }

}
