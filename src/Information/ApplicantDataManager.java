package src.Information;

import java.io.*;

public class ApplicantDataManager {
    private static final String FILE_NAME = "applicants.dat";

    public static void saveApplicants(Applicant[] applicants, int count) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeInt(count);
            for (int i = 0; i < count; i++) {
                out.writeObject(applicants[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to find the file for saving applicants.");
        } catch (IOException e) {
            System.out.println("Error: Unable to save applicants. Please check file permissions.");
        }
    }

    public static int loadApplicants(Applicant[] applicants) {
        int count = 0;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            count = in.readInt();
            for (int i = 0; i < count; i++) {
                applicants[i] = (Applicant) in.readObject();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading applicants: " + e.getMessage());
        }
        return count;
    }
}
