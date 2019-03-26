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
        this.armour=armour;
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

    public Boolean canLevelUp(){
        return 10*Math.pow(1.2, getLevel())<getExperience();
    }

    public Iterator<Magic> getMagics(){
        return magics.iterator();
    }

    public void addMagic(Magic magic){
        magics.add(magic);
    }

    public Map<String,Double> getDecoratedAttributes(){
        Map<String, Double> attr = new HashMap<>();
        attr.put("def_chop",getAttribute("def_chop"));
        attr.put("def_stab",getAttribute("def_stab"));
        attr.put("def_crush",getAttribute("def_crush"));
        attr.put("def_ice",getAttribute("def_crush"));
        attr.put("def_flame",getAttribute("def_flame"));
        attr.put("def_earth",getAttribute("def_earth"));
        attr.put("def_lightning",getAttribute("def_lightning"));
        attr.put("accuracy",getAttribute("accuracy"));
        return attr;
    }
    public List<TableVO> getDecoratedAttributesView(){
        List<TableVO> attr = new ArrayList<>();

        attr.add(new TableVO("防御力——劈砍攻击",(double)Math.round(getAttribute("def_chop") * 100) / 100));

        attr.add(new TableVO("防御力——刺击攻击",(double)Math.round(getAttribute("def_stab") * 100) / 100));

        attr.add(new TableVO("防御力——钝击攻击",(double)Math.round(getAttribute("def_crush") * 100) / 100));

        attr.add(new TableVO("防御力——冰属性魔法",(double)Math.round(getAttribute("def_ice") * 100) / 100));

        attr.add(new TableVO("防御力——火属性魔法",(double)Math.round(getAttribute("def_flame") * 100) / 100));

        attr.add(new TableVO("防御力——土属性魔法",(double)Math.round(getAttribute("def_earth") * 100) / 100));

        attr.add(new TableVO("防御力——雷属性魔法",(double)Math.round(getAttribute("def_lightning") * 100) / 100));
        attr.add(new TableVO("命中率",(double)Math.round(getAttribute("accuracy") * 100) / 100));

        return attr;
    }

    public boolean canWeaponLevelUp(){
        return getWeapon().getLevel()*10<getMoney();
    }

    public boolean weaponLevelUp(){
        if(canWeaponLevelUp()){
            setMoney(getMoney()-getWeapon().getLevel()*10);
            getWeapon().levelUp();
            return true;
        }
        return false;
    }

    public boolean canArmourLevelUp(){
        return getArmour().getLevel()*10<getMoney();
    }

    public boolean armourLevelUp(){
        if(canArmourLevelUp()){
            setMoney(getMoney()-getArmour().getLevel()*10);
            getArmour().levelUp();
            return true;
        }
        return false;
    }

    public boolean canMagicLevelUp(Integer magicIndex){
        return magics.get(magicIndex).getLevel()*10<getMoney();
    }

    public boolean magicLevelUp(Integer magicIndex){
        if(canMagicLevelUp(magicIndex)){
            setMoney(getMoney()-magics.get(magicIndex).getLevel()*10);
            magics.get(magicIndex).levelUp();
            return true;
        }
        return false;
    }


    public Magic getMagicByName(String name){
        for (Magic m:magics ) {

            if(m.getName().equals(name)){
                return m;
            }
        }
        return null;
    }

}
