package ui;
import java.util.Scanner;
import data.User;
import logic.TaskManager;
public class Main 
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter User ID");
		String userID=sc.nextLine(); 
		System.out.println("Enter Username");
		String username=sc.nextLine(); 
		
		TaskManager tm=new TaskManager(userID);
		User u1=new User(userID, username, tm);
		
		int choice; 
		do
		{	System.out.println(); 
			System.out.println("TASK-MANAGER MENU:");
			System.out.println("1.Add New Task");
			System.out.println("2.Update Existing Task");
			System.out.println("3.Delete Old Task");
			System.out.println("4.View All Tasks");
			System.out.println("5.View All Pending/Completed Tasks");
			System.out.println("6.View Tasks by Category");
			System.out.println("7.Mark Task as Completed");
			System.out.println("8.Exit");
			choice=sc.nextInt();
			sc.nextLine(); 
		
			switch(choice)
			{
				case 1: 
					u1.createTask();
					break; 
					
				case 2:
					System.out.println();
					System.out.println("Enter the TaskID of the Task to be updated");
					int id = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter new Task Name");
					String newName = sc.nextLine();
					System.out.println("Enter new Task Deadline");
					String newDeadline = sc.nextLine();
					System.out.println("Enter new Task Status - Pending/Completed");
					String newStatus= sc.nextLine();
					System.out.println("Enter new Task Category - Personal/Work/Random");
					String newCategory= sc.nextLine();
					tm.updateTask(id, newName, newDeadline, newStatus, newCategory);
					break; 
					
				case 3:
					System.out.println();
					System.out.println("Enter the TaskID of the Task to be deleted");		
					int delete=sc.nextInt(); 
					sc.nextLine(); 
					tm.deleteTask(delete);
					break;
					
				case 4:
					tm.displayTasks();
					break;
					
				case 5: 
					System.out.println();
					System.out.println("1.Pending 2.Completed");
					int statusChoice=sc.nextInt();
					sc.nextLine();
					if(statusChoice == 1)
					{
					    tm.getPendingorCompletedTasks("pending");
					}
					else
					{
					    tm.getPendingorCompletedTasks("completed");
					}
					break; 
					
				case 6:
					System.out.println();
				    System.out.println("1.Personal 2.Work 3.Random");
				    int categoryChoice = sc.nextInt();
				    sc.nextLine();
				    tm.displayByCategory(categoryChoice);
				    break;
				    
				case 7:
					System.out.println();
				    System.out.println("Enter the TaskID of the Task to be marked as completed:");
				    int completedID = sc.nextInt();
				    sc.nextLine();
				    tm.markTaskAsComplete(completedID);
				    break;
				    
				case 8: 
					tm.saveTasksToFile();
					System.out.println();
					System.out.println("Exiting Task-Manager"); 
					break; 
					
				default: 
					System.out.println();
					System.out.println("Invalid Choice"); 
			}
		} while(choice != 8); 
	}
} 

