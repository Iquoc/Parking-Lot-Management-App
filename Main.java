import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Create a Scanner object to read input from the console
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello World!");

        // Prompt the user for input
        String input = scan.nextLine();
        System.out.println("You entered: " + input);


        // Close the Scanner to free up resources
        scan.close();
    }

}
