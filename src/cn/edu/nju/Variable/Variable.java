package cn.edu.nju.Variable;

import cn.edu.nju.Entity.Battle;
import cn.edu.nju.Entity.Character;

public class Variable {

    private static Character player;

    private static Battle currentBattle;

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
}
