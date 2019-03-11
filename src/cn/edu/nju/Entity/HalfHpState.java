package cn.edu.nju.Entity;

public class HalfHpState implements HpState {

    @Override
    public Double getAttribute(String attrName) {

        switch (attrName){
            case "attack":
                return 0.9;
            case "accuracy":
                return 0.9;
            default:
                return 1.0;
        }

    }

}
