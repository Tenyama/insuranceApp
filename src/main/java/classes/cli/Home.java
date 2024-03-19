package classes;
/**
 * @author Le Thien Son - s3977955
 */

import java.util.Scanner;
import picocli.CommandLine;
public class Home {
    public static void displayHomeScreen() {
        System.out.println("==================================================");
        System.out.println("|                   Insurance App                |");
        System.out.println("==================================================");
        System.out.println("|                                                |");
        System.out.println("|          Welcome to Insurance Management       |");
        System.out.println("|                                                |");
        System.out.println("|   1. Manage Customers                          |");
        System.out.println("|   2. Manage Claims                             |");
        System.out.println("|   3. Reports                                   |");
        System.out.println("|   4. Exit                                      |");
        System.out.println("|                                                |");
        System.out.println("==================================================");
        System.out.print("Please enter your choice (1-4): ");

        // Read user input
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        // Process user choice
        switch (choice) {
            case 1:
                // Call method to manage customers
                System.out.println("You selected: Manage Customers");
                break;
            case 2:
                // Call method to manage claims
                System.out.println("You selected: Manage Claims");
                break;
            case 3:
                // Call method to generate reports
                System.out.println("You selected: Reports");
                break;
            case 4:
                // Exit the program
                System.out.println("Exiting...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
        }
    }
}
