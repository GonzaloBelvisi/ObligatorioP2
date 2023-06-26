package uy.edu.um.prog2.adt.Hash2;

public class Test {

    public static void main(String[] args) {
        MyClosedHashImpl2 hastable = new MyClosedHashImpl2<>();

        hastable.put("Hola", 126);
        hastable.put("Holis", 121);
        hastable.put("Alo", 23);
        hastable.put("Aufbidenseena", 333);
        hastable.put("hello", 533);
        hastable.put("Hola", 232);

        for (int i = 0; i < hastable.getAll("Hola").size(); i++) {
            System.out.println(hastable.getAll("Hola").get(i));
        }
    }
}
