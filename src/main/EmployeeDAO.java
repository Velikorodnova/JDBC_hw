import java.util.List;

public interface EmployeeDAO {
    void createEmployee(String firstName, String lastName, String gender, int age, int cityId);
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployee();

    void correctiveEmployeeById(int id, String firstName, String lastName, String gender, int age, int cityId);

    void deleteEmployeeById(int id);
}