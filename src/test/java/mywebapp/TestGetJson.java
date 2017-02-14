package mywebapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.shadow.domain.TitleMapping;
import com.shadow.domain.TitlePO;
import com.shadow.utils.ReadFileUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * Created by alice on 2016/12/2 19:54
 */
public class TestGetJson {

    public static void main(String[] args) throws Exception {
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("spring/mybatis-config.xml"));
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        AreaServerDao areaServerDao = sqlSession.getMapper(AreaServerDao.class);
//        List<TitlePO> set = areaServerDao.getInfo("G603");

        String game = "G479.json";
        //数据库表数据
        List<TitlePO> titlePOList = JSON.parseObject(ReadFileUtil.toString("/config/titlePO/" + game), new TypeReference<List<TitlePO>>() {
        });
        //映射规则
        Map<String, TitleMapping> titleMappingMap = JSON.parseObject(ReadFileUtil.toString("/config/titleMapping/" + game), new TypeReference<LinkedHashMap<String, TitleMapping>>() {
        });
        //excel数据
        Map<String, String> excelDataList = JSON.parseObject(ReadFileUtil.toString("/config/excelData/" + game), new TypeReference<LinkedHashMap<String, String>>() {
        });

        for (TitlePO t : titlePOList) {
            String ename = t.getEname();
            Long ishaveto = t.getIshaveto();
            String evalue = t.getEvalue();
            Long gieid = t.getGieid();

            //通过ename得到对象
            TitleMapping titleMapping = titleMappingMap.get(ename);
            if (titleMapping != null) {
                titleMapping.setGieid(gieid);

                if (ishaveto == 0) {
                    titleMapping.setNecessary(false);
                } else if (ishaveto == 1) {
                    titleMapping.setNecessary(true);
                }
                int mappingType = titleMapping.getMappingType();
                if (mappingType == 1) {
                    titleMapping.setConstraint(evalue);
                }
            }
        }
        ArrayList<TitleMapping> titleMapping = new ArrayList<>(titleMappingMap.values());
        ListIterator<TitleMapping> it = titleMapping.listIterator();
        while (it.hasNext()) {//CNM NC数据结构
            TitleMapping t = it.next();
            if (t.getGieid() == null) {
                if (it.hasPrevious()) {
                    t.setGieid(titleMapping.get(it.previousIndex() - 1).getGieid());
                }
            }
        }

        List<String> msgList = new ArrayList<>();
        List<String> req = new ArrayList<>();
        for (TitleMapping t : titleMapping) {//
            String title = t.getTitle();
            String value = excelDataList.get(title);
            int type = t.getType();
            String constraint = t.getConstraint();

            if (t.isNecessary()) {
                if (StringUtils.isBlank(value)) {
                    msgList.add(title + "不能为空");
                    continue;
                }
            }

            String tmp = "";
            int max, min, val;
            String[] range = null;
            switch (type) {
                case 1://固定下拉限制
                    List<String> c = Arrays.asList(constraint.split(","));
                    if (!c.contains(value)) {
                        msgList.add(title + "无此下拉选项");
                        continue;
                    }
                    break;
                case 2://字符串长度限制
                    range = constraint.split(",");
                    min = Integer.parseInt(range[0]);
                    max = Integer.parseInt(range[1]);
                    if (value != null) {
                        val = value.length();
                        if (val > max || val < min) {
                            msgList.add(title + "字符限制在" + min + "~" + max + "范围内");
                        }
                    }
                    break;
                case 3://数字范围限制
                    range = constraint.split(",");
                    min = Integer.parseInt(range[0]);
                    max = Integer.parseInt(range[1]);
                    Integer num = null;
                    num = NumberUtils.createInteger(value);//null值则会返回null，不会报空指针
                    if (num != null) {
                        if (num > max || num < min) {
                            msgList.add(title + tmp + "数字在" + min + "~" + max + "范围内");
                            continue;
                        }
                    }
                    break;
                case 4://正则约束限制
                    if (value != null) {
                        if (!value.matches(constraint)) {
                            msgList.add(tmp + title + "格式不正确");
                            continue;
                        }
                    }
                    break;
                case 5://特殊方法校验
//                    Class clazz = getClassForStatic();
//                    Method method = clazz.getDeclaredMethod(value, String.class);
//                    boolean b = (boolean) method.invoke(clazz.newInstance(), "");
//                    if (!b) {
//                        msgList.addObserver(tmp + title + "错误");
//                    }
                    break;
                case 6://使用;和,分隔的限制，如职业
                    range = constraint.split(";");
                    List<String> male = Arrays.asList(range[0].split(","));
                    List<String> female = Arrays.asList(range[1].split(","));
                    String sex = excelDataList.get("性别");
                    if ("男".equals(sex) && !male.contains(value)) {
                        msgList.add(tmp + "男性没有" + value + "职业");
                    } else if ("女".equals(sex) && !female.contains(value)) {
                        msgList.add(tmp + "女性没有" + value + "职业");
                    }
                    break;
//                case 7://关联约束 必填项约束
//                    break;
                default:
                    break;
            }
        }
        System.out.println("msgList" + msgList);
        System.out.println("req" + req);
    }


    private static final Class getClassForStatic() {
        return new Object() {
            public Class getClassForStatic() {
                return this.getClass();
            }
        }.getClassForStatic();
    }

}
