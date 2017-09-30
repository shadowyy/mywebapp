package serializable;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shadowyy
 * @version 2017/9/17 11:51
 */
public class TestProtoStuff {

    public static void main(String[] args) throws Exception {
        Person person = new Person();
        person.setName("kaka");

        //RuntimeSchema<Person> schema = RuntimeSchema.createFrom(Person.class);
        //byte[] bytes = ProtostuffIOUtil.toByteArray(person, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));//序列化
        //Person person1 = schema.newMessage();// 空对象
        //ProtostuffIOUtil.mergeFrom(bytes, person1, schema);//反序列化
        //System.out.println("Hi, My name is " + person1.getName());


        byte[] bytes1=ProtostuffUtil.serializer(person);
        Person person2=ProtostuffUtil.deserializer(bytes1,Person.class);
    }

    /**
     * 序列化
     */
    public List<byte[]> serializeProtoStuffProductList(List<Product> pList) {
        if (pList == null || pList.size() <= 0) {
            return null;
        }
        long start = System.currentTimeMillis();
        List<byte[]> bytes = new ArrayList<byte[]>();
        Schema<Product> schema = RuntimeSchema.getSchema(Product.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(4096);
        byte[] protostuff = null;
        for (Product p : pList) {
            try {
                protostuff = ProtostuffIOUtil.toByteArray(p, schema, buffer);
                bytes.add(protostuff);
            } finally {
                buffer.clear();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return bytes;
    }

    ///**
    // * 反序列化
    // */
    //public List<Product> deserializeProtoBufDataListToProductList(List<byte[]> bytesList) {
    //    long start = System.currentTimeMillis();
    //    List<Product> list = new ArrayList<Product>();
    //    for (byte[] b : bytesList) {
    //        try {
    //            list.add(Products2.Product.parseFrom(b));
    //        } catch (InvalidProtocolBufferException e) {
    //            e.printStackTrace();
    //        }
    //    }
    //    long end = System.currentTimeMillis();
    //    System.out.println(end - start);
    //    return list;
    //}
}


class Product {
    private String name;
    private String val;

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
