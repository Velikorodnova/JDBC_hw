import java.util.List;

public class Application {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee1 = new Employee("Evgenii", "Popov", "Man", 32, 8);
        employeeDAO.createEmployee(employee1);
        System.out.println(employeeDAO.getEmployeeById(1));
        List<Employee> employeeList = employeeDAO.getAllEmployee();
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
        employeeDAO.deleteEmployeeById(employee1);
    }
}