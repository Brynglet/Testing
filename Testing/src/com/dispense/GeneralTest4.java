package com.dispense;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneralTest4 {

	static final int CHANGE_VALUE = 88;

	private static Map<Integer, List<List<Integer>>> closestExchaneMap = new HashMap<>();
	private static int currHighestDispense = 0;

	private int intitialNumberOfMoneyInMachine;

	public static void main(String argv[]) {
		GeneralTest4 gt4 = new GeneralTest4();
		gt4.run();

		System.out.println("*********Result*********");
		System.out.println("All these dispsnse lists can be returned to customer:" + closestExchaneMap);

		/* Get as many bills and coins as possible to customer: */
		List<Integer> result =
				closestExchaneMap.get(currHighestDispense).stream()
				.sorted(Comparator.comparingInt(List::size))
				.findFirst().orElse(new ArrayList<>());
		System.out.println("Hi customer, here is FEW bills and coins: $" + currHighestDispense + "= " + result);

		/* Get as few bills and coins as possible to customer: */
		result =
				closestExchaneMap.get(currHighestDispense).stream()
				.sorted((list1, list2)->(list2.size() - list1.size()))
				.findFirst().orElse(new ArrayList<>());
		System.out.println("Hi customer, here is MUCH bills and coins: $" + currHighestDispense + "= " + result);
	}

	public void run() {
		List<Integer> intitialMoneyInMachine = getInitialMoneyInTheVendingMAchine(false);
		System.out.println("Initial monyes in vending machine:" + intitialMoneyInMachine);
		this.setIntitialNumberOfMoneyInMachine(intitialMoneyInMachine.size());
		calculateDispense(intitialMoneyInMachine, null);
	}

	private void calculateDispense(List<Integer> moneyLeftInMachine, List<Integer> tracker) {

		if (moneyLeftInMachine.size() == 0) System.out.println("SIZE00000");

		for (int k = 0; k < moneyLeftInMachine.size(); k++) {

			if (moneyLeftInMachine.size() == this.getIntitialNumberOfMoneyInMachine()) {
				tracker = new ArrayList<>();
			}

			int trackerSum = tracker.stream().mapToInt(Integer::intValue).sum();

			if ((moneyLeftInMachine.get(k) + trackerSum) > CHANGE_VALUE) {
				continue;
			} else {

				int moneyLeftInMachineSum = moneyLeftInMachine.stream().mapToInt(Integer::intValue).sum();

				List<Integer> subList = null;
				boolean doBreak = false;
				if ((trackerSum + moneyLeftInMachineSum) <= CHANGE_VALUE) {
					/* No need for recursion in this case */
					tracker.addAll(moneyLeftInMachine);
					doBreak = true;
				} else {
					int numberOfmoneyLeftInMachineSumFirstItem = Collections.frequency(moneyLeftInMachine, moneyLeftInMachine.get(0));
					if ((trackerSum + (numberOfmoneyLeftInMachineSumFirstItem * moneyLeftInMachine.get(0))) <= CHANGE_VALUE) {
						tracker.addAll(moneyLeftInMachine.subList(0, numberOfmoneyLeftInMachineSumFirstItem));
						subList = moneyLeftInMachine.subList(numberOfmoneyLeftInMachineSumFirstItem, moneyLeftInMachine.size());
					} else {
						tracker.add(moneyLeftInMachine.get(k));
						subList = moneyLeftInMachine.subList(k+1, moneyLeftInMachine.size());
					}
				}

				trackerSum = tracker.stream().mapToInt(Integer::intValue).sum();

				if (trackerSum >= currHighestDispense) {
					if (trackerSum > currHighestDispense) {
						closestExchaneMap = new HashMap<>();
					}
					currHighestDispense = trackerSum;
					List<List<Integer>> tmp = new ArrayList<List<Integer>>();
					if (closestExchaneMap.get(currHighestDispense) != null) {
						tmp.addAll(closestExchaneMap.get(currHighestDispense));
					}
					if (!tmp.contains(tracker)) {
						tmp.add(tracker);
					}
					closestExchaneMap.put(trackerSum, tmp);
				}

				if (doBreak) {
					break;
				}

				this.setIntitialNumberOfMoneyInMachine(subList.size());
				calculateDispense(subList, tracker);
			}
		}
	}

	private List<Integer> getInitialMoneyInTheVendingMAchine(boolean getManyRandom) {

		List<Integer> ret = new ArrayList<Integer>();

		/* Available bills in machine. The number of each one is in putMoneyInMachine() */
		final List<Integer> moneys = Arrays.asList(100, 40, 20, 5, 1);

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

			List<Integer> hundreds = IntStream.range(0, 10).mapToObj(i -> moneys.get(0)).collect(Collectors.toList());
			List<Integer> fifties = IntStream.range(0, 10).mapToObj(i -> moneys.get(1)).collect(Collectors.toList());
			List<Integer> twenties = IntStream.range(0, 10).mapToObj(i -> moneys.get(2)).collect(Collectors.toList());
			List<Integer> fives = IntStream.range(0, 10).mapToObj(i -> moneys.get(3)).collect(Collectors.toList());
			List<Integer> ones = IntStream.range(0, 10).mapToObj(i -> moneys.get(4)).collect(Collectors.toList());

			ret.addAll(ones);
			ret.addAll(fives);
			ret.addAll(twenties);
			ret.addAll(fifties);
			ret.addAll(hundreds);

		}

		return ret.stream()
				.filter(i -> i > 0)
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toList());
	}

	public int getIntitialNumberOfMoneyInMachine() {
		return intitialNumberOfMoneyInMachine;
	}

	public void setIntitialNumberOfMoneyInMachine(int intitialNumberOfMoneyInMachine) {
		this.intitialNumberOfMoneyInMachine = intitialNumberOfMoneyInMachine;
	}
}
