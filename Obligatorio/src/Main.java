import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        f1TweeterReport f1Registry = new f1TweeterReport();
        long startTime = System.currentTimeMillis();
        f1Registry.parse();
        long endTime = System.currentTimeMillis();

        System.out.println("Program Initialization Time:" + ((endTime - startTime)/1000.0) +"s");

        Scanner scanner = new Scanner(System.in);
        int choice;


        do {
            System.out.println("Menu:");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.println("3. Option 3");
            System.out.println("4. Option 4");
            System.out.println("5. Option 5");
            System.out.println("6. Option 6");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            String year;
            String month;
            String day;
            switch (choice) {
                case 1:
                    System.out.println("Imprimir 10 pilotos mas mencionados.");

                    System.out.print(" - Ingresar año:");
                    int year1= scanner.nextInt();
                    scanner.nextLine();

                    System.out.print(" - Ingresar mes:");
                    int month1= scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("");

                    startTime = System.currentTimeMillis();
                    f1Registry.printTop10Pilotos(year1,month1);
                    endTime = System.currentTimeMillis();
                    System.out.println("Program Initialization Time:" + ((endTime - startTime)/1000.0) +"s");

                    break;
                case 2:
                    System.out.println("Imprimir usaurios con mas tweets.");
                    startTime = System.currentTimeMillis();
                    f1Registry.printTop15Users();

                    endTime = System.currentTimeMillis();
                    System.out.println("Program Initialization Time:" + ((endTime - startTime)/1000.0) +"s");
                    break;
                case 3:
                    System.out.println("Imprimir cantidad de hashtags distintos para el dia:");

                    System.out.print(" - Ingresar año /yyyy/:");
                    year= scanner.nextLine();
                    System.out.print(" - Ingresar mes /MM/:");
                    month= scanner.nextLine();
                    System.out.print(" - Ingresar dia /dd/:");
                    day= scanner.nextLine();

                    System.out.println("");
                    // Perform actions for Option 3
                    startTime = System.currentTimeMillis();
                    f1Registry.printCantidadHashtags(year,month,day);
                    endTime = System.currentTimeMillis();
                    System.out.println("Program Initialization Time:" + ((endTime - startTime)/1000.0) +"s");
                    break;

                case 4:
                    System.out.println("Imprimir hashtag mas popular para el dia:");

                    System.out.print(" - Ingresar año:");
                    year= scanner.nextLine();


                    System.out.print(" - Ingresar mes:");
                    month= scanner.nextLine();

                    System.out.print(" - Ingresar dia:");
                    day= scanner.nextLine();


                    System.out.println("");
                    // Perform actions for Option 3
                    startTime = System.currentTimeMillis();

                    f1Registry.printHashtagsMostReapeted(year,month,day);
                    endTime = System.currentTimeMillis();
                    System.out.println("Program Initialization Time:" + ((endTime - startTime)/1000.0) +"s");
                    break;

                case 5:
                    System.out.println("Imprimir usaurios con mas favoritos.");
                    startTime = System.currentTimeMillis();
                    f1Registry.printTop7Users();

                    endTime = System.currentTimeMillis();
                    System.out.println("Program Initialization Time:" + ((endTime - startTime)/1000.0) +"s");
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;


            }

            System.out.println();
        } while (choice != 6);

        scanner.close();
    }
}



