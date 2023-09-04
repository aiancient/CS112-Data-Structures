package prereqchecker;

import java.util.*;

/**
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
 * NeedToTakeInputFile name is passed through the command line as args[1]
 * Read from NeedToTakeInputFile with the format:
 * 1. One line, containing a course ID
 * 2. c (int): Number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * NeedToTakeOutputFile name is passed through the command line as args[2]
 * Output to NeedToTakeOutputFile with the format:
 * 1. Some number of lines, each with one course ID
 */
public class NeedToTake {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java NeedToTake <adjacency list INput file> <need to take INput file> <need to take OUTput file>");
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
        String targetCourse = " "; 
        int count = 0;   
        while(StdIn.hasNextLine())
        {
            if(count == 0)
            {
                targetCourse = StdIn.readLine(); 
            }

            if(count > 1)
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

        ArrayList<String> targetCoursePreReqs = new ArrayList<String>(); 
        targetCoursePreReqs = gh.getAllPreReqs(targetCoursePreReqs,targetCourse,gh.getIndex(targetCourse));
        
        for(String s: targetCoursePreReqs)
        {
            if(!(allCompletedCourses.contains(s)) && !(s.equals(targetCourse)))
            {
                StdOut.println(s); 
            }
        }
    }
}
