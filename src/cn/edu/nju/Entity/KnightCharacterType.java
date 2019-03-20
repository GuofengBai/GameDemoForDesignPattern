package cn.edu.nju.Entity;

import cn.edu.nju.Item.Magic;

import java.util.Map;

public class KnightCharacterType implements CharacterType {

    private Character owner;

    public KnightCharacterType(Character owner) {
        this.owner = owner;
    }

    @Override
    public String getType() {
        return "knight";
    }

    @Override
    public Integer levelUp() {
        Double cost = 10*Math.pow(1.2, owner.getLevel());
        if(cost<owner.getExperience()){
            owner.setLevel(owner.getLevel()+1);
            CharacterAttribute baseAttr = owner.getBaseAttr();
            baseAttr.evenlyLevelUp();
            baseAttr.setFullHp(baseAttr.getFullHp()*1.047619047619048);
        }
        return owner.getLevel();
    }

    @Override
    public Double weaponAttack(Character enemy) {
        return valueAttack(owner.getWeaponHurts(),enemy);
    }

    @Override
    public Double magicAttack(Magic magic, Character enemy) {
        Double nmp = enemy.getAttribute("mp")-magic.getConsumedMp();
        if(nmp<0){
            return -2.0;
        }else{
            enemy.setMp(nmp);
            return valueAttack(magic.getValue(),enemy);
        }
    }

    @Override
    public Double valueAttack(Map<String, Double> hurts, Character enemy) {
        //如果命中
        if(Math.random()<owner.getAttribute("accuracy")){
            //职业克制影响伤害
            Double restriction;
            if(owner.getType().equals("assassin")){
                restriction=1.1;
            }else if(owner.getType().equals("knight")){
                restriction=1.0;
            }else{
                restriction=0.9;
            }

            Double totalHurt = 0.0;
            for(Map.Entry<String, Double> entry : hurts.entrySet()){
                Double hurt = entry.getValue()*restriction-enemy.getAttribute("def_"+entry.getKey());
                if(hurt>0){
                    enemy.setHp(enemy.getAttribute("hp")-hurt);
                    totalHurt+=hurt;
                }
            }
            return totalHurt;
        }else{
            return -1.0;
        }
    }

    @Override
    public Integer typeRestriction(CharacterType characterType) {
        if(characterType instanceof AssassinCharacterType){
            return 1;
        }else if(characterType instanceof KnightCharacterType){
            return 0;
        }else{
            return -1;
        }
    }
}
