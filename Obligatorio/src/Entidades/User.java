package Entidades;

import uy.edu.um.prog2.adt.LinkedList.MyLinkedListImpl;

public class User  {
    private long id;
    private String name;
    private int numberOfTweets;
    private boolean verified;
    private float numberOfFavourites;


    private final MyLinkedListImpl<Tweet> tweets = new MyLinkedListImpl<>();

    public User(long id, String name, boolean verified, float numberOfFavourites) {
        this.id = id;
        this.name = name;
        this.numberOfTweets = 0;
        this.verified = verified;
        this.numberOfFavourites = numberOfFavourites;
    }

    public float getNumberOfFavourites() {
        return numberOfFavourites;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getNumberOfTweets() {
        return numberOfTweets;
    }

    public void setNumberOfTweets(int numberOfTweets) {
        this.numberOfTweets = numberOfTweets;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MyLinkedListImpl getTweets() {
        return tweets;
    }

    public void setNumberOfFavourites(float numberOfFavourites) {
        this.numberOfFavourites = numberOfFavourites;
    }
}
