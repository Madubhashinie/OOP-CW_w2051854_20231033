package com.BackendPrac.BackendPrac1.repository;

import com.BackendPrac.BackendPrac1.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {

}
