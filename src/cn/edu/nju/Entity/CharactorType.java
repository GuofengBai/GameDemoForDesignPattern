package cn.edu.nju.Entity;

import cn.edu.nju.Item.Magic;

public interface CharactorType {

    Integer levelUp();

    //物理攻击，三种chop,stab,crush，攻击类型视装备武器的攻击类型而定
    Double weaponAttack(Character enemy);

    Double magicAttack(Magic magic, Character enemy);

}
