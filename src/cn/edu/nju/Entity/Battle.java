package cn.edu.nju.Entity;

import cn.edu.nju.Factory.CharacterFactory;
import cn.edu.nju.Item.Magic;
import cn.edu.nju.Variable.Variable;

import java.util.Iterator;
import java.util.Observable;

public class Battle extends Observable {

    private Character enemy;

    //战斗胜利获得的经验
    private Double experience;

    private Double bonusMoney;

    private boolean playerTurn = false;

    public Battle(Character enemy, Double experience, Double bonusMoney) {
        this.enemy = enemy;
        this.experience = experience;
        this.bonusMoney = bonusMoney;
        Variable.setCurrentBattle(this);
        addObserver(Variable.getInformationBoard());
        init();
    }

    public static Battle createBattle(){
        Integer level = Variable.getPlayer().getLevel();
        Character enemy = CharacterFactory.getInstance().createCharacter("敌人",level-1>0?level-1:1);
        return new Battle(enemy,level*1.0,level*1.0);
    }

    public void init(){
        String information = "战斗开始。\n";
        if(Variable.getPlayer().typeRestriction(enemy)>-1){
            information+="由于职业克制，玩家先行动。\n";
        }else{
            information+="由于职业被克制，敌人先行动。\n";
        }
        setChanged();
        notifyObservers(information);

        if(Variable.getPlayer().typeRestriction(enemy)>-1){
            playerTurn=true;
        }else{
            enemyTurn();
        }
    }

    public Integer checkIfEnd(){
        if(Variable.getPlayer().getAttribute("hp")<=0){
            return -1;
        }else if(enemy.getAttribute("hp")<=0){
            return 1;
        }else{
            return 0;
        }
    }

    public void end(Integer result){
        if(result>0){
            Variable.getPlayer().setExperience(Variable.getPlayer().getExperience()+experience);
            Variable.getPlayer().setMoney(Variable.getPlayer().getMoney()+bonusMoney);
            Variable.getPlayer().setHp(Variable.getPlayer().getAttribute("fullHp"));
            Variable.getPlayer().setMp(Variable.getPlayer().getAttribute("fullMp"));
            String information = "战斗结束，玩家获胜，体力值与魔法值恢复为全满。\n获得金钱"+bonusMoney+"，经验"+experience+"。\n\n\n";
            Variable.setCurrentBattle(null);
            setChanged();
            notifyObservers(information);
        }else{
            String information = "玩家死亡，战斗失败，游戏结束。\n";
            setChanged();
            notifyObservers(information);
            Variable.setCurrentBattle(null);
        }
    }

    public void enemyTurn(){
        if(!playerTurn){
            String information = "敌方行动。";
            Magic magic = null;
            Iterator<Magic> magics = enemy.getMagics();
            while (magics.hasNext()){
                magic = magics.next();
                if(Math.random()<0.5){
                    break;
                }
            }
            if(Math.random()<0.5&&magic.getConsumedMp()<enemy.getAttribute("mp")){
                information+="敌方使用魔法-"+magic.getName()+",";
                Double hurt = enemy.magicAttack(magic,Variable.getPlayer());
                if(hurt<0){
                    information+="没有命中，没造成伤害。\n";
                }else{
                    information+="由于玩家本身抗性及装备加成，造成"+hurt+"伤害。\n";
                }
            }else{
                information+="敌方使用武器攻击，";
                Double hurt = enemy.weaponAttack(Variable.getPlayer());
                if(hurt<0){
                    information+="没有命中，没造成伤害。\n";
                }else{
                    information+="由于玩家本身抗性及装备加成，造成"+hurt+"伤害。\n";
                }
            }
            setChanged();
            notifyObservers(information);

            playerTurn=true;
            Integer result = checkIfEnd();
            if(result!=0){
                end(result);
            }
        }
    }

    public void playerWeaponTurn(){
        if(playerTurn){
            String information="玩家行动，使用武器攻击。";
            Double hurt = Variable.getPlayer().weaponAttack(enemy);
            if(hurt<0){
                information+="没有命中，没造成伤害。\n";
            }else{
                information+="由于敌方本身抗性及装备加成，造成"+hurt+"伤害。\n";
            }
            setChanged();
            notifyObservers(information);
            playerTurn=false;
            Integer result = checkIfEnd();
            if(result==0){
                enemyTurn();
            }else{
                end(result);
            }
        }
    }

    public void playerMagicTurn(Integer magicIndex){
        if(playerTurn){
            Magic magic = null;
            int i=0;
            Iterator<Magic> magics = Variable.getPlayer().getMagics();
            while (magics.hasNext()){
                magic = magics.next();
                if(i==magicIndex){
                    break;
                }
                i++;
            }
            String information="玩家行动，使用魔法-"+magic.getName()+"，";
            if(magic.getConsumedMp()<Variable.getPlayer().getAttribute("mp")){
                information+="剩余魔法值不足，无法使用该魔法，请重新行动。\n";
                setChanged();
                notifyObservers(information);
                return;
            }else{
                Double hurt = Variable.getPlayer().magicAttack(magic,enemy);
                if(hurt<0){
                    information+="没有命中，没造成伤害。\n";
                }else{
                    information+="由于敌方本身抗性及装备加成，造成"+hurt+"伤害。\n";
                }
            }

            setChanged();
            notifyObservers(information);
            playerTurn=false;
            Integer result = checkIfEnd();
            if(result==0){
                enemyTurn();
            }else{
                end(result);
            }
        }
    }
}
