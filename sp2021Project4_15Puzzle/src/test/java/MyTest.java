import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MyTest {
	
	A_IDS_A_15solver aiObject1;
	A_IDS_A_15solver aiObject2;
	int array1[] = {0, 14, 13, 12, 15, 9, 5, 8, 11, 7, 4, 1, 3, 10, 6, 2};
	@BeforeEach
	void start() {
		aiObject1 = new A_IDS_A_15solver(1, array1);
		aiObject2 = new A_IDS_A_15solver(2, array1);
	}
	@Test
	void test1() {
		// test the size of the array returned
		ArrayList<Node> solutionPath1 = aiObject1.getSolution();
		ArrayList<Node> solutionPath2 = aiObject2.getSolution();
		assertEquals(191, solutionPath1.size());
		assertEquals(235, solutionPath2.size());
	}

}
