package data;
import java.util.Scanner;
import logic.TaskManager;
public class User 
{
	Scanner sc = new Scanner(System.in);
	private String userID; 
	private String username; 
	private TaskManager tm; 
	
	public User(String userID, String username, TaskManager tm)
	{
		this.userID=userID; 
		this.username=username; 
		this.tm=tm; 
	}
	
	public void createTask()
	{
		System.out.println();
		System.out.println("Enter TaskID");
		int taskID=sc.nextInt();
		sc.nextLine(); 
		System.out.println("Enter Task Name");
		String taskName=sc.nextLine();
		System.out.println("Enter Deadline");
		String deadline=sc.nextLine();
		System.out.println("Enter Status - Pending/Completed");
		String status=sc.nextLine();
		System.out.println("Enter Category - Personal/Work/Random");
		String category = sc.nextLine();
		
		Task t = new Task(taskID, taskName, deadline, status, category);
		tm.addTask(t);
		System.out.println("Task Created");
	}
	public void viewTasks()
	{
		tm.displayTasks();
	}
}
