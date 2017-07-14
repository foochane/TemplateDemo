package com.foochane.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by fucheng on 2017/7/13.
 */

//把配置文件中带前缀为girl的属性映射过来，取到配置文件中的字段
@Component
@ConfigurationProperties(prefix = "girl")
public class PersonProperties {

    private String cupSize;

    private Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
