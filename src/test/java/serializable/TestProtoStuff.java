package serializable;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

import java.io.Serializable;

/**
 * @author shadowyy
 * @version 2017/9/17 11:51
 */
public class TestProtoStuff {

    public static void main(String[] args) throws Exception {
        RuntimeSchema<Person> schema = RuntimeSchema.createFrom(Person.class);
        Person person = new Person();
        person.setName("kaka");
        //参数三缓冲器
        byte[] bytes = ProtostuffIOUtil.toByteArray(person, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        //反序列化
        // 空对象
        Person person1 = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, person1, schema);
        System.out.println("Hi, My name is " + person1.getName());
    }
}


class Person implements Serializable {
    private static final long serialVersionUID = -763618247875550322L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
