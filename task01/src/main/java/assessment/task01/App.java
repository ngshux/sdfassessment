package assessment.task01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
        String csvFilePath = args[0];
        String templateFilePath = args[1];

        MailMerge.templateMerge(csvFilePath, templateFilePath);
    }
}
