//Package directive
package Project1;

//Pre-processor directives
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Name: Showad Huda
 * Course: CNT4714 Summer 2021
 * Assignment title: Project 1–Multi-threaded programming in Java
 * Date: June 6, 2021
 * Class: PackageManagementFacility
*/

public class PackageManagementFacility {
	//Read configuration file
	static String fileName = "config.txt";
	
	//maximum number of routing stations and conveyers that can be configured
	static int MAX = 10;
	
	//Declare variables
	static int[] workload;
	static Conveyers[] conveyers;
	static RoutingStation[] stations;
	public static void main(String[] args) throws FileNotFoundException { //FileNotFoundException
		//Print start messages
		System.out.println("\n CNT 4714 - Project 1 - Summer 2021 - Showad Huda \n");
		System.out.println("\n * * * SIMULATION BEGINS * * * \n");
		//Read config.txt input file containing number of routing stations and conveyers
		File file = new File(fileName);
		Scanner read = new Scanner(file);
		
		//Retrieves input from file config.txt
		//Array placement
		MAX = read.nextInt();
		workload = new int[MAX];
		
		//Close file
		//file.close();
		
		//Array for conveyers
		conveyers = new Conveyers[MAX];
		
		//Fill array with conveyers
		for(int i = 0; i < conveyers.length; i++) {
			conveyers[i] = new Conveyers(i);
		}
		
		//Routing Station creation
		stations = new RoutingStation[MAX];
		
		//Thread pool start
		ExecutorService application = Executors.newFixedThreadPool(MAX);
		
		for(int i = 0; i < workload.length && i < conveyers.length && i < stations.length; i++)  {
			//set workload
			workload[i] = read.nextInt();
			//create instance of stations
			stations[i] = new RoutingStation(workload[i], i, MAX, i, i);
			//Input and Output for conveyer statements
			stations[i].inConveyer(conveyers[stations[i].inNumber()]);
			stations[i].outConveyer(conveyers[stations[i].outNumber()]);
			
			//start up the stations
			try {
					application.execute(stations[i]);
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
				
		//Application shutdown
		while(!application.isTerminated()) {
		}
		application.shutdown();
		//Finished all threads statement
		System.out.println("\n * * * ALL WORKLOADS COMPLETE * * * SIMULATION ENDS * * * ");
		
	}
	//catch(FileNotFoundException e) {
		//System.out.println("File not found, try again.");
		
	//}

}