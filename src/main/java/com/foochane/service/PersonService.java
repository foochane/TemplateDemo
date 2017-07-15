package com.foochane.service;

import com.foochane.domain.Person;
import com.foochane.enums.ResultEnum;
import com.foochane.exception.ExceptionBase;
import com.foochane.repository.PersonCrudRepository;
import com.foochane.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 涉及到保存/修改/删除的应该在service层
 * Created by fucheng on 2017/7/13.
 */
@Service
public class PersonService {

    @Autowired
    private PersonJpaRepository personJpaRepository;

    @Autowired
    private PersonCrudRepository personCrudRepository;

    /**
     * 添加 /更新
     * @param person
     * @return 返回对象
     */
    public Person save(Person person){
        return personJpaRepository.save(person);
    }

    /**
     * 通过Id查询
     * @param id
     * @return
     */
    public Person getById(Integer id) {
        return personJpaRepository.findOne(id);
    }

    /**
     * 查询列表
     * @returng
     */
    public List<Person>getList(){
        return personJpaRepository.findAll();
    }

    /**
     * 删除
     * @param id
     */
    public String delete (Integer id){
        personJpaRepository.delete(id);
        return "删除成功";
    }

    /**
     * 通过age查询，列表
     * @param age
     * @return
     */
    public List<Person> getListByAge(Integer age){
        return personJpaRepository.findByAge(age);
    }


    public Person setByAge(Integer id) throws Exception{
        Person person = personJpaRepository.findOne(id);
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

    @Transactional
    public String updateNameById(Integer id,String name){
        personJpaRepository.updateNameById(id,name);
        return "修改成功";
    }


   /*  public String deleteByName(String name){
        personJpaRepository.deleteByName(name);
        return "删除成功";
    }*/


   public Iterable<Person> savePersonList(List<Person> personList){
       return personCrudRepository.save(personList);
   }


}
