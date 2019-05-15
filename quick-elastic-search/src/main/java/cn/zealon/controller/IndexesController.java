package cn.zealon.controller;

import cn.zealon.repository.IndexesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: tangyl
 * @since: 2019/5/7
 * @version: 1.0
 */
@Controller
@RequestMapping("indexes")
public class IndexesController {

    @Autowired
    private IndexesRepository indexesRepository;

    @RequestMapping("/create-page")
    public String createPage(){
        return "indexes/create";
    }

    @RequestMapping("/create")
    public @ResponseBody Object createIndex(String index){
        String type = "book";
        return indexesRepository.createIndex(index,index,type).getJsonString();
    }

    @RequestMapping("/delete")
    public @ResponseBody Object deleteIndex(String index){
        return indexesRepository.deleteIndex(index).getJsonString();
    }

    @GetMapping("/list")
    public String list(){
        return "indexes/list";
    }

    @RequestMapping("/cat")
    public @ResponseBody Object catIndex(){
        return indexesRepository.catIndex().getJsonString();
    }
}
