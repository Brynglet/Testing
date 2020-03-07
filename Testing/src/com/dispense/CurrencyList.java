package com.dispense;

import java.util.List;

class CurrencyList implements Comparable<CurrencyList>{
  public StringBuilder signature;
  public int sum;
  public List<Integer> list;

  public CurrencyList(StringBuilder signature, int sum, List<Integer> list) {
    this.signature = signature;
    this.sum = sum;
    this.list = list;
  }

  @Override
  public int compareTo(CurrencyList o) {

    if(this.sum == o.sum) return this.list.size() - o.list.size();
    return o.sum - this.sum;
  }

  public int getProductibility(Integer[][] availableChanges){
    int productibility = Integer.MAX_VALUE;

    for(Integer[] note : availableChanges){
      int usage = (int) this.list.stream().filter(item-> item.equals(note[0])).count();
      if(usage > 0){
        productibility = (note[1] / usage) < productibility ? (note[1] / usage) : productibility;
      }
    }
    return productibility;
  }
}