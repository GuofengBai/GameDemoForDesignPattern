package cn.edu.nju.Entity;

public class TableVO {
    String key;
    Double value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public TableVO(String key, Double value) {
        this.key = key;
        this.value = value;
    }
}
