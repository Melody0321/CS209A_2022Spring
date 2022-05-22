package com.example.demo.dao;
import com.example.demo.pojo.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ProjectDAO extends JpaRepository<Project, Long> {
    List<Project> findAll();
    List<Project> findTop8ByOrderById();
    @Query(value="with a as(select distinct project_id as id from project_topic\n" +
            "where topic like '%an%'\n" +
            "union\n" +
            "select distinct id\n" +
            "from project\n" +
            "where name like '%an%')\n" +
            "select * from project,a where project.id=a.id",nativeQuery = true)
    List<Project> findByProjectInfo(String name, String tag);
}
