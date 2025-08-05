package org.example;

import org.example.util.AOCFileIO;

import java.lang.reflect.Array;
import java.util.*;

public class Day5 {

//    static String content =
//            "47|53\n" +
//            "97|13\n" +
//            "97|61\n" +
//            "97|47\n" +
//            "75|29\n" +
//            "61|13\n" +
//            "75|53\n" +
//            "29|13\n" +
//            "97|29\n" +
//            "53|29\n" +
//            "61|53\n" +
//            "97|53\n" +
//            "61|29\n" +
//            "47|13\n" +
//            "75|47\n" +
//            "97|75\n" +
//            "47|61\n" +
//            "75|61\n" +
//            "47|29\n" +
//            "75|13\n" +
//            "53|13\n" +
//            "\n" +
//            "75,47,61,53,29\n" +
//            "97,61,53,29,13\n" +
//            "75,29,13\n" +
//            "75,97,47,61,53\n" +
//            "61,13,29\n" +
//            "97,13,75,29,47";

    public static void main(String[] args) {
        String content = AOCFileIO.parseFile("glorp.txt");
        // gaaaha

        // so we have rules array
        // Rules
        // is an array of arrays of Strings
        // array of all rules, each rule is its own array with key / value

        String[] contentArr = content.split("\r\n\r\n");

        String[] rulesAsTheyAre = contentArr[0].split("\r\n");

        Integer[][] rules = new Integer[rulesAsTheyAre.length][2];

        for (int i = 0; i < rulesAsTheyAre.length; i++) {
            String rule = rulesAsTheyAre[i];
            Integer[] finalRule = new Integer[2];

            String[] ruleInArr = rule.split("\\|");
            for (int j = 0; j < ruleInArr.length; j++) {
                finalRule[j] = Integer.parseInt(ruleInArr[j]);
            }

            rules[i] = finalRule;
        }

        String[] production = contentArr[1].split("\r\n");
        ArrayList<ArrayList<Integer>> productionInstructions = new ArrayList<>();
        for(String prod : production) {
            String[] prodarr = prod.split(",");
            ArrayList<Integer> prodarrint = new ArrayList<>();
            for (int i = 0; i < prodarr.length; i++) {
                int thisVar = Integer.parseInt(prodarr[i]);
                prodarrint.add(thisVar);
            }

            productionInstructions.add(prodarrint);
        }

        System.out.println("\nProduction Instructions: " + productionInstructions);



        // fill in the rules map
        // for each X value, we have to figure out:
        // 1. has it been used before? if so, add to the value arr
        // 2. if not, make a new key and add to the arr

        Map<Integer, ArrayList<Integer>> rulesMap = new HashMap<>();

        for (Integer[] rule : rules) {
            int x = rule[0];
            int y = rule[1];

            if (rulesMap.containsKey(x)) {
                ArrayList<Integer> curYarr = rulesMap.get(x);
                ArrayList<Integer> newYarr = curYarr;
                newYarr.add(y);
                rulesMap.replace(x, curYarr, newYarr);
            } else {
                ArrayList<Integer> newYarr = new ArrayList<>();
                newYarr.add(y);
                rulesMap.put(x, newYarr);
            }
        }

        System.out.println("Rules: " + rulesMap);

        // now run through each prod
        // for each number:
        // 1. check all numbers before it to make sure that it itself does not fit any keys

        ArrayList<Integer> workingProds = new ArrayList<>();

        // goes through all instructions
        productionLooper:
        for (int i = 0; i < productionInstructions.size(); i++) {
            // loops through production instructions
            System.out.println("--------------------------------");

            ArrayList<Integer> currentInstruction = productionInstructions.get(i);
            for (int j = 0; j < currentInstruction.size(); j++) {
                // loops through the individual numbers of the current instruction

                int currentNumber = currentInstruction.get(j);

                if (j != 0) {
                    List<Integer> beforeCurrentNumberArr = currentInstruction.subList(0, j);
                    ArrayList<Integer> illegalNumbers = rulesMap.get(currentNumber);

                    // check if every number before the current number is allowed to be there
                    if (illegalNumbers != null) {
                        for (int currentBeforeNumber : beforeCurrentNumberArr) {

                            for (int currentIllegalNumber : illegalNumbers) {

                                if (currentIllegalNumber == currentBeforeNumber) {
                                    // this production has failed.
                                    // print out the offending key and value
                                    // move to next production

                                    System.out.println("Production " + i + " has failed!\nOffending key: " + currentNumber + "\nCurrent Illegal value: " + currentBeforeNumber + "\nProduction Instructions: " + currentInstruction);

                                    continue productionLooper;
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("Production " + i + " is successful. Adding...");
            workingProds.add(i);
        }
        System.out.println("--------------------------------");

        System.out.println("Working production instruction values: " + workingProds);

        ArrayList<ArrayList<Integer>> workingProdsArray = new ArrayList<>(workingProds.size());

        for (int workingProd : workingProds) {
            workingProdsArray.add(productionInstructions.get(workingProd));
        }

        System.out.println("Working production instructions: " + workingProdsArray);

        int middleNumberSum = 0;
        for (int i = 0; i < workingProdsArray.size(); i++) {
            // individual production instructions

            ArrayList<Integer> currentArr = workingProdsArray.get(i);
            int halfIndex = (currentArr.size()-1) /2;
            middleNumberSum += currentArr.get(halfIndex);
        }

        System.out.println(middleNumberSum);
    }
}
