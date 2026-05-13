package logic;
import data.Task;
import data.Category;
import java.util.ArrayList;
public class TaskManager 
{
	private ArrayList<Task> taskList;
	private Category personal;
	private Category work;
	private Category random;
	private FileManager fm;
	
	public TaskManager(String userID)
	{
	    taskList = new ArrayList<>();
	    personal = new Category("Personal");
	    work = new Category("Work");
	    random = new Category("Random");
	    
	    fm = new FileManager("tasks_" + userID + ".txt");
	    taskList = fm.loadFromFile();
	    
	    for(Task t : taskList)
	    {
	        addToCategory(t);
	    }
	}
	public ArrayList<Task> getTaskList()
	{
	    return taskList;
	}
	
	public void addTask(Task t)
	{
	    taskList.add(t);
	    addToCategory(t); 
	}
	private void addToCategory(Task t)
	{
	    String category = t.getCategory();

	    if(category.equalsIgnoreCase("Personal"))
	    {
	        personal.addTask(t);
	    }
	    else if(category.equalsIgnoreCase("Work"))
	    {
	        work.addTask(t);
	    }
	    else
	    {
	        random.addTask(t);
	    }
	}
	public void deleteTask(int delete)
	{
	    for(int i=0 ; i<taskList.size(); i++)
	    {
	        if(taskList.get(i).getTaskID() == delete)
	        {
	            Task t = taskList.get(i);

	            removeFromCategory(t); 

	            taskList.remove(i);
	            System.out.println("Task Deleted");
	            return;
	        }
	    }
	}
	private void removeFromCategory(Task t)
	{
	    personal.removeTask(t);
	    work.removeTask(t);
	    random.removeTask(t);
	}

	public void displayTasks()
	{
		System.out.println();
		for(Task t: taskList)
		{
			t.getDetails(); 
			System.out.println();
		}
	}
	public void displayByCategory(int choice)
	{
	    switch(choice)
	    {
	        case 1:
	            personal.displayTasks();
	            break;
	        case 2:
	            work.displayTasks();
	            break;
	        case 3:
	            random.displayTasks();
	            break;
	        default:
	            System.out.println("Invalid Category");
	    }
	}
	public void getPendingorCompletedTasks(String statusChoice)
	{
		for (Task t: taskList)
        {
            if (t.getStatus().equalsIgnoreCase(statusChoice))
            {
                t.getDetails();
                System.out.println();
            }
        }
	}
	
	public void markTaskAsComplete(int taskID)
	{
	    for(Task t : taskList)
	    {
	        if(t.getTaskID() == taskID)
	        {
	            t.markComplete();  
	            return;
	        }
	    }

	    System.out.println("Task not found");
	}
	public void updateTask(int taskID, String newName, String newDeadline, String newStatus, String newCategory)
	{
	    for(Task t: taskList)
	    {
	        if(t.getTaskID() == taskID)
	        {        
	            removeFromCategory(t);

	            t.updateTask(newName, newDeadline, newStatus, newCategory);
	            addToCategory(t);

	            System.out.println("Task Updated");
	            return;
	        }
	    }
	}
	public void saveTasksToFile()
	{
	    fm.saveToFile(taskList);
	}
}
