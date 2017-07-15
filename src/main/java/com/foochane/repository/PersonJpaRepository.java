package com.foochane.repository;

import com.foochane.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by fucheng on 2017/7/13.
 */
public interface PersonJpaRepository extends JpaRepository<Person,Integer> {

    //方法名只能按照这个格式来写
    //通过年龄来查询
    public List<Person> findByAge(Integer age);

    //使用Query注解
    //查出id最大的一条数据
    @Query("select o from  Person o where id=(select max(id) from  Person t1)")
    Person getPersonByMaxId();

    //根据姓名和年龄查询
    @Query("select o from  Person o where o.name=?1 and o.age=?2")
    List<Person> getByNameAndAge1(String name,Integer age);
    @Query("select o from  Person o where o.name=:name and o.age=:age")
    List<Person> getByNameAndAge2(@Param("name") String name, @Param("age") Integer age);

    //有问题。。。。,估计是要继承Repository才行
    //like查询
    //@Query("select o from Person o where o.name like %?1%")
   // public List<Person> getByNameMsg(String name);

    //查询数量
    @Query(nativeQuery = true, value = "select count(1) from Person")
    public long getCount();

    //@Modifying和 @Query结合执行更新操作
    //涉及事务操作，需要在service层进行处理
    //通过id更改name
    @Modifying
    @Query("update Person o set o.name = :name where o.id = :id")
    public void updateNameById(@Param("id")Integer id, @Param("name")String name);


    //通过name删除  在service层进行处理
    //@Query("delete  from person   where  name=:name")
    //public void deleteByName(@Param("name")String name);



}
