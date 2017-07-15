package com.foochane.service;

import com.foochane.domain.Person;
import com.foochane.enums.ResultEnum;
import com.foochane.exception.ExceptionBase;
import com.foochane.repository.PersonCrudRepository;
import com.foochane.repository.PersonJpaRepository;
import com.foochane.repository.PersonPagingAndSortingRepository;
import com.foochane.repository.personJpaSpecificationExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
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

    @Autowired
    private PersonPagingAndSortingRepository personPagingAndSortingRepository;

    @Autowired
    private personJpaSpecificationExecutor personJpaSpecificationExecutor;

    /**
     * 添加 /更新
     *
     * @param person
     * @return 返回对象
     */
    public Person save(Person person) {
        return personJpaRepository.save(person);
    }

    /**
     * 通过Id查询
     *
     * @param id
     * @return
     */
    public Person getById(Integer id) {
        return personJpaRepository.findOne(id);
    }

    /**
     * 查询列表
     *
     * @returng
     */
    public List<Person> getList() {
        return personJpaRepository.findAll();
    }

    /**
     * 删除
     *
     * @param id
     */
    public String delete(Integer id) {
        personJpaRepository.delete(id);
        return "删除成功";
    }

    /**
     * 通过age查询，列表
     *
     * @param age
     * @return
     */
    public List<Person> getListByAge(Integer age) {
        return personJpaRepository.findByAge(age);
    }


    public Person setByAge(Integer id) throws Exception {
        Person person = personJpaRepository.findOne(id);
        Integer age = person.getAge();
        if (age < 10) {
            //返回"你还在上小学吧" code=100
            throw new ExceptionBase(ResultEnum.PRIMARY_SCHOOL);
        } else if (age > 10 && age < 16) {
            //返回"你可能在上初中" code=101
            throw new ExceptionBase(ResultEnum.MIDDLE_SCHOOL);
        } else {
            //操作。。。。
            return person;
        }

    }

    @Transactional
    public String updateNameById(Integer id, String name) {
        personJpaRepository.updateNameById(id, name);
        return "修改成功";
    }


   /*  public String deleteByName(String name){
        personJpaRepository.deleteByName(name);
        return "删除成功";
    }*/


    public Iterable<Person> savePersonList(List<Person> personList) {
        return personCrudRepository.save(personList);
    }


    //分页
    public void PageList() {
        //page:index是从0开始的
        Pageable pageable = new PageRequest(0, 10);
        Page<Person> page = personPagingAndSortingRepository.findAll(pageable);

        System.out.println("查询的总页数" + page.getTotalPages());
        System.out.println("查询的总记录数" + page.getTotalElements());
        System.out.println("查询当前第几页" + page.getNumber() + 1);
        System.out.println("查询当前页的集合" + page.getContent());
        System.out.println("查询当前页面的记录书" + page.getNumberOfElements());
    }

    //分页 并且排序
    public void PageAndSort() {

        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order);
        //page:index是从0开始的
        Pageable pageable = new PageRequest(0, 5, sort);
        Page<Person> page = personPagingAndSortingRepository.findAll(pageable);

        System.out.println("查询的总页数" + page.getTotalPages());
        System.out.println("查询的总记录数" + page.getTotalElements());
        System.out.println("查询当前第几页" + page.getNumber() + 1);
        System.out.println("查询当前页的集合" + page.getContent());
        System.out.println("查询当前页面的记录书" + page.getNumberOfElements());
    }


    /**
     * JpaSpecificationExecutor使用
     * 1.分页
     * 2.排序
     * 3.查询条件 :age>20
     */
    public void PageSortAndQuery() {

        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        Sort sort = new Sort(order);

        //page:index是从0开始的
        Pageable pageable = new PageRequest(0, 5, sort);

        /**
         * root:就是我们要查询的类型（Person)
         * query:添加查询条件
         * cb: 构建Predicate
         */
        Specification<Person> specification = new Specification<Person>() {
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //root(employee (age))
                Path path = root.get("age");
                return cb.gt(path,20);
            }
        };
        Page<Person> page = personJpaSpecificationExecutor.findAll(specification,pageable);

        System.out.println("查询的总页数" + page.getTotalPages());
        System.out.println("查询的总记录数" + page.getTotalElements());
        System.out.println("查询当前第几页" + page.getNumber() + 1);
        System.out.println("查询当前页的集合" + page.getContent());
        System.out.println("查询当前页面的记录书" + page.getNumberOfElements());
    }


}
