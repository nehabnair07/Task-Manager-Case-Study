package data;
public class Task 
{
	private int taskID;
	private String taskName; 
	private String deadline; 
	private String status;  
	private String category;
	
	public Task(int taskID, String taskName, String deadline, String status, String category)
	{
	    this.taskID = taskID;
	    this.taskName = taskName;
	    this.deadline = deadline;
	    this.status = status;
	    this.category = category;
	}
	public int getTaskID()
	{ return taskID; }
	public String getStatus()
	{ return status; }
	public String getCategory()
	{ return category; }
	public String getTaskName()
	{ return taskName; }
	public String getDeadline()
	{ return deadline; }
	
	public void markComplete()
	{
		status = "Completed";
	}
	public void getDetails()
	{
		System.out.println("Task ID: " +taskID);
		System.out.println("Task Name: " +taskName);
		System.out.println("Deadline: " +deadline);
		System.out.println("Status: " +status);
		System.out.println("Category: " +category);
	}
	public void updateTask(String newName, String newDeadline, String newStatus, String newCategory)
	{
		taskName = newName; 
		deadline = newDeadline; 
		status = newStatus; 
		category = newCategory; 
	}
}

