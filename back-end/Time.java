package com.example.demo.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "project_time")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private long id;
    @NotNull
    private Timestamp create_time;
    @NotNull
    private Timestamp update_time;
    public Time(){

    }
    @Override
    public String toString(){
        return "Time{" +
                "id=" + id +
                ", create_time='" + create_time.toString().substring(0,11) + '\'' +
                ", update_time='" + update_time.toString().substring(0,11) + '\'' +
                '}';
    }
    public String getCreate_time(){ return create_time.toString().substring(0,11);}
    public String getUpdate_time(){ return update_time.toString().substring(0,11);}
}
