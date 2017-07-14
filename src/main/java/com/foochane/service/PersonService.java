package com.foochane.service;

import com.foochane.domain.Person;
import com.foochane.enums.ResultEnum;
import com.foochane.exception.ExceptionBase;
import com.foochane.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fucheng on 2017/7/13.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * 添加 /更新
     * @param person
     * @return 返回对象
     */
    public Person save(Person person){
        return personRepository.save(person);
    }

    /**
     * 通过Id查询
     * @param id
     * @return
     */
    public Person getById(Integer id) {
        return personRepository.findOne(id);
    }

    /**
     * 查询列表
     * @returng
     */
    public List<Person>getList(){
        return personRepository.findAll();
    }

    /**
     * 删除
     * @param id
     */
    public String delete (Integer id){
        personRepository.delete(id);
        return "删除成功";
    }

    /**
     * 通过age查询，列表
     * @param age
     * @return
     */
    public List<Person> getListByAge(Integer age){
        return personRepository.findByAge(age);
    }


    public Person setByAge(Integer id) throws Exception{
        Person person = personRepository.findOne(id);
        Integer age = person.getAge();
        if (age < 10) {
            //返回"你还在上小学吧" code=100
            throw new ExceptionBase(ResultEnum.PRIMARY_SCHOOL);
        }else if (age > 10 && age < 16) {
            //返回"你可能在上初中" code=101
            throw new ExceptionBase(ResultEnum.MIDDLE_SCHOOL);
        }else {
            //操作。。。。
            return person;
        }

    }


}
