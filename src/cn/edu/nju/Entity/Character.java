package cn.edu.nju.Entity;

import cn.edu.nju.Item.Equipment;
import cn.edu.nju.Item.Magic;
import cn.edu.nju.Item.NullEquipment;

import java.util.*;

public class Character {

    //人物本身属性
    private CharacterAttribute baseAttr;

    //经过装备加成后的属性
    private AttributeDecorator finalAttr;

    private Equipment weapon = new NullEquipment();

    private Equipment armour = new NullEquipment();

    //角色职业类型
    private CharacterType characterType;

    //满血、残血状态可能对攻击力、命中率等属性有影响
    private HpState hpState = new NormalHpState();

    private String name;

    private Integer level = 1;

    private Double experience;

    private Double money = 100.0;

    private List<Magic> magics = new ArrayList<>();

    public Character(CharacterAttribute baseAttr){
        this.baseAttr=baseAttr;
        constructFinalAttr();
    }

    public void setCharacterType(CharacterType characterType){
        this.characterType=characterType;
    }


    public Double getAttribute(String attrName){
        return hpState.getAttribute(attrName)*finalAttr.getAttribute(attrName);
    }

    public void constructFinalAttr(){
        finalAttr = new AttributeDecoratorImpl(baseAttr,weapon);
        finalAttr = new AttributeDecoratorImpl(finalAttr,armour);
    }

    public Map<String, Double> getWeaponHurts(){
        Map<String, Double> res = new HashMap<>();
        for(Map.Entry<String, Double> entry:weapon.getAttributes().entrySet()){
            res.put(entry.getKey(),entry.getValue()+getAttribute("ap"));
        }
        return res;
    }

    public Equipment getWeapon() {
        return weapon;
    }

    public Equipment getArmour() {
        return armour;
    }

    public void setWeapon(Equipment weapon){
        this.weapon=weapon;
        constructFinalAttr();
    }

    public void setArmour(Equipment armour){
        this.armour=weapon;
        constructFinalAttr();
    }

    public Double weaponAttack(Character enemy){
        return characterType.weaponAttack(enemy);
    }

    public Double magicAttack(Magic magic, Character enemy){
        return characterType.magicAttack(magic, enemy);
    }

    public CharacterAttribute getBaseAttr() {
        return baseAttr;
    }

    public String getType(){
        return characterType.getType();
    }

    public CharacterType getCharacterType(){
        return characterType;
    }

    public void setBaseAttr(CharacterAttribute baseAttr) {
        this.baseAttr = baseAttr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    public void setHp(Double hp){
        baseAttr.setHp(hp);
        if(hp/getAttribute("fullHp")<0.5){
            if(hpState instanceof NormalHpState){
                hpState = new HalfHpState();
            }
        }else{
            if(hpState instanceof HalfHpState){
                hpState = new NormalHpState();
            }
        }
    }

    public void setMp(Double mp){
        baseAttr.setMp(mp);
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer typeRestriction(Character enemy){
        return characterType.typeRestriction(enemy.getCharacterType());
    }

    public Integer levelUp(){
        return characterType.levelUp();
    }

    public Iterator<Magic> getMagics(){
        return magics.iterator();
    }

    public void addMagic(Magic magic){
        magics.add(magic);
    }

}
