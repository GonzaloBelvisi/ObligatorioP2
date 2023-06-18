package Entidades;

import uy.edu.um.prog2.adt.LinkedList.MyLinkedListImpl;

import java.time.LocalDate;

public class Tweet {

    private long id;
    private String content;
    private String source;
    private float numFavourites;
    private boolean isRetweet;
    private boolean verified;
    private LocalDate date;


    private MyLinkedListImpl<HashTag> hastagLinkedList = new MyLinkedListImpl<>();

    public Tweet(long id, String content, String source, boolean isRetweet, boolean verified, float numFavourites,LocalDate date) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.numFavourites = numFavourites;
        this.isRetweet = isRetweet;
        this.verified = verified;
        this.date = date;
    }

    public MyLinkedListImpl<HashTag> getHashtagLinkedList() {
        return hastagLinkedList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }
}
