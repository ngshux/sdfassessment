package assessment.task01;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String csvFilePath = args[0];
        String templateFilePath = args[1];

        System.out.println("\n-----------------------------\nMerged file templates: \n\n");
        MailMerge.templateMerge(csvFilePath, templateFilePath);
    }
}
