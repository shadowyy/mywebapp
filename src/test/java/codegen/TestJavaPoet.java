package codegen;

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
    private static String SERVICEIMPL = "ServiceImpl";
    private static String MAPPER = "Mapper";

    private static String PACKAGE = "com.shadow";
    private static String DAO = "dao";
    private static String COMMA = ".";

    private static String XX = "/";
    private static String NAME = "Users";
    private static String NAME_X = "users";

    public static void main(String[] args) throws Exception {
        controller();
        service();
        //JavaFile javaFile = JavaFile.builder("com.shadow.service.impl", TypeSpec.interfaceBuilder("HelloWorld").build()).build();
        //javaFile.writeTo(new File("D:\\projects\\mywebapp\\src\\main\\java"));
    }

    public static void controller() throws Exception {

        AnnotationSpec controller = AnnotationSpec.builder(Controller.class).build();
        AnnotationSpec classRequestMapping = AnnotationSpec.builder(RequestMapping.class).addMember("value", "$S", XX + NAME).build();
        AnnotationSpec methodRequestMapping = AnnotationSpec.builder(RequestMapping.class).addMember("value", "$S", XX + INDEX).build();

        //FieldSpec fieldSpec=FieldSpec.builder()

        MethodSpec index = MethodSpec.methodBuilder(INDEX).addAnnotation(methodRequestMapping).addModifiers(Modifier.PUBLIC).returns(String.class)
                .addCode("return \"" + INDEX + "\";").build();

        TypeSpec typeSpec = TypeSpec.classBuilder(NAME + SUFFIX_CONTROLLER).addModifiers(Modifier.PUBLIC).addAnnotation(controller).addAnnotation
                (classRequestMapping).addMethod(index).build();

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
        String mapperName=NAME_X+ MAPPER;

        Class clazz = Class.forName(PACKAGE + COMMA + DAO + COMMA + NAME+MAPPER);

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
                //CodeBlock.of("return "+mapperName+COMMA+method.getName()+"();",).toBuilder().build();
                names.add(variableName);
            }

            MethodSpec index = MethodSpec.methodBuilder(method.getName())
                    //.addModifiers(Modifier.valueOf(java.lang.reflect.Modifier.toString(method.getModifiers())))
                    .addModifiers(Modifier.PUBLIC)
                    .addParameters(list1)
                    //.addJavadoc("XXX")
                    .returns(method.getGenericReturnType())
                    //.returns(ParameterizedTypeName.get( NAME+MAPPER, ClassName.get(model.originatingElement()), TypeVariableName.get("?")))
                    //.addCode(codeBuilder.build())
                    .addCode("return "+mapperName+COMMA+method.getName()+"("+ Joiner.on(",").join(names)+");")
                    .build();

            list.add(index);
        }

        //TypeVariableName typeVariableName = TypeVariableName.get("T", clazz);
        //TypeSpec t = TypeSpec.classBuilder(NAME + MAPPER).addTypeVariable(typeVariableName).build();

        FieldSpec fieldSpec= FieldSpec.builder(clazz,mapperName,Modifier.PRIVATE).addAnnotation(resource).build();

        TypeSpec typeSpec = TypeSpec.classBuilder(NAME + SERVICEIMPL).addModifiers(Modifier.PUBLIC).addField(fieldSpec).addAnnotation(service).addMethods(list).build();

        JavaFile javaFile = JavaFile.builder("com.shadow.service.impl", typeSpec).build();
        javaFile.writeTo(new File("D:\\projects\\mywebapp\\src\\main\\java"));

    }

    public static void test3() throws Exception {

    }


}
