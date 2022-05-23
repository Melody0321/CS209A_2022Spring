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
    @Column(name = "project_id")
    private long id;
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
    public int getID(){ return (int) id;}
    public String getTopic(){ return topic;}
}
