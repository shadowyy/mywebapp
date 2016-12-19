package mywebapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.shadow.domain.Title;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by alice on 2016/11/29 17:59
 */
public class TestVal {

    public static void main(String[] args) throws Exception {
        //全部读取
        List<Title> titles = JSON.parseArray(readToString("/config/title.json"), Title.class);
        Map<String, String> data = JSON.parseObject(readToString("/config/data.json"), new TypeReference<Map<String, String>>() {
        });

        List<String> msgList = new ArrayList<>();
        List<String> req = new ArrayList<>();
        for (Title t : titles) {
            String title = t.getTitle();
            String value = data.get(title);
            int i = t.getType();
            String constraint = t.getConstraint();

            if (t.isNecessary()) {
                if (StringUtils.isBlank(value)) {
                    msgList.add(t.getTitle() + "不能为空");
                    continue;
                }
            }

            String tmp = "";
            int max, min;
            switch (i) {
                case 1://固定下拉限制
                    List<String> c = Arrays.asList(constraint.split(","));
                    if (!c.contains(value)) {
                        msgList.add(title + "下拉选项错误");
                        continue;
                    }
                    break;
                case 2://字符串长度限制
                    max = Integer.parseInt(constraint);
                    if (value.length() > max) {
                        msgList.add(title + "超出" + max + "个字符限制");
                        continue;
                    }
                    break;
                case 3://数字范围限制
                    String[] range = constraint.split(",");
                    min = Integer.parseInt(range[0]);
                    max = Integer.parseInt(range[1]);
                    int val = Integer.parseInt(value);
                    if (val > max || val < min) {
                        msgList.add(title + tmp + "数字在" + min + "~" + max + "范围内");
                        continue;
                    }
                    break;
                case 4://正则约束限制
                    if (!value.matches(constraint)) {
                        msgList.add(tmp + title + "格式不正确");
                        continue;
                        }
                    break;
//                case 5://关联下拉约束
//                    if (!value.matches(constraint)) {
//                        msgList.add(tmp + title + "格式不正确");
//                        continue;
//                    }
//                    break;
                case 6://特殊的
                    Class clazz = getClassForStatic();
                    Method method = clazz.getDeclaredMethod(value, String.class);
                    boolean b = (boolean) method.invoke(clazz.newInstance(), "");
                    if (!b){
                        msgList.add(tmp + title + "错误");
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("msgList" + msgList);
        System.out.println("req" + req);
    }

    private static boolean haha(String s) {
        return true;
    }

    private static final Class getClassForStatic() {
        return new Object() {
            public Class getClassForStatic() {
                return this.getClass();
            }
        }.getClassForStatic();
    }

    /**
     * 读取文件全部内容
     *
     * @param filePath
     * @return
     */
    public static String readToString(String filePath) {
        String fileName = getClassForStatic().getResource(filePath).getPath();
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        FileInputStream in=null;
        try {
            in = new FileInputStream(file);
            in.read(filecontent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    /**
     *使用fastjson逐行读取
     */
//    public static  List<T> readFile<T>(String filePath) {
//        List<T> list = new ArrayList<>();
//        try {
//            JSONReader reader = new JSONReader(new FileReader(new File(filePath)));
//            reader.startArray();
//            while (reader.hasNext()) {
//                Title t = JSON.parseObject(reader.readString(), Title.class);
//                list.add(t);
//            }
//            reader.endArray();
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }

}

class Area {
    private String id;
    private String name;
    private List<Server> servers = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
}

class Server {
    private String id;
    private String name;
    private Area area;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
