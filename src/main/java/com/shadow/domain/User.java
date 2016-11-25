package com.shadow.domain;

import com.google.common.base.Objects;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import static com.shadow.constant.UserConstant.*;

public class User {
    private int id;
    private String name;

    @Max(value = MAX_AGE, message = "年龄最大不能超过" + MAX_AGE + "岁")
    @Min(value = MIN_AGE, message = "年龄最小不能低于" + MIN_AGE + "岁")
    @Digits(integer = 3, fraction = 0, message = "年龄应该为整数")
    private int age;

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                age == user.age &&
                Objects.equal(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, age);
    }
}
