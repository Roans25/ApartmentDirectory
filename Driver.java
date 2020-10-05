import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Driver file is the java file that takes the user input and displays the output by using
 * methods created in the other files.
 *
 *
 * @author Zahaan Motiwala
 */

public class Driver {

  public static final String ANSI_RESET = "\u001B[0m"; // Used to change the colour of output to
                                                       // Default
  public static final String ANSI_RED = "\u001B[31m"; // Used to change the colour of output to Red
  public static final String ANSI_GREEN = "\u001B[32m"; // Used to change the colour of output to
                                                        // Green
  public static final String ANSI_YELLOW = "\u001B[33m"; // Used to change the colour of output to
                                                         // Yellow
  public static final String ANSI_BLUE = "\u001B[34m"; // Used to change the colour of output to
                                                       // Blue
  public static final String ANSI_PURPLE = "\u001B[35m"; // Used to change the colour of output to
                                                         // Purple
  public static final String ANSI_CYAN = "\u001B[36m"; // Used to change the colour of output to
                                                       // Cyan

  /**
   * Static Method that is called when user chooses to list an apartment. Displays an ANSII art logo
   * and asks the user to input information regarding the apartment.
   *
   * @Param DataHandler dataHandler
   */
  public static void listingMethod(DataHandler dataHandler) {
    String name; // contains Name of apartment type String
    String location;// contains location of apartment type String
    float price;// contains Price of apartment type float
    int availableRooms;// contains Available rooms of apartment type Int
    String phoneNumber;// contains Phone Number of apartment type String
    Scanner scanner = new Scanner(System.in);
    // Print Display Banner
    System.out.println(ANSI_BLUE
        + " __       __       _______.___________. __  .__   __.   _______      _______.");
    System.out
        .println("|  |     |  |     /       |           ||  | |  \\ |  |  /  _____|    /       |");
    System.out
        .println("|  |     |  |    |   (----`---|  |----`|  | |   \\|  | |  |  __     |   (----`");
    System.out.println(
        "|  |     |  |     \\   \\       |  |     |  | |  . `  | |  | |_ |     \\   \\    ");
    System.out
        .println("|  `----.|  | .----)   |      |  |     |  | |  |\\   | |  |__| | .----)   |   ");
    System.out
        .println("|_______||__| |_______/       |__|     |__| |__| \\__|  \\______| |_______/    "
            + ANSI_RESET);
    System.out.println("");
    // Get Name String Value
    System.out.println(ANSI_GREEN + "What is the name of the Appartment? \n");
    name = scanner.nextLine();
    // Get Room number to concatenate to name
    System.out.println("What is the room number of the Appartment? \n");
    String num = scanner.nextLine();
    name = num + " " + name;
    // Get Location String Value
    System.out.println("What is the location of the Appartment? \n");
    location = scanner.nextLine();
    // Get price float value.
    System.out.println("What is the price of the Appartment? \n");
    price = scanner.nextFloat();
    scanner.nextLine();
    // Get roomAvailable Int value
    System.out.println("How many rooms are available? \n");
    availableRooms = scanner.nextInt();
    scanner.nextLine();
    // Get Phone number String value
    System.out.println("What is your contact phone number? \n");
    phoneNumber = scanner.nextLine();
    // Create an apartment object with input information collect
    Apartments newApartment = new Apartments(name, location, availableRooms, price, phoneNumber);
    // edit information in txt file
    dataHandler.listApartment(newApartment);
    System.out.println("Thank you for listing your apartment! \n" + ANSI_RESET);

  }

  /**
   * Static Method that is called when user chooses to Search for an appartment. Displays an ANSII
   * art logo and asks the user to input information regarding the search request.
   *
   * @Param DataHandler dataHandler
   */
  public static void searchingMethod(DataHandler dataHandler) {
    // Print Display Banner
    System.out.println(ANSI_RED + "\n _____                     _     _             ");
    System.out.println("/  ___|                   | |   (_)            ");
    System.out.println("\\ `--.  ___  __ _ _ __ ___| |__  _ _ __   __ _ ");
    System.out.println(" `--. \\/ _ \\/ _` | '__/ __| '_ \\| | '_ \\ / _` |");
    System.out.println("/\\__/ /  __/ (_| | | | (__| | | | | | | | (_| |");
    System.out.println("\\____/ \\___|\\__,_|_|  \\___|_| |_|_|_| |_|\\__, |");
    System.out.println("                                          __/ |");
    System.out.println("                                         |___/ " + ANSI_RESET);

    Scanner scanner = new Scanner(System.in);

    // Asks user to choose search query parameter
    System.out.println(ANSI_GREEN + "How would you like to Search?");
    System.out.println("1. name of apartment");
    System.out.println("2. location:");
    System.out.println("3. number of rooms");
    System.out.println("4. price");
    System.out.println("5. comparative" + ANSI_RESET);
    int choice2 = scanner.nextInt();
    scanner.nextLine();

    // If user chooses to search with Name. It displays the information regarding
    // the searched appartment and returns not found if null.
    if (choice2 == 1) {
      System.out.println(ANSI_BLUE + "Name of apartment:");
      String name = scanner.nextLine();
      System.out.println("room number:");
      String num = scanner.nextLine();
      name = num + " " + name;
      Apartments apartment = dataHandler.searchByName(name);
      if (apartment != null) {
        System.out.println(apartment);
      } else {
        System.out.println("Apartment not found");
      }
    } 
    // If user chooses to search with Address . It displays the information
    // regarding the searched apartment and returns not found if null.
    else if (choice2 == 2) {
      System.out.println(ANSI_BLUE + "address:");
      String address = scanner.nextLine();
      ArrayList<Apartments> apartmentList = dataHandler.searchByLoc(address);
      if (apartmentList != null) {
        for (int x = 0; x < apartmentList.size(); x++) {
          System.out.println();
          System.out.println(apartmentList.get(x).toString());
        }
      } else {
        System.out.println("There are no apartments listed at that adress.");
      }
    }
    // If user chooses to search with Rooms Available. It displays the information
    // regarding the available apartments and returns not found if null.
    else if (choice2 == 3) {
      System.out.println(ANSI_BLUE + "Rooms available:");
      int numRooms = scanner.nextInt();
      ArrayList<Apartments> apartmentList = dataHandler.searchByRoomNum(numRooms);
      scanner.nextLine();
      if (apartmentList != null) {
        for (int x = 0; x < apartmentList.size(); x++) {
          System.out.println();
          System.out.println(apartmentList.get(x).toString());
        }
      } else {
        System.out.println("No apartments with " + Integer.toString(numRooms) + " rooms.");
      }
    }
    // If user chooses to search with a Price range. It displays the information
    // regarding the relevant apartments and returns not found if null.
    else if (choice2 == 4) {
      System.out.println(ANSI_BLUE + "0. 0-999:");
      System.out.println("1. 1000-1999:");
      System.out.println("2. 2000-2999:");
      System.out.println("3. 3000-3999:");
      System.out.println("4. 4000-4999:");
      System.out.println("5. 5000-5999:");
      System.out.println("6. 6000-6999:");
      int priceHash = scanner.nextInt();
      scanner.nextLine();
      ArrayList<Apartments> apartmentList = dataHandler.searchByPrice(priceHash);
      if (apartmentList != null) {
        for (int x = 0; x < apartmentList.size(); x++) {
          System.out.println();
          System.out.println(apartmentList.get(x).toString());
        }
      } else {
        System.out.println("No Apartments listed in that price range.");
      }
    }
    // Allows you to compare two apartments by providing information of both
    else if (choice2 == 5) {
      ArrayList<String> apartmentNames = new ArrayList<>();
      System.out.println(ANSI_BLUE + "Enter all the names of apartments you want to compare.");
      System.out.println("Hit Enter after each one and type 1 when done.");
      boolean done = false;
      while (!done) {
        System.out.println("press 1 if done, press 0 to add an apartment.");
        int choice3 = scanner.nextInt();
        scanner.nextLine();
        if (choice3 != 1) {
          System.out.println("Name of apartment:");
          String name = scanner.nextLine();
          System.out.println("room number:");
          String num = scanner.nextLine();
          name = num + " " + name;
          apartmentNames.add(name);
        } else {
          done = true;
          ArrayList<Apartments> apartmentList = dataHandler.compareTo(apartmentNames);
          if (apartmentList != null) {
            for (int x = 0; x < apartmentList.size(); x++) {
              if (apartmentList.get(x) != null) {
                System.out.println();
                System.out.println(apartmentList.get(x));
              } else {
                System.out.println(apartmentNames.get(x) + " is not listed.");
              }

            }
          } else {
            System.out.println("None of those Apartments are listed");
          }
        }
      }
    }
    System.out.print(ANSI_RESET);

  }

  /**
   * Main method that prints welcome message and prompts the user to either list an apartment or
   * search. It also handles exceptions and calls the appropriate static methods.
   */

  public static void main(String[] args) {

    DataHandler dataHandler = new DataHandler();// create new Data Handler object
    dataHandler.dataHasher();
    // Presents user with colourful welcome message
    System.out.println(ANSI_RED + "  __  __           _ _                                      ");
    System.out.println(" |  \\/  |         | (_)                                     ");
    System.out.println(" | \\  / | __ _  __| |_ ___  ___  _ __                       ");
    System.out.println(" | |\\/| |/ _` |/ _` | / __|/ _ \\| \'_ \\                      ");
    System.out.println(" | |  | | (_| | (_| | \\__ \\ (_) | | | |                     ");
    System.out
        .println(" |_|  |_|\\__,_|\\__,_|_|___/\\___/|_| |_|                 _   " + ANSI_RESET);
    System.out
        .println(ANSI_PURPLE + "        /\\                    | |                      | |  ");
    System.out.println("       /  \\   _ __   __ _ _ __| |_ _ __ ___   ___ _ __ | |_ ");
    System.out.println("      / /\\ \\ | \'_ \\ / _` | \'__| __| \'_ ` _ \\ / _ \\ \'_ \\| __|");
    System.out.println("     / ____ \\| |_) | (_| | |  | |_| | | | | |  __/ | | | |_ ");
    System.out.println("    /_/    \\_\\ .__/ \\__,_|_|   \\__|_| |_| |_|\\___|_| |_|\\__|");
    System.out.println("             | |                                            ");
    System.out.println("         ____|_|_               _                           " + ANSI_RESET);
    System.out.println(ANSI_CYAN + "        |  __ \\(_)             | |                          ");
    System.out.println("        | |  | |_ _ __ ___  ___| |_ ___  _ __ _   _         ");
    System.out.println("        | |  | | | \'__/ _ \\/ __| __/ _ \\| \'__| | | |        ");
    System.out.println("        | |__| | | | |  __/ (__| || (_) | |  | |_| |        ");
    System.out.println("        |_____/|_|_|  \\___|\\___|\\__\\___/|_|   \\__, |        ");
    System.out.println("                                               __/ |        ");
    System.out.println("                                              |___/         " + ANSI_RESET);
    int input = 0; // used to check option selected by user
    while (input != 3) {
      // presents user with options
      System.out.println(ANSI_GREEN + "");
      System.out.println("Are you ( Please Enter a value of 1 or 2 )");
      System.out.println("");
      System.out.println("      - > 1) Listing an Appartment");
      System.out.println("");
      System.out.println("           or");
      System.out.println("");
      System.out.println("      - > 2) Searching for an Appartment");
      System.out.println("");
      System.out.println("           or");
      System.out.println("");
      System.out.println("      - > 3) Exiting the program" + ANSI_RESET);
      System.out.println("");
      @SuppressWarnings("resource")
      Scanner scanner = new Scanner(System.in);

      // Handles Index out of bounds and Input Mismatch Exceptions.
      try {
        input = scanner.nextInt();
        if (input == 1) {
          listingMethod(dataHandler);
        } else if (input == 2) {
          searchingMethod(dataHandler);
        } else if (input == 3) {
          System.out.println("You are now exiting the program.");
        } else {
          throw new IndexOutOfBoundsException("");
        }
      } catch (IndexOutOfBoundsException | java.util.InputMismatchException e) {
        System.out.println("You did not input an appropriate digit");
      }

    }
  }
}
