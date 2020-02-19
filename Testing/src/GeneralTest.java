import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneralTest {

	static List<Integer> closestExchane = new ArrayList<>();

	static int changeValue = 64;

	/* Available bills in machine */
	final List<Integer> moneys = Arrays.asList(100, 50, 20, 5, 1);

	public static void main(String argv[]) {

		GeneralTest gt = new GeneralTest();
		gt.printChangeArray();
		System.out.println("Exchane:" + closestExchane +"= $" + closestExchane.stream().mapToInt(Integer::intValue).sum());
	}

	public void printChangeArray() {

		List<Integer> moneyInMachineBank = getMoneyInMachineBank(true);
		calculateExchangeToCustomer(moneyInMachineBank, new ArrayList<Integer>());
	}

	private void calculateExchangeToCustomer(List<Integer> moneyInMachineBank, List<Integer> ret) {

		int retSum = ret.stream().mapToInt(Integer::intValue).sum();

		if ((moneyInMachineBank.get(0) + retSum) <= changeValue) {
			ret.add(moneyInMachineBank.get(0));
			retSum = ret.stream().mapToInt(Integer::intValue).sum();

			if (retSum > closestExchane.stream().mapToInt(Integer::intValue).sum()) {
				closestExchane = ret;
				/* TODO break/exit/return when == */
			}
		}

		List<Integer> subList = moneyInMachineBank.subList(1, moneyInMachineBank.size());

		if (subList.size() > 0) {
			calculateExchangeToCustomer(subList, ret);
		}
	}

	/**
	 * getManyRandom false: Get about 10 defined bills/coins
	 * getManyRandom true: Get 10000 random defined bills/coins
	 * @return
	 */
	private List<Integer> getMoneyInMachineBank(boolean getManyRandom) {

		List<Integer> ret = new ArrayList<Integer>();

		if (getManyRandom == true) {
			for (int k = 0; k < 1000; k++) {
				int rand = (int)(Math.random() * 5);

				switch(rand){
				case 0:
					ret.add(moneys.get(0));
				    break;
				case 1:
					ret.add(moneys.get(1));
				    break;
				case 2:
					ret.add(moneys.get(2));
				    break;
				case 3:
					ret.add(moneys.get(3));
				    break;
				case 4:
					ret.add(moneys.get(4));
				    break;
				}

			}

		} else {

			/**
			 * Good for testing
			 * 100:2
			 * 50:1
			 * 20:3
			 * 5:3
			 * 1:2
			 */

			List<Integer> hundreds = IntStream.range(0, 2).mapToObj(i -> moneys.get(0)).collect(Collectors.toList());
			List<Integer> fifties = IntStream.range(0, 1).mapToObj(i -> moneys.get(1)).collect(Collectors.toList());
			List<Integer> twenties = IntStream.range(0, 3).mapToObj(i -> moneys.get(2)).collect(Collectors.toList());
			List<Integer> fives = IntStream.range(0, 3).mapToObj(i -> moneys.get(3)).collect(Collectors.toList());
			List<Integer> ones = IntStream.range(0, 2).mapToObj(i -> moneys.get(4)).collect(Collectors.toList());

			ret.addAll(twenties);
			ret.addAll(fives);
			ret.addAll(hundreds);
			ret.addAll(ones);
			ret.addAll(fifties);
		}

		return ret
				.stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
	}

}
