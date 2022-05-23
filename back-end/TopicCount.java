package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "topic_count")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class TopicCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic")
    private String topic;

    @NotNull
    private int cnt;


    public TopicCount(){

    }
    @Override
    public String toString(){
        return "{" +
                "name:" + topic +
                ", value:'" + cnt + '\'' +
                '}';
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
