package cn.zealon.controller;

import cn.zealon.common.Constant;
import cn.zealon.domain.Book;
import cn.zealon.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: tangyl
 * @since: 2019/5/7
 * @version: 1.0
 */
@Controller
@RequestMapping("doc")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @RequestMapping("/create-page")
    public String createPage(){
        return "document/create";
    }

    @RequestMapping("/create")
    @ResponseBody
    public String createDocument(String index, Book book){
        return documentRepository.createDocument(index,Constant.DEFAULT_TYPE,book).getJsonString();
    }
}
