package com.diverse;
//Lambdas are functions that exists in isolation
//Functions can be treated as values

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@FunctionalInterface
interface Cab {
	void play(String type);
}

public class TestingLambdas {
	public static void main(String[] args) {

		System.out.println("kalle0");
		int CHANGE_VALUE = 187;
		List<Integer> tracker = new ArrayList<Integer>();
		tracker.add(1);
		int trackerSum = 1;
		List<Integer> moneyLeftInMachine = Arrays.asList(40,40,40,40,7,8,7,4,5,6,7,33);
		moneyLeftInMachine = Arrays.asList(1, 1, 1, 1, 1, 1, 1, 1, 1);
		System.out.println("kalle1");
		int numberOfmoneyLeftInMachineSumFirstItem = Collections.frequency(moneyLeftInMachine, moneyLeftInMachine.get(0));
		System.out.println("kalle2 numberOfmoneyLeftInMachineSumFirstItem:"+numberOfmoneyLeftInMachineSumFirstItem);

		if ((trackerSum + (numberOfmoneyLeftInMachineSumFirstItem * moneyLeftInMachine.get(0))) <= CHANGE_VALUE) {


			List<Integer> subList1 = moneyLeftInMachine.subList(0, numberOfmoneyLeftInMachineSumFirstItem);

			System.out.println("kalle subList1:" + subList1);

			List<Integer> subList2 = moneyLeftInMachine.subList(numberOfmoneyLeftInMachineSumFirstItem, moneyLeftInMachine.size());

			System.out.println("kalle subList2:" + subList2);

			tracker.addAll(subList1);

			System.out.println("kalle tracker:" + tracker);

		}

	}
}
