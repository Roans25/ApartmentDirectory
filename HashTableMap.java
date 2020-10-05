import java.util.NoSuchElementException;

/**
 * Creates a Hash Table Map
 *
 * @author Roans
 *
 * @param <KeyType> Generic Type for key used to hash
 * @param <ValueType> Generic Type for value stored in table
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  HashNode<KeyType, ValueType>[] hashArray; //Array to store key-value pairs
  private int capacity;
  private double loadFactor = 0.8;
  private int size;


  /**
   * Constructor for HashTableMap class.
   *
   * @param capacity total number of values that the hash table can store
   */
  @SuppressWarnings("unchecked")
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    hashArray = new HashNode[capacity];
  }

  /**
   * No argument constructor for HashTableMap class.
   */
  @SuppressWarnings("unchecked")
  public HashTableMap() {
    this.capacity = 10;
    this.size = 0;
    hashArray = new HashNode[10];
  }

  /**
   * Adds the value at the index calculated from hashing the key
   *
   * @key key used to calculate the hash code
   * @value value stored in the hash table
   * @return true if value added to hash table and false if not
   */
  @Override
  public boolean put(KeyType key, ValueType value) {
    int hashIndex = getHashIndex(key);

    if (hashArray[hashIndex] == null)
      hashArray[hashIndex] = new HashNode<KeyType, ValueType>(key, value);
    else {
      HashNode<KeyType, ValueType> hashDouble = hashArray[hashIndex];

      while (hashDouble.next != null && hashDouble.getKey().equals(key))
        hashDouble = hashDouble.next;

      if (hashDouble.getKey().equals(key))
        return false;
      else
        hashDouble.next = new HashNode<KeyType, ValueType>(key, value);
    }

    size++;
    if ((double) size >= capacity * loadFactor)
      resizeArray();

    return true;
  }

  /**
   * Helper method for put()
   * resizes the array by doubling the capacity
   *
   */
  @SuppressWarnings("unchecked")
  private void resizeArray() {
    HashNode<KeyType, ValueType>[] tempArray = hashArray;
    capacity = capacity * 2;
    hashArray = new HashNode[capacity];
    size = 0;

    for (HashNode<KeyType, ValueType> node : tempArray)
      while (node != null) {
        put(node.getKey(), node.getValue());
        node = node.next;
      }
  }

  /**
   * Gets the value with key matching the argument provided
   *
   * @throws NoSuchElementException if there is no value at the index calculated from key
   * @key key used to calculate the hash code
   * @return value required by user
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    int hashIndex = getHashIndex(key);

    if (hashArray[hashIndex] == null)
      throw new NoSuchElementException("There is no element at given key");
    else {
      HashNode<KeyType, ValueType> reqHash = hashArray[hashIndex];

      while (reqHash != null && !reqHash.getKey().equals(key))
        reqHash = reqHash.next;

      if (reqHash == null)
        throw new NoSuchElementException("There is no element at given key");
      else
        return reqHash.getValue();
    }
  }

  /**
   *
   * @return the size of the hash array.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Checks if there is a value at the index calculated from hashing the key
   *
   * @key key used to calculate the hash code
   * @return true if value exists at index and false if not
   */
  @Override
  public boolean containsKey(KeyType key) {
    int hashIndex = getHashIndex(key);
    HashNode<KeyType, ValueType> reqHash = hashArray[hashIndex];

    while (reqHash != null && !reqHash.getKey().equals(key))
      reqHash = reqHash.next;

    if (reqHash == null)
      return false;
    else
      return true;
  }

  /**
   * Removes value at the index calculated from hashing the key
   *
   * @key key used to calculate the hash code
   * @return the value at calculated index if it exists, null if not
   */
  @Override
  public ValueType remove(KeyType key) {
    int hashIndex = getHashIndex(key);

    if(hashArray[hashIndex] !=  null) {
      HashNode<KeyType, ValueType> prevHash = null;
      HashNode<KeyType, ValueType> currHash = hashArray[hashIndex];

      while (currHash.next != null && !currHash.getKey().equals(key)) {
        prevHash = currHash;
        currHash = currHash.next;
      }

      if (currHash.getKey().equals(key)) {
        HashNode<KeyType, ValueType> removedHash = currHash;

        if(prevHash == null)
          hashArray[hashIndex] = currHash.next;
        else
          prevHash.next = currHash.next;

        size--;
        return removedHash.getValue();
      }
    }

    return null;
  }

  /**
   * Clears the entire hash array by setting all values to null
   */
  @Override
  public void clear() {
    for (int i = 0; i < capacity; i++) {
      hashArray[i] = null;
    }
    size = 0;
  }

  /**
   * Helper method that hashes the key to calculate index to place the value in
   *
   * @key key used to calculate the hash code
   * @return the calculated index
   */
  private int getHashIndex(KeyType key) {
    int hashCode = key.hashCode();
    int hashIndex = Math.abs(hashCode) % capacity;
    return hashIndex;
  }

}
