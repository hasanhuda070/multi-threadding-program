package Project1;

//Pre-processor directives
//import java.io.*;
//import java.util.*;
import java.util.Random;
/*
 * Name: Showad Huda
 * Course: CNT4714 Summer 2021
 * Assignment title: Project 1–Multi-threaded programming in Java
 * Date: June 6, 2021
 * 
 * Class: RoutingStation
*/
public class RoutingStation implements Runnable {
	protected Random sleeptime = new Random();
	protected Random holdtime = new Random();
	//Sleep in milliseconds
	private static int MAXSLEEP = 500;
	private static int MAXHOLD = 500;
	private static int maxSN;
	//Number for routing station
	private int workload;
	private int inNumber;
	private int outNumber;
	private int stationNumber;
	private Conveyers input;
	private Conveyers  output;
	//private static Random generator = new Random();
	
	
	//Routing Station
	public RoutingStation(int workload, int stationNumber, int maxSN, int inNumber, int outNumber) {
		this.workload = workload;
		this.stationNumber = stationNumber;
		RoutingStation.maxSN = maxSN;
		this.inNumber = inNumber;
		this.outNumber = outNumber;
		
		
	}
	
	//Sleep method for routing station
	public void goToSleep() {
		try {
			//Simulate random time between 1 and MAXSLEEP in milliseconds
			Thread.sleep(sleeptime.nextInt(MAXSLEEP-1+1)+1);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void holdConveyers() {
		try {
			Thread.sleep(holdtime.nextInt(MAXHOLD-1+1)+1);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void doWork() {	
	
				this.input.inputPrint(this.stationNumber);
				this.output.outputPrint(this.stationNumber);
				this.workload--; //decrement workload
				System.out.println("Routing Station " + stationNumber + ": Workload set. Station " + this.stationNumber + " has a total of " + this.workload + " package groups to move");	
	}
	@Override
	//Print statements for inconveyer and outconveyer of conveyer and initial workload
	//Loop to showcase all possible instances of the configuration file from C0 to C2
	public void run() { 
		System.out.println("Routing Station " + stationNumber + ": Input connection is set to coveyer number " + inNumber );
		System.out.println("Routing Station " + stationNumber + ": Output connection is set to coveyer number " + outNumber );
		System.out.println("Routing Station " + stationNumber + ": Workload set on Station has a total of  " + workload + " package groups to move.");
		while(this.workload != 0) {
			//Lock system
			if(input.theLock.tryLock()) {
				System.out.println("Routing Station " + this.stationNumber + ": LOCK ACQUIRED! Now holding lock on input conveyer C" + this.inNumber);
	
				if(output.theLock.tryLock()) {
					System.out.println("Routing Station " + this.stationNumber + ": LOCK ACQUIRED! Now holding lock on output conveyer C" + this.inNumber);
					doWork();
					
				
				
				}
				else
				{	
					//Lock releases
					System.out.println("Routing Station " + this.stationNumber + ": Unlocks output conveyer C " + this.inNumber);
					input.theLock.unlock();
					
					try 
					{
						//Thread sleep simulating a wait
						Thread.sleep(500);
					} catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
				
				//Lock releases
				if(input.theLock.isHeldByCurrentThread()) {
					System.out.println("Routing Station " + this.stationNumber + ": Number of packages groups left to move is " + this.inNumber);
					input.theLock.unlock();

				}
				
				if(output.theLock.isHeldByCurrentThread()) {
					System.out.println("Routing Station " + this.stationNumber + ": Number of packages groups left to move is " + this.outNumber);
					output.theLock.unlock();
				}
				
				try 
				{
					//put thread to sleep (simulate a wait)
					Thread.sleep(1500);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}

			}
		}
		
		//Finish statements
		System.out.println(); 
		System.out.println("* * * Routing Station " + stationNumber + ": Workload successfully completed * * * Station Going Idle!");
		System.out.println();
		
		
	}
	

	
	//Numerous getters and setters for input and output with conveyer
	public Conveyers getInput() {
		return input;
	}

	public void inConveyer(Conveyers conveyers){
		this.input = conveyers;
	}

	public Conveyers getOutput(){
		return output;
	}

	public void outConveyer(Conveyers conveyers) {
		this.output = conveyers;
	}
	
	public int getInputNumber() {
		return inNumber;
	}

	public void setInputNumber() {
		if(stationNumber == 0) {
			this.inNumber = 0;
		}
		else {
			this.inNumber = this.stationNumber - 1;
		}
		
		System.out.println("Routing Station " + stationNumber + ": Input connection is set to conveyer number C" + this.inNumber);

	}

	public int getOutputNumber() {
		return outNumber;
	}

	public void setOutputNumber() {
		if(this.stationNumber == 0) {
			this.outNumber = RoutingStation.maxSN - 1;
		}
		else {
			this.outNumber = this.stationNumber;
		}
		
		System.out.println("Routing Station " + stationNumber + ": Output connection is set to conveyer number C" + this.outNumber);
	}

	public int inNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int outNumber() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}