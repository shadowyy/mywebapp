package codegen.javapoet;

import com.google.common.base.Joiner;
import com.shadow.util.string.StringUtil;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowyy
 * @version 2017/8/22 10:52
 */
public class TestJavaPoet {
    private static String INDEX = "index";
    private static String SUFFIX_CONTROLLER = "Controller";
    private static String PREFIX_SERVICE = "I";
    private static String SUFFIX_SERVICE = "Service";
    private static String SUFFIX_SERVICE_IMPL = "ServiceImpl";
    private static String SUFFIX_MAPPER = "Mapper";

    private static String PACKAGE = "com.shadow";
    private static String DAO = "dao";
    private static String SERVICE = "service";
    private static String SERVICE_IMPL = "service.impl";
    private static String POINT = ".";

    private static String XX = "/";
    private static String NAME = "Users";
    private static String NAME_X = "users";

    public static void main(String[] args) throws Exception {
        intf();
        //service();
        //controller();
    }

    public static void intf() throws Exception {
        TypeSpec.Builder tBuilder= TypeSpec.interfaceBuilder(PREFIX_SERVICE+NAME+SUFFIX_SERVICE);
        //  通过dao接口
        Class clazz = Class.forName(PACKAGE + POINT + DAO + POINT + NAME+ SUFFIX_MAPPER);
        List<MethodSpec> methodSpecs=new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            String methodName=method.getName();

            List<ParameterSpec> parameters=new ArrayList<>();//method parameters
            List<String> names=new ArrayList<>();
            for (Type gType : genericParameterTypes) {
                String variableName=StringUtil.lowerFirstLetter(((Class) gType).getSimpleName());//variable name
                parameters.add(ParameterSpec.builder(gType, variableName).build());
                names.add(variableName);
            }

            MethodSpec methodSpec = MethodSpec.methodBuilder(methodName)
                    .addModifiers(Modifier.PUBLIC,Modifier.ABSTRACT)
                    .addParameters(parameters)
                    .returns(method.getGenericReturnType())
                    .build();

            methodSpecs.add(methodSpec);
        }

        TypeSpec typeSpec = tBuilder.addModifiers(Modifier.PUBLIC).addMethods(methodSpecs).build();
        JavaFile javaFile = JavaFile.builder("com.shadow.service", typeSpec).build();
        javaFile.writeTo(new File("D:\\projects\\mywebapp\\src\\main\\java"));
    }

    public static void controller() throws Exception {
        AnnotationSpec controller = AnnotationSpec.builder(Controller.class).build();
        AnnotationSpec classRequestMapping = AnnotationSpec.builder(RequestMapping.class).addMember("value", "$S", XX + NAME).build();
        AnnotationSpec methodRequestMapping = AnnotationSpec.builder(RequestMapping.class).addMember("value", "$S", XX + INDEX).build();
        String className=NAME+ SUFFIX_CONTROLLER;// class name
        String varName=NAME_X+ SUFFIX_SERVICE_IMPL;// variable name

        //  通过dao接口
        Class clazz = Class.forName(PACKAGE + POINT + DAO + POINT + NAME+ SUFFIX_MAPPER);
        List<MethodSpec> methodSpecs=new ArrayList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            String methodName=method.getName();

            List<ParameterSpec> parameters=new ArrayList<>();//method parameters
            List<String> names=new ArrayList<>();
            for (Type gType : genericParameterTypes) {
                String variableName=StringUtil.lowerFirstLetter(((Class) gType).getSimpleName());//variable name
                parameters.add(ParameterSpec.builder(gType, variableName).build());
                names.add(variableName);
            }

            MethodSpec methodSpec = MethodSpec.methodBuilder(methodName)
                    .addModifiers(Modifier.PUBLIC)
                    .addParameters(parameters)
                    .addAnnotation(AnnotationSpec.builder(RequestMapping.class).addMember("value", "$S", XX + methodName).build())
                    .addAnnotation(ResponseBody.class)
                    .returns(method.getGenericReturnType())
                    //.returns(ParameterizedTypeName.get( NAME+SUFFIX_MAPPER, ClassName.get(model.originatingElement()), TypeVariableName.get("?")))
                    //.addCode(codeBuilder.build())
                    .addCode("return "+varName+ POINT +methodName+"("+ Joiner.on(",").join(names)+");")
                    .build();

            methodSpecs.add(methodSpec);
        }

        Class clz= Class.forName(PACKAGE + POINT + SERVICE+ POINT +PREFIX_SERVICE+ NAME+ SUFFIX_SERVICE);
        FieldSpec fieldSpec= FieldSpec.builder(clz,varName,Modifier.PRIVATE).addAnnotation(Resource.class).build();

        MethodSpec index = MethodSpec.methodBuilder(INDEX).addAnnotation(methodRequestMapping).addModifiers(Modifier.PUBLIC).returns(String.class)
                .addCode("return \"" + INDEX + "\";").build();

        TypeSpec typeSpec = TypeSpec.classBuilder(className).addModifiers(Modifier.PUBLIC).addAnnotation(controller).addAnnotation
                (classRequestMapping).addField(fieldSpec).addMethod(index).addMethods(methodSpecs).build();

        JavaFile javaFile = JavaFile.builder("com.shadow.controller", typeSpec).build();
        try {
            javaFile.writeTo(new File("D:\\projects\\mywebapp\\src\\main\\java"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void service() throws Exception {
        AnnotationSpec service = AnnotationSpec.builder(Service.class).build();
        AnnotationSpec resource = AnnotationSpec.builder(Resource.class).build();
        String mapperName=NAME_X+ SUFFIX_MAPPER;

        Class clazz = Class.forName(PACKAGE + POINT + DAO + POINT + NAME+ SUFFIX_MAPPER);

        List<MethodSpec> list=new ArrayList<>();

        for (Method method : clazz.getDeclaredMethods()) {
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            //CodeBlock.Builder codeBuilder = CodeBlock.builder();

            List<ParameterSpec> list1=new ArrayList<>();
            List<String> names=new ArrayList<>();
            for (Type gType : genericParameterTypes) {
                String variableName=StringUtil.lowerFirstLetter(((Class) gType).getSimpleName());
                ParameterSpec parameterSpec=  ParameterSpec.builder(gType, variableName).build();
                list1.add(parameterSpec);
                //CodeBlock.of("return "+mapperName+POINT+method.getName()+"();",).toBuilder().build();
                names.add(variableName);
            }

            MethodSpec index = MethodSpec.methodBuilder(method.getName())
                    //.addModifiers(Modifier.valueOf(java.lang.reflect.Modifier.toString(method.getModifiers())))
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .addParameters(list1)
                    //.addJavadoc("XXX")
                    .returns(method.getGenericReturnType())
                    //.returns(ParameterizedTypeName.get( NAME+SUFFIX_MAPPER, ClassName.get(model.originatingElement()), TypeVariableName.get("?")))
                    //.addCode(codeBuilder.build())
                    .addCode("return "+mapperName+ POINT +method.getName()+"("+ Joiner.on(",").join(names)+");")
                    .build();

            list.add(index);
        }

        //TypeVariableName typeVariableName = TypeVariableName.get("T", clazz);
        //TypeSpec t = TypeSpec.classBuilder(NAME + SUFFIX_MAPPER).addTypeVariable(typeVariableName).build();

        FieldSpec fieldSpec= FieldSpec.builder(clazz,mapperName,Modifier.PRIVATE).addAnnotation(resource).build();

        Class clz= Class.forName(PACKAGE + POINT + SERVICE+ POINT +PREFIX_SERVICE+ NAME+ SUFFIX_SERVICE);

        TypeSpec typeSpec = TypeSpec.classBuilder(NAME + SUFFIX_SERVICE_IMPL).addModifiers(Modifier.PUBLIC).addField(fieldSpec)
                .addAnnotation(service).addSuperinterface(clz).addMethods(list).build();

        JavaFile javaFile = JavaFile.builder(PACKAGE+ POINT +SERVICE_IMPL, typeSpec).build();
        javaFile.writeTo(new File("D:\\projects\\mywebapp\\src\\main\\java"));

    }

    public static void test3() throws Exception {

    }


}
