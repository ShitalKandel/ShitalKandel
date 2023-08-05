
import java.util.*;
import java.io.*;

//we give a class random team creation as RTC
public class RTC {
    public static void main(String[] args) {

        //we create a scanner to take user input
        Scanner scanner = new Scanner(System.in);

    //Initialize a list to hold user input
        List<String> rollnum = RollNum();

        System.out.println("Enter the number of students:");
        //taking input from users for the number of students

        int numStudents = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character


        //generate roll number for the number of students
        rollnum = new ArrayList<>();
        for (int i = 1; i <= numStudents; i++) {
            rollnum.add("RollNum." + i);
        }

        //taking input from users to prompt the number of groups
        System.out.println("Enter the required number of Groups:");
        int numGroups = scanner.nextInt();

        //creating random groups from the provided input for students and groups
        List<List<String>> groups = createRandomGroup(rollnum, numGroups);

        //storing the created random group on filewriter named as team_group.txt
        String filename = storegroup(groups, "team_groups.txt");

        //print message to show successfull creation and store of group
        System.out.println("Random teams created and stored in '" + filename + "'");
    }

    //method to generate list of RollNUm
    private static List<String> RollNum() {
        return null; // we can even modify this method to return a predefined list
    }


    //method to create random group from list of rollNumbers
    private static List<List<String>> createRandomGroup(List<String> rollnum, int numGroup) {
        List<List<String>> groups = new ArrayList<>();
        Random random = new Random();

        //shuffle the role number randomly
        List<String> shufflerollforteam = new ArrayList<>(rollnum);
        for (int i = shufflerollforteam.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String s = shufflerollforteam.get(i);
            shufflerollforteam.set(i, shufflerollforteam.get(j));
            shufflerollforteam.set(j, s);
        }

        //calculate the number of rollnumber per group and remaining one
        int rollNumGroup = shufflerollforteam.size() / numGroup;
        int leftrollnum = shufflerollforteam.size() % numGroup;
        int startIndex = 0;

        //create group and assign roll number to each group
        for (int i = 0; i < numGroup; i++) {
            int endIndex = startIndex + rollNumGroup;

            if (leftrollnum > 0) {
                endIndex++;
                leftrollnum--;
            }

            List<String> group = shufflerollforteam.subList(startIndex, endIndex);
            groups.add(group);

            startIndex = endIndex;
        }

        return groups;
    }

    //method to store the created ranodm group in text file
    private static String storegroup(List<List<String>> createRandomGroup, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (int i = 0; i < createRandomGroup.size(); i++) {
                writer.write("Group " + (i + 1) + ": ");
                for (String rollNumber : createRandomGroup.get(i)) {
                    writer.write(rollNumber + " ");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename;
    }
}
