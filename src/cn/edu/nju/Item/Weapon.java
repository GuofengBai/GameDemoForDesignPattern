package cn.edu.nju.Item;

import java.util.Map;

public class Weapon implements Equipment{

    //存放物理攻击类型与数值。chop,stab,crush
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
    public Map<String, Double> getAttributes() {
        return attr;
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
        level++;
        for(String key: attr.keySet()){
            attr.replace(key, getAttribute(key)*1.02);
        }
    }

}
