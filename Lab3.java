package edu.monmouth.lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Lab3 {

	public static void main(String[] args) {
		if (args.length != 1) {
            System.err.println("Error: Please provide a filename as a command line argument.");
            System.exit(1);
        }
		
		try {
            System.setOut(new PrintStream(new FileOutputStream(args[0])));
            System.setErr(new PrintStream(new FileOutputStream(args[0])));
        } catch (FileNotFoundException e) {
            System.err.println("Error: Unable to redirect output to the specified file.");
            System.exit(1);
        }
		
		final int MAX_COMPETITORS = 10;
        Competitor[] competitors = new Competitor[MAX_COMPETITORS];
        
        try (Scanner fileScanner = new Scanner(new File("golf.txt"))) {
            int index = 0;
            while (fileScanner.hasNextLine() && index < MAX_COMPETITORS) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                
                if (parts.length == 2 && !parts[0].isEmpty() && !parts[1].isEmpty()) {
                    String name = parts[0].trim();
                    try {
                        int score = Integer.parseInt(parts[1].trim());
                        competitors[index++] = new Competitor(name, score);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid score for golfer " + name + ", skipping.");
                    }
                } else {
                    System.err.println("Invalid line: " + line + ", skipping.");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File 'golf.txt' not found.");
            System.exit(1);
        }
        
        Arrays.sort(competitors);
        
        System.out.println("Sorted Golfers:");
        for (Competitor competitor : competitors) {
            if (competitor != null) {
                System.out.println(competitor);
            }
        }
        System.out.println("");
        
        PriorityQueue
        <Competitor> golfers = new PriorityQueue<>();
        
        for (Competitor competitor : competitors) {
            if (competitor != null) {
                golfers.offer(competitor);
            }
        }
        
        System.out.println("Leader of Golfers:");
        System.out.println(golfers.poll());
        System.out.println("");
        
        // Bowlers
        
        PriorityQueue<Competitor> bowlers = new PriorityQueue<>(new BowlerComparator());
        
        try (Scanner fileScanner = new Scanner(new File("bowling.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                	if(parts[0] != "" && parts[1] != "") {
                		String name = parts[0].trim();
                        int score = Integer.parseInt(parts[1].trim());
                        Competitor bowler = new Competitor(name, score);
                        bowlers.offer(bowler);
                	}
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: File 'bowling.txt' not found.");
            System.exit(1);
        }
        
        System.out.println("Leader of Bowlers:");
        System.out.println(bowlers.poll());
        System.out.println("");
        
        Iterator<Competitor> bowlersIterator = bowlers.iterator();
        System.out.println("The iterator values are:");
        while (bowlersIterator.hasNext()) {
            System.out.println(bowlersIterator.next());
        }



	}

}
