package com.shadow.domain;

import java.util.List;

public class CellTemplate {
    private String name;//数据库ename的值
    private String title;//展示的标题，不存在则使用name
    private Integer mappingType;//0,不使用数据库字段值.1,使用evalue作为rule
    private boolean notNull;//非空
    private String type;//校验类型:select,bool,range,regx,checker
    private String rule;//规则,与type字段组合使用
    private Integer fieldType = 1;//1:普通字段,2:附加信息,3:垂直属性
    private String key;//根据fieldType来，垂直属性为数字
    private boolean show;//前端展示
    private boolean multi;//拥有多个属性
    private List<String> attrs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getMappingType() {
        return mappingType;
    }

    public void setMappingType(Integer mappingType) {
        this.mappingType = mappingType;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public Integer getFieldType() {
        return fieldType;
    }

    public void setFieldType(Integer fieldType) {
        this.fieldType = fieldType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isMulti() {
        return multi;
    }

    public void setMulti(boolean multi) {
        this.multi = multi;
    }

    public List<String> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<String> attrs) {
        this.attrs = attrs;
    }
}
