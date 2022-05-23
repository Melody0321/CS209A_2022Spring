package com.example.demo.dao;

import com.example.demo.pojo.Topic;
import com.example.demo.pojo.TopicCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicCountDAO extends JpaRepository<TopicCount, Integer> {

//    @Query(value = "select * from topic_count",nativeQuery = true)
    List<TopicCount> findAll();
}
