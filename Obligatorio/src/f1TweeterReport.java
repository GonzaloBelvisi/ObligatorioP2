import Entidades.HashTag;
import Entidades.Tweet;
import Entidades.User;
import Entidades.pilotoActivo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import uy.edu.um.prog2.adt.ArrayList.MyArrayListImpl;
import uy.edu.um.prog2.adt.Hash.MyClosedHashImpl;
import uy.edu.um.prog2.adt.Hash2.MyClosedHashImpl2;
import uy.edu.um.prog2.adt.LinkedList.MyLinkedListImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class f1TweeterReport {

    MyClosedHashImpl<String,pilotoActivo> activePilotsHash = new MyClosedHashImpl(100);
    MyClosedHashImpl<String, User> userRegistryHash = new MyClosedHashImpl<>(20000);
    MyClosedHashImpl2<LocalDate, Tweet> TweetRegistryHash = new MyClosedHashImpl2<>(200000);
    MyLinkedListImpl<User> topUsersTweets = new MyLinkedListImpl<>();


    public void parse(){
        String filePath1 = "C:\\Users\\Gonzalo\\Desktop\\Prog 2 - Copy (2)\\ObligatorioP2\\Obligatorio\\src\\Recursos\\drivers.txt";
        String filePath2 = "C:\\Users\\Gonzalo\\Desktop\\Prog 2 - Copy (2)\\ObligatorioP2\\Obligatorio\\src\\Recursos\\f1_dataset.csv";


        //Leer la file de texto
        try (BufferedReader br = new BufferedReader(new FileReader(filePath1))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Procesamos cada linea del archivo de texto
                activePilotsHash.put(line, new pilotoActivo(line));
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        int count = 1;
        int errorCounter = 0;

        try (CSVParser parser = CSVParser.parse(new FileReader(filePath2), CSVFormat.DEFAULT)) {
            parser.iterator().next();

            for (CSVRecord record : parser) {
                String[] nextRow = new String[record.size()];
                for (int i = 0; i < record.size(); i++) {
                    nextRow[i] = record.get(i);
                }

                try{

                    String strHashtagArray = nextRow[11];
                    strHashtagArray = strHashtagArray.replaceAll("[\\[\\]'\"]", "");
                    String[] array = strHashtagArray.split(","); //


                    String strDate = nextRow[9].split(" ")[0];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(strDate, formatter);


                    //Chequeamos si existe el usuario y en caso de que no lo haga lo agregamos al Hash userRegistry
                    agregarUser(
                            nextRow[1],
                            Long.parseLong(nextRow[0]),
                            Boolean.parseBoolean(nextRow[8]),
                            Float.parseFloat(nextRow[7])
                    );


                        Tweet tweet = new Tweet(
                                Long.parseLong(nextRow[0]),
                                nextRow[10].toLowerCase(),
                                nextRow[12],
                                Boolean.parseBoolean(nextRow[13]),
                                Boolean.parseBoolean(nextRow[8]),
                                Float.parseFloat(nextRow[7]),
                                date);

                    for (int i = 0; array.length > i; i++) {
                        HashTag hashtag;
                        hashtag = new HashTag(Long.parseLong(nextRow[0]),array[i].replaceAll("\\s+", ""));
                        tweet.getHashtagLinkedList().addFirst(hashtag);
                    }

                    userRegistryHash.get(nextRow[1]).getTweets().addFirst(tweet);

                    TweetRegistryHash.put(date,tweet);
                    count++;

                } catch (Exception e){
                    errorCounter++;
                    continue;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(
                "Abnormal Data: "+errorCounter+
                "\nData Successfuly Loaded: "+count);
    }

    private void agregarUser(String profilename, long id, boolean verified, float numberOfFavourites){
        User user1;
        if(!existsUser(profilename)){
            user1 = new User(id,profilename,verified,numberOfFavourites);
            userRegistryHash.put(profilename, user1);
        }
    }

    private boolean existsUser(String username){
        return (userRegistryHash.get(username)!=null);
    }


    private User[] top15_Users(){
        User[] Top15 = new User[15];
        sortForTweetCount quickSort = new sortForTweetCount();

        for (int i = 0; i < userRegistryHash.size(); i++){
            User userAt = userRegistryHash.getPosition(i);
            userAt.setNumberOfTweets(userAt.getTweets().getSize());

            if (i<=14){Top15[i] = userAt;
                if(i==14){ quickSort.quicksort(Top15);}}

            else if ((userAt.getNumberOfTweets()>Top15[0].getNumberOfTweets())){
                Top15[0]=userAt;
                quickSort.quicksort(Top15);}
        }
        return Top15;
    }


    private pilotoActivo[] top10_Pilotos(int year, int month) {
        pilotoActivo[] Top10 = new pilotoActivo[10];
        pilotoActivo[] Top20 = new pilotoActivo[20];

        YearMonth yearMonth = YearMonth.of(year, month); // Specify the year and month
        LocalDate startDate = yearMonth.atDay(1);
        int lastDayOfMonth = yearMonth.lengthOfMonth();

        MyArrayListImpl<Tweet> tweetsFromthatMonth = new MyArrayListImpl<>(130000);

        // Collect all tweets for the given month
        for (int day = 0; day < lastDayOfMonth; day++) {
            LocalDate currentDate = startDate.plusDays(day);
            MyArrayListImpl<Tweet> tweetsFromThatDay = TweetRegistryHash.getAll(currentDate);


            for (int i = 0; i < tweetsFromThatDay.size(); i++) {
                tweetsFromthatMonth.add(tweetsFromThatDay.get(i));
        }}

        System.out.println("Total tweets for that month: "+ tweetsFromthatMonth.size());

        for (int j = 0; j < 20; j++) {
            pilotoActivo piloto = activePilotsHash.getPosition(j);
            String nombreApellido = piloto.getName();
            String nombre;
            String apellido;

            if (nombreApellido!="Nyck de Vries"){
                String [] arrayNombreApellido = nombreApellido.split(" ",2);
                 nombre = arrayNombreApellido[0].toLowerCase();
                 apellido = arrayNombreApellido[1].toLowerCase();}
            else {
                String [] arrayNombreApellido = nombreApellido.split(" ");
                 nombre = arrayNombreApellido[0].replaceAll("\\s+", "").toLowerCase();
                 apellido = arrayNombreApellido[1].replaceAll("\\s+", "").toLowerCase();}

            int occurrences = 1;

            // Iterate over all tweets and check if pilot's name is present
            for (int i = 0; i <tweetsFromthatMonth.size(); i++) {
                Tweet currentTweet = tweetsFromthatMonth.get(i);

                if (    currentTweet.getContent().contains(nombre)||
                        currentTweet.getContent().contains(apellido)) {

                    occurrences++;
                }
            }

            piloto.setCantidadDeOcurrencias(occurrences);
            Top20[j] = piloto;
        }

        SortForTop10Pilot quicksort = new SortForTop10Pilot();// Sort the pilots based on occurrences
        quicksort.quicksort(Top20);
        // Copy top 10 pilots to the final array

            int index1 = 0;
            int index2 = 10;
            for (int i = 0; i <10 ; i++) {
                Top10[index1] = Top20[index2];
                index1++;
                index2++;
            }

        return Top10;
    }

    private int cantidadHashtags(String ano, String mes, String dia) {

        String strDate =ano+"-"+mes+"-"+dia;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(strDate, formatter);

        MyArrayListImpl<Tweet> tweetsForDay = TweetRegistryHash.getAll(date);
        MyClosedHashImpl<String, HashTag> hashtagRegistry = new MyClosedHashImpl<String, HashTag>(10000);

        for (int i = 0; i < tweetsForDay.size(); i++) {
            Tweet tweet = tweetsForDay.get(i);
            for (int j = 0; j < tweet.getHashtagLinkedList().getSize(); j++) {
                HashTag hashtag = tweet.getHashtagLinkedList().get(j);
                String key = hashtag.getText();

                HashTag existingHashtag = hashtagRegistry.get(key);
                if (existingHashtag != null) {
                    existingHashtag.setCount(existingHashtag.getCount() + 1);
                } else {
                    hashtagRegistry.put(key, hashtag);
                }
            }
        }

        return hashtagRegistry.getKeyList().size()-1;
    }

    private HashTag TopHashtag(String ano, String mes, String dia) {

        String strDate =ano+"-"+mes+"-"+dia;
        LocalDate date = null;

        HashTag hashtagNoOccurrences = new HashTag(0,"");
        hashtagNoOccurrences.setCount(0);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(strDate, formatter);
        }catch(DateTimeParseException E){
            throw new RuntimeException("Error en el formato de la fecha!");
        }
        MyArrayListImpl<Tweet> tweetsForDay = TweetRegistryHash.getAll(date);
        MyClosedHashImpl<String, HashTag> hashtagRegistry = new MyClosedHashImpl<String, HashTag>();

        for (int i = 0; i < tweetsForDay.size(); i++) {
            Tweet tweet = tweetsForDay.get(i);
            for (int j = 0; j < tweet.getHashtagLinkedList().getSize(); j++) {
                HashTag hashtag = tweet.getHashtagLinkedList().get(j);
                String key = hashtag.getText();

                HashTag existingHashtag = hashtagRegistry.get(key.toLowerCase());
                if (existingHashtag != null) {
                    existingHashtag.setCount(existingHashtag.getCount() + 1);
                } else {
                    hashtagRegistry.put(key.toLowerCase(), hashtag);
                }
            }
        }

        for (int i = 0; i <hashtagRegistry.size() ; i++) {
            HashTag currentHashTag= hashtagRegistry.getPosition(i);
            if ( currentHashTag.getCount() > hashtagNoOccurrences.getCount() && (!currentHashTag.getText().contains("f1")
            &&(!currentHashTag.getText().contains("F1")))){
                hashtagNoOccurrences = currentHashTag;
            }

        }

        return hashtagNoOccurrences;
    }

    public void printCantidadHashtags(String ano, String mes, String dia){
        int cantidad = cantidadHashtags(ano,mes,dia);
        System.out.println("Cantidad de Hashtags distintos: "+cantidad );
    }

    public void printHashtagsMostReapeted(String ano, String mes, String dia){
        HashTag hashtag = TopHashtag(ano,mes,dia);
        System.out.println("Hastag mas popular: "+hashtag.getText() + "| Cantidad de ocurrencias: "+hashtag.getCount()+"|" );
    }


    public void printTop15Users(){
        User[] usuarios = top15_Users();
        int index = 14;

        for (int i = 0; i <15 ; i++) {
            String verified;
            if(usuarios[index].isVerified()){verified = "Verified";}
            else {verified = "Unverified";}
            System.out.println(
                    i+1+"." +
                    " Username: "+ usuarios[index].getName() +
                    "| Number of Tweets: " +usuarios[index].getNumberOfTweets() +
                    "| User Status: "+ verified+"|") ;
            index--;

        }
    }
    public void printTop10Pilotos(int year, int month){

        pilotoActivo[] top10Pilots = top10_Pilotos(year,month);

        int position = 9;
        for (int i = 0; i < 10; i++) {
            System.out.println(i+1 + ": " + top10Pilots[position].getName() +
                    " contador: " + top10Pilots[position].getCantidadDeOcurrencias());
            position--;
        }
    }



}
