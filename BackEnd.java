import java.util.ArrayList;
import java.util.NoSuchElementException;


/*
 * Compares apartments on their price, number of rooms, amenities, and size. Return: ArrayList of
 * Apartment obsjects within these names, null in every spot the name is not list This should return
 * the same size list as the on entered
 */

public class BackEnd {

  /**
   * Searches the hash table and returns information based on the apartment name
   * 
   * @param name the key that is used to search the hash table
   * @param apartmentMap the hash table that is searched
   * @return Apartment object with that name or null if not listed
   */
  public Apartments searchByName(String name, HashTableMap<String, Apartments> apartmentMap) {
    Apartments returnApartment;
    try {
      returnApartment = apartmentMap.get(name);
    } catch (NoSuchElementException e) {
      returnApartment = null;
    }
    return returnApartment;
  }

  /**
   * Searches the hash table and returns information based on the location
   * 
   * @param address the key that is used to search the hash table
   * @param apartmentMap the hash table that is searched
   * @return Apartment object with that name or null if not listed
   */
  public ArrayList<Apartments> searchByLoc(String address,
      HashTableMap<String, ArrayList<Apartments>> apartmentMap) {
    ArrayList<Apartments> returnApartments;
    try {
      returnApartments = apartmentMap.get(address);
    } catch (NoSuchElementException e) {
      returnApartments = null;
    }

    return returnApartments;
  }

  /**
   * Searches the hash table and returns information based on the number of rooms
   * 
   * @param roomss the key that is used to search the hash table
   * @param apartmentMap the hash table that is searched
   * @return Apartment object with that name or null if not listed
   */
  public ArrayList<Apartments> searchByRoomNum(int rooms,
      HashTableMap<Integer, ArrayList<Apartments>> apartmentMap) {
    ArrayList<Apartments> returnApartments;
    try {
      returnApartments = apartmentMap.get(rooms);
    } catch (NoSuchElementException e) {
      returnApartments = null;
    }
    return returnApartments;
  }

  /**
   * Searches the hash table and returns information based on the price range
   * 
   * @param priceRange the key that is used to search the hash table
   * @param apartmentMap the hash table that is searched
   * @return Apartment object with that name or null if not listed
   */
  public ArrayList<Apartments> searchByPrice(int priceRange,
      HashTableMap<Integer, ArrayList<Apartments>> apartmentMap) {
    ArrayList<Apartments> returnApartments;
    try {
      returnApartments = apartmentMap.get(priceRange);
    } catch (NoSuchElementException e) {
      returnApartments = null;
    }
    return returnApartments;
  }

  /**
   * Compares apartments on their price, number of rooms, amenities, and size.
   * 
   * @param compareApartments ArrayList of names of apartments that the user wanted to compare
   * @param apartmentMap the hash table that is searched
   * @return ArrayList of Apartment objects with the names user provided or null if not listed
   */
  public ArrayList<Apartments> compareTo(ArrayList<String> compareApartments,
      HashTableMap<String, Apartments> apartmentMap) {
    ArrayList<Apartments> returnApartments = new ArrayList<Apartments>();
    for (String apt : compareApartments) {
      try {
        returnApartments.add(apartmentMap.get(apt));
      } catch (NoSuchElementException e) {
        returnApartments.add(null);
      }
    }
    return returnApartments;
  }
}
