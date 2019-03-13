package cn.edu.nju.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompositeMagic implements Magic {

    private List<Magic> composite = new ArrayList<>();

    @Override
    public void add(Magic magic) {
        composite.add(magic);
    }

    @Override
    public void remove(Magic magic) {
        composite.remove(magic);
    }

    @Override
    public String getName() {
        String name="";
        for(Magic magic: composite){
            name+="+"+magic.getName();
        }
        name=name.substring(1);
        return name;
    }

    @Override
    public Map<String, Double> getValue() {
        Map<String, Double> res = new HashMap<>();
        Map<String, Double> tmp;
        for(Magic magic: composite){
            tmp=magic.getValue();
            for(String key: tmp.keySet()){
                res.put(key,res.getOrDefault(key,0.0)+tmp.get(key));
            }
        }
        return res;
    }

    @Override
    public Double getConsumedMp() {
        Double sum=0.0;
        for(Magic magic: composite){
            sum+=magic.getConsumedMp();
        }
        return sum*0.95;
    }

    @Override
    public Integer getLevel() {
        int sum=0;
        for(Magic magic: composite){
            sum+=magic.getLevel();
        }
        return sum;
    }

    @Override
    public void levelUp() {
        for(Magic magic: composite){
            magic.levelUp();
        }
    }

}
