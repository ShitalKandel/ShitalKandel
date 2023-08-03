
import java.util.*;
import java.io.*;

//we give a class random team creation as RTC
public class RTC {
    public static void main(String[] args) {

        //we assign stdline
        Scanner scanner = new Scanner(System.in);
    //constructor for  class list
        List<String> rollnum = RollNum();

        System.out.println("Enter the required number of Groups:");
        int numGroups = scanner.nextInt();

        List<List<String>> groups = createRandomGroup(rollnum, numGroups);

        String filename = storegroup(groups, "team_groups.txt");

        System.out.println("Random teams created and stored in '" + filename + "'");
    }
//assigning rollnum on a list
    public static List<String> RollNum() {
        List<String> rollnum = new ArrayList<>();

        rollnum.add("RollNum.1");
        rollnum.add("RollNum.2");
        rollnum.add("RollNum.3");
        rollnum.add("RollNum.4");
        rollnum.add("RollNum.5");
        rollnum.add("RollNum.6");
        rollnum.add("RollNum.7");
        rollnum.add("RollNum.8");
        rollnum.add("RollNum.9");
        rollnum.add("RollNum.10");
        rollnum.add("RollNum.11");
        rollnum.add("RollNum.12");
        rollnum.add("RollNum.13");
        rollnum.add("RollNum.14");

        return rollnum;
    }

    private static List<List<String>> createRandomGroup(List<String> rollnum, int numGroup) {
        List<List<String>> groups = new ArrayList<>();
        Random random = new Random();
//Making input from CLI for (int) num of group
        List<String> shufflerollforteam = new ArrayList<>(rollnum);
        for (int i = shufflerollforteam.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String s = shufflerollforteam.get(i);
            shufflerollforteam.set(i, shufflerollforteam.get(j));
            shufflerollforteam.set(j, s);
        }

        int rollNumGroup = shufflerollforteam.size() / numGroup;
        int leftrollnum = shufflerollforteam.size() % numGroup;
        int startIndex = 0;

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
//string output on filewriter
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
