
public class RecursionTest {

	public static void main(String[] args) {
	
		System.out.println(fact(2));
		
	}

	static int fact(int number){
		
		if (number == 0) {
			return 1;
		}
		number = number * fact(number - 1);
		
		return number;
		
	}

}