package com.foochane.controller;

import com.foochane.domain.Girl;
import com.foochane.utils.Result;
import com.foochane.repository.GirlRepository;
import com.foochane.service.GirlService;
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
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    /**
     * 查询列表
     * @returng
     */
    @GetMapping(value = "/list")
    public List<Girl> girlList(){
        return girlRepository.findAll();
    }

    /**
     * 添加
     * @return
     */
    @PostMapping(value = "/add")
    public Result<Girl> girlAdd(@Valid Girl girl , BindingResult bindingResult){
        //发生错误，打印错误，并return null
        if(bindingResult.hasErrors()){
            /*System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return null;*/
            return ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(girlRepository.save(girl));
    }
    /*public Girl girlAdd(@RequestParam("cupSize") String cupSize,
                              @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }*/

    /**
     * 通过id查询
     * @return
     */
    @GetMapping(value = "/get/{id}")
    public Girl girlFindOne (@PathVariable("id") Integer id){
        return girlRepository.findOne(id);
    }

   /**
     * 通过id更新
     * @return
     *  注意：put方式的请求body格式只能是x-www-form-urlencoded
     */
    @PutMapping(value = "/update/{id}")
    public Girl update(@PathVariable("id") Integer id,
                             @RequestParam("cupSize") String cupSize,
                             @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setId(id);
        girl.setAge(age);
        girl.setCupSize(cupSize);
        return girlRepository.save(girl);
    }

    /**
     * 通过id删除
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    public void girlDelete (@PathVariable("id") Integer id){
        girlRepository.delete(id);
    }

    /**
     * 通过年龄查询列表
     */
    @GetMapping(value = "/get/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        return girlRepository.findByAge(age);
    }


    /**
     * 插入两条数据
     */
    @PostMapping(value = "/two")
    public void girlTwo(){
        girlService.insertTwo();
    }


    @GetMapping(value = "girls/getAge/{id}")
    public Result<Girl> getAge(@PathVariable("id") Integer id) throws Exception{
        //girlService.getAge(id);
        return ResultUtil.success(girlService.getAge(id));
    }

}
