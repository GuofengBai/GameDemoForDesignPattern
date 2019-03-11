package cn.edu.nju.Item;

import java.util.Map;

public interface Equipment {

    Map<String,Double> getAttributes();

    Double getAttribute(String attrName);

    Integer getLevel();

    String getName();

    String getDesc();

    void levelUp();

}
