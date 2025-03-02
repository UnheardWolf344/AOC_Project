package org.example;

import org.example.util.AOCFileIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Day1 {
    public static void main (String[] args) {
        List<Integer> numList1 = new ArrayList<>(0);
        List<Integer> numList2 = new ArrayList<>(0);
        String[] allNums = new String[2000];
        int totalDifference = 0;

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

        numList1.sort(Comparator.naturalOrder());
        numList2.sort(Comparator.naturalOrder());

        for (int i = 0; i < numList1.size(); i++) {
            int l1Value = numList1.get(i);
            int l2Value = numList2.get(i);

            totalDifference += Math.abs(l1Value - l2Value);
        }

        System.out.println(totalDifference);
    }
}