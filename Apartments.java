public class Apartments {
  private String aptNameNum;
  private String location;
  private int numOfRooms;
  private float price;
  private String phoneNum;

  /**
   * Construct method for the class
   * 
   * @param aptNameNum Name and Number of the Apartment
   * @param location Address of apartment
   * @param numOfRooms Number of Rooms in the apartment
   * @param price Price of the apartment
   * @param phoneNum Phone number to contact the owners
   */
  public Apartments(String aptNameNum, String location, int numOfRooms, float price, String phoneNum) {
    this.aptNameNum = aptNameNum;
    this.location = location;
    this.numOfRooms = numOfRooms;
    this.price = price;
    this.phoneNum = phoneNum;
  }

  /**
   * Prints the data of the apartment
   */
  @Override
  public String toString() {
    return "Apartment Name: " + aptNameNum + "\nAddress: " + location + "\nNumber of Rooms:" + numOfRooms +
        "\nRental Price: " + price + "\nContact Number: " + phoneNum;
  }

  /*
   * Returns the name and number of the Apartment
   */
  public String getAptNameNum() {
    return aptNameNum;
  }

  /*
   * Returns the address of apartment
   */
  public String getLocation() {
    return location;
  }

  /*
   * Returns the number of Rooms in the apartment
   */
  public int getNumOfRooms() {
    return numOfRooms;
  }

  /*
   * Returns the price of the apartment
   */
  public float getPrice() {
    return price;
  }

  /*
   * Returns the phone number to contact the owners
   */
  public String getPhoneNum() {
    return phoneNum;
  }

}
