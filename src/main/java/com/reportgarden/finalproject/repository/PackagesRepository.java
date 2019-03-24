package com.reportgarden.finalproject.repository;

import com.reportgarden.finalproject.entity.RepoContent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackagesRepository extends CrudRepository<RepoContent, Integer> {
}
