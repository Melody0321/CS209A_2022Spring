package com.example.demo.controller;

import com.example.demo.pojo.Goods;
import com.example.demo.pojo.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    
    @GetMapping("/record")
    public List<Project> findAll(){
        System.out.println(projectService.getClass().getName());
        return projectService.findAll();
    }
}
