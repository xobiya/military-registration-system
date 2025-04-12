package tests;

import src.Information.Applicant;

public class ApplicantTest {
    public static void main(String[] args) {
        Applicant applicant = new Applicant(
                "John Doe", "Ethiopian", "Unmarried", 22, 1.75, "Male", 70,
                "above", false, false, true);

        String eligibility = applicant.checkEligibility();
        System.out.println("Eligibility: " + eligibility);
        assert eligibility.equals("Eligible") : "Test failed!";
    }
}
