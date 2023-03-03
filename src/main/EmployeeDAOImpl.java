import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    public EmployeeDAOImpl(Connection connection) {
    }

    @Override
    public void createEmployee(Employee employee) {
        final String user = "postgres";
        final String password = "51PtkRdf";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement
                     ("INSERT INTO employee (first_name, last_Name, gender, age) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        Employee employee = new Employee();
        final String user = "postgres";
        final String password = "51PtkRdf";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement
                     ("SELECT * FROM employee INNER JOIN city ON city.city_id = employee.city_id AND employee.id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee.setId(Integer.parseInt(resultSet.getString("id")));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(Integer.parseInt(resultSet.getString("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        final String user = "postgres";
        final String password = "51PtkRdf";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement
                     ("SELECT * FROM employee INNER JOIN city ON employee.id=city.city_id")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = Integer.parseInt(resultSet.getString("id"));
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = Integer.parseInt(resultSet.getString("age"));
                employeeList.add(new Employee(id, firstName, lastName, gender, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void correctiveEmployeeById(int id, int age) {
        final String user = "postgres";
        final String password = "51PtkRdf";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement
                     ("UPDATE employee SET age=(?) WHERE id=(?)")) {
            statement.setInt(1, age);
            statement.setInt(2, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        final String user = "postgres";
        final String password = "51PtkRdf";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM employee WHERE id=(?)")) {
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}