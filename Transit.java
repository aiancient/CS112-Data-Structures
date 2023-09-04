import java.util.ArrayList;

/**
 * This class contains methods which perform various operations on a layered linked
 * list to simulate transit
 * 
 * @author Ishaan Ivaturi
 * @author Prince Rawal
 */
public class Transit {
    /**
     * Makes a layered linked list representing the given arrays of train stations, bus
     * stops, and walking locations. Each layer begins with a location of 0, even though
     * the arrays don't contain the value 0.
     * 
     * @param trainStations Int array listing all the train stations
     * @param busStops Int array listing all the bus stops
     * @param locations Int array listing all the walking locations (always increments by 1)
     * @return The zero node in the train layer of the final layered linked list
     */
    public static TNode makeList(int[] trainStations, int[] busStops, int[] locations) {
        // WRITE YOUR CODE HERE
        TNode woriginal = new TNode(0,null,null); 
        TNode boriginal = new TNode(0,null,woriginal); 
        TNode toriginal = new TNode(0,null,boriginal); 

        TNode wprevptr = woriginal; 

        for(int i = 0; i<=locations.length -1; i++)
        {
            TNode newWalk = new TNode(locations[i],null,null);

            wprevptr.next = newWalk; 
            wprevptr = newWalk; 
        }

        TNode wptr = woriginal.next; 
        wprevptr = woriginal;
        TNode bprevptr = boriginal; 

        for(int i = 0; i <= busStops.length - 1; i++)
        {
            TNode newBus = new TNode(busStops[i],null,null); 

            while(wptr.next != null)
            {
                if(wptr.location == newBus.location)
                {
                    newBus.down = wptr;
                    break;  
                }

                if(wptr.next.next == null && wptr.next.location == busStops[i])
                {
                    newBus.down = wptr.next; 
                    break; 
                }

                wprevptr = wptr; 
                wptr = wptr.next; 
            }

            bprevptr.next = newBus; 
            bprevptr = newBus; 
        }

        TNode bptr = boriginal.next; 
        bprevptr = boriginal;
        TNode tprevptr = toriginal;

        for(int i = 0; i <= trainStations.length - 1; i++)
        {
            TNode newTrain = new TNode(trainStations[i],null,null); 

            if(i == 0 && i == trainStations.length - 1 && trainStations[i] == bptr.location)
            {
                tprevptr.next = newTrain; 
                newTrain.down = bptr; 
                return toriginal; 
            }

            while(bptr.next != null)
            {
                if(bptr.location == newTrain.location)
                {
                    newTrain.down = bptr;
                    break;  
                }

                if(bptr.next.next == null && bptr.next.location == trainStations[i])
                {
                    newTrain.down = bptr.next; 
                    break; 
                }

                bprevptr = bptr; 
                bptr = bptr.next; 
            }

            tprevptr.next = newTrain; 
            tprevptr = newTrain; 
        }
        return toriginal; 
    }

    /**
     * Modifies the given layered list to remove the given train station but NOT its associated
     * bus stop or walking location. Do nothing if the train station doesn't exist
     * 
     * @param trainZero The zero node in the train layer of the given layered list
     * @param station The location of the train station to remove
     */
    public static void removeTrainStation(TNode trainZero, int station) {
        // WRITE YOUR CODE HERE

        TNode x = trainZero.next; 
        TNode previous = trainZero; 

        while( x.location != 0)
        {
            if(x.location > station || x.next == null && station > x.location)
            {
                return; 
            }

            if(x.location == station && x.next != null)
            {
                previous.next = x.next; 
                return; 
            }

            else if(x.location == station && x.next == null)
            {
                previous.next = null; 
                return; 
            }

            previous = x; 
            x = x.next; 
        }

        return;
    }

    /**
     * Modifies the given layered list to add a new bus stop at the specified location. Do nothing
     * if there is no corresponding walking location.
     * 
     * @param trainZero The zero node in the train layer of the given layered list
     * @param busStop The location of the bus stop to add
     */
    public static void addBusStop(TNode trainZero, int busStop) {
        // WRITE YOUR CODE HERE

        TNode owalking = trainZero.down.down; 
        TNode obus = trainZero.down; 
        //TNode wPrevious = owalking; 
        //TNode tPrevious = trainZero; 
        TNode bPrevious = trainZero.down; 
        TNode newBusStop; 

        while(obus.next != null)
        {
            if(obus.location < busStop && obus.next.location > busStop)
            {
                bPrevious = obus; 
                break; 
            }

            else if(obus.location == busStop)
            {
                return; 
            }

            else if(obus.next.next == null && obus.next.location < busStop)
            {
                bPrevious = obus.next; 
                //StdOut.println("bPrevious Location: " + bPrevious.location);
                break;
            }

            else if(obus.next.next == null && obus.next.location == busStop)
            {
                return; 
            }

            bPrevious = obus; 
            obus = obus.next; 

        }

        //StdOut.println("Hi");
        while(owalking.next != null)
        {
            //StdOut.println("owalking" + owalking.location); 
            if(owalking.location == busStop)
            {
                //StdOut.println("owalking location: " + owalking.location); 
                newBusStop = new TNode(busStop,(bPrevious.next == null ? null : bPrevious.next),owalking);
                bPrevious.next = newBusStop; 
                break; 
            }

            else if(owalking.next.next == null && owalking.next.location == busStop)
            {
                newBusStop = new TNode(busStop,null,owalking.next);
                bPrevious.next = newBusStop; 
                break;
            }

            owalking = owalking.next; 
        }

        /*while(trainZero.next != null)
        {
            if(trainZero.location == busStop)
            {
                trainZero.down = newBusStop; 
            }
            
            trainZero = trainZero.next; 
        }*/

    }
    
    /**
     * Determines the optimal path to get to a given destination in the walking layer, and 
     * collects all the nodes which are visited in this path into an arraylist. 
     * 
     * @param trainZero The zero node in the train layer of the given layered list
     * @param destination An int representing the destination
     * @return
     */
    public static ArrayList<TNode> bestPath(TNode trainZero, int destination) {
        // WRITE YOUR CODE HERE
        ArrayList<TNode> send = new ArrayList<TNode>(); 
        TNode ptr = trainZero; 
        TNode busStop = ptr;  
        TNode walkStop = new TNode(); 

        while(ptr.next != null)
        {
            //train stop equals destination so that would mean bus is there and then bus has walking too 
            if (ptr.down.location == destination)
            {
                //StdOut.println(1); 
                send.add(ptr);
                send.add(ptr.down); 
                send.add(ptr.down.down);
                return send; 
            }

            //destination between two train nodes 
            if(ptr.location < destination && ptr.next.location > destination)
            {
                //StdOut.println(2); 
                send.add(ptr);
                busStop = ptr.down; 
                break; 
            }

            //destination between the last two train stations 
            if(ptr.location < destination && ptr.next.location < destination && ptr.next.next == null)
            {
                //StdOut.println(3); 
                send.add(ptr);
                send.add(ptr.next); 
                busStop = ptr.next.down; 
                break; 
            }

            //edge case where last train node equals destination 
            if(ptr.location < destination && ptr.next.next == null && ptr.next.location == destination)
            {
                //StdOut.println(4); 
                send.add(ptr);
                send.add(ptr.next); 
                send.add(ptr.next.down);
                send.add(ptr.next.down.down); 
                return send; 
            }

            //StdOut.println(5); 
            send.add(ptr); 
            ptr = ptr.next; 
            
        }
 
        //StdOut.println(); 

        while(busStop.next != null)
        {
            //add all busStop and down if at destination 
            if(busStop.location == destination)
            {
                //StdOut.println(1); 
                send.add(busStop); 
                send.add(busStop.down);
                return send; 
            }
            
            //destination between two bus stops 
            if(busStop.location < destination && busStop.next.location > destination)
            {
                //StdOut.println(2); 
                send.add(busStop);
                walkStop = busStop.down; 
                break; 
            }

            //destination between last two bus stops 
            if(busStop.location < destination && busStop.next.next == null && busStop.next.location < destination)
            {
                //StdOut.println(3); 
                send.add(busStop);
                send.add(busStop.next);
                walkStop = busStop.next.down; 
                break; 
            }

            //edge case where last bus node equals destination 
            if(busStop.location < destination && busStop.next.next == null && busStop.next.location == destination)
            {
                //StdOut.println(4); 
                send.add(busStop);
                send.add(busStop.next); 
                send.add(busStop.next.down); 
                return send; 
            }

            //StdOut.println(5); 
            send.add(busStop);
            busStop = busStop.next; 
        }

        while(walkStop.next != null)
        {
            if(walkStop.next.next == null && walkStop.next.location == destination)
            {
                send.add(walkStop);
                send.add(walkStop.next); 
                return send; 
            }

            if(walkStop.location == destination)
            {
                send.add(walkStop);  
                return send; 
            }

            send.add(walkStop);
            walkStop = walkStop.next; 
        }

        return send; 
    }

    /**
     * Returns a deep copy of the given layered list, which contains exactly the same
     * locations and connections, but every node is a NEW node.
     * 
     * @param trainZero The zero node in the train layer of the given layered list
     * @return
     */
    public static TNode duplicate(TNode trainZero) {
        // WRITE YOUR CODE HERE
        
        TNode ttoriginal = trainZero; 
        TNode bboriginal = trainZero.down; 
        TNode wworiginal = trainZero.down.down; 

        int tcount = 0; 
        int bcount = 0; 
        int wcount = 0; 
         
        while(ttoriginal.next != null)
        {
            tcount+=1; 
            ttoriginal = ttoriginal.next; 
        }
        //StdOut.println(tcount); 

        while(bboriginal.next != null)
        {
            bcount+=1; 
            bboriginal = bboriginal.next; 
        }
        //StdOut.println(bcount); 

        while(wworiginal.next != null)
        {
            wcount++; 
            wworiginal = wworiginal.next; 
        }
        //StdOut.println(wcount); 

        int[] trainStations = new int[tcount]; 
        int[] busStops = new int[bcount]; 
        int[] locations = new int[wcount]; 

        ttoriginal = trainZero; 
        bboriginal = trainZero.down; 
        wworiginal = trainZero.down.down; 

        int ttcount = 0; 
        while(ttoriginal.next != null)
        {
            if(ttoriginal.location == 0 && ttoriginal.next.next == null)
            {
                trainStations[ttcount] = ttoriginal.next.location; 
                break; 
            }

            if(ttoriginal.next.next == null)
            {
                trainStations[ttcount] = ttoriginal.location; 
                ttcount += 1; 
                trainStations[ttcount] = ttoriginal.next.location;
                break;  
            }

            if(ttoriginal.location == 0)
            {
                ttoriginal = ttoriginal.next; 
                continue; 
            }

            trainStations[ttcount] = ttoriginal.location; 
            ttoriginal = ttoriginal.next; 
            ttcount++; 
        }


        int bbcount = 0; 
        while(bboriginal.next != null)
        {
            if(bboriginal.location == 0 && bboriginal.next.next == null)
            {
                busStops[bbcount] = bboriginal.next.location; 
                break; 
            }

            if(bboriginal.next.next == null)
            {
                busStops[bbcount] = bboriginal.location; 
                bbcount += 1; 
                busStops[bbcount] = bboriginal.next.location;
                break;  
            }

            if(bboriginal.location == 0)
            { 
                bboriginal = bboriginal.next; 
                continue; 
            }

            busStops[bbcount] = bboriginal.location; 
            bboriginal = bboriginal.next;
            bbcount++;   
        }

        int wwcount = 0; 
        while(wworiginal.next != null)
        {
            if(wworiginal.location == 0 && wworiginal.next.next == null)
            {
                locations[wwcount] = wworiginal.next.location; 
                break; 
            }

            if(wworiginal.next.next == null)
            {
                locations[wwcount] = wworiginal.location; 
                wwcount += 1; 
                locations[wwcount] = wworiginal.next.location;
                break;  
            }

            if(wworiginal.location == 0)
            {
                wworiginal = wworiginal.next; 
                continue; 
            }

            locations[wwcount] = wworiginal.location; 
            wworiginal = wworiginal.next; 
            wwcount++; 
        }

        TNode woriginal = new TNode(0,null,null); 
        TNode boriginal = new TNode(0,null,woriginal); 
        TNode toriginal = new TNode(0,null,boriginal); 

        TNode wprevptr = woriginal; 

        for(int i = 0; i<=locations.length -1; i++)
        {
            TNode newWalk = new TNode(locations[i],null,null);

            wprevptr.next = newWalk; 
            wprevptr = newWalk; 
        }

        TNode wptr = woriginal.next; 
        wprevptr = woriginal;
        TNode bprevptr = boriginal; 

        for(int i = 0; i <= busStops.length - 1; i++)
        {
            TNode newBus = new TNode(busStops[i],null,null); 

            while(wptr.next != null)
            {
                if(wptr.location == newBus.location)
                {
                    newBus.down = wptr;
                    break;  
                }

                if(wptr.next.next == null && wptr.next.location == busStops[i])
                {
                    newBus.down = wptr.next; 
                    break; 
                }

                wprevptr = wptr; 
                wptr = wptr.next; 
            }

            bprevptr.next = newBus; 
            bprevptr = newBus; 
        }

        TNode bptr = boriginal.next; 
        bprevptr = boriginal;
        TNode tprevptr = toriginal;

        for(int i = 0; i <= trainStations.length - 1; i++)
        {
            TNode newTrain = new TNode(trainStations[i],null,null); 

            if(i == 0 && i == trainStations.length - 1 && trainStations[i] == bptr.location)
            {
                tprevptr.next = newTrain; 
                newTrain.down = bptr; 
                return toriginal; 
            }

            while(bptr.next != null)
            {
                if(bptr.location == newTrain.location)
                {
                    newTrain.down = bptr;
                    break;  
                }

                if(bptr.next.next == null && bptr.next.location == trainStations[i])
                {
                    newTrain.down = bptr.next; 
                    break; 
                }

                bprevptr = bptr; 
                bptr = bptr.next; 
            }

            tprevptr.next = newTrain; 
            tprevptr = newTrain; 
        }
        return toriginal; 

    }

    /**
     * Modifies the given layered list to add a scooter layer in between the bus and
     * walking layer.
     * 
     * @param trainZero The zero node in the train layer of the given layered list
     * @param scooterStops An int array representing where the scooter stops are located
     */
    public static void addScooter(TNode trainZero, int[] scooterStops) {
        // WRITE YOUR CODE HERE

        TNode woriginal = trainZero.down.down; 
        TNode soriginal = new TNode(0,null,woriginal);
        TNode sprevptr = soriginal; 
        TNode wptr = trainZero.down.down; 

        for(int i = 0; i<scooterStops.length; i++)
        {
            while(wptr.next != null)
            {
                if(scooterStops[i] == wptr.location)
                {
                    TNode newScooter = new TNode(scooterStops[i],null,wptr);
                    sprevptr.next = newScooter; 
                    sprevptr = newScooter; 
                    break; 
                }

                if(scooterStops[i] == wptr.next.location)
                {
                    TNode newScooter = new TNode(scooterStops[i],null,wptr.next); 
                    sprevptr.next = newScooter; 
                    sprevptr = newScooter; 
                    break; 
                }

                wptr = wptr.next; 
            }
        }

        sprevptr = soriginal; 
        TNode boriginal = trainZero.down;
        TNode bprevptr = boriginal; 
        TNode sptr = soriginal; 

        //boriginal.down = soriginal; 
        TNode bptr = boriginal; 

        while(bptr.next != null)
        {
            if(bptr.next.next == null)
            {
                //bprevptr.down = sprevptr; 
            

                while(sptr.next != null)
                {
                    if(sptr.location == bptr.location)
                    {
                        bptr.down = sptr;  
                        bprevptr = bptr; 
                    }

                    if(sptr.location == bptr.next.location)
                    {
                        bptr.next.down = sptr; 
                        break; 
                    }

                    if(sptr.next.next == null && sptr.next.location == bptr.next.location)
                    {
                        bptr.next.down = sptr.next; 
                        break; 
                    }

                    sprevptr = sptr; 
                    sptr = sptr.next; 
                } 
            }

            while(sptr.next != null)
            {
                if(bptr.location == sptr.location)
                {
                    bptr.down = sptr;  
                    bprevptr = bptr; 
                    sprevptr = sptr;
                    sptr = sptr.next; 
                    break; 
                }

                sprevptr = sptr; 
                sptr = sptr.next; 
            }

            bptr = bptr.next; 
        }
    
    }
}