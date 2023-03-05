import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private Connection getConnection() {
        Connection CONNECTION;
        try {
            CONNECTION = DriverManager.getConnection(Application.URL, Application.USER, Application.PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return CONNECTION;
    }

    @Override
    public void createEmployee(String firstName, String lastName, String gender, int age, int cityId) {
        try (PreparedStatement statement = getConnection().prepareStatement
                ("INSERT INTO employee (first_name, last_Name, gender, age, city_id) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, gender);
            statement.setInt(4, age);
            statement.setInt(5, cityId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeById(int cityId) {
        Employee employee = new Employee();
        try (PreparedStatement statement = getConnection().prepareStatement
                ("SELECT * FROM employee WHERE id = (?)")) {
            statement.setInt(1, cityId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
                employee.setCityId(resultSet.getInt("city_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement ("SELECT * FROM employee")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                int cityId = resultSet.getInt("city_id");
                employeeList.add(new Employee(firstName, lastName, gender, age, cityId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void correctiveEmployeeById(int id, String firstName, String lastName, String gender, int age, int cityId) {
        try (PreparedStatement statement = getConnection().prepareStatement
                ("UPDATE employee SET first_name = (?), last_name = (?), gender = (?), age = (?), city_id = (?) WHERE id = (?)")) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, gender);
            statement.setInt(4, age);
            statement.setInt(5, cityId);
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        try (PreparedStatement statement = getConnection().prepareStatement(
                     "DELETE FROM employee WHERE id=(?)")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}