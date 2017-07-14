package com.foochane.controller;

import com.foochane.domain.Person;
import com.foochane.utils.Result;
import com.foochane.repository.PersonRepository;
import com.foochane.service.PersonService;
import com.foochane.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by fucheng on 2017/7/13.
 */
@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    /**
     * 查询列表
     * @returng
     */
    @GetMapping(value = "/list")
    public List<Person> personList(){
        return personRepository.findAll();
    }

    /**
     * 添加
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Person> personAdd(@Valid Person person , BindingResult bindingResult){

        //如果发生错误，打印错误
        if(bindingResult.hasErrors()){
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(personRepository.save(person));
    }
    /**
     * 通过id查询
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public Result<Person> personFindOne (@PathVariable("id") Integer id){
        return ResultUtil.success(personRepository.findOne(id));
    }

   /**
     * 通过id更新
     * @return
     *  注意：put方式的请求body格式只能是x-www-form-urlencoded
     */
    @PutMapping(value = "/update/{id}")
    public Result<Person> personUpdate(@PathVariable("id") Integer id,
                         @RequestParam("name") String name,
                         @RequestParam("age") Integer age,
                         @RequestParam("money") Double money){
        Person person = new Person();
        person.setId(id);
        person.setAge(age);
        person.setName(name);
        person.setMoney(money);
        return ResultUtil.success(personRepository.save(person));
    }

    /**
     * 通过id删除
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public void girlDelete (@PathVariable("id") Integer id){
        personRepository.delete(id);
    }

    /**
     * 通过年龄查询列表
     */
    @GetMapping(value = "/get/age/{age}")
    public List<Person> girlListByAge(@PathVariable("age") Integer age){
        return personRepository.findByAge(age);
    }


    /**
     * 插入两条数据
     */
    @PostMapping(value = "/two")
    public void girlTwo(){
        personService.insertTwo();
    }


    @GetMapping(value = "girls/getAge/{id}")
    public Result<Person> getAge(@PathVariable("id") Integer id) throws Exception{
        //personService.getAge(id);
        return ResultUtil.success(personService.getAge(id));
    }

}
