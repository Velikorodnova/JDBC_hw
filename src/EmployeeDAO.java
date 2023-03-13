import java.util.List;

public interface EmployeeDAO {
    void createEmployee(Employee employee);

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployee();

    void correctiveEmployeeById(Employee employee);

    void deleteEmployeeById(Employee employee);
}