package assessment.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MailMerge {

    public static void templateMerge (String csvPath, String templatePath) throws IOException{
        File csvFile = new File(csvPath);
        File templateFile = new File(templatePath);
        List<String[]> recipients = new ArrayList<>();
        Scanner scanner;
        String prefix = "__";

        try {
            //add csv into array
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            //get first row
            String[] variables = br.readLine().split(",");
            String line;
            while ((line = br.readLine()) != null) {
                String[] csvLineInArray = line.split(",");
                recipients.add(csvLineInArray);
            }
            //template
            for (String[] vars : recipients){
                scanner = new Scanner(templateFile);
                while(scanner.hasNextLine()){
                String text = scanner.nextLine();
                for (int i = 0; i < variables.length; i++) {
                    if(text.contains(variables[i])){
                        text=text.replace(prefix+variables[i]+prefix, vars[i]);
                    }
                }
                /*if(text.contains("__address__")){
                    text = text.replace("__address__",vars[2]);
                }
                if(text.contains("__first_name__")){
                    text = text.replace("__first_name__",vars[0]);
                }
                if(text.contains("__years__")){
                    text = text.replace("__years__",vars[3]);
                }*/
                text = text.replaceAll("\\\\n","\n");  
                System.out.println(text);
                }
                System.out.println("\n\n");
            }   
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
