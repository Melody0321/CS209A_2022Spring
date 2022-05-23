package com.example.demo.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Entity
@Table(name = "project")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String url;
    @NotNull
    private int star = 0;
    @NotNull
    private int issues = 0;
    @NotNull
    private int watch = 0;
    @NotNull
    private int fork = 0;

    public Project(){

    }
    @Override
    public String toString(){
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", star=" + star +
                ", issues=" + issues +
                ", watch=" + watch +
                ", fork=" + fork +
                '}';
    }
    public String getUrl() {
        return url;
    }
    public int getID(){ return (int) id; }

    public String getName() {
        return name;
    }

    public int getWatch() {
        return watch;
    }

    public int getStar() {
        return star;
    }

    public int getFork() {
        return fork;
    }

    public int getIssues() {
        return issues;
    }
}
