package com.reportgarden.finalproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RepoContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String repo;
    private String dependenciesMap;

    public RepoContent() {

    }

    public Integer getId() {
        return id;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDependenciesMap() {
        return dependenciesMap;
    }

    public void setDependenciesMap(String dependenciesMap) {
        this.dependenciesMap = dependenciesMap;
    }
}
