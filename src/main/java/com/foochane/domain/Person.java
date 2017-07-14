package com.foochane.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by fucheng on 2017/7/13.
 */

//跟数据库对应的类，下面的属性值会对于数据库中的字段，在数据库中自动创建表和字段
@Entity
public class Person {
    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "这个字段必传")
    private String name;

    @Min(value = 18, message = "未满18岁")
//    @NotNull
//    @Max()
//    @Length()
    private Integer age;

    @NotNull(message = "金额必传")
    private Double money;

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
