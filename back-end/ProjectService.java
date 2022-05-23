package com.example.demo.service;
import com.example.demo.dao.ProjectDAO;
import com.example.demo.pojo.Project;
import com.example.demo.pojo.Topic;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    ProjectDAO projectDAO;
    public List<Project> findTop8(){
        return projectDAO.findTop8ByOrderById();
    }
    public List<Project> findByProjectInfo(String name, int sort){
        String realName="%"+name+"%";
        List<Project> re = projectDAO.findByProjectInfo(realName);
        // by star
        if (sort==1){
            re.sort(((o1, o2) -> o2.getStar()-o1.getStar()));
        }
        // by fork
        else if (sort==2){
            re = re.stream().sorted(Comparator.comparing(Project::getFork).reversed()).collect(Collectors.toList());
        }
        // by watch
        else if (sort==3){
            re.sort(((o1, o2) -> o2.getWatch()-o1.getWatch()));
        }
        //by issues
        else if (sort==4){
            re.sort(((o1, o2) -> o2.getIssues()-o1.getIssues()));
        }
        return re;
    }

}
