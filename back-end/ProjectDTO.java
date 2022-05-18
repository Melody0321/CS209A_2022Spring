package com.example.demo.dto;

import lombok.Data;


@Data
public class ProjectDTO {
    private long id;
    private String name;
    private String url;
    private int star;
    private int issues;
    private int watch;
    private int fork;
}
