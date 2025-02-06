package org.alpercaktug;

import java.util.Scanner;

import io.cucumber.core.cli.Main;
import org.alpercaktug.core.BaseTest;

public class MainTestApp extends BaseTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Test Type: \n1. API Tests \n2. Browser Tests \n3. Mobile Tests");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String testType = null;
        switch (choice) {
            case 1:
                testType = "api";
                break;
            case 2:
                testType = "chrome"; // Or "firefox"
                break;
            case 3:
                testType = "android"; // Or "ios"
                break;
            default:
                System.out.println("Invalid choice!");
                System.exit(1);
        }

        System.out.println("Enter Cucumber Tags (e.g., @smoke): ");
        String tags = scanner.nextLine();

        new MainTestApp().setup(testType);

        String[] cucumberArgs = {"-p", "pretty", "-g", "steps", "-t", tags, "src/test/resources/features"};
        try {
            Main.run(cucumberArgs, Thread.currentThread().getContextClassLoader());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            new MainTestApp().teardown();
        }

        scanner.close();
    }
}
