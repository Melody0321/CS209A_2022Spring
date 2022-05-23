package com.example.demo.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "project_topic")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotNull
    private int project_id;
    @NotNull
    private String topic;


    public Topic(){

    }
    @Override
    public String toString(){
        return "Topic{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                '}';
    }
    public int getId(){ return id;}
    public String getTopic(){ return topic;}
    public int getProject_id(){return project_id;}
}
