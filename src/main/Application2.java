import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application2 {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "51PtkRdf";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        try (final Connection connection = DriverManager.getConnection(url, user, password)) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);
            City city = new City(7, "Tyla");
            Employee employee1 = new Employee(7, "Alena", "Efimova", "woman", 25);
            employeeDAO.createEmployee(employee1);
            List<Employee> employeeList = new ArrayList<>(employeeDAO.getAllEmployee());
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }
}
