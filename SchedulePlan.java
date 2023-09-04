//package prereqchecker;

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
 * SchedulePlanInputFile name is passed through the command line as args[1]
 * Read from SchedulePlanInputFile with the format:
 * 1. One line containing a course ID
 * 2. c (int): number of courses
 * 3. c lines, each with one course ID
 * 
 * Step 3:
 * SchedulePlanOutputFile name is passed through the command line as args[2]
 * Output to SchedulePlanOutputFile with the format:
 * 1. One line containing an int c, the number of semesters required to take the course
 * 2. c lines, each with space separated course ID's
 */
public class SchedulePlan {
    public static void main(String[] args) {

        if ( args.length < 3 ) {
            StdOut.println("Execute: java -cp bin prereqchecker.SchedulePlan <adjacency list INput file> <schedule plan INput file> <schedule plan OUTput file>");
            return;
        }

	    // WRITE YOUR CODE HERE
       /* StdIn.setFile(args[0]); 
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

        //StdOut.setFile(args[2]);
        ArrayList<String> allCompletedCourses = new ArrayList<String>(); 

        for(int i = 1; i<completedCourses.size(); i++)
        {
            allCompletedCourses = gh.getAllPreReqs(allCompletedCourses,completedCourses.get(i),gh.getIndex(completedCourses.get(i)));
        }

        ArrayList<String> targetCoursePreReqss = new ArrayList<String>(); 
        targetCoursePreReqss = gh.getAllPreReqs(targetCoursePreReqss,targetCourse,gh.getIndex(targetCourse));
        
        ArrayList<String> targetCoursePreReqs = new ArrayList<String>(); 
        for(String s: targetCoursePreReqss)
        {
            if(!(allCompletedCourses.contains(s)) && !(s.equals(targetCourse)))
            {
                targetCoursePreReqs.add(s); 
            }
        }

        for(String r: targetCoursePreReqs)
        {
            StdOut.println(r); 
        }
        
        StdOut.println("Loop starts now: "); 
        int ct = 0; 
        ArrayList<ArrayList<String>> schedule = new ArrayList<ArrayList<String>>();
        ArrayList<String> remove = new ArrayList<String>();

        while(remove.size() != targetCoursePreReqs.size())
        {
             
            boolean isFirst = true; 
            for(String preReq: targetCoursePreReqs)
            {
                StdOut.println("PRE REQQQQ: " + preReq); 
                ArrayList<String> preReqCoursePreReqs = new ArrayList<String>(); 
                preReqCoursePreReqs = gh.getAllPreReqs(preReqCoursePreReqs,preReq,gh.getIndex(preReq));
                preReqCoursePreReqs.remove(preReq); 
                 
                ///CODE FROM HERE THIS IS ALL INCORRECT 
                
            }
            

            for(String rr: remove)
            {
                targetCoursePreReqs.remove(rr); 
            }

            StdOut.println("remaining prereqss "); 
            for(String abc: targetCoursePreReqs)
            {
                StdOut.println(abc); 
            }
            ct++; 
            if(ct == 3)
            {
                break; 
            }
        }

        for(ArrayList<String> a: schedule)
        {
            for(String s: a)
            {
                StdOut.print(s + " "); 
            }
            StdOut.println(); 
        }

    }

    public static boolean tryAdding(ArrayList<String> allCompletedCourses, ArrayList<String> preReqCoursePreReqs)
    {
        for(String s : preReqCoursePreReqs)
        {
            if(!allCompletedCourses.contains(s))
            {
                return false; 
            }
        }
        
        return true; 
    }

    public static boolean tryAddingCheckWSchedule(ArrayList<String> allCompletedCourses, ArrayList<String> preReqCoursePreReqs,ArrayList<String> remove)
    {
        for(String s : preReqCoursePreReqs)
        {
            if( !allCompletedCourses.contains(s) || !remove.contains(s))
            {
                return false; 
            }
        }
        
        return true;
    }

    public static boolean inSchedule(ArrayList<ArrayList<String>> schedule, String course)
    {
        for(ArrayList al: schedule)
        {
            if(al.contains(course))
            {
                return true; 
            }
        }
        return false; 
    }*/

    }
}





























/*




ArrayList<String> remove = new ArrayList<String>();
        while(!targetCoursePreReqs.isEmpty())
        {
             
            boolean isFirst = true; 
            for(String preReq: targetCoursePreReqs)
            {
                StdOut.println("PRE REQQQQ: " + preReq); 
                ArrayList<String> preReqCoursePreReqs = new ArrayList<String>(); 
                preReqCoursePreReqs = gh.getAllPreReqs(preReqCoursePreReqs,preReq,gh.getIndex(preReq));
                preReqCoursePreReqs.remove(preReq); 
                for(String a: preReqCoursePreReqs)
                {
                    StdOut.println(a); 
                }
                
                //StdOut.println(); 
                if(ct == 0 && tryAdding(allCompletedCourses,preReqCoursePreReqs))
                {
                    ArrayList<String> preReqAL = new ArrayList<String>(); 
                    preReqAL.add(preReq); 
                    schedule.add(ct,preReqAL); 
                    StdOut.println(preReq + " added.");
                    remove.add(preReq); 
                    allCompletedCourses.add(preReq); 
                    //targetCoursePreReqs.add(targetCoursePreReqs.indexOf(preReq), null); 
                    //targetCoursePreReqs.remove(preReq); 
                }

                else {
                StdOut.println(tryAddingCheckWSchedule(allCompletedCourses,preReqCoursePreReqs,remove));
                if(tryAddingCheckWSchedule(allCompletedCourses,preReqCoursePreReqs,remove))
                {
                    if(isFirst)
                    {
                        ArrayList<String> preReqAL = new ArrayList<String>(); 
                        preReqAL.add(preReq); 
                        schedule.add(ct,preReqAL);
                        StdOut.println(preReq + " added first time with schedule already made."); 
                        remove.add(preReq);
                        allCompletedCourses.add(preReq); 
                        isFirst = false; 
                    }

                    else
                    {
                        schedule.get(ct).add(preReq); 
                        allCompletedCourses.add(preReq); 
                        StdOut.println(preReq + " added later with schedule already made."); 
                        remove.add(preReq);
                    }
                    
                    //targetCoursePreReqs.add(targetCoursePreReqs.indexOf(preReq), null);
                    //targetCoursePreReqs.remove(preReq); 
                }
            }
            }

            for(String rr: remove)
            {
                targetCoursePreReqs.remove(rr); 
            }

            StdOut.println("remaining prereqss "); 
            for(String abc: targetCoursePreReqs)
            {
                StdOut.println(abc); 
            }
            ct++; 
            if(ct == 3)
            {
                break; 
            }
        }

        for(ArrayList<String> a: schedule)
        {
            for(String s: a)
            {
                StdOut.print(s + " "); 
            }
            StdOut.println(); 
        }*/
        