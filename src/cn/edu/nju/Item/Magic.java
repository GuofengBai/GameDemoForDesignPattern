package cn.edu.nju.Item;

import java.util.Map;

public interface Magic {

    void add(Magic magic);

    void remove(Magic magic);

    String getName();

    //各类型魔法的伤害值
    Map<String, Double> getValue();

    //消耗魔法值
    Double getConsumedMp();

    Integer getLevel();

    void levelUp();

}
