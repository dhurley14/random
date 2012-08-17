import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Write a description of class Routes here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Routes {

	protected static ArrayList<String> possibleRoutes 
	= new ArrayList<String>();
	protected static ArrayList<String> temp 
	= new ArrayList<String>();
	protected static ArrayList<String> doubleRoutes 
	= new ArrayList<String>();
	protected static String path;

	public void Routes() {

	}

	/**
	 * this method checks if the city passed 
	 * in is contained in the route that
	 * the player is given.
	 * 
	 * @param Rectangle
	 * @return true if the city passed in is contained in the route
	 * */

	public static void main(String[] args) {
		routeChecker();
		for (int i = 0; i < possibleRoutes.size(); i++) {
			System.out.println(possibleRoutes.get(i));

		}
		edgeChecker("Vaasa");
		System.out.println("");
		System.out.println("");
		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i));

		}
		System.out.println("");
		System.out.println("");

		tempChecker("Sundsvall");
		for (int i = 0; i < doubleRoutes.size(); i++) {
			System.out.println(doubleRoutes.get(i));

		}
		removePossibleRouteWithColor("Blue");
		System.out.println("");
		System.out.println("");
		for (int i = 0; i < possibleRoutes.size(); i++) {
			System.out.println(possibleRoutes.get(i));

		}
		System.out.println("");
		System.out.println("");
		edgeChecker("Vassa");
		for (int i = 0; i < temp.size(); i++) {
			System.out.println(temp.get(i));

		}
	}

	/**
	 * Method to see if the city we 
	 * click on is in the arrayList of possible
	 * routes we can take from
	 * 
	 * @param String
	 *            city
	 * @return void
	 * 
	 * 
	 */
	public static void edgeChecker(String city) {
		for (int i = 0; i < possibleRoutes.size(); i++) {
			if (possibleRoutes.get(i).contains(city)) {
				temp.add(possibleRoutes.get(i));
			}
		}
	}

	/**
	 * Method to see if the city has more than one route
	 * 
	 * 
	 * @param String
	 *            city
	 * @return void
	 */
	public static void tempChecker(String city) {
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).contains(city)) {
				doubleRoutes.add(temp.get(i));
			}
		}
	}

	/**
	 * Method to delete a route from 
	 * possible routes using that colored path
	 * 
	 * @param String
	 *            color
	 * @return void
	 */
	public static void removePossibleRouteWithColor(String color) {
		for (int i = 0; i < doubleRoutes.size(); i++) {
			if (doubleRoutes.get(i).contains(color)) {
				possibleRoutes.remove(doubleRoutes.get(i));
				temp.clear();
				doubleRoutes.clear();
			}
		}

	}

	/**
	 * checks any possible route feed in a file from blackboard
	 * 
	 */

	public static void routeChecker() {
		Scanner scanner = null;
		int i = 0;
		try {
			scanner = new Scanner(new File("edges.txt"));

		} catch (FileNotFoundException e) {
			System.exit(0);
		}
		while (scanner.hasNextLine()) {
			possibleRoutes.add(scanner.nextLine());
		}

	}
}
