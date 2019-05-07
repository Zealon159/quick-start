package cn.zealon.controller;

import cn.zealon.domain.Book;
import cn.zealon.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tangyl
 * @since: 2019/5/7
 * @version: 1.0
 */
@RestController
@RequestMapping("doc")
public class DocumentController {

    @Autowired
    private DocumentRepository documentRepository;

    @RequestMapping("/create")
    public Object createDocument(String index, String type, Book book){
        return documentRepository.createDocument(index,type,book).getJsonString();
    }
}
