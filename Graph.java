package prereqchecker;
import java.util.*;

public class Graph {
    private final int V;
    private ArrayList<ArrayList<String>> adj;

    public Graph(ArrayList<String> al, int V) 
    {
        this.V = V;
        adj =  new ArrayList<ArrayList<String>>();

        for (int i = 0; i < V; i++) 
        {
            ArrayList<String> inADJ = new ArrayList<String>(); 
            inADJ.add(al.get(i+1)); 
            adj.add(i, inADJ);
        }

        for (int i = V + 2; i < al.size(); i++) 
        {
            String[] details = al.get(i).split(" ");
            int courseIndex = getIndex(details[0]);

            for (int j = 1; j < details.length; j++) 
            {
                adj.get(courseIndex).add(details[j]); 
            }

        }
    }

    public void printGraph()
    {
        for(ArrayList<String> al: adj)
        {
            for(String s: al)
            {
                StdOut.print(s + " "); 
            }
            StdOut.println(); 
        }
    }

    public ArrayList<ArrayList<String>> getArrayList()
    {
        return adj; 
    }

    public void addPreReq(String course, String preReq)
    {
        for(ArrayList<String> all: adj)
        {
            if(all.get(0).equals(course))
            {
                all.add(preReq); 
            }
        }
    }

    public ArrayList<String> getPreReqs(String course)
    {
        for(ArrayList<String> all: adj)
        {
            if(all.get(0).equals(course))
            {
                return all;  
            }
        }
        return null; 
    }

    public int getIndex(String course)
    {
        int count = 0; 
        for(ArrayList<String> als: adj)
        {
            if(als.get(0).equals(course))
            {
                return count; 
            }
            count++; 
        }
        return count; 
    }

    public ArrayList<String> getAllPreReqs(ArrayList<String> result, String course, int i)
    {
        if(adj.get(i).size() == 1)
        {
            if(!result.contains(adj.get(i).get(0)))
            {
                result.add(adj.get(i).get(0));
            }
            return result; 
        }

        else
        {
            int count = 0; 
            for(ArrayList<String> all: adj)
            {
                if(all.get(0).equals(course) && all.size() >1)
                {
                    if(!result.contains(all.get(0)))
                    {
                        result.add(all.get(0));
                    }

                    for(int j = 1; j<all.size(); j++)
                    {
                        result = getAllPreReqs(result, all.get(j), getIndex(all.get(j))); 
                    }

                }
                count++; 
            }
            return result; 
        }
         
    }
}
