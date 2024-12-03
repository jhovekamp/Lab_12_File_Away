import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        //String rec = "";
        //final int FIELDS_LENGTH = 5;


        String fName, lName, email, id, yob;
        boolean done = false;
        String continueYN = "";
        Scanner in = new Scanner(System.in);

        ArrayList<String>recs = new ArrayList<>();
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\TestWriteFile.csv");

        try
        {
            do
            {
                fName = SafeInput.getNonZeroLenString(in, "Enter the first name");
                lName = SafeInput.getNonZeroLenString(in, "Enter the last name");
                email = SafeInput.getRegExString(in, "Enter the email", "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
                id = SafeInput.getRegExString(in, "Enter the 6 digit ID", "^\\d{6}$");
                yob = SafeInput.getRegExString(in, "Enter the year of birth [YYYY]", "^\\d{4}$");

                recs.add(String.join(",", fName, lName, email, id, yob));
                System.out.print("Do you want to enter another record?: ");
                continueYN = in.nextLine();
            }
            while(continueYN.equalsIgnoreCase("Y"));


//            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
//            {
//                selectedFile = chooser.getSelectedFile();
//                Path file = selectedFile.toPath();
                OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));//take low level output stream and wrap a buffered output stream around output stream
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));//take buffered output stream (called out) and create a buffered writer (highlevel). Use the writer to write to the file

                for (String rec : recs)//write to the output stream.for every record in recs (arraylist). loop through and write all the records.
                {
                    writer.write(rec, 0, rec.length());
                    writer.newLine();
                }
                writer.close();//close the file
                System.out.println("Data file written!");//notify the file has been written

                // Now process the lines in the arrayList
                // Split the line into the fields by using split with a comma
                // use trim to remove leading and trailing spaces
                // Numbers need to be converted back to numberic values. Here only
                // the last field year of birth yob is an int the rest are strings.
/*
                String[] fields;
                for(String l:lines)
                {
                    fields = l.split(","); // Split the record into the fields

                    if(fields.length == FIELDS_LENGTH)
                    {
                        id        = fields[0].trim();
                        fName     = fields[1].trim();
                        lName     = fields[2].trim();
                        email     = fields[3].trim();
                        yob       = fields[4].trim();
                        System.out.printf("\n%-8s%-25s%-25s%-6s%6d", id, fName, lName, email, yob);
                    }
                    else
                    {
                        System.out.println("Found a record that may be corrupt: ");
                        System.out.println(l);
                    }
                }

            }
            else  // user closed the file dialog wihtout choosing
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }*/
        }  // end of TRY
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
//        try
//        {
//
////            //replace with file chooser
////            File workingDirectory = new File(System.getProperty("user.dir"));//get IntelliJ working directory
////            Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");//get path from working directory add literal path > add source to it then add data.txt file to source
//

//        }

    }
}
