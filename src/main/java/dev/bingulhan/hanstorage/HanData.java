package dev.bingulhan.hanstorage;


import java.util.Objects;

/**
 * Holds data as Key and Value
 */
public class HanData implements Comparable<HanData>{

    private String key;

    private int line = 0;

    private String value;

    public HanData(int line) {
        this.line = line;
    }
    public HanData() {
    }


    public int getLine() {
        return line;
    }

    public String getKey() {
        return key;
    }
    public HanData setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public HanData setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(line);
    }

    @Override
    public int compareTo(HanData o) {
        if (o.getLine()>this.getLine()) {
            return -1;
        }else if (o.getLine()<this.getLine()) {
            return 1;
        }else {
            return 0;
        }
    }
}
