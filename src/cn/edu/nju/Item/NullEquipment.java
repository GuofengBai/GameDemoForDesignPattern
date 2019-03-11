package cn.edu.nju.Item;

import java.util.HashMap;
import java.util.Map;

public class NullEquipment implements Equipment {

    public NullEquipment() {
    }

    @Override
    public Map<String, Double> getAttributes() {
        return new HashMap<>();
    }

    @Override
    public Double getAttribute(String attrName) {
        return 0.0;
    }

    @Override
    public Integer getLevel() {
        return 0;
    }

    @Override
    public String getName() {
        return "无装备";
    }

    @Override
    public String getDesc() {
        return "无装备";
    }

    @Override
    public void levelUp() {

    }
}
