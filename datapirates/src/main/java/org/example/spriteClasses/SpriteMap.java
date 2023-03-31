package org.example.spriteClasses;

public class SpriteMap<K> {

  K key;

  int amount;
  String rootName;

  public SpriteMap(K key, int quantity, String fileName) {
    this.key = key;
    amount = quantity;
    rootName = fileName;
  }

  public K getKey() {
    return key;
  }


}
