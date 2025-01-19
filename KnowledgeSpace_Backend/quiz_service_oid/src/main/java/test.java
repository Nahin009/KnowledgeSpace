import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://db.mcadknotoicmciwbdgdg.supabase.co:5432/postgres?user=postgres&password=msmkn1516132";
        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                System.out.println("Connection successful!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }
}
