package com.example.demo.dao;
import com.example.demo.pojo.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicDAO extends JpaRepository<Topic, Long> {

    @Query(value="select * from project_topic\n" +
            "where project_id = ?1",nativeQuery = true)
    List<Topic> findTag(int id);
}
