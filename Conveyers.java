package Project1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

/*
 * Name: Showad Huda
 * Course: CNT4714 Summer 2021
 * Assignment title: Project 1–Multi-threaded programming in Java
 * Date: June 6, 2021
 * 
 * Class: Conveyer
*/

public class Conveyers {
	//Number for conveyer
	private int conveyerNum;
	
	//Lock
	public ReentrantLock theLock = new ReentrantLock();

	private boolean occupied = false;
	
	//Constructor
	public Conveyers(int conveyerNum) {
		this.conveyerNum = conveyerNum;
	}
	
	//Routing station to get lock
	//Print getter and setters
	public void inputPrint(int stationNumber) {	
		
		System.out.println("Routing Station " + stationNumber + ": ...Active... moving packages into station on input conveyer " + this.conveyerNum);

		
	}
	
	
	
	public void outputPrint(int stationNumber) {	
		System.out.println("Routing Station " + stationNumber + ": ...Active... moving packages into station on output conveyer " + this.conveyerNum);
	}
}

/*	public boolean isOccupied() {
		return occupied;
	}



	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
}
	*/