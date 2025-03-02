package org.example;

import org.example.util.AOCFileIO;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.regex.*;


public class Day3 {
    public static void main(String[] args) {
        int section = 1;
        
        String content = AOCFileIO.parseFile("aoc3.txt");
        assert content != null;
        
        Pattern startPattern = Pattern.compile("mul\\(");
        Matcher startMatcher = startPattern.matcher(content);
        
        Pattern endPattern = Pattern.compile("\\)");
        Matcher endMatcher = endPattern.matcher(content);
        
        Pattern doPattern = Pattern.compile("do\\(\\)");
        Matcher doMatcher = doPattern.matcher(content);
        
        Pattern dontPattern = Pattern.compile("don't\\(\\)");
        Matcher dontMatcher = dontPattern.matcher(content);
        
        
        List<String> allMuls = new ArrayList<>();
        while (startMatcher.find()){
            int startInt = startMatcher.start();
            
//            endMatcher.reset();
            if (endMatcher.find(startMatcher.start())) {
//                System.out.println(endMatcher.start());
                int endInt = endMatcher.start() + 1;
                allMuls.add(content.substring(startInt, endInt));
                
            }
        }
        
        List<String> validMuls = new ArrayList<>();
        
        for (String s : allMuls) {
//            System.out.println(s);
            // Regex pattern to check for characters other than specified ones
            String pattern = "[^mul(\\\\b(0|[1-9][0-9]{0,2}|1000)\\\\b,)]";
            
            // Compile the regex pattern
            Pattern compiledPattern = Pattern.compile(pattern);
            
            // Create a matcher object
            Matcher matcher = compiledPattern.matcher(s);
            if (matcher.find()) {
            } else {
                validMuls.add(s);
            }
        }
        
        Pattern literallyJustAComma = Pattern.compile(",");
        
        int sum = 0;
        
        BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
        
        for (String s : validMuls) {
            Matcher commaMatcher = literallyJustAComma.matcher(s);
            Matcher endOfTheMulMatcher = endPattern.matcher(s);
            
            commaMatcher.find();
            endOfTheMulMatcher.find();
            
            int firstNum = Integer.parseInt(s.substring(4, commaMatcher.start()));
            int secondNum = Integer.parseInt(s.substring(commaMatcher.start() + 1, endOfTheMulMatcher.start()));
            sum += multiply.apply(firstNum, secondNum);
        }
        
        System.out.println(sum);
    }
}
