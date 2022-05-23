package com.example.demo.dto;
import lombok.Data;
import java.sql.Timestamp;


@Data
public class TimeDTO {
    private long project_id;
    private Timestamp create_time;
    private Timestamp update_time;
}
