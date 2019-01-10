package pl.lukaszgrymulski;

public class Main {
	public static void main(String[] args) {
//		EmployeeViewer.viewEmployees();		
//		User user1 = new User(null, "Anna", "Wanna","anna.wanna@armatura.pl");
//		EmployeeInserter.updateEmployees(user1);		
//		EmployeeUpdater.updateEmployee("email='update1@damn.com'", "id=4");
		EmployeeViewer.viewEmployees();		
		EmployeeDeleter.deleteEmployee("last_name='Debby'");
		EmployeeViewer.viewEmployees();
	}
}
