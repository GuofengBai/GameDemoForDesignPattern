package cn.edu.nju.Factory;

import cn.edu.nju.Entity.*;
import cn.edu.nju.Entity.Character;
import cn.edu.nju.Item.Equipment;
import cn.edu.nju.Item.Weapon;

//此工厂负责生产人物，使用工厂模式与单例模式
class CharacterFactory {

    private static CharacterFactory instance = new CharacterFactory();

    private CharacterFactory() {
    }

    public static CharacterFactory getInstance(){
        return instance;
    }

    public Character createCharacter(String name, Integer level, String type){
        CharacterAttribute characterAttribute = new CharacterAttribute();
        Character character = new Character(characterAttribute);
        CharacterType characterType;
        if(type.equals("assassin")){
            characterType = new AssassinCharacterType(character);
        }else if(type.equals("knight")){
            characterType = new KnightCharacterType(character);
        }else{
            characterType = new MageCharacterType(character);
        }
        character.setCharacterType(characterType);
        character.setName(name);
        character.setExperience(10*Math.pow(1.2, level));
        for(int i=1;i<level;i++){
            character.levelUp();
        }
        Equipment weapon = EquipmentFactory.getInstance().createWeapon(level);
        Equipment armour = EquipmentFactory.getInstance().createArmour(level);
        character.setArmour(armour);
        character.setWeapon(weapon);
        character.addMagic(MagicFactory.getInstance().createBaseMagic("ice",level));
        character.addMagic(MagicFactory.getInstance().createBaseMagic("flame",level));
        character.addMagic(MagicFactory.getInstance().createBaseMagic("earth",level));
        character.addMagic(MagicFactory.getInstance().createBaseMagic("lightning",level));
        return character;
    }

}
