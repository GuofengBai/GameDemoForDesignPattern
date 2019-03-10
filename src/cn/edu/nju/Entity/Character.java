package cn.edu.nju.Entity;

public class Character {

    //人物本身属性
    private CharactorAttribute baseAttr;

    //经过装备加成后的属性
    private AttributeDecorator finalAttr;

    //角色职业类型
    private CharactorType charactorType;

    //满血、残血状态可能对攻击力、命中率等属性有影响
    private HpState hpState;

    private String name;

    private Integer level;

    private Double experience;
}
