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

	static final int CHANGE_VALUE = 87;

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
		List<Integer> intitialMoneyInMachine = getInitialMoneyInTheVendingMAchine();
		System.out.println("Initial monyes in vending machine:" + intitialMoneyInMachine);
		this.setIntitialNumberOfMoneyInMachine(intitialMoneyInMachine.size());
		this.calculateDispense(intitialMoneyInMachine, null);
	}

	private void calculateDispense(List<Integer> moneyLeftInMachine, List<Integer> tracker) {

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
					int numberOfmoneyLeftInMachineSumFirstItemMinusOne = Collections.frequency(moneyLeftInMachine, moneyLeftInMachine.get(0)) - 1;
					if ((numberOfmoneyLeftInMachineSumFirstItemMinusOne > 0) && ((trackerSum + (numberOfmoneyLeftInMachineSumFirstItemMinusOne * moneyLeftInMachine.get(0))) <= CHANGE_VALUE)) {
						/* Make less recursion. */
						tracker.addAll(moneyLeftInMachine.subList(0, numberOfmoneyLeftInMachineSumFirstItemMinusOne));
						subList = moneyLeftInMachine.subList(numberOfmoneyLeftInMachineSumFirstItemMinusOne, moneyLeftInMachine.size());
						//k+=numberOfmoneyLeftInMachineSumFirstItemMinusOne;
					} else {
						tracker.add(moneyLeftInMachine.get(k));
						subList = moneyLeftInMachine.subList(k+1, moneyLeftInMachine.size());
						//k++;
					}
				}

				trackerSum = tracker.stream().mapToInt(Integer::intValue).sum();

				if (trackerSum >= currHighestDispense) {
					if (trackerSum > currHighestDispense) {
						closestExchaneMap = new HashMap<>();
						currHighestDispense = trackerSum;
						List<List<Integer>> tmp = new ArrayList<List<Integer>>();
						tmp.add(tracker);
						closestExchaneMap.put(trackerSum, tmp);
					} else {
						List<List<Integer>> tmp = closestExchaneMap.get(currHighestDispense);
						if (!tmp.contains(tracker)) {
							tmp.add(tracker);
							closestExchaneMap.put(trackerSum, tmp);
						}
					}
				}

				if (doBreak) {
					break;
				}

				this.setIntitialNumberOfMoneyInMachine(subList.size());
				this.calculateDispense(subList, tracker);
			}
		}
	}


	private List<Integer> getInitialMoneyInTheVendingMAchine() {
		List<Integer> ret = new ArrayList<Integer>();
		final List<Integer> moneys = Arrays.asList(100, 40, 20, 5, 1);
		ret.addAll(IntStream.range(0, 7).mapToObj(i -> moneys.get(0)).collect(Collectors.toList()));
		ret.addAll(IntStream.range(0, 7).mapToObj(i -> moneys.get(1)).collect(Collectors.toList()));
		ret.addAll(IntStream.range(0, 7).mapToObj(i -> moneys.get(2)).collect(Collectors.toList()));
		ret.addAll(IntStream.range(0, 7).mapToObj(i -> moneys.get(3)).collect(Collectors.toList()));
		ret.addAll(IntStream.range(0, 7).mapToObj(i -> moneys.get(4)).collect(Collectors.toList()));
		return ret.stream().filter(i -> i > 0).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	}

	public int getIntitialNumberOfMoneyInMachine() {
		return intitialNumberOfMoneyInMachine;
	}

	public void setIntitialNumberOfMoneyInMachine(int intitialNumberOfMoneyInMachine) {
		this.intitialNumberOfMoneyInMachine = intitialNumberOfMoneyInMachine;
	}
}