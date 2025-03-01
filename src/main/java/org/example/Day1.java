package org.example;

import org.example.util.AOCFileIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day1 {
    public static void main (String[] args) {
        ArrayList<Integer> numList1 = new ArrayList<>(0);
        ArrayList<Integer> numList2 = new ArrayList<>(0);
        String[] allNums = new String[2000];
        int totalDifference = 0;

        try {
            String content = AOCFileIO.parseFile("aoc1.txt");
            allNums = content.split(" {3}|\n");
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.exit(1);
        }

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
            int l2Value = numList2.get(i);

            totalDifference += Math.abs(l1Value - l2Value);
        }

        System.out.println(totalDifference);
    }
}