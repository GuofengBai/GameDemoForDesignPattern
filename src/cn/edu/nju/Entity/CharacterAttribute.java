package cn.edu.nju.Entity;

public class CharacterAttribute implements AttributeDecorator {

    private Double fullHp=100.0;

    //生命值
    private Double hp=fullHp;

    private Double fullMp=100.0;

    //魔法值
    private Double mp=fullMp;

    //防御力——劈砍攻击
    private Double def_chop=10.0;

    //防御力——刺击攻击
    private Double def_stab=10.0;

    //防御力——钝击攻击
    private Double def_crush=10.0;

    //防御力——冰属性魔法
    private Double def_ice=10.0;

    //防御力——火属性魔法
    private Double def_flame=10.0;

    //防御力——土属性魔法
    private Double def_earth=10.0;

    //防御力——雷属性魔法
    private Double def_lightning=10.0;

    //攻击力（人物本身攻击力+物理攻击力=总攻击力）
    private Double ap=10.0;

    //命中率
    private Double accuracy=0.4;

    public CharacterAttribute() {
    }

    public CharacterAttribute(Double fullHp, Double hp, Double fullMp, Double mp, Double def_chop, Double def_stab, Double def_crush, Double def_ice, Double def_flame, Double def_earth, Double def_lightning, Double ap, Double accuracy) {
        this.fullHp = fullHp;
        this.hp = hp;
        this.fullMp = fullMp;
        this.mp = mp;
        this.def_chop = def_chop;
        this.def_stab = def_stab;
        this.def_crush = def_crush;
        this.def_ice = def_ice;
        this.def_flame = def_flame;
        this.def_earth = def_earth;
        this.def_lightning = def_lightning;
        this.ap = ap;
        this.accuracy = accuracy;
    }

    @Override
    public Double getAttribute(String attrName) {

        switch (attrName){
            case "hp":
                return getHp();
            case "fullHp":
                return getFullHp();
            case "fullMp":
                return getFullMp();
            case "mp":
                return getMp();
            case "def_chop":
                return getDef_chop();
            case "def_stab":
                return getDef_stab();
            case "def_crush":
                return getDef_crush();
            case "def_ice":
                return getDef_ice();
            case "def_flame":
                return getDef_flame();
            case "def_earth":
                return getDef_earth();
            case "def_lightning":
                return getDef_lightning();
            case "ap":
                return getAp();
            case "accuracy":
                return getAccuracy();
            default:
                return 1.0;
        }

    }

    public void evenlyLevelUp(){
        fullHp*=1.05;
        hp=fullHp;
        fullMp*=1.05;
        mp=fullMp;
        def_chop*=1.05;
        def_stab*=1.05;
        def_crush*=1.05;
        def_ice*=1.05;
        def_flame*=1.05;
        def_earth*=1.05;
        def_lightning*=1.05;
        ap*=1.05;
        accuracy=Math.min(1.0, accuracy*1.05);
    }

    public Double getFullHp() {
        return fullHp;
    }

    public void setFullHp(Double fullHp) {
        this.fullHp = fullHp;
        this.hp=fullHp;
    }

    public Double getFullMp() {
        return fullMp;
    }

    public void setFullMp(Double fullMp) {
        this.fullMp = fullMp;
        this.mp=fullMp;
    }

    public Double getHp() {
        return hp;
    }

    public void setHp(Double hp) {
        this.hp = hp;
    }

    public Double getMp() {
        return mp;
    }

    public void setMp(Double mp) {
        this.mp = mp;
    }

    public Double getDef_chop() {
        return def_chop;
    }

    public void setDef_chop(Double def_chop) {
        this.def_chop = def_chop;
    }

    public Double getDef_stab() {
        return def_stab;
    }

    public void setDef_stab(Double def_stab) {
        this.def_stab = def_stab;
    }

    public Double getDef_crush() {
        return def_crush;
    }

    public void setDef_crush(Double def_crush) {
        this.def_crush = def_crush;
    }

    public Double getDef_ice() {
        return def_ice;
    }

    public void setDef_ice(Double def_ice) {
        this.def_ice = def_ice;
    }

    public Double getDef_flame() {
        return def_flame;
    }

    public void setDef_flame(Double def_flame) {
        this.def_flame = def_flame;
    }

    public Double getDef_earth() {
        return def_earth;
    }

    public void setDef_earth(Double def_earth) {
        this.def_earth = def_earth;
    }

    public Double getDef_lightning() {
        return def_lightning;
    }

    public void setDef_lightning(Double def_lightning) {
        this.def_lightning = def_lightning;
    }

    public Double getAp() {
        return ap;
    }

    public void setAp(Double ap) {
        this.ap = ap;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }
}
