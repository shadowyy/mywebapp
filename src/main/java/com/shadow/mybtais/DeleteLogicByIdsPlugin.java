package com.shadow.mybtais;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * @author shadowyy
 * @version 2017/8/21 11:57
 */
public class DeleteLogicByIdsPlugin extends PluginAdapter {
    /**
     * {@inheritDoc}
     */
    public boolean validate(List<String> warnings) {
        return true;
    }


    /**
     * Java Client Methods 生成
     * 具体执行顺序 http://www.mybatis.org/generator/reference/pluggingIn.html
     * @param interfaze
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //topLevelClass.addMethod(generateDeleteLogicByIds(method,  introspectedTable));
        return true;
    }


    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名

        XmlElement parentElement = document.getRootElement();

        // 产生分页语句前半部分
        XmlElement deleteLogicByIdsElement = new XmlElement("update");
        deleteLogicByIdsElement.addAttribute(new Attribute("id", "deleteLogicByIds"));

        deleteLogicByIdsElement.addElement(
                new TextElement(
                        "update " + tableName + " set deleteFlag = #{deleteFlag,jdbcType=INTEGER} where id in "
                                + " <foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach> "
                ));

        parentElement.addElement(deleteLogicByIdsElement);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private Method generateDeleteLogicByIds(Method method, IntrospectedTable introspectedTable) {
        Method m = new Method("deleteLogicByIds");

        m.setVisibility(method.getVisibility());

        m.setReturnType(FullyQualifiedJavaType.getIntInstance());

        m.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "deleteFlag", "@Param(\"deleteFlag\")"));
        m.addParameter(new Parameter(new FullyQualifiedJavaType("Integer[]"), "ids", "@Param(\"ids\")"));

        context.getCommentGenerator().addGeneralMethodComment(m,
                introspectedTable);
        return m;
    }
}
