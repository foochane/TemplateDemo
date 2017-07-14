package com.foochane.controller;

import com.foochane.properties.PersonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by fucheng on 2017/7/13.
 */
//@RestController 等同意@Controller + @ResponseBody
@RestController
public class HelloController {

    @Autowired
    private PersonProperties personProperties;

    @RequestMapping(value = {"say/{id}"},method = RequestMethod.GET)
    public String say(@RequestParam(value = "id",required = false,defaultValue="0") Integer id){
        //return personProperties.getCupSize();
        //return "index";
        return "id:"+id;
    }


}
