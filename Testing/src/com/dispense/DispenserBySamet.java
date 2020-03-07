package com.dispense;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class DispenserBySamet {

  public static void main(String[] args) {

    long startTime = System.currentTimeMillis();
    String initial = "100, 100, 100, 100, 100, 100, 100, 40, 3, 20, 7, 5, 7, 1, 7";

    StringBuilder initialSB = new StringBuilder();
    initialSB.append("100,");initialSB.append("100,");initialSB.append("100,");initialSB.append("100,");initialSB.append("100,");initialSB.append("100,");initialSB.append("100,");
    initialSB.append("40,");initialSB.append("40,");initialSB.append("40,");initialSB.append("40,");initialSB.append("40,");initialSB.append("40,");initialSB.append("40,");
    initialSB.append("20,");initialSB.append("20,");initialSB.append("20,");initialSB.append("20,");initialSB.append("20");initialSB.append("20,");initialSB.append("20,");
    initialSB.append("5,");initialSB.append("5,");initialSB.append("5,");initialSB.append("5,");initialSB.append("5,");initialSB.append("5,");initialSB.append("5,");
    initialSB.append("1,");initialSB.append("1,");initialSB.append("1,");initialSB.append("1,");initialSB.append("1,");initialSB.append("1,");initialSB.append("1");
    initial=initialSB.toString();
    //initial = "100, 100, 100, 40, 40, 40, 20, 20, 20, 5, 1, 1";

    StringTokenizer st = new StringTokenizer(initial,",");
    int target = 87;

    Integer[][] initialLoad = new Integer[st.countTokens() / 2][2];
    int counter = 0;
    while(st.hasMoreTokens()){
      initialLoad[counter][0] = Integer.valueOf(st.nextToken().trim());
      initialLoad[counter++][1] = Integer.valueOf(st.nextToken().trim());
    }


    List<CurrencyList> resultLists = getPossibleResults(initialLoad, target);
    int max = resultLists.stream().mapToInt(item -> item.sum).max().getAsInt();
    resultLists = resultLists.stream()
        .filter(item -> item.sum == max).sorted().collect(Collectors.toList());

    System.out.println("All possible results for $" + target);
    int notesQuantity = -1;
    for (CurrencyList curList : resultLists) {
      if(curList.list.size() != notesQuantity){
        notesQuantity = curList.list.size();
        System.out.println("Result(s) for " + notesQuantity + " pieces of notes:");
      }
      System.out.println(Arrays.toString(curList.list.toArray()) + " - This combination can be producted in " + curList.getProductibility(initialLoad) + " times.");
    }

    System.out.print("Calculation time is " + (System.currentTimeMillis() - startTime) + " millis.");


  }

  private static List<CurrencyList> getPossibleResults(Integer[][] notes, int target) {
      List<CurrencyList> result = new ArrayList<>();
      Map<String, Integer> signatureMap = new HashMap<>();

    for (Integer[] note : notes) {
      for (int j = 0; j < note[1]; j++) {
        int n = result.size();
        int val = note[0];
        for (int k = 0; k < n; k++) {
          if (result.get(k).sum + note[0] <= target) {
            StringBuilder newSignature = new StringBuilder(result.get(k).signature.toString());
            newSignature.append('-').append(String.valueOf(val));
            if (signatureMap.containsKey(newSignature.toString())) {
              continue;
            }
            List<Integer> newList = new ArrayList<>(result.get(k).list);
            newList.add(val);
            int newKey = result.get(k).sum + val;
            result.add(new CurrencyList(newSignature, newKey, newList));
            signatureMap.put(newSignature.toString(), 0);
          }
        }
        if (j == 0 && val <= target) {
          StringBuilder newSignature = new StringBuilder(val);
          newSignature.append(String.valueOf(val));
          List<Integer> newList = new ArrayList<>();
          newList.add(val);
          result.add(new CurrencyList(newSignature, val, newList));
          signatureMap.put(newSignature.toString(), 0);
        }
      }
    }
      return result;
  }

}