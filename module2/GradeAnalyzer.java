package module2;

import java.io.*; 
import java.util.ArrayList;

public class GradeAnalyzer {

    public static void main(String[] args) {
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        String filename = "scores.txt";
        ArrayList<Integer> scores = readScores(filename);
        for(Integer score : scores) {
            if(score < lowest) {
                lowest = score;
            }
            if(score > highest) {
                highest = score;
            }
        }
        double average = calculateAverage(scores);
        writeReport(scores, average, highest, lowest, "report.txt");
    } 
 
    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int n = Integer.parseInt(line.trim());
                    scores.add(n);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid value: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read file: " + e.getMessage());
        }
        return scores;
    }
 
    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        double totalScore = 0.0;
        double numberOfScores = 0.0;
        if(scores.isEmpty()) {
            return totalScore;
        }
        for(Integer score : scores) {
            totalScore += score;
            numberOfScores++;
        }
        return totalScore / numberOfScores;
    } 
 
    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile) {
        int countA = 0;
        int countB = 0;
        int countC = 0;
        int countD = 0;
        int countF = 0;

        for(Integer score : scores) {
            if(score >= 90) {
                countA++;
            } else if(score >= 80 && score < 90) {
                countB++;
            } else if(score >= 70 && score < 80) {
                countC++;
            } else if(score >= 60 && score < 70) {
                countD++;
            } else {
                countF++;
            }
        }
 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(String.format("=== Grade Analysis Report ==="));
            writer.newLine();
            writer.write(String.format("Total scores processed: %d%n", scores.size()));
            writer.newLine();
            writer.write(String.format("Average score: %.2f%n", avg));
            writer.newLine();
            writer.write(String.format("Highest score: %d%n", high));
            writer.newLine();
            writer.write(String.format("Lowest score: %d%n", low));
            writer.newLine();
            writer.newLine();
            writer.write(String.format("Grade distribution:"));
            writer.newLine();
            writer.write(String.format("A (90 - 100): %d%n", countA));
            writer.newLine();
            writer.write(String.format("B (80 - 89): %d%n", countB));
            writer.newLine();
            writer.write(String.format("C (70 - 79): %d%n", countC));
            writer.newLine();
            writer.write(String.format("D (60 - 69): %d%n", countD));
            writer.newLine();
            writer.write(String.format("F (below 60): %d%n", countF));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Could not write file: " + e.getMessage());
        }
    }
}
