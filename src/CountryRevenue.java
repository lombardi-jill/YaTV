import java.sql.*;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Represents a query to produce a ranked list of revenue for apps in a given country
 */
public class CountryRevenue {
    Connection conn;
    Scanner scan;
    public CountryRevenue(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Prints a ranked list of revenue for apps in a given country
     */
    public void Execute() {
        CommonQueries cq = new CommonQueries(conn, scan);
        cq.getCountries();
        System.out.printf("Enter the country to query by: ");
        String country = scan.nextLine();

        final String sql = "SELECT s.AppName AS AppName, CONCAT('$', ROUND(SUM(s.Cost), 2)) AS totalRevenue FROM Subscription s " +
                "JOIN User u ON u.Email = s.UserEmail " +
                "WHERE u.Country = ? " +
                "GROUP BY s.AppName " +
                "ORDER BY SUM(s.Cost) DESC;";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            //set param
            stmt.setString(1, country);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                int counter = 0;
                while(res.next()){
                    System.out.printf("%s: %s%n", res.getString("AppName"), res.getString("totalRevenue"));
                    counter++;
                }
                if (counter == 0){
                    System.out.printf("There are no apps in use in that country.%n");
                }
            }
        }  catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.out.println();
            System.out.printf("**********INVALID INPUTS, TRY AGAIN.*********%n");
            System.out.println();
            this.Execute();
        }
    }
}
