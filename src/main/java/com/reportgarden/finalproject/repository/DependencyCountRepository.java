package com.reportgarden.finalproject.repository;

import com.reportgarden.finalproject.entity.DependencyCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DependencyCountRepository extends CrudRepository<DependencyCount, String> {
}
