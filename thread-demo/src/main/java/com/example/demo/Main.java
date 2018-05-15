package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static int[] subtract(int[] A, int[] B) {
        int lastA = A.length - 1;
        int lastB = B.length - 1;

        List<Integer> list = new ArrayList<Integer>();

        while (lastA >= 0 && lastB >= 0) {
            if (A[lastA] >= B[lastB]) {
                list.add(0, A[lastA] - B[lastB]);
            } else {
                list.add(0, A[lastA] + 10 -B[lastB]);
                A[lastA -1 ]--;
            }
            lastA--;
            lastB--;
        }

        // A的位数比B的位数多
        while (lastA != -1) {
            list.add(0, A[lastA--]);
        }

        int[] ret = new int[list.size()];
        for(int i = 0;i<list.size();i++){
            ret[i] = list.get(i);
        }
        return ret;
    }


    public static void main(String[] args) {
        int[] a = {6,0,0,0};
        int[] b = {  1,2,3};

        System.out.println(6000-123);
        int[] c = subtract(b, a);
        for (int i : c) {
            System.out.print(i);
        }
    }
}
