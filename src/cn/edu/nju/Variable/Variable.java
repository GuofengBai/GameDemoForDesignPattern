package cn.edu.nju.Variable;

import cn.edu.nju.Entity.Battle;
import cn.edu.nju.Entity.Character;

import java.util.Observer;

public class Variable {

    private static Character player;

    private static Battle currentBattle;

    //战斗信息板，监听Battle的变化，将字符串及时逐行输出
    private static Observer informationBoard;

    public static Character getPlayer() {
        return player;
    }

    public static void setPlayer(Character player) {
        Variable.player = player;
    }

    public static Battle getCurrentBattle() {
        return currentBattle;
    }

    public static void setCurrentBattle(Battle currentBattle) {
        Variable.currentBattle = currentBattle;
    }

    public static Observer getInformationBoard() {
        return informationBoard;
    }

    public static void setInformationBoard(Observer informationBoard) {
        Variable.informationBoard = informationBoard;
    }
}
