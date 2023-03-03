import java.sql.*;

public class Application1 {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "51PtkRdf";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        try (final Connection connection = DriverManager.getConnection(url, user, password)) {
            PreparedStatement statement = connection.prepareStatement
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
        }
    }
}
