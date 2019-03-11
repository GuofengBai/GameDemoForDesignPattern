package cn.edu.nju.Item;

import java.util.Map;

public class Armour implements Equipment {

    //存放防御类型与数值。def_chop,def_stab,def_crush,def_ice,def_flame,def_earth,def_lightning
    private Map<String,Double> attr;

    private Integer level;

    private String name = "无名装备";

    private String desc = "无名装备";

    public Armour() {
    }

    public Armour(Map<String, Double> attr, Integer level, String name, String desc) {
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
