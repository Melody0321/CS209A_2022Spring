package com.example.demo.service;
import com.example.demo.dao.ProjectDAO;
import com.example.demo.pojo.Project;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Objects;

@Service
public class ProjectService {
    @Autowired
    ProjectDAO projectDAO;
//    public List<Project> findAll(){
//        return projectDAO.findAll();
//    }
    public List<Project> findTop8(){
        return projectDAO.findTop8ByOrderById();
    }
    public List<Project> findByProjectInfo(String name, String tag){
        String realName="%";
        String realTag="%";
        if(Objects.equals(name, "")){
            realName="%";
            realTag="%"+tag+"%";
        }
        if (Objects.equals(tag, "")){
            realTag="%";
            realName="%"+name+"%";
        }
        return projectDAO.findByProjectInfo(realName, realTag);
    }
}
