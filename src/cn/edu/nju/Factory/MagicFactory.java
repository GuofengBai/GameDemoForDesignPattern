package cn.edu.nju.Factory;

import cn.edu.nju.Item.BaseMagic;
import cn.edu.nju.Item.Magic;

import java.util.HashMap;
import java.util.Map;

public class MagicFactory {

    private static MagicFactory instance = new MagicFactory();

    private MagicFactory(){

    }

    public static MagicFactory getInstance(){
        return instance;
    }

    public Magic createBaseMagic(String type){
        return createBaseMagic(type,1);
    }

    public Magic createBaseMagic(String type, Integer level){
        Map<String, Double> value = new HashMap<>();
        value.put(type, 10.0);
        String name;
        if(type.equals("ice")){
            name="冰系魔法";
        }else if(type.equals("flame")){
            name="火系魔法";
        }else if(type.equals("earth")){
            name="土系魔法";
        }else{
            name="雷系魔法";
        }
        Magic magic = new BaseMagic(value,name,10.0);
        for(int i=1;i<level;i++){
            magic.levelUp();
        }
        return magic;
    }
}
