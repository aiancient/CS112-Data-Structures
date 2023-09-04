
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
 * ValidPreReqInputFile name is passed through the command line as args[1]
 * Read from ValidPreReqInputFile with the format:
 * 1. 1 line containing the proposed advanced course
 * 2. 1 line containing the proposed prereq to the advanced course
 * 
 * Step 3:
 * ValidPreReqOutputFile name is passed through the command line as args[2]
 * Output to ValidPreReqOutputFile with the format:
 * 1. 1 line, containing either the word "YES" or "NO"
 */
public class ValidPrereq 
{
    public static void main(String[] args) 
    {

        if ( args.length < 3 ) 
        {
            StdOut.println("Execute: java -cp bin prereqchecker.ValidPrereq <adjacency list INput file> <valid prereq INput file> <valid prereq OUTput file>");
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
        String[] courses  = new String[2];
        int count = 0;   
        while(StdIn.hasNextLine())
        {
            courses[count] = StdIn.readLine();
            count++; 
        }

        StdOut.setFile(args[2]); 
        gh.addPreReq(courses[0],courses[1]); 
        ArrayList<String> preReqs = gh.getPreReqs(courses[0]); 

        boolean notPossible = false; 
        for(int i = 1; i< preReqs.size(); i++)
        {
            if(check(graph,courses,preReqs.get(i)) == false) 
            {
                StdOut.println("NO"); 
                break; 
            }
            notPossible = true; 
        }
        if(notPossible)
        {
            StdOut.println("YES"); 
        }

    }

    public static boolean check(ArrayList<ArrayList<String>> graph, String[] courses, String course)
    {
        boolean result = true; 
        if(course.equals(courses[0]))
        {  
            return false; 
        }

        else
        {
            for(ArrayList<String> als: graph)
            {
                if(als.get(0).equals(course) && als.size() > 1)
                {   
                    for(int i = 1; i<als.size(); i++)
                    {
                        result = check(graph,courses,als.get(i)); 
                        if(result == false)
                        {
                            return false; 
                        }
                    }
                }
            }
        } 
        return result;
    }
}
