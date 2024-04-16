package model;

import java.util.ArrayList;
//Author: Jason Butterworth.

public class OurHashMap<K, V> implements OurMap<K, V> {

	private class HashNode {
		private K key;
		private V value;

		public HashNode(K key, V value) {
			this.key = key;
			this.value = value;
		}

	}

	public static int TABLE_SIZE = 1000000;
	private OurLinkedList<HashNode>[] lists;

	public OurHashMap() {
		lists = new OurLinkedList[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++) {
			lists[i] = new OurLinkedList();
		}
	}

	private int hashCode(K key) {
		return Math.abs(key.hashCode()) % TABLE_SIZE;
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < TABLE_SIZE; i++) {
			result += i + " " + lists[i].toString() + "\n";
		}
		return result;
	}

	@Override
	public V put(K key, V value) {
		int hashCode = hashCode(key);
		OurLinkedList<HashNode> list = lists[hashCode];
		boolean exists = containsKey(key);
		if (exists) {
			for (int i = 0; i < list.size(); i++) {
				HashNode node = list.get(i);
				if (node.key.equals(key)) {
					V firstValue = node.value;
					node.value = value;
					return firstValue;
				}
			}
		} else {
			HashNode newPair = new HashNode(key, value);
			lists[hashCode].addFront(newPair);
		}

		return null;
	}

	@Override
	public int size() {
		int mapCount = 0;
		// Used this for loop instead because we can't use i=0 since each index is
		// made with the hashCode method. (Can't do 0,1,2,3...)
		for (OurLinkedList<HashNode> linkedList : lists) {
			mapCount += linkedList.size();
		}
		return mapCount;
	}

	@Override
	public V get(K key) {
		int hashCode = hashCode(key);
		OurLinkedList<HashNode> list = lists[hashCode];
		boolean exists = containsKey(key);
		if (exists) {
			for (int i = 0; i < list.size(); i++) {
				HashNode node = list.get(i);
				if (node.key.equals(key)) {
					return node.value;
				}
			}
		}
		return null;
	}

	@Override
	public boolean containsKey(K key) {
		int hashCode = hashCode(key);
		OurLinkedList<HashNode> list = lists[hashCode];
		for (int i = 0; i < list.size(); i++) {
			HashNode node = list.get(i);
			if (node.key.equals(key)) {
				return true;
			}
		}
		return false;
	}
}
