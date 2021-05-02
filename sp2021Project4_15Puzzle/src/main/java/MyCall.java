import java.util.ArrayList;
import java.util.concurrent.Callable;

class MyCall implements Callable<ArrayList<Node>> {
	int puzzelArray[] = new int[16];
	//int puzzelArray[];
	int hueristicType;
	A_IDS_A_15solver object;
	String s;
	MyCall(int array[], int h, String input){
		puzzelArray = array.clone();
		hueristicType = h;
		s = input;
		object = new A_IDS_A_15solver(hueristicType, puzzelArray);
	}
	@Override
	public ArrayList<Node> call() throws Exception {
		// TODO Auto-generated method stub
		//Node newNode = new Node(puzzleArray);
		ArrayList<Node> solutionList = new ArrayList<Node>();
		solutionList.addAll(object.getSolution());
//			solutionList = object.getSolution();
		return solutionList;
	}


}


