import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * Todo
 *
 * 1. break/exit/return when ==
 * 2. keep looking for the fewest bills
 * 3. general case where the machine has 1000s of bills. Solution: Use 2 data sets, one for the majority and one that is handled in recursion. The ones that are used must be calculated.
 *
 * 4. connect these to the this object (insted of sending them around in the recursion):
 * List<Integer> moneyLeftInMachine, List<Integer> tracker, then calculateExchangeToCustomer has no parameters.
 *
 * 5. CHANGE_VALUE = 122; gives an interesting answer when final List<Integer> moneys = Arrays.asList(100, 40, 7 , 2, 1); and 3 of each:
 * Exchane:[40, 40, 40, 2] = $122. Why not [100 7 7 7 1]?
 *
 * Answer it checks 40 40 40 2 before. Proof: final List<Integer> moneys = Arrays.asList(100, 23, 20, 1, 1); and 120 works fine: Exchane:[100, 20] = $120
 */
public class GeneralTest {

	/* Set to 0 to time the whole thing (recurse everything) if returns and/or breaks/exits are used */
	static final int CHANGE_VALUE = 120;

	/* Available bills in machine. The number of each one is in putMoneyInMachineBank() */
	final List<Integer> moneys = Arrays.asList(100, 40, 1 , 1, 1);

	private List<Integer> closestExchane;

	private int intitialNumberOfMoneyInMachine;

	public static void main(String argv[]) {
		GeneralTest gt = new GeneralTest();
		gt.run();
		System.out.println("Exchane:" + gt.getClosestExchane().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()) +" = $" + gt.getClosestExchane().stream().mapToInt(Integer::intValue).sum());
	}

	public void run() {
		List<Integer> intitialMoneyInMachineBank = putMoneyInMachineBank(false);
		this.setIntitialNumberOfMoneyInMachine(intitialMoneyInMachineBank.size());
		calculateDispense(intitialMoneyInMachineBank, null);
	}

	private void calculateDispense(List<Integer> moneyLeftInMachine, List<Integer> tracker) {
		for (int k = 0; k < moneyLeftInMachine.size(); k++) {
			if (moneyLeftInMachine.size() == this.getIntitialNumberOfMoneyInMachine()) {
				tracker = new ArrayList<>();
			}
			if ((moneyLeftInMachine.get(k) + tracker.stream().mapToInt(Integer::intValue).sum()) > CHANGE_VALUE) {
				continue;
			} else {
				tracker.add(moneyLeftInMachine.get(k));
				if (tracker.stream().mapToInt(Integer::intValue).sum() > this.getClosestExchane().stream().mapToInt(Integer::intValue).sum()) {
					this.setClosestExchane(tracker);
				}
				this.setIntitialNumberOfMoneyInMachine(moneyLeftInMachine.subList(k+1, moneyLeftInMachine.size()).size());
				calculateDispense(moneyLeftInMachine.subList(k+1, moneyLeftInMachine.size()), tracker);
			}
		}
	}

	private List<Integer> putMoneyInMachineBank(boolean getManyRandom) {

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

			List<Integer> hundreds = IntStream.range(0, 3).mapToObj(i -> moneys.get(0)).collect(Collectors.toList());
			List<Integer> fifties = IntStream.range(0, 3).mapToObj(i -> moneys.get(1)).collect(Collectors.toList());
			List<Integer> twenties = IntStream.range(0, 3).mapToObj(i -> moneys.get(2)).collect(Collectors.toList());
			List<Integer> fives = IntStream.range(0, 3).mapToObj(i -> moneys.get(3)).collect(Collectors.toList());
			List<Integer> ones = IntStream.range(0, 3).mapToObj(i -> moneys.get(4)).collect(Collectors.toList());

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
		return closestExchane != null ? closestExchane : new ArrayList<Integer>();
	}

	public void setClosestExchane(List<Integer> closestExchane) {
		this.closestExchane = closestExchane;
	}

	public int getIntitialNumberOfMoneyInMachine() {
		return intitialNumberOfMoneyInMachine;
	}

	public void setIntitialNumberOfMoneyInMachine(int intitialNumberOfMoneyInMachine) {
		this.intitialNumberOfMoneyInMachine = intitialNumberOfMoneyInMachine;
	}
}
