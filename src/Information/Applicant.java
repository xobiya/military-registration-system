package src.Information;

import java.io.Serializable;

/**
 * Represents an applicant for the military registration system.
 * Stores personal details and provides eligibility checks.
 */
public class Applicant implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fullName;
    private String citizenship;
    private String maritalStatus;
    private int age;
    private double height;
    private String gender;
    private double weight;
    private String educationLevel;
    private boolean hasCriminalRecord;
    private boolean exMilitaryOrPolice;
    private boolean passedMedicalTest;

    public Applicant(String fullName, String citizenship, String maritalStatus, int age, double height, String gender,
            double weight, String educationLevel, boolean hasCriminalRecord,
            boolean exMilitaryOrPolice, boolean passedMedicalTest) {
        this.fullName = fullName;
        this.citizenship = citizenship;
        this.maritalStatus = maritalStatus;
        this.age = age;
        this.height = height;
        this.gender = gender;
        this.weight = weight;
        this.educationLevel = educationLevel;
        this.hasCriminalRecord = hasCriminalRecord;
        this.exMilitaryOrPolice = exMilitaryOrPolice;
        this.passedMedicalTest = passedMedicalTest;
    }

    // Setters and Getters omitted here for brevity (same as before)

    /**
     * Checks the eligibility of the applicant based on predefined criteria.
     * 
     * @return A string indicating eligibility or reasons for ineligibility.
     */
    public String checkEligibility() {
        String result = "";
        if (!citizenship.equalsIgnoreCase("Ethiopian"))
            result += "Not eligible - Must be Ethiopian.\n";
        if (maritalStatus.equalsIgnoreCase("Married"))
            result += "Not eligible - Must be unmarried.\n";
        if (age < 18 || age > 25)
            result += "Not eligible - Age must be between 18 and 25.\n";
        if (gender.equalsIgnoreCase("Male") && height < 1.60)
            result += "Not eligible - Male height must be >= 1.60m.\n";
        if (gender.equalsIgnoreCase("Male") && (weight < 55 || weight > 75))
            result += "Not eligible - Male weight must be 55-75kg.\n";
        if (gender.equalsIgnoreCase("Female") && height < 1.55)
            result += "Not eligible - Female height must be >= 1.55m.\n";
        if (gender.equalsIgnoreCase("Female") && (weight < 45 || weight > 65))
            result += "Not eligible - Female weight must be 45-65kg.\n";
        if (hasCriminalRecord)
            result += "Not eligible - Has criminal record.\n";
        if (exMilitaryOrPolice)
            result += "Not eligible - Ex-military/police not allowed.\n";
        if (!educationLevel.equalsIgnoreCase("above"))
            result += "Not eligible - Education must be above high school.\n";
        if (!passedMedicalTest)
            result += "Not eligible - Did not pass medical test.\n";
        return result.isEmpty() ? "Eligible" : result;
    }

    @Override
    public String toString() {
        return "Applicant [Full Name=" + fullName + ", Citizenship=" + citizenship +
                ", Marital Status=" + maritalStatus + ", Age=" + age + ", Height=" + height +
                ", Gender=" + gender + ", Weight=" + weight + ", Education Level=" + educationLevel + "]";
    }
}
