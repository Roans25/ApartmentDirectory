/**
 * Class represents a node in the Hash table
 * @author Roans
 *
 * @param <KeyType>
 * @param <ValueType>
 */
public class HashNode<KeyType, ValueType> {
  private KeyType key;
  private ValueType value;
  HashNode<KeyType, ValueType> next;

  /**
   * Constructor for the HashNode class
   *
   * @param key used to calculate the hash code
   * @param value stored in the hash table
   */
  public HashNode(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
    this.next = null;
  }

  /**
   * Getter method for key
   *
   * @return key
   */
  public KeyType getKey() {
    return key;
  }

  /**
   * Setter method for key
   *
   */
  public void setKey(KeyType key) {
    this.key = key;
  }

  /**
   * Getter method for value
   *
   * @return value
   */
  public ValueType getValue() {
    return value;
  }

  /**
   * Setter method for value
   *
   */
  public void setValue(ValueType value) {
    this.value = value;
  }

}
