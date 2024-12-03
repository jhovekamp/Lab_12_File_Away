import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileInspector
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();//create a file chooser
        Scanner inFile;
        String rec = "";

        try //if no exception, try block runs. code in try block code that might throw an exception. All file handling code goes here, including file chooser, get file, process file.
        {
            Path workingDirectory = new File(System.getProperty("user.dir")).toPath();//call to system to current working directory & return as file
            workingDirectory = workingDirectory.resolve("src");//open working directory to the src folder
            chooser.setCurrentDirectory(workingDirectory.toFile());//opens to the users current project

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)//chooser.showOpenDialog - a command show wizard for user to select a file. null when working in a console program. Test JFileChooser - did user chose a file? T/F
            { //true, process it
                workingDirectory = chooser.getSelectedFile().toPath();//what user chose & convert to a path
                //        inFile = new Scanner(workingDirectory);
//                while (inFile.hasNextLine())
//                {
//                    line = inFile.nextLine();
//                    System.out.println(line);
//                }
//                inFile.close();

                InputStream in = new BufferedInputStream(Files.newInputStream(workingDirectory, CREATE));//take input stream create input stream
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));//buffer reader read text file

                //read the line
                int lineNum = 0;
                int wordCount = 0;
                int charCount = 0;
                String line;

                while (reader.ready())
                {
                    rec = reader.readLine();
                    //read a line from the file. read line by line
                    lineNum++;
                    charCount += rec.length();
                    String[] words = rec.split("\\s+");
                    wordCount += words.length;
                    System.out.printf("\nLine %4d %-50s ", lineNum, rec);
                }
                System.out.println("\n\nFILE SUMMARY REPORT");
                System.out.println("--------------------");
                System.out.println("File Name: " + workingDirectory.getFileName());
                System.out.println("Number of Lines: " + lineNum);
                System.out.println("Number of Words: " + wordCount);
                System.out.println("Number of Characters: " + charCount);
                reader.close();
            }

                //read a line or part of a line, specify what char to start on. Or delimited file.
//                String[] dataElements;//string search method
//                dataElements = line.split(", ");//string function - split line and use comma and space as delimiter and put each element as its own element in the array

            else//F user didnt select a file. Retry/end
            {
                System.out.println("Sorry, you must select a file! Termininating!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e) //runs if there is an exception for specific exceptions
        {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e)//general goes after specific exception
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }

    }

//        File selectedFile;
//        String rec = "";
//        ArrayList<String> lines = new ArrayList<>();
//
//        final int FIELDS_LENGTH = 5;
//                String id, firstName, lastName, title;
//        int yob;
//        InputStream in = new BufferedInputStream(Files.newInputStream(target, CREATE));
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//        int lineN = 0;
//                while(reader.ready());
//                {
//        rec = reader.readLine();
//                lines.add(rec);
//        lineN++;
//                System.out.printf("\nLine %4d %-60s ", lineN, rec);
//            }
//                    reader.close();
//                System.out.println("\n\nData file read!");
//
//        String[] fields;
//                for(String l:lines)
//                {
//        fields = l.split(",");
//
//                if(fields.length == FIELDS_LENGTH)
//                {
//                    id =        fields[0].trim();
//                    firstName = fields[1].trim();
//                    lastName =  fields[2].trim();
//                    title =     fields[3].trim();
//                    yob =       Integer.parseInt(fields[4].trim());
//                System.out.printf("\n%-8s%-25s%-25s%-6s%6d", id, firstName, lastName, title, yob);
//                }
//                        else
//                        {
//                        System.out.println("Found a record that may be corrupt: ");
//                    System.out.println(1);
//        }
}

