package com.example.demo.service;
import com.example.demo.dao.TimeDAO;
import com.example.demo.pojo.Time;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@Service
public class TimeService {
    @Autowired
    TimeDAO timeDAO;
    public List<Time> findProjectTime(int id) { return timeDAO.findTime(id);}
}
