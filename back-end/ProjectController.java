package com.example.demo.controller;
import com.example.demo.pojo.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;


    @GetMapping("/projectTop8")
    public List<Project> findTop8(){
        System.out.println("top 8 call");
        return projectService.findTop8();
    }

    @GetMapping("/projectInfo")
    public List<Project> findByProjectInfo(@RequestParam String name,
                                           @RequestParam int sort){
        System.out.println("info function called.");
        return projectService.findByProjectInfo(name,sort);
    }


}
