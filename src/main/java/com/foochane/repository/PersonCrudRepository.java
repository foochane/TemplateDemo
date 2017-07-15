package com.foochane.repository;

import com.foochane.domain.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fucheng on 2017/7/15.
 */
public interface PersonCrudRepository extends CrudRepository<Person,Integer>{


}
