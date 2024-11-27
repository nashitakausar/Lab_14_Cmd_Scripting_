import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataSaver {
    public static void main(String[] args) {
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;

        while (true) {
            System.out.println("\nEnter new record:");

            String firstName = SafeInput.getNonEmptyString("Please enter first name: ");
            String lastName = SafeInput.getNonEmptyString("Please enter last name: ");
            String idNumber = String.format("%06d", idCounter++);
            String email = SafeInput.getEmail("Please enter email: ");
            int yearOfBirth = SafeInput.getInt("Please enter year of birth: ");

            String record = firstName + "," + lastName + "," + idNumber + "," + email + "," + yearOfBirth;
            records.add(record);

            boolean addMore = SafeInput.getYesOrNo("Would you like to add another record?");
            if (!addMore) break;
        }

        String fileName = SafeInput.getNonEmptyString("\nEnter the file name (without extension): ") + ".csv";

        saveToFile(records, fileName);
    }

    private static void saveToFile(ArrayList<String> records, String fileName) {
        try (FileWriter writer = new FileWriter("src/" + fileName)) {
            for (String record : records) {
                writer.write(record + "\n");
            }
            System.out.println("\nData saved successfully to " + fileName);
        } catch (IOException e) {
            System.out.println("There was an error writing to file: " + e.getMessage());
        }
    }
}
