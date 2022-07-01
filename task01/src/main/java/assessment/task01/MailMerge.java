package assessment.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MailMerge {

    public static void templateMerge (String csvPath, String templatePath) throws IOException{
        File csvFile = new File(csvPath);
        File templateFile = new File(templatePath);
        List<String[]> recipients = new ArrayList<>();
        Scanner scanner;
        String prefix = "";

        try {
            //add csv into array
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] csvLineInArray = line.split(",");
                recipients.add(csvLineInArray);
            }
            //test
            for (String[] recipient : recipients){
                scanner = new Scanner(templateFile);
                while(scanner.hasNextLine()){
                String text = scanner.nextLine();
                if(text.contains("__first_name__")){
                    text = text.replace("__first_name__",recipient[0]);
                }
                if(text.contains("__address__")){
                    text = text.replace("__address__",recipient[2]);
                }
                if(text.contains("__years__")){
                    text = text.replace("__years__",recipient[3]);
                }
                System.out.println(text);
                }
            //System.out.println(Arrays.toString(recipient));
            }    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
