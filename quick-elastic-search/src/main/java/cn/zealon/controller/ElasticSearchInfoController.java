package cn.zealon.controller;

import io.searchbox.client.JestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tangyl
 * @since: 2019/5/7
 * @version: 1.0
 */
@RestController
@RequestMapping("es")
public class ElasticSearchInfoController {

    @Autowired
    private JestClient jestClient;

    @GetMapping("/client-info")
    public Object getClientInfo(){
        return jestClient.toString();
    }
}
