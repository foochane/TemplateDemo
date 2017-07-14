package com.foochane.service;

import com.foochane.domain.Person;
import com.foochane.enums.ResultEnum;
import com.foochane.exception.PersonException;
import com.foochane.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by fucheng on 2017/7/13.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void insertTwo() {
        Person personA = new Person();
        personA.setName("A");
        personA.setAge(18);
        personA.setMoney(23.3);
        personRepository.save(personA);


        Person personB = new Person();
        personB.setName("B");
        personB.setAge(19);
        personB.setMoney(1.1);
        personRepository.save(personB);
    }

    public Person setByAge(Integer id) throws Exception{
        Person person = personRepository.findOne(id);
        Integer age = person.getAge();
        if (age < 10) {
            //返回"你还在上小学吧" code=100
            throw new PersonException(ResultEnum.PRIMARY_SCHOOL);
        }else if (age > 10 && age < 16) {
            //返回"你可能在上初中" code=101
            throw new PersonException(ResultEnum.MIDDLE_SCHOOL);
        }else {
            //操作。。。。
            return person;
        }

    }

    /**
     * 通过Id查询一个人的信息
     * @param id
     * @return
     */
    public Person findOne(Integer id) {
        return personRepository.findOne(id);
    }

}
