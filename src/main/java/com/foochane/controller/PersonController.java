package com.foochane.controller;

import com.foochane.domain.Person;
import com.foochane.enums.ResultEnum;
import com.foochane.repository.PersonJpaRepository;
import com.foochane.service.PersonService;
import com.foochane.utils.Result;
import com.foochane.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    private PersonService personService;

    @Autowired
    private PersonJpaRepository personJpaRepository;


    /**
     * 添加
     * @param person
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Person> personAdd(@Valid Person person , BindingResult bindingResult){

        //如果发生错误，打印错误
        if(bindingResult.hasErrors()){
            return ResultUtil.error(ResultEnum.FAILED.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(personService.save(person));
    }

    /**
     * 通过id查询
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public Result<Person> getById (@PathVariable("id") Integer id){
        return ResultUtil.success(personService.getById(id));
    }

    /**
     * 查询列表
     * @returng
     */
    @GetMapping(value = "/list")
    public Result<List<Person>> personList(){
        return ResultUtil.success(personService.getList());
    }


   /**
     * 更新
     * @return
     *  注意：put方式的请求body格式只能是x-www-form-urlencoded
     */
    @PutMapping(value = "/update")
    public Result<Person> personUpdate(@Valid Person person , BindingResult bindingResult){

        //如果发生错误，打印错误
        if(bindingResult.hasErrors()){
            return ResultUtil.error(ResultEnum.FAILED.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(personService.save(person));
    }

    /**
     * 通过id删除
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public Result personDelete (@PathVariable("id") Integer id){
        return  ResultUtil.success(personService.delete(id));
    }

    /**
     * 通过年龄查询列表
     */
    @GetMapping(value = "/get/age/{age}")
    public Result<List> getListByAge(@PathVariable("age") Integer age){
        return ResultUtil.success(personService.getListByAge(age));
    }



    @GetMapping(value = "/set/age/{id}")
    public Result<Person> setByAge(@PathVariable("id") Integer id) throws Exception{
        return ResultUtil.success(personService.setByAge(id));
    }



    @GetMapping(value = "get/max/id")
    public Result<Person> getPersonByMaxId() throws Exception{
        return ResultUtil.success(personJpaRepository.getPersonByMaxId());
    }

    @GetMapping(value = "get/nameAndAge")
    public Result<List> getByNameAndAge(@Param("name") String name,
                                        @Param("age") Integer age) throws Exception{
        return ResultUtil.success(personJpaRepository.getByNameAndAge2(name,age));
    }


  /*  @GetMapping(value = "get/msg")
    public Result<List> getByNameAndAge(@PathVariable("name") String name){
        return ResultUtil.success(personRepository.getByNameMsg(name));
    }*/

    @GetMapping(value = "get/count")
    public Result<List> getCount() throws Exception{
        return ResultUtil.success(personJpaRepository.getCount());
    }


    @PostMapping(value = "update/name")
    public Result<List> updateNameById(@Param("name") String name,
                                       @Param("id") Integer id) throws Exception{
        return ResultUtil.success(personService.updateNameById(id,name));
    }


  /*  @PostMapping(value = "delete/name")
    public Result<Object> deleteByName(@Param("name") String name) throws Exception{
        return ResultUtil.success(personService.deleteByName(name));
    }*/





}
