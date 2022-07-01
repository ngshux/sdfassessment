package assessment.task02;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import javax.print.attribute.standard.RequestingUserName;

import java.util.ArrayList;

public class App 
{
    static String requestId;
    static String name = "Ng Shu Xian";
    static String email = "shuxian_98@hotmail.com";
    static Float total = 0f;
    static Float average = 0f;

    public static void main( String[] args )
    {
        try {
            Socket sock = new Socket("task02.chuklee.com",80);
            sock.setSoTimeout(15000);

            OutputStream os = sock.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            
            InputStream is = sock.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            
            String[] requestFromServer = ois.readUTF().split(" ");
            requestId = requestFromServer[0];
            
            ArrayList<Float> number = new ArrayList<Float>();
            for (String s : requestFromServer[1].split(",")){
                number.add(Float.parseFloat(s));
            }
            calcAverage(number);

            oos.writeUTF(requestId);
            System.out.println("Request Id: "+requestId);
            oos.writeUTF(name);
            System.out.println("Name :" + name);
            oos.writeUTF(email);
            System.out.println("Email :" + email);
            oos.writeFloat(average);
            System.out.println("Average of numbers :"+average);

            oos.flush();
            Boolean result = ois.readBoolean();
            if(result)
                System.out.println("SUCCESS");
            else{
                String error = ois.readUTF();
                System.out.println("FAILED\n" + error);
            }    
            //System.exit();

            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    private static void calcAverage(ArrayList<Float> listofNums){
        for (int i = 0; i < listofNums.size(); i++) {
            total += listofNums.get(i);
        }
        average = total/listofNums.size();
    }
}
