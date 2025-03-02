package org.example;

import org.example.util.AOCFileIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day1 {
    public static void main (String[] args) {
        int section = 2;
        // change this to do part 1 / 2
        
        ArrayList<Integer> numList1 = new ArrayList<>(0);
        ArrayList<Integer> numList2 = new ArrayList<>(0);
        
        
        String[] allNums = new String[2000];

        String content = AOCFileIO.parseFile("aoc1.txt");
        assert content != null;
        allNums = content.split(" {3}|\n");

        for (int i = 0; i < allNums.length; i++) {
            String currentString = allNums[i];
            int currentInt = Integer.parseInt(currentString);

            // if it's even
            if (i % 2 == 0) {
                numList1.add(currentInt);
            } else {
                numList2.add(currentInt);
            }
        }

        // now, numList1 is the left column
        // numList2 is the right column
        
        Collections.sort(numList1);
        Collections.sort(numList2);

        for (int i = 0; i < numList1.size(); i++) {
            int l1Value = numList1.get(i);
            
            int timesl1Appears = 0;
            for (int j = 0; j < numList2.size(); j++) {
                int l2Value = numList2.get(j);
                
                if (l2Value == l1Value) {
                    timesl1Appears++;
                } else if (l2Value > l1Value) {
                    break;
                }
            }
        }
        
        if (section == 1) {
            part1(numList1, numList2);
        } else if (section == 2) {
            part2(numList1, numList2);
        }

    }
    
    public static void part1 (ArrayList<Integer> numList1, ArrayList<Integer> numList2) {
        int totalDifference = 0;
        
        for (int i = 0; i < numList1.size(); i++) {
            int l1Value = numList1.get(i);
            int l2Value = numList2.get(i);
            
            totalDifference += Math.abs(l1Value - l2Value);
        }
        
        System.out.println(totalDifference);
    }
    
    public static void part2(ArrayList<Integer> numList1, ArrayList<Integer> numList2) {
        int totalDifference = 0;
        
        for (int i = 0; i < numList1.size(); i++) {
            int l1Value = numList1.get(i);
            
            int timesl1Appears = 0;
            for (int j = 0; j < numList2.size(); j++) {
                int l2Value = numList2.get(j);
                
                if (l2Value == l1Value) {
                    timesl1Appears++;
                } else if (l2Value > l1Value) {
                    break;
                }
            }
            
            totalDifference += l1Value * timesl1Appears;
        }
        
        System.out.println(totalDifference);
    }
}