package com.example.demo.controller;
import com.example.demo.pojo.Topic;
import com.example.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class TopicController {
    @Autowired
    private TopicService topicService;
    @GetMapping("/projectTag")
    public List<Topic> findProjectTag(@RequestParam int id){
        System.out.println("tag function called. ID:"+id);
        //        System.out.println(re);
        return topicService.findProjectTag(id);
    }
}
