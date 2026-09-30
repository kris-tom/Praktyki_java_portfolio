package com.krdl.task05;

import java.util.ArrayList;
import java.util.List;

public class Data {

  private List<Integer> list;

  public Data() {
    this.list = new ArrayList<>();
  }

  public void setList(List<Integer> list) {
    if (list == null) {
      this.list = new ArrayList<>();
    } else {
      this.list = list;
    }
  }

  private List<Integer> getSafeList() {
    if (list == null) {
      list = new ArrayList<>();
    }
    return list;
  }

  public void printData() {
    System.out.println("List contents: " + getSafeList());
  }

  public int sum() {
    int sum = 0;
    for (int number : getSafeList()) {
      sum += number;
    }
    return sum;
  }

  public double average() {
    if (getSafeList().isEmpty()) {
      return 0;
    }
    return (double) sum() / getSafeList().size();
  }

  public int[] min() {
    if (getSafeList().isEmpty()) {
      return new int[] { 0, -1 };
    }

    int min = getSafeList().get(0);
    int index = 0;

    for (int i = 1; i < getSafeList().size(); i++) {
      if (getSafeList().get(i) < min) {
        min = getSafeList().get(i);
        index = i;
      }
    }
    return new int[] { min, index };
  }

  public int positiveCount() {
    int count = 0;
    for (int number : getSafeList()) {
      if (number > 0) {
        count++;
      }
    }
    return count;
  }
}