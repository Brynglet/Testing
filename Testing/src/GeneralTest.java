import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneralTest {

	static final int CHANGE_VALUE= 451;

	/* Available bills in machine ($100 dollar bills, $50 dollar bills etc )*/
	final List<Integer> moneys = Arrays.asList(100, 50, 20, 5, 1);

	private List<Integer> closestExchane;
	private int numberOfBillsAndCoinsInMachine;

	public static void main(String argv[]) {

		GeneralTest gt = new GeneralTest();
		gt.setClosestExchane(new ArrayList<>());
		gt.printChangeArray();
		System.out.println("Exchane:" + gt.getClosestExchane().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()) +"= $" + gt.getClosestExchane().stream().mapToInt(Integer::intValue).sum());
	}

	public void printChangeArray() {

		List<Integer> moneyInMachineBank = getMoneyInMachineBank(false);

		this.setNumberOfBillsAndCoinsInMachine(moneyInMachineBank.size());
		calculateExchangeToCustomer(moneyInMachineBank, null);
	}

	private void calculateExchangeToCustomer(List<Integer> moneyInMachine, List<Integer> ret) {

		for (int k = 0; k < moneyInMachine.size(); k++) {

			if (moneyInMachine.size() == this.getNumberOfBillsAndCoinsInMachine()) {
				/* Highest level , clear out. */
				ret = new ArrayList<>();
			}

			int retSum = ret.stream().mapToInt(Integer::intValue).sum();

			if ((moneyInMachine.get(k) + retSum) > CHANGE_VALUE) {
				continue;
			} else {

				ret.add(moneyInMachine.get(k));

				if (ret.stream().mapToInt(Integer::intValue).sum() > this.getClosestExchane().stream().mapToInt(Integer::intValue).sum()) {
					this.setClosestExchane(ret);
					/* TODO 1. break/exit/return when == ; 2. keep looking for the fewest bills */
				}

				List<Integer> subList = moneyInMachine.subList(k+1, moneyInMachine.size());
				calculateExchangeToCustomer(subList, ret);
			}
		}
	}

	/**
	 * getManyRandom false: Get about 10 defined bills/coins
	 * getManyRandom true: Get 100 random defined bills/coins
	 * @return
	 */
	private List<Integer> getMoneyInMachineBank(boolean getManyRandom) {

		List<Integer> ret = new ArrayList<Integer>();

		if (getManyRandom == true) {

			for (int k = 0; k < 100; k++) {

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
			 * Good for testing. Initial:
			 * 100:2
			 * 50:3
			 * 20:3
			 * 5:3
			 * 1:2
			 */

			/**
			 * NB! Another good test is
			 * 100:1
			 * 40:3
			 * 1:2
			 *
			 * And let the changeValue be 120 and then 121
			 */

			List<Integer> hundreds = IntStream.range(0, 2).mapToObj(i -> moneys.get(0)).collect(Collectors.toList());
			List<Integer> fifties = IntStream.range(0, 3).mapToObj(i -> moneys.get(1)).collect(Collectors.toList());
			List<Integer> twenties = IntStream.range(0, 3).mapToObj(i -> moneys.get(2)).collect(Collectors.toList());
			List<Integer> fives = IntStream.range(0, 3).mapToObj(i -> moneys.get(3)).collect(Collectors.toList());
			List<Integer> ones = IntStream.range(0, 2).mapToObj(i -> moneys.get(4)).collect(Collectors.toList());

			ret.addAll(ones);
			ret.addAll(fives);
			ret.addAll(twenties);
			ret.addAll(fifties);
			ret.addAll(hundreds);

		}

		return ret
				.stream()
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
	}

	public List<Integer> getClosestExchane() {
		return closestExchane;
	}

	public void setClosestExchane(List<Integer> closestExchane) {
		this.closestExchane = closestExchane;
	}

	public int getNumberOfBillsAndCoinsInMachine() {
		return numberOfBillsAndCoinsInMachine;
	}

	public void setNumberOfBillsAndCoinsInMachine(int numberOfBillsAndCoinsInMachine) {
		this.numberOfBillsAndCoinsInMachine = numberOfBillsAndCoinsInMachine;
	}
}
