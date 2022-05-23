package com.example.demo.service;
import com.example.demo.pojo.Topic;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.demo.dao.TopicDAO;
@Service
public class TopicService {
    @Autowired
    TopicDAO topicDAO;
    public List<Topic> findProjectTag(int id){
        System.out.println(topicDAO.findTag(id));
        return topicDAO.findTag(id);
    }
}
