package design_pattern.builder;

/**
 * @author shadowyy
 * @version 2017/9/4 16:38
 */
public class TestBuilder {
    public static void main(String[] args) throws Exception {
        Director director = new Director(new ConcreteBuilder());

        Product product= director.construct();
        System.out.println(product.getPartA());
        System.out.println(product.getPartB());
        System.out.println(product.getPartC());
    }
}

/**
 * Builder：为创建一个产品对象的各个部件指定抽象接口
 */
abstract class Builder {
    //创建产品对象
    protected Product product = new Product();

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    //返回产品对象
    public Product getResult() {
        return product;
    }
}

class ConcreteBuilder extends Builder{
    @Override
    public void buildPartA() {
        product.setPartA("A");
        System.out.println("buildPartA");
    }

    @Override
    public void buildPartB() {
        product.setPartB("B");
        System.out.println("buildPartB");
    }

    @Override
    public void buildPartC() {
        product.setPartC("C");
        System.out.println("buildPartC");
    }
}

/**
 * 在指挥者类中可以注入一个抽象建造者类型的对象,构造一个使用Builder接口的对象，指导构建过程。
 */
class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    //产品构建与组装方法
    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}


class Product {
    private String partA;
    private String partB;
    private String partC;

    public String getPartA() {
        return partA;
    }

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public String getPartB() {
        return partB;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public String getPartC() {
        return partC;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }
}