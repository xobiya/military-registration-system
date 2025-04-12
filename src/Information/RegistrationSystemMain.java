package src.Information;

import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

public class RegistrationSystemMain {
    static Applicant[] applicants = new Applicant[100];
    static int applicantCount = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Load applicants from file
        applicantCount = ApplicantDataManager.loadApplicants(applicants);

        while (true) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("🪖 Military Registration System");
            System.out.println("1. Add New Applicant");
            System.out.println("2. Display All Applicants");
            System.out.println("3. Check Eligibility of an Applicant");
            System.out.println("4. Display All Eligible Applicants");
            System.out.println("5. Update an Applicant's Details");
            System.out.println("6. Remove Applicant");
            System.out.println("7. Exit");
            System.out.println("--------------------------------------------------------------");
            System.out.print("Choose an option: ");

            try {
                int choice = input.nextInt();
                input.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> addApplicant(input);
                    case 2 -> displayAllApplicants();
                    case 3 -> checkEligibility(input);
                    case 4 -> displayEligibleApplicants();
                    case 5 -> updateApplicant(input);
                    case 6 -> removeApplicant(input);
                    case 7 -> {
                        System.out.println("Exiting system. Goodbye!");
                        input.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please select again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                input.nextLine(); // clear invalid input
            }
        }
    }

    public static void addApplicant(Scanner input) {
        try {
            System.out.print("Full Name: ");
            String fullName = input.nextLine().trim();

            String citizenship;
            while (true) {
                System.out.print("Citizenship ('Ethiopian'): ");
                citizenship = input.nextLine();
                if (citizenship.equalsIgnoreCase("Ethiopian")) break;
                else System.out.println("Citizenship must be 'Ethiopian'.");
            }

            String maritalStatus;
            while (true) {
                System.out.print("Marital Status (Married/Unmarried): ");
                maritalStatus = input.nextLine();
                if (maritalStatus.equalsIgnoreCase("Married") || maritalStatus.equalsIgnoreCase("Unmarried")) break;
                else System.out.println("Enter 'Married' or 'Unmarried'.");
            }

            String gender;
            while (true) {
                System.out.print("Gender (Male/Female): ");
                gender = input.nextLine();
                if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) break;
                else System.out.println("Enter 'Male' or 'Female'.");
            }

            System.out.print("Age: ");
            int age = input.nextInt();
            System.out.print("Height (in meters): ");
            double height = input.nextDouble();
            System.out.print("Weight (in kg): ");
            double weight = input.nextDouble();
            input.nextLine(); // consume newline

            String educationLevel;
            while (true) {
                System.out.print("Education Level (above/not): ");
                educationLevel = input.nextLine();
                if (educationLevel.equalsIgnoreCase("above") || educationLevel.equalsIgnoreCase("not")) break;
                else System.out.println("Enter 'above' or 'not'.");
            }

            boolean hasCriminalRecord = askBoolean(input, "Has Criminal Record (yes/no)? ");
            boolean exMilitary = askBoolean(input, "Ex-Military or Police (yes/no)? ");
            boolean passedMedical = askBoolean(input, "Passed Medical Test (yes/no)? ");

            applicants[applicantCount++] = new Applicant(
                fullName, citizenship, maritalStatus, age, height, gender, weight,
                educationLevel, hasCriminalRecord, exMilitary, passedMedical
            );

            System.out.println("Applicant added successfully.");

            // Save after adding
            ApplicantDataManager.saveApplicants(applicants, applicantCount);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            input.nextLine(); // clear buffer
        }
    }

    public static boolean askBoolean(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = input.nextLine().toLowerCase();
            if (response.equals("yes")) return true;
            if (response.equals("no")) return false;
            System.out.println("Enter 'yes' or 'no'.");
        }
    }

    public static void displayAllApplicants() {
        if (applicantCount == 0) {
            System.out.println("No applicants found.");
            return;
        }
        for (int i = 0; i < applicantCount; i++) {
            System.out.println(applicants[i]);
        }
    }

    public static void checkEligibility(Scanner input) {
        System.out.print("Enter full name to check eligibility: ");
        String name = input.nextLine();
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i].getFullName().equalsIgnoreCase(name)) {
                System.out.println("Eligibility: " + applicants[i].checkEligibility());
                return;
            }
        }
        System.out.println("Applicant not found.");
    }

    public static void displayEligibleApplicants() {
        boolean found = false;
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i].checkEligibility().equals("Eligible")) {
                System.out.println(applicants[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No eligible applicants found.");
    }

    public static void updateApplicant(Scanner input) {
        System.out.print("Enter full name of applicant to update: ");
        String name = input.nextLine();
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i].getFullName().equalsIgnoreCase(name)) {
                System.out.print("New Age: ");
                applicants[i].setAge(input.nextInt());
                System.out.print("New Height (m): ");
                applicants[i].setHeight(input.nextDouble());
                System.out.print("New Weight (kg): ");
                applicants[i].setWeight(input.nextDouble());
                input.nextLine(); // consume newline

                System.out.print("Education Level (above/not): ");
                applicants[i].setEducationLevel(input.nextLine());

                applicants[i].setHasCriminalRecord(askBoolean(input, "Has Criminal Record (yes/no)? "));
                applicants[i].setExMilitaryOrPolice(askBoolean(input, "Ex-Military or Police (yes/no)? "));
                applicants[i].setPassedMedicalTest(askBoolean(input, "Passed Medical Test (yes/no)? "));

                System.out.println("Applicant updated.");

                // Save after update
                ApplicantDataManager.saveApplicants(applicants, applicantCount);
                return;
            }
        }
        System.out.println("Applicant not found.");
    }

    public static void removeApplicant(Scanner input) {
        System.out.print("Enter full name to remove: ");
        String name = input.nextLine();
        for (int i = 0; i < applicantCount; i++) {
            if (applicants[i].getFullName().equalsIgnoreCase(name)) {
                for (int j = i; j < applicantCount - 1; j++) {
                    applicants[j] = applicants[j + 1];
                }
                applicants[--applicantCount] = null;
                System.out.println("Applicant removed.");

                // Save after removal
                ApplicantDataManager.saveApplicants(applicants, applicantCount);
                return;
            }
        }
        System.out.println("Applicant not found.");
    }
}
