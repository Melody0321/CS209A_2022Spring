package com.example.demo.controller;
import com.example.demo.pojo.Time;
import com.example.demo.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class TimeController {
    @Autowired
    private TimeService timeService;
    @GetMapping("/projectTime")
    public List<Time> findProjectTime(@RequestParam int id){
        System.out.println("time function called. ID:"+id);
        List<Time> re = timeService.findProjectTime(id);
        System.out.println(re);
        return re;
    }
}
