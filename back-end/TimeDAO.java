package com.example.demo.dao;
import com.example.demo.pojo.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface TimeDAO extends JpaRepository<Time, Long> {
    @Query(value="select * from project_time\n" +
            "where project_id = ?1",nativeQuery = true)
    List<Time> findTime(int id);
}
