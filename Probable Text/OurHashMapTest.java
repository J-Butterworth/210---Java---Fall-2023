package tests;
//Author: Jason Butterworth.

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;


import org.junit.jupiter.api.Test;

import model.OurHashMap;
import model.OurMap;

class OurHashMapTest {

  @Test
  void testConstructorAndToString() {
     OurMap<String, ArrayList<Character>> map = new OurHashMap<>();
     ArrayList<Character> followers = new ArrayList<>();
     followers.add('U');
     followers.add('A');
     map.put("Alice", followers);
     System.out.println(map.get("Alice").toString());
  }
  
  @Test
  void testPush() {
	  OurMap<String, ArrayList<Character>> map = new OurHashMap<>();
	  ArrayList<Character> followers = new ArrayList<>();
	  followers.add('1');
	  followers.add('4');
	  map.put("soccer", followers);
	  assertEquals(followers, map.get("soccer"));
  }
}