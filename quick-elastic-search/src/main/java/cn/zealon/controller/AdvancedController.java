package cn.zealon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 高级查询
 * @author: tangyl
 * @since: 2019/5/22
 * @version: 1.0
 */
@RequestMapping("advanced")
@Controller
public class AdvancedController {

    /**
     * 高级查询
     */
    @RequestMapping("/search-page")
    public String advancedSearch(){
        return "search/advanced-search";
    }


}
