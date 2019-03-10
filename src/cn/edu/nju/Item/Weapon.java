package cn.edu.nju.Item;

import java.util.Map;

public class Weapon implements Equipment{

    private Map<String,Double> attr;

    private Integer level;

    private String name = "无名装备";

    private String desc = "无名装备";

    public Weapon() {
    }

    public Weapon(Map<String, Double> attr, Integer level, String name, String desc) {
        this.attr = attr;
        this.level = level;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public Double getAttribute(String attrName) {

        return attr.getOrDefault(attrName,0.0);

    }

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public void levelUp() {

    }

}
