import java.util.ArrayList;
import java.util.concurrent.Callable;

class MyCall implements Callable<ArrayList<Integer>> {
	int puzzelArray[];
	int hueristicType;
	MyCall(int array[], int h){
		puzzelArray = array;
		hueristicType = h;
	}
	@Override
	public ArrayList<Integer> call() throws Exception {
		// TODO Auto-generated method stub
		
		return null;
	}

}
