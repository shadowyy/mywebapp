package com.shadow.domain;

/**
 * Created by alice on 2016/12/2 20:34
 */
public class Title {

    private int index;//输出顺序
    private String title;//标题
    private boolean necessary;//是否非空 true为必填不能为空 false可以为空
    private int type;//验证类型 0.特殊处理 1.固定下拉限制 2.字符串长度限制 3.数字范围限制 4.正则约束限制 5.关联限制
    private String constraint;//约束
    private Long gieid;//属性ID

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isNecessary() {
        return necessary;
    }

    public void setNecessary(boolean necessary) {
        this.necessary = necessary;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public Long getGieid() {
        return gieid;
    }

    public void setGieid(Long gieid) {
        this.gieid = gieid;
    }
}
