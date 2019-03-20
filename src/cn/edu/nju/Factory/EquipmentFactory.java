package cn.edu.nju.Factory;

import cn.edu.nju.Item.Armour;
import cn.edu.nju.Item.Equipment;
import cn.edu.nju.Item.Weapon;

import java.util.HashMap;
import java.util.Map;

public class EquipmentFactory {

    private static EquipmentFactory instance = new EquipmentFactory();

    private EquipmentFactory(){

    }

    public static EquipmentFactory getInstance(){
        return instance;
    }

    public Equipment createWeapon(Integer level){
        Map<String, Double> value = new HashMap<>();
        String[] wattacks = new String[]{"chop","stab","crush"};
        for(String wattck:wattacks){
            if(Math.random()<0.5){
                value.put(wattck, Math.random()*10+10);
            }
        }
        Weapon weapon = new Weapon(value,1,"随机武器","随机武器，全凭运气");
        for(int i=1;i<level;i++){
            weapon.levelUp();
        }
        return weapon;
    }

    public Equipment createArmour(Integer level){
        Map<String, Double> value = new HashMap<>();
        String[] defences = new String[]{"def_chop","def_stab","def_crush","def_ice","def_flame","def_earth","def_lightning"};
        for(String defence:defences){
            if(Math.random()<0.5){
                value.put(defence, Math.random()*5+5);
            }
        }
        Armour armour = new Armour(value,1,"随机装备","随机装备，全凭运气");
        for(int i=1;i<level;i++){
            armour.levelUp();
        }
        return armour;
    }

}
