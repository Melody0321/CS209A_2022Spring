package com.example.demo.service;

import com.example.demo.dao.TopicCountDAO;
import com.example.demo.pojo.TopicCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicCountService {
    @Autowired
    private TopicCountDAO topicCountDAO;


    public List<TopicCount> getTopic(){
        return topicCountDAO.findAll();
    }
}
