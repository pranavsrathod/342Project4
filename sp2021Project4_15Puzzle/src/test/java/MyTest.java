import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {
	
	static A_IDS_A_15solver aiObject1;
	static A_IDS_A_15solver aiObject2;
	static int array1[] = {0, 14, 13, 12, 15, 9, 5, 8, 11, 7, 4, 1, 3, 10, 6, 2};
	@BeforeAll
	static void start() {
		aiObject1 = new A_IDS_A_15solver(1, array1);
		aiObject2 = new A_IDS_A_15solver(2, array1);
	}
	
	@Test
	void testNode() {
		Node nodeObject = new Node (array1);
		int array[] = nodeObject.getKey();
		for (int i = 0; i < array1.length; i++) {
			assertEquals(array1[i], array[i]);
		}
	}
	@Test
	void testNode2() {
		Node nodeObject = new Node (array1);
		int array[] = nodeObject.getKey();
		for (int i = 0; i < array1.length; i++) {
			assertEquals(array1[i], array[i]);
		}
		assertEquals("[0, 14, 13, 12, 15, 9, 5, 8, 11, 7, 4, 1, 3, 10, 6, 2]", nodeObject.getKey2());
		ArrayList<Node> solutionPath1 = aiObject1.getSolution();
		assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", solutionPath1.get(solutionPath1.size()-1).getKey2());
		ArrayList<Node> solutionPath2 = aiObject2.getSolution();
		assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", solutionPath2.get(solutionPath2.size()-1).getKey2());
		
		assertNotEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", solutionPath2.get(solutionPath1.size()-1).getKey2());
		// assertNotEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", solutionPath2.get(solutionPath1.size()-1).getKey2());
	}
	
	@Test
	void testUserInterFace() {
		UserInterface userObject = new UserInterface("0 14 13 12 15 9 5 8 11 7 4 1 3 10 6 2");
		int array[] = userObject.getPuzzle();
		for (int i = 0; i < array1.length; i++) {
			assertEquals(array1[i], array[i]);
		}
	}
	@Test
	void testNode3() {
		Node nodeObject = new Node (array1);
		int array[] = nodeObject.getKey();
		for (int i = 0; i < array1.length; i++) {
			assertEquals(array1[i], array[i]);
		}
		assertEquals("[0, 14, 13, 12, 15, 9, 5, 8, 11, 7, 4, 1, 3, 10, 6, 2]", nodeObject.getKey2());
		ArrayList<Node> solutionPath1 = aiObject1.getSolution();
		for (int i = 0; i < solutionPath1.size(); i++) {
			System.out.println(i + "+" + solutionPath1.get(i).get_hValue());
			assertEquals(0, solutionPath1.get(i).getDepth());
		}
	}
	
	@Test
	void test1() {
		// test the size of the array returned
		ArrayList<Node> solutionPath1 = aiObject1.getSolution();
		ArrayList<Node> solutionPath2 = aiObject2.getSolution();
		assertEquals(191, solutionPath1.size());
		assertEquals(235, solutionPath2.size());
	}
	
	@Test
	void test2() {
		// testing that the first Node of the array is in the same order as the initial Node of the array
		ArrayList<Node> solutionPath1 = aiObject1.getSolution();
		ArrayList<Node> solutionPath2 = aiObject2.getSolution();
		assertEquals(191, solutionPath1.size());
		assertEquals(235, solutionPath2.size());
		int init1[] = solutionPath1.get(0).getKey();
		int init2[] = solutionPath2.get(0).getKey();
		for (int i = 0; i < init1.length; i++) {
			assertEquals(array1[i], init1[i]);
		}
		for (int i = 0; i < init2.length; i++) {
			assertEquals(array1[i], init2[i]);
		}
	}
	
	
	@Test
	void test3() {
		// When Running heuristicOne no other node other than the last one must have the array as {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}
		ArrayList<Node> solutionPath1 = aiObject1.getSolution();
		assertEquals(191, solutionPath1.size());
		
		for (int i = 1; i < solutionPath1.size()-1; i++) {
			boolean flag = true;
			int array[] =  solutionPath1.get(i).getKey();
			for (int j = 0; j < array.length; j++) {
				if(array[j] != j) {
					flag = false;
					break;
				}
			}
			assertEquals(false, flag);
		}
		
	}
	
	@Test
	void test4() {
		// When Running heuristicTwo no other node other than the last one must have the array as {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}
		ArrayList<Node> solutionPath2 = aiObject2.getSolution();
		assertEquals(235, solutionPath2.size());
		
		for (int i = 1; i < solutionPath2.size()-1; i++) {
			boolean flag = true;
			int array[] =  solutionPath2.get(i).getKey();
			for (int j = 0; j < array.length; j++) {
				if(array[j] != j) {
					flag = false;
					break;
				}
			}
			assertEquals(false, flag);
		}
	}
	@Test
	void test5() {
		// testing that the last Node of the ArrayList is the goalState, which is {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}
		ArrayList<Node> solutionPath1 = aiObject1.getSolution();
		ArrayList<Node> solutionPath2 = aiObject2.getSolution();
		assertEquals(191, solutionPath1.size());
		assertEquals(235, solutionPath2.size());
		int init1[] = solutionPath1.get(0).getKey();
		int init2[] = solutionPath2.get(0).getKey();
		for (int i = 0; i < init1.length; i++) {
			assertEquals(array1[i], init1[i]);
		}
		for (int i = 0; i < init2.length; i++) {
			assertEquals(array1[i], init2[i]);
		}
		
		int final1[] = solutionPath1.get(solutionPath1.size()-1).getKey();
		int final2[] = solutionPath2.get(solutionPath2.size()-1).getKey();
		
		for (int i = 0; i < final1.length; i++) {
			assertEquals(i, final1[i]);
		}
		for (int i = 0; i < final2.length; i++) {
			assertEquals(i, final2[i]);
		}
		
	}

}
