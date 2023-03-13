import java.util.List;

public class Application {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        Employee employee1 = new Employee("Evgenii", "Popov", "Man", 32, 5);
        CityDAO cityDAO = new CityDAOImpl();
        List<Employee> employeeFromCity = List.of(employee1);
        City city1 = new City(7, "Kaliningrad", employeeFromCity);
        cityDAO.createCity(city1);
        City city2 = new City("Ekaterinburg");
        cityDAO.createCity(city2);
        Employee employee2 = new Employee("Lev", "Agafonov", "Man", 41, 8);
        employeeDAO.createEmployee(employee2);
        System.out.println(cityDAO.getCityById(6));
        List<City> cityList = cityDAO.getAllCity();
        for (City city : cityList) {
            System.out.println(city);
        }
        cityDAO.deleteCityById(city1);
        cityDAO.getAllCity();
        City omsk = new City(city1.getCityId(), "Omsk");
        cityDAO.correctiveCityById(omsk);
    }
}