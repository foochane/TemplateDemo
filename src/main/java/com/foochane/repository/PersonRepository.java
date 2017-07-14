package com.foochane.repository;

import com.foochane.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by fucheng on 2017/7/13.
 */
public interface PersonRepository extends JpaRepository<Person,Integer> {

    //方法名只能按照这个格式来写
    //通过年龄来查询
    public List<Person> findByAge(Integer age);

}
