package Entidades;

import java.util.Objects;

public class HashTag {

        private long id;
        private String text;

        private int count;



    public HashTag(long id, String text) {
        this.id = id;
        this.text = text;
        this.count = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTag hashTag = (HashTag) o;
        return id == hashTag.id && Objects.equals(text, hashTag.text);
    }

}


