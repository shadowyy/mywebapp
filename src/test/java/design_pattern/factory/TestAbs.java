package design_pattern.factory;

/**
 * 产品较多且有一定聚集关系
 *
 * @author shadowyy
 * @version 2017/9/4 16:12
 */
public class TestAbs {
    public static void main(String[] args) throws Exception {
    }
}

//发动机以及型号    
interface Engine {
}

class EngineA implements Engine {
    public EngineA() {
        System.out.println("制造-->EngineA");
    }
}

class EngineB implements Engine {
    public EngineB() {
        System.out.println("制造-->EngineB");
    }
}

//空调以及型号    
interface Aircondition {
}

class AirconditionA implements Aircondition {
    public AirconditionA() {
        System.out.println("制造-->AirconditionA");
    }
}

class AirconditionB implements Aircondition {
    public AirconditionB() {
        System.out.println("制造-->AirconditionB");
    }
}

//创建工厂的接口    
interface AbstractFactory {
    //制造发动机  
    Engine createEngine();

    //制造空调
    Aircondition createAircondition();
}

class FactoryBMW1 implements AbstractFactory {

    @Override
    public Engine createEngine() {
        return new EngineA();
    }

    @Override
    public Aircondition createAircondition() {
        return new AirconditionA();
    }
}

class FactoryBMW2 implements AbstractFactory {

    @Override
    public Engine createEngine() {
        return new EngineB();
    }

    @Override
    public Aircondition createAircondition() {
        return new AirconditionB();
    }
}