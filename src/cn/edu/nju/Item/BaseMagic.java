package cn.edu.nju.Item;

import java.util.Map;

public class BaseMagic implements Magic {

    private Map<String, Double> value;

    private String name;

    private Double consumedMp;

    public BaseMagic(Map<String, Double> value, String name, Double consumedMp) {
        this.value = value;
        this.name = name;
        this.consumedMp = consumedMp;
    }

    @Override
    public void add(Magic magic) {

    }

    @Override
    public void remove(Magic magic) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<String, Double> getValue() {
        return value;
    }

    @Override
    public Double getConsumedMp() {
        return consumedMp;
    }

}
