package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.TopicCount;
import com.example.demo.service.TopicCountService;
import com.example.demo.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TopicCountController {
    public TopicCountController() {
    }

    @Autowired
    private TopicCountService topicCountService;

    @GetMapping("/projectTopic")
    @ResponseBody
    public String getTopic(){
        List<TopicCount> result=topicCountService.getTopic();
        String data= JSON.toJSONString(result);
        return  data;
    }
}
