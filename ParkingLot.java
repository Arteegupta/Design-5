import java.util.PriorityQueue;

//Time Complexity : O(log n)
//Space Complexity : O(n)
public class ParkingLot {	
	class ParkingSpot{
		int floor;
		int spot;
		public ParkingSpot(int fId, int sId) {
			this.floor= fId;
			this.spot= sId;
		}
		public int getFloor() {
			return this.floor;
		}
		public int getSpot() {
			return this.spot;
		}
	}
	
	//Define class level variables and constructor
	PriorityQueue<ParkingSpot> pq;	//Contains available spots
	int totalFloors;
	int totalSpots;
	public ParkingLot(int floors, int spots) {
		this.totalFloors= floors;
		this.totalSpots= spots;
		this.pq= new PriorityQueue<>((a,b)-> {
			if(a.floor == b.floor)
				return a.spot-b.spot;
			else
				return a.floor-b.floor;
		});
	}
	
	public void park() { //O(log n)
		if(pq.isEmpty()) throw new IllegalArgumentException("Parking lot full!");
		ParkingSpot nextSpot= pq.peek();
		System.out.println("Parked at Floor "+ nextSpot.floor + " Spot "+ nextSpot.spot);
		pq.remove();
	}
	
	public void unPark(int floor, int spot) { //O(log n)
		ParkingSpot s= new ParkingSpot(floor, spot);
		System.out.println("Unparked from Floor "+ s.floor + " Spot "+ s.spot);
		pq.add(s);
	}
	
	public void addParkingSpot(int floor, int spot) { //O(log n)
		if(floor > totalFloors) throw new IllegalArgumentException("Floor input is greater than max allowed!");
		if(spot > totalSpots) throw new IllegalArgumentException("Spot input is greater than max allowed!");
		ParkingSpot newSpot= new ParkingSpot(floor, spot);	
		pq.add(newSpot);
	}
	
	public ParkingSpot getNextAvailable() { //O(1)
		System.out.println("Next available spot is Floor "+ pq.peek().floor + " Spot "+ pq.peek().spot);
		return pq.peek();
	}
	
	
	// Driver code to test above
	public static void main (String[] args) {
		ParkingLot ob= new ParkingLot(2, 5);//Parking lot capacity is 2 floors and 5 spots each floor
		ob.addParkingSpot(1, 1);
		ob.addParkingSpot(1, 2);		
		ob.park(); //floor 1, spot 1
		ob.getNextAvailable();
		ob.park(); //floor 1, spot 2
		ob.unPark(1, 2); 
		ob.park(); //floor 1, spot 2		
		ob.addParkingSpot(2, 1);
		ob.getNextAvailable();
		ob.park();	//floor 2, spot 1
		ob.park(); //Parking lot full
		
	}
}
