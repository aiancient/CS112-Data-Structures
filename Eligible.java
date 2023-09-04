package prereqchecker;

import java.util.*;

/**
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * AdjListInputFile name is passed through the command line as args[0]
 * Read from AdjListInputFile with the format:
 * 1. a (int): number of courses in the graph
 * 2. a lines, each with 1 course ID
 * 3. b (int): number of edges in the graph
 * 4. b lines, each with a source ID
 * 
 * Step 2:
 * EligibleInputFile name is passed through the command line as args[1]
 * Read from EligibleInputFile with the format:
 * 1. c (int): Number of courses
 * 2. c lines, each with 1 course ID
 * 
 * Step 3:
 * EligibleOutputFile name is passed through the command line as args[2]
 * Output to EligibleOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class Eligible {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.Eligible <adjacency list INput file> <eligible INput file> <eligible OUTput file>");
            return;
        }

	// WRITE YOUR CODE HERE
        StdIn.setFile(args[0]); 
        ArrayList<String> al = new ArrayList<String>();  
        while(StdIn.hasNextLine())
        {
            al.add(StdIn.readLine()); 
        }

        Graph gh = new Graph(al,Integer.parseInt(al.get(0)));
        ArrayList<ArrayList<String>> graph = gh.getArrayList(); 

        StdIn.setFile(args[1]); 
        ArrayList<String> completedCourses  = new ArrayList<String>();
        int count = 0;   
        while(StdIn.hasNextLine())
        {
            if(count > 0)
            {
                completedCourses.add(StdIn.readLine());
            }
            count++; 
        }

        StdOut.setFile(args[2]);
        ArrayList<String> allCompletedCourses = new ArrayList<String>(); 

        for(int i = 1; i<completedCourses.size(); i++)
        {
            allCompletedCourses = gh.getAllPreReqs(allCompletedCourses,completedCourses.get(i),gh.getIndex(completedCourses.get(i)));
        }

        ArrayList<String> eligibleCourses = eligible(graph, allCompletedCourses); 
    
        for(String s: eligibleCourses)
        {
            StdOut.println(s); 
        }

    }

    public static ArrayList<String> eligible(ArrayList<ArrayList<String>> graph,ArrayList<String> allCompletedCourses)
    {
        ArrayList<String> el = new ArrayList<String>(); 


        for(ArrayList<String> al: graph)
        {
            String course = al.get(0); 
            boolean canTake = true; 
            for(int i = 1; i<al.size(); i++)
            {
                if(!allCompletedCourses.contains(al.get(i)))
                {
                    canTake = false; 
                    break; 
                }
            }

            if(!(allCompletedCourses.contains(course)) && canTake == true)
            {
                el.add(al.get(0)); 
            }
        }

        return el; 
    }

}
