package org.example;

import org.example.util.AOCFileIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Day2 {
    public static void main(String[] args) {
        int section = 1;
        
        String content;
        ArrayList<ArrayList<Integer>> organizedReports = new ArrayList<>();
        
        content = AOCFileIO.parseFile("aoc2.txt");
        assert content != null;
//
//        content = "7 6 4 2 1\n" +
//                "1 2 7 8 9\n" +
//                "9 7 6 2 1\n" +
//                "1 3 2 4 5\n" +
//                "8 6 4 4 1\n" +
//                "1 3 6 7 9 11";
        
        String[] reports = content.split("\n");
        
        for (String report : reports) {
            ArrayList<Integer> thisOrganizedReport = new ArrayList<>();
            
            StringBuilder currentNum = new StringBuilder();
            for (int j = 0; j < report.length(); j++) {
                try {
                    currentNum.append(String.valueOf(Integer.parseInt(String.valueOf(report.charAt(j)))));
                } catch (NumberFormatException ignored) {
                    // this means that  there was a space
                    thisOrganizedReport.add(Integer.parseInt(currentNum.toString()));
                    currentNum = new StringBuilder();
                }
            }
            
            thisOrganizedReport.add(Integer.parseInt(currentNum.toString()));
            
            organizedReports.add(thisOrganizedReport);
        }
        
        // organizedReports is an arraylist of arraylists (the actual reports)
        
        HashMap<Integer, Boolean> hm = new HashMap<>();
        
        for (int i = 0; i < organizedReports.size(); i++) {
            ArrayList<Integer> report = organizedReports.get(i);
            
            if (section == 1)
                hm.put(i, isSafe1(report));
            else if (section == 2)
                hm.put(i, isSafe2(report));
        }
        
        int numThatAreGood = 0;
        for (int i = 0; i < hm.size(); i++) {
            numThatAreGood = hm.get(i) ? numThatAreGood + 1: numThatAreGood;
        }
        
        System.out.println(numThatAreGood);
    }
    
    enum State {
        DECREASING,
        INCREASING,
        EQUAL
    }
    
    public static boolean isSafe1 (ArrayList<Integer> report) {
        State state;
        boolean isValid = false;
        
        if (report.get(0) < report.get(1)) {
            state = State.INCREASING;
        } else if (report.get(0) > report.get(1)) {
            state = State.DECREASING;
        } else  {
            state = State.EQUAL;
        }
        
        int reportLength = report.size();
        
        switch (state) {
            case EQUAL:
                break;
                
            // if either of these
            case INCREASING:
                
                //do stuff
                
                for (int i = 0; i < reportLength - 1; i++) {
                    int currentVal = report.get(i);
                    int nextVal = report.get(i + 1);
                    
                    if (!((nextVal - currentVal) >= 1 && (nextVal - currentVal) <= 3)) {
                        break;
                    }
                    
                    if (i == reportLength - 2) {
                        isValid = true;
                    }
                }
                
                break;
            case DECREASING:
                // do stuff
                
                for (int i = 0; i < reportLength - 1; i++) {
                    int currentVal = report.get(i);
                    int nextVal = report.get(i + 1);
                    
                    if (!((currentVal - nextVal) >= 1 && (currentVal - nextVal) <= 3)) {
                        break;
                    }
                    
                    if (i == reportLength - 2) {
                        isValid = true;
                    }
                    
                }
                
                break;
        }
        
        return isValid;
    }
    
    public static boolean isSafe2 (ArrayList<Integer> report) {
        State state;
        boolean isValid = false;
        
        int errorNum = 0;
        
        if (report.get(0) < report.get(1)) {
            state = State.INCREASING;
        } else if (report.get(0) > report.get(1)) {
            state = State.DECREASING;
        } else  {
            errorNum++;
            if (report.get(1) < report.get(2)) {
                state = State.INCREASING;
            } else if (report.get(1) > report.get(2)) {
                state = State.DECREASING;
            } else {
                state = State.EQUAL;
            }
        }
        
        int reportLength = report.size();
        
        switch (state) {
            case EQUAL:
                break;
                
            case INCREASING:
                
                //do stuff
                
                for (int i = 0; i < reportLength - 1; i++) {
                    int currentVal = report.get(i);
                    int nextVal = report.get(i + 1);
                    
                    if (!((nextVal - currentVal) >= 1 && (nextVal - currentVal) <= 3)) {
                        errorNum++;
                        if (errorNum >= 2) {
                            break;
                        }
                    }
                    
                    if (i == reportLength - 2) {
                        isValid = true;
                    }
                }
                
                break;
            case DECREASING:
                // do stuff
                
                for (int i = 0; i < reportLength - 1; i++) {
                    int currentVal = report.get(i);
                    int nextVal = report.get(i + 1);
                    
                    if (!((currentVal - nextVal) >= 1 && (currentVal - nextVal) <= 3)) {
                        errorNum++;
                        if (errorNum >= 2) {
                            break;
                        }
                    }
                    
                    if (i == reportLength - 2) {
                        isValid = true;
                    }
                    
                }
                
                break;
        }
        
        return isValid;
    }
}