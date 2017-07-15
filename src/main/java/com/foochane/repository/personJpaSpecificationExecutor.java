package com.foochane.repository;

import com.foochane.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Specification封装了JPA Criteria查询条件
 * Created by fucheng on 2017/7/15.
 */
public interface personJpaSpecificationExecutor extends JpaSpecificationExecutor<Person> ,JpaRepository<Person,Integer> {
}
