package com.reportgarden.finalproject.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DependencyCount {

    @Id
    private String dependencyName;
    private Integer dependencyCount;

    public DependencyCount() {
    }

    public DependencyCount(String dependencyName, Integer dependencyCount) {
        this.dependencyName = dependencyName;
        this.dependencyCount = dependencyCount;
    }

    public String getDependencyName() {
        return dependencyName;
    }

    public void setDependencyName(String dependencyName) {
        this.dependencyName = dependencyName;
    }

    public Integer getDependencyCount() {
        return dependencyCount;
    }

    public void setDependencyCount(Integer dependencyCount) {
        this.dependencyCount = dependencyCount;
    }
}
