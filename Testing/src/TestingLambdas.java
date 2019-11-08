//Lambdas are functions that exists in isolation
//Functions can be treated as values

@FunctionalInterface
interface Cab {
	void play(String type);
}

public class TestingLambdas {
	public static void main(String[] args) {
		Cab cab2 = (type) -> System.out.println("Play " + type);
		cab2.play("Bandy");
	}
}
