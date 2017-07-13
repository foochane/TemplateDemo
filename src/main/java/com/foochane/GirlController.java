package com.foochane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param cupSize
     * @param age
     * @return
     */
    @PostMapping(value = "/add")
    public Girl girlAdd(@RequestParam("cupSize") String cupSize,
                              @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setCupSize(cupSize);
        girl.setAge(age);
        return girlRepository.save(girl);
    }

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



}
