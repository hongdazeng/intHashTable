class hashNode {
	hashNode next;
	int key;
	int value;

	public hashNode(int akey, int avalue) {
		key = akey;
		value = avalue;
		next = null;
	}
}

public class myHashTable {
	public hashNode[] table;

	public myHashTable() {
		table = new hashNode[101];
	}

	public int myHash(int akey) {
		int hashValue = akey % 101;
		return hashValue;
	}

	public void insert(int akey, int avalue) {
		int location = myHash(akey);
		hashNode newNode = new hashNode (akey, avalue);
		if (table[location] == null) {
			table[location] = newNode;
		} else {
			newNode.next = table[location];
			table[location] = newNode;
		}
	}

	public void delete(int akey) {
		int location = myHash(akey);
		if (table[location] == null) {
			return;
		} else {
			hashNode thisNode = table[location];
			hashNode prevNode = null;
			while (thisNode.key != akey) {
				prevNode = thisNode;
				thisNode = thisNode.next;
				if (thisNode == null) {
					return;
				}
			}

			if (thisNode.key == akey) {
				if (prevNode == null) {
					table[location] = thisNode.next;
				} else {
					prevNode.next = thisNode.next;
				}
			} else {
				return;
			}
		}
	}

	public int getValue (int akey) {
		int location = myHash(akey);
		if (table[location] == null) {
			return -1;
		} else {
			hashNode thisNode = table[location];
			while (thisNode.key != akey) {
				thisNode = thisNode.next;
				if (thisNode == null) {
					return -1;
				}
			}

			if (thisNode.key == akey) {
				return thisNode.value;
			} else {
				return -1;
			}
		}
	}

	public void dump () {
		int count = 0;
		for (hashNode a : table) {
			System.out.print("Bucket " + count + ": ");
			if (table[count] == null) {
				System.out.println("Empty");
			} else {
				hashNode thisNode = a;
				while (thisNode != null) {
					System.out.print("Key " + thisNode.key + " Value " + thisNode.value + ", ");
					thisNode = thisNode.next;
				}
				System.out.println();
			}

			count++;
		}
	}

	public static void main(String[] args) {
		myHashTable newTable = new myHashTable();
		for (int i = 0; i < 500; i++) {
			newTable.insert(i, i * 4);
		}

		for (int i = 20; i < 500; i = i + 100) {
			newTable.delete(i);
		}

		for (int i = 0; i < 10; i++) {
			System.out.println("GET: " + i + " is " + newTable.getValue(i));
		}

		for (int i = 490; i < 510; i++) {
			System.out.println("GET: " + i + " is " + newTable.getValue(i));
		}

		for (int i = 100; i < 300; i++) {
			newTable.delete(i);
		}

		newTable.dump();
	}
}
