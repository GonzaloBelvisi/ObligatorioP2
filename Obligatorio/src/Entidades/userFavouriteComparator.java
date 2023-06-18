package Entidades;

import java.util.Comparator;

public class userFavouriteComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return Float.compare(u1.getNumberOfFavourites(),u2.getNumberOfFavourites());
    }
}
