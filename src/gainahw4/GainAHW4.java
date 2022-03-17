/** 
 * Integrity Policy Statement  
 * My words and actions will reflect Academic Integrity. 
 * I will not cheat or lie or steal in academic matters. 
 * I will promote integrity in the UNCG community.
 * Austin Gain 03/22/2022 
 */

/*
GainAHW4
Austin Gain
CSC 230, Sec 3
*/

// extra methods created to organize code
// txt files are in the same folder as this java file

package gainahw4;

// import statements
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GainAHW4 {

    // prints the program information
    public static void printInfo() {
        System.out.println("GainAHW4\n" + 
                            "Austin Gain\n" + 
                            "CSC 230, Sec 3\n\n" + 
                            "This program loads to an array a list of 50 grades read from a file.\n" +
                            "Once the scores have been loaded to the array, provide the user with a menu of options:\n" +
                            "A. Print the grades to the screen sorted in ascending order\n" +
                            "B. Calculate and print to the screen the average value of the scores\n" +
                            "C. Calculate and print to the screen the standard deviation of the scores\n" +
                            "D. Print the grades to a file sorted in ascending order, along with the average value and standard deviation of the scores\n" +
                            "E. Quit\n");
    }

    // prints the menu
    public static void printMenu() {
        System.out.println("****************************************************************\n" +
                            "* Menu:\n" + 
                            "* A - Show Grades in Ascending Order\n" +
                            "* B - Calculate and Print Average of Grades\n" +
                            "* C - Calculate and Print Standard Deviation of Grades\n" +
                            "* D - Create File with Grades, Average, and Standard Deviation\n" +
                            "* E - Quit\n" +
                            "****************************************************************\n");
    }

    // reads the input file to the array
    public static int[] readFileToArray() throws IOException, FileNotFoundException {
        File file = new File("gradesIn.txt");
        if (!file.exists()) {
            System.out.println("File does not exist, please check.");
        }
        Scanner input = new Scanner(file);
        int[] gradeArray = new int[50];
        while (input.hasNext()) {
            for (int i = 0; i < gradeArray.length; i++) {
                gradeArray[i] = input.nextInt();
            }
        }
        input.close();
        return gradeArray;
    }

    // returns the choice made by the user
    public static char getChoice() throws InputMismatchException {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter a letter for your choice on the menu (A, B, C, D, or E): ");
        char choice = keyboard.next().charAt(0);
        return choice;
    }

    // checks to see if the user choice is valid based on the menu
    public static boolean checkInArray(char choice) throws InputMismatchException {
        boolean contains = false;
        char[] choiceArray = {'A', 'B', 'C', 'D', 'a', 'b', 'c', 'd'};
        for (int i = 0; i < choiceArray.length; i++) {
            if (choice == choiceArray[i]) {
                contains = true;
            }
        }
        return contains;
    }

    // sorts the array
    public static int[] sortArraysMethod(int[] gradeArray) {
        Arrays.sort(gradeArray);
        return gradeArray;
    }

    // prints the array to the console
    public static void printValuesMethod(int[] gradeArray) {
        for (int i = 0; i < gradeArray.length; i++) {
            System.out.print(gradeArray[i] + " ");
        }
    }

    // calculates the average
    public static double averageArrayMethod(int[] gradeArray) {
        double sum = 0.0;
        double average = 0.0;
        for (int i = 0; i < gradeArray.length; i++) {
            sum += gradeArray[i];
        }
        average = sum / gradeArray.length;
        return average;
    }

    // calculates the standard deviation
    public static double StandDev(int[] gradeArray, double average) {
        double standDev = 0.0;
        for (int i = 0; i < gradeArray.length; i++) {
            standDev += Math.pow(gradeArray[i] - average, 2);
        }
        return Math.sqrt(standDev / gradeArray.length);
    }

    // outputs the data to a text file
    public static void printToFileArray(int[] gradeArray, double average, double standDev) throws IOException {
        PrintWriter writer = new PrintWriter("gradesOut.txt");
        gradeArray = sortArraysMethod(gradeArray);
        writer.println("The array in ascending order is:");
        for (int i = 0; i < gradeArray.length; i++) {
            writer.print(gradeArray[i] + " ");
        }
        writer.println("\n");
        writer.printf("The Average of the Grades is %.3f\n", average);
        writer.printf("The Standard Deviation of the Grades is %.3f", standDev);
        writer.close();
    }

    // performs the tasks based off the choice made by the user
    // contained in a while loop to have the user return to the menu
    public static void performMethods(int[] gradeArray, double average, double standDev, char choice, boolean contains) throws IOException, InputMismatchException {
        while (contains) {
            switch (choice) {
                case 'A', 'a':
                    gradeArray = sortArraysMethod(gradeArray);
                    System.out.println("The array in ascending order is:");
                    printValuesMethod(gradeArray);
                    System.out.println("\n");
                    printMenu();
                    choice = getChoice();
                    contains = checkInArray(choice);
                    break;
                case 'B', 'b': 
                    System.out.printf("The Average of the Grades is %.3f\n\n", average);
                    printMenu();
                    choice = getChoice();
                    contains = checkInArray(choice);
                    break;
                case 'C', 'c':
                    System.out.printf("The Standard Deviation of the Grades is %.3f\n\n", standDev);
                    printMenu();
                    choice = getChoice();
                    contains = checkInArray(choice);
                    break;
                case 'D', 'd':
                    printToFileArray(gradeArray, average, standDev);
                    System.out.println("Data outputed to txt file\n");
                    printMenu();
                    choice = getChoice();
                    contains = checkInArray(choice);
                    break;
                default:
                    break;
            }
        }
    }

    // main method
    public static void main(String[] args) throws IOException, FileNotFoundException, InputMismatchException {
        // prints program information and menu
        printInfo();
        printMenu();

        // initializes data from methods
        int[] gradeArray = readFileToArray();
        double average = averageArrayMethod(gradeArray);
        double standDev = StandDev(gradeArray, average);
        char choice = getChoice();
        boolean contains = checkInArray(choice);

        // perform tasks based off menu choice
        performMethods(gradeArray, average, standDev, choice, contains);

        // end of program
        System.out.println("Thanks for using my program!");
    }
}