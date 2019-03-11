package cn.edu.nju.Entity;

import cn.edu.nju.Item.Equipment;
import cn.edu.nju.Item.NullEquipment;

public class Character {

    //人物本身属性
    private CharactorAttribute baseAttr;

    //经过装备加成后的属性
    private AttributeDecorator finalAttr;

    private Equipment weapon = new NullEquipment();

    private Equipment armour = new NullEquipment();

    //角色职业类型
    private CharactorType charactorType;

    //满血、残血状态可能对攻击力、命中率等属性有影响
    private HpState hpState = new NormalHpState();

    private String name;

    private Integer level;

    private Double experience;

    public Character(CharactorAttribute baseAttr, CharactorType charactorType){
        this.baseAttr=baseAttr;
        this.charactorType=charactorType;
        constructFinalAttr();
    }


    public Double getAttribute(String attrName){
        return hpState.getAttribute(attrName)*finalAttr.getAttribute(attrName);
    }

    public void constructFinalAttr(){
        finalAttr = new AttributeDecoratorImpl(baseAttr,weapon);
        finalAttr = new AttributeDecoratorImpl(finalAttr,armour);
    }

    public void setWeapon(Equipment weapon){
        this.weapon=weapon;
        constructFinalAttr();
    }

    public void setArmour(Equipment armour){
        this.armour=weapon;
        constructFinalAttr();
    }


}
