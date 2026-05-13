package data;
import java.util.ArrayList;
public class Category 
{
    private String categoryName;
    private ArrayList<Task> tasks;
    
    public Category(String categoryName)
    {
        this.categoryName = categoryName;
        this.tasks = new ArrayList<>();
    }
    public String getCategoryName()
    { return categoryName; }
    
    public void addTask(Task t)
    {
        tasks.add(t);
    }
    public void displayTasks()
    {
        if(tasks.isEmpty())
        {
            System.out.println("Category is Empty");
            return;
        }
        for(Task t: tasks)
        {
        	t.getDetails();
        	System.out.println();
        }
    }
    public void removeTask(Task t)
    {
        tasks.remove(t);
    }
    
}