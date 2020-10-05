import java.util.ArrayList;

public class DataHandler {
  private HashTableMap<String, Apartments> nameAptMap;
  private HashTableMap<String, ArrayList<Apartments>> locAptMap;
  private HashTableMap<Integer, ArrayList<Apartments>> roomAptMap;
  private HashTableMap<Integer, ArrayList<Apartments>> priceAptMap;

  FileHandler aptFile = new FileHandler();
  BackEnd searcher = new BackEnd();

  /**
   * Calls an instance of FileHandler to read the apartmentData into an array Creates an apartment
   * object from each array line and puts it in a hashtable
   */
  @SuppressWarnings("unchecked")
  public void dataHasher() {
    ArrayList<String> apartmentList = new ArrayList<String>();
    // using filehandler class to read the data from ApartmentData text file and into an array
    apartmentList = aptFile.fileToList("Apartments.txt");

    // creating hash tables for each specific key
    nameAptMap = new HashTableMap<String, Apartments>(apartmentList.size() * 2);
    locAptMap = new HashTableMap<String, ArrayList<Apartments>>(apartmentList.size());
    roomAptMap = new HashTableMap<Integer, ArrayList<Apartments>>();
    priceAptMap = new HashTableMap<Integer, ArrayList<Apartments>>();

    // creating array lists to be used as data for each hash table
    ArrayList<Apartments>[] priceList = new ArrayList[10];
    ArrayList<ArrayList<Apartments>> locList = new ArrayList<ArrayList<Apartments>>();
    ArrayList<String> addressList = new ArrayList<>();
    ArrayList<Apartments>[] roomNumList = new ArrayList[10];

    // dividing each line of text file into specific attributes of the apartment
    for (String apt : apartmentList) {
      String[] aptInfo = apt.split("///");
      String name = aptInfo[0];
      String location = aptInfo[1];
      int numOfRooms = Integer.parseInt(aptInfo[2]);
      float price = Float.parseFloat(aptInfo[3]);
      String phoneNum = aptInfo[4];
      // creating an apartment object from these attributes
      Apartments apartment = new Apartments(name, location, numOfRooms, price, phoneNum);
      // puts the apartment in the name hash map
      nameAptMap.put(name, apartment);
      
      // adding the apartment object into the location array
      if (!addressList.contains(location)) {
        // case when the array doesn't contain an apartment in the same location
        addressList.add(location); 
        locList.add(new ArrayList<Apartments>());
        locList.get(addressList.indexOf(location)).add(apartment);
      } else {
        // case when array already contains another apartment in the same location
        locList.get(addressList.indexOf(location)).add(apartment);
      }

      // adding the apartment object to the number of rooms array
      if (roomNumList[numOfRooms] == null) {
        // case when array doesn't contain an apartment with the same number of rooms
        roomNumList[numOfRooms] = new ArrayList<Apartments>();
        roomNumList[numOfRooms].add(apartment);
      } else {
        // case when array already contains another apartment with the same number of rooms
        roomNumList[numOfRooms].add(apartment);
      }

      // designing hash code for price attribute of apartment
      // Adding apartments with respect to their price range instead of their exact price
      // Range: 0-999 = 0, 1000-1999 = 1, 2000-2999 = 2, etc 
      int priceHash = ((int) price / 1000);
      
      // adding the apartment object to the price array
      if (priceList[priceHash] == null) {
        // case when array doesn't contain another apartment in the same price range
        priceList[priceHash] = new ArrayList<Apartments>();
        priceList[priceHash].add(apartment);
      } else {
        // case when array already contains another apartment in the same price range
        priceList[priceHash].add(apartment);
      }
    }
    
    // putting all the data from location array into location hash Map
    for (int i = 0; i < locList.size(); i++)
      locAptMap.put(addressList.get(i), locList.get(i));

    // putting all the data from number of rooms array into number of rooms hash map
    for (int i = 0; i < roomNumList.length; i++)
      roomAptMap.put(i, roomNumList[i]);

    // putting all the data form the price array into price hash table
    for (int i = 0; i < priceList.length; i++)
      priceAptMap.put(i, priceList[i]);

  }

  /**
   * Writes a new apartment to the Apartment data text file and 
   * adds the apartment to all the hash maps
   * 
   * @param apartment new apartment to add
   */
  public void listApartment(Apartments apartment) {
    // converts the attributes into a string that can be written to the text file
    String name = apartment.getAptNameNum();
    String address = apartment.getLocation();
    String numRooms = Integer.toString(apartment.getNumOfRooms());
    String price = Float.toString(apartment.getPrice());
    String phoneNum = apartment.getPhoneNum();
    String aptString =
        (name + "///" + address + "///" + numRooms + "///" + price + "///" + phoneNum);
    
    // puts the apartment in the name hash map
    // returns true if the apartment is new and false if the apartment already exists in hash map
    boolean putName = nameAptMap.put(name, apartment);
    
    if (putName) {
      aptFile.listToFile(aptString, "Apartments.txt");
      
      // adds the apartment to the location hash map
      if (!locAptMap.containsKey(address)) {
        // case when the hash map doesn't contain any apartments in the same location
        ArrayList<Apartments> addressList = new ArrayList<Apartments>();
        addressList.add(apartment);
        locAptMap.put(address, addressList);
      } else {
        //case when the hash map already contains another apartment in the same location
        locAptMap.get(address).add(apartment);
      }

      // adds the apartment to the number of rooms hash map
      if (!roomAptMap.containsKey(apartment.getNumOfRooms())) {
        // case when the hash map doesn't contain any apartments with the same number of rooms
        ArrayList<Apartments> roomNumList = new ArrayList<Apartments>();
        roomNumList.add(apartment);
        roomAptMap.put(apartment.getNumOfRooms(), roomNumList);
      } else {
        //case when the hash map already contains another apartment with the same number of rooms
        roomAptMap.get(apartment.getNumOfRooms()).add(apartment);
      }

      // hash code for price attribute of apartment - same as the one used in data hasher
      int priceHash = ((int) apartment.getPrice() / 1000);
      
      // adds the apartment to the price hash map
      if (!priceAptMap.containsKey(priceHash)) {
        // case when the hash map doesn't contain any apartments in the same price range
        System.out.println("here");
        ArrayList<Apartments> priceList = new ArrayList<Apartments>();
        priceList.add(apartment);
        priceAptMap.put(priceHash, priceList);
      } else {
        //case when the hash map already contains another apartment in the same price range
        priceAptMap.get(priceHash).add(apartment);
      }
      
    } else {
      // case if apartment already exists in name hash map
      System.out.println("This apartment has already been listed");
    }
  }

  /**
   * Searches the hash table using the aptNameNum as key
   * 
   * @param aptNameNum the Name and Number of the apartment
   * @return the apartment object with matching key
   */
  public Apartments searchByName(String aptNameNum) {
    Apartments returnApartment = searcher.searchByName(aptNameNum, nameAptMap);
    return returnApartment;
  }

  /**
   * Searches the hash table using the location as key
   * 
   * @param location the address of the apartment
   * @return the apartment objects with matching key
   */
  public ArrayList<Apartments> searchByLoc(String location) {
    ArrayList<Apartments> returnApartments = searcher.searchByLoc(location, locAptMap);
    return returnApartments;
  }

  /**
   * Searches the hash table using the number of rooms as key
   * 
   * @param numOfRooms the number of rooms in the apartment
   * @return the apartment objects with matching key
   */
  public ArrayList<Apartments> searchByRoomNum(int numOfRooms) {
    ArrayList<Apartments> returnApartments = searcher.searchByRoomNum(numOfRooms, roomAptMap);
    return returnApartments;
  }

  /**
   * Searches the hash table using the price range the price falls under as key
   * 
   * @param priceRange the range the price of apartment falls under
   * @return the apartment object with matching key
   */
  public ArrayList<Apartments> searchByPrice(int priceRange) {
    ArrayList<Apartments> returnApartments = searcher.searchByPrice(priceRange, priceAptMap);
    return returnApartments;
  }

  /**
   * Compares the selected arrays
   * 
   * @param compareApartments the apartment that needs to be compared
   * @return the apartment objects with matching key
   */
  public ArrayList<Apartments> compareTo(ArrayList<String> compareApartments) {
    ArrayList<Apartments> returnApartments =
        searcher.compareTo(compareApartments, nameAptMap);
    return returnApartments;
  }

}
