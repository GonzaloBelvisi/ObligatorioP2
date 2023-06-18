package Entidades;


import java.util.Comparator;

public class userTweetCountComparator implements Comparator<User> {

    @Override
    public int compare(User u1, User u2) {
        return Integer.compare(u1.getNumberOfTweets(),u2.getNumberOfTweets());
    }
}