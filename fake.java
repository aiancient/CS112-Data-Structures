public class fake {
    TNode bptr = trainZero.down; 
		TNode bconnectprev = new TNode(); 
		TNode bconnect = new TNode();
		TNode soriginal = new TNode(); 
		TNode busOrignial = trainZero.down;
		TNode walkingOrignial = trainZero.down.down; 
		TNode wprev = new TNode();  
		TNode sptr = soriginal; 
		TNode sprev = new TNode();  
		TNode wptr = trainZero.down.down; 

		for(int i = 0; i<scooterStops.length; i++)
		{
			while(wptr.next != null)
			{
				if(scooterStops[i] == wptr.location)
				{
					boolean connectBus = false; 
					while(bptr.next != null)
					{
						if(bptr.location == scooterStops[i])
						{
							connectBus = true; 
							bconnect = bptr; 
							break; 
						}

						else if(bptr.next.next == null && bptr.next.location == scooterStops[i])
						{
							connectBus = true; 
							bconnect = bptr.next; 
							break; 
						}

						bconnectprev = bptr; 
						bptr = bptr.next; 
					}

					if(connectBus)
					{
						TNode newWalking = new TNode(scooterStops[i],(wprev.next == null ? null : wprev.next),null); 
						TNode newScooter = new TNode(scooterStops[i], null, newWalking); 
						sprev.next = newScooter; 
						TNode newConnectBus = new TNode(bconnect.location,(bconnect.next == null ? null : bconnect.next),newScooter);
						bconnectprev.next = newConnectBus; 
						bconnectprev = newConnectBus; 
						sprev = newScooter; 
						wprev = newWalking; 
						break; 
					}

					else
					{
						TNode newWalking = new TNode(scooterStops[i],(wprev.next == null ? null : wprev.next),null); 
						TNode newScooter = new TNode(scooterStops[i], null, newWalking); 
						sprev.next = newScooter; 
						sprev = newScooter; 
						wprev = newWalking; 
						break;
					}
				}

				wptr = wptr.next; 
			}

			wptr = wprev; 
			bptr = bconnectprev; 
			sptr = sprev; 
		}

		busOrignial.down = soriginal; 
		soriginal.down = walkingOrignial; 
	}







	//current add scooters
	TNode boriginal = trainZero.down; 
        TNode soriginal = new TNode(); 
        TNode woriginal = trainZero.down.down; 

        TNode sprevptr = soriginal; 

        for(int i = 0; i<=scooterStops.length -1; i++)
        {
            TNode newScooter = new TNode(scooterStops[i],null,null);

            sprevptr.next = newScooter; 
            sprevptr = newScooter; 
        }

        TNode bptr = boriginal; 
        TNode bprevptr = boriginal;
        TNode sptr = soriginal; 
        sprevptr = soriginal; 
        
        boriginal.down = soriginal; 


        while(bptr.next != null)
        {
            sptr = sptr.next; 

            if(sptr == null)
            {
                break; 
            }

            else
            {
                if(bptr.location < sptr.location)
                {
                    bprevptr = bptr; 
                    bptr = bptr.next;
                    sptr = sprevptr; 
                    continue; 
                }

                else if(bptr.location > sptr.location)
                {
                    sprevptr = sptr; 
                    continue; 
                }

                else
                {
                    bptr.down = sptr; 
                    sprevptr = sptr; 
                }
            }
            

            bprevptr = bptr; 
            bptr = bptr.next; 

            if(bprevptr.next.next == null)
            {
                while(sptr.next != null)
                {
                    
                }
            }
        }


		//changed make list 
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














        /////so far final make list 
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

            /*if(tprevptr.next.next == null)
            {
                tprevptr.next.down = bptr; 
                return toriginal; 
            }*/

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
