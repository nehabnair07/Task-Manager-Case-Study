package logic;
import data.Task;
import java.io.*;
import java.util.ArrayList;
public class FileManager 
{
    private String fileName;

    public FileManager(String fileName)
    {
        this.fileName = fileName;
    }
    
    public void saveToFile(ArrayList<Task> taskList)
    {
        try
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for(Task t : taskList)
            {
            	String line = t.getTaskID() +"," +t.getTaskName() +"," +t.getDeadline() +"," +t.getStatus() +"," +t.getCategory();
                writer.write(line);
                writer.newLine();
            }

            writer.close();
            System.out.println("Tasks saved to file.");
        }
        catch(IOException e)
        {
            System.out.println("Error saving file.");
        }
    }
    public ArrayList<Task> loadFromFile()
    {
        ArrayList<Task> taskList = new ArrayList<>();

        try
        {
        	File file = new File(fileName);
            if(!file.exists())
            {
                return taskList; 
            }

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String deadline = parts[2];
                String status = parts[3];
                String category = parts[4];

                Task t = new Task(id, name, deadline, status, category);
                taskList.add(t);
            }

            reader.close();
        }
        catch(IOException e)
        {
            System.out.println("Error loading file.");
        }

        return taskList;
    }
}