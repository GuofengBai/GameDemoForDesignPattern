package cn.edu.nju.Entity;

public class TableVO {
    String key;
    String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TableVO(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
