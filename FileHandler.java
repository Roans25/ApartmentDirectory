import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
  ArrayList<String> apartmentList = new ArrayList<String>();
  
  /**
   * This methods scans the file and adds the data line by line to an array list
   * 
   * @param fileName the file needed to be scanned.
   * @return the arrayList
   */
  public ArrayList<String> fileToList(String fileName) {
    try {
      // uses scanner to read from the file
      Scanner fileScanner = new Scanner(new File(fileName));

      while (fileScanner.hasNextLine())
        apartmentList.add(fileScanner.nextLine());

      fileScanner.close();
    } catch (FileNotFoundException e) {
      System.out.println("Can't find the file you are looking for.");
    }

    return apartmentList;
  }

  /**
   * Uses a BufferedWriter to write the contents of an array to the file.
   * 
   * @param fileName the file to write to
   */
  public void listToFile(String Apartment, String fileName) {
    BufferedWriter fileWriter;
    apartmentList.add(Apartment);
    try {
      // selects the file to write to and clears it
      fileWriter = new BufferedWriter(new FileWriter(fileName));
      // buffered writer writes to the selected text file
      for (int x = 0; x < apartmentList.size(); x++) {
        fileWriter.write(apartmentList.get(x));
        fileWriter.newLine();
      }
      fileWriter.close();
    } catch (IOException e) {
      System.out.println("Can't find the file you are looking for.");
    }
  }

}
