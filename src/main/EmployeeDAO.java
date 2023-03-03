import java.util.List;

public interface EmployeeDAO {
    void createEmployee(Employee employee);
    Employee getEmployeeById(int id);
    List<Employee> getAllEmployee();
    void correctiveEmployeeById(int id, int age);
    void deleteEmployeeById(int id);
}