import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    static final String USER = "postgres";
    static final String PASSWORD = "51PtkRdf";
    static final String URL = "jdbc:postgresql://localhost:5432/skypro";

    public static void main(String[] args) throws SQLException {
        try (final Connection CONNECTION = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement statement = CONNECTION.prepareStatement
                    ("SELECT * FROM employee INNER JOIN city ON city.city_id = employee.city_id AND employee.id = ?");
            statement.setInt(1, 1);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String firstName = "first_name - " + resultSet.getString("first_name");
                String lastName = "last_name - " + resultSet.getString("last_name");
                String gender = "gender - " + resultSet.getString("gender");
                String city_name = "city_name - " + resultSet.getString("city_name");
                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(gender);
                System.out.println(city_name);
            }
            EmployeeDAOImpl employeeDAOImpl = new EmployeeDAOImpl();
            City city = new City(7, "Tyla");
            Employee employee1 = new Employee(7, "Alena", "Efimova", "woman", 25, city);
            employeeDAOImpl.createEmployee(employee1);
            List<Employee> employeeList = new ArrayList<>(employeeDAOImpl.getAllEmployee());
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }
}
