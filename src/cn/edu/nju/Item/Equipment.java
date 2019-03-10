package cn.edu.nju.Item;

public interface Equipment {

    Double getAttribute(String attrName);

    Integer getLevel();

    String getName();

    String getDesc();

    void levelUp();

}
