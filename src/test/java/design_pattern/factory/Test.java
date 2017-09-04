package design_pattern.factory;

/**
 * @author shadowyy
 * @version 2017/9/4 16:05
 */
public class Test {
    public static void main(String[] args) throws Exception {
        FactoryBMW factoryBMW= new FactoryBMW320();
        factoryBMW.createBMW();
    }

}

//产品类
abstract class BMW {
    public BMW(){

    }
}
class BMW320 extends BMW {
    public BMW320() {
        System.out.println("制造-->BMW320");
    }
}
class BMW523 extends BMW{
    public BMW523(){
        System.out.println("制造-->BMW523");
    }
}


// 工厂类模式（缺点：产品较多的时候会产生大量的工厂类）
interface FactoryBMW {
    BMW createBMW();
}

class FactoryBMW320 implements FactoryBMW{
    @Override
    public BMW320 createBMW() {
        return new BMW320();
    }

}
class FactoryBMW523 implements FactoryBMW {
    @Override
    public BMW523 createBMW() {
        return new BMW523();
    }
}