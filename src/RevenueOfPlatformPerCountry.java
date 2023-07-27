import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents a query to generate a ranked list of revenue of a platform by country
 */
/*
-- 1 pt for subquery
-- 1 pt strong motivation - platforms partnered with our app will be able to determine which countries are their
    biggest customers to better tailor content
-- 1 pt non-aggregate function (concat/round)
-- 1 pt order by
-- 1 pt aggregate function (sum)
-- 1 pt group by
-- 6 pts -> complex query
 */
public class RevenueOfPlatformPerCountry {
    Connection conn;
    Scanner scan;
    public RevenueOfPlatformPerCountry(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * prints a ranked list of revenue of a given platform by country
     */
    public void Execute() {
        CommonQueries cq = new CommonQueries(conn, scan);
        cq.getPlatform();
        System.out.printf("Enter the platform you want to query by: ");
        final String platform = scan.nextLine();

        final String sql = "SELECT u.Country, CONCAT('$', ROUND(SUM(s.Cost), 2)) AS totalPrice FROM Subscription s " +
                "JOIN (SELECT * FROM AvailableOn ao WHERE ao.Platform = ?) ao1 " +
                "ON s.AppName = ao1.AppName " +
                "JOIN User u ON u.Email = s.UserEmail " +
                "GROUP BY u.Country " +
                "ORDER BY SUM(s.Cost) DESC, u.Country ASC;";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            //set param
            stmt.setString(1, platform);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                int count = 0;
                while(res.next()){
                    System.out.printf("%s %s%n", res.getString("country"), res.getString("totalPrice"));
                    count++;
                }

                if (count == 0){
                    System.out.printf("This platform is not being used in any countries.%n");
                }
            }
        }  catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.out.println();
            System.out.printf("**********INVALID INPUTS, TRY AGAIN.*********%n");
            scan.nextLine();
            this.Execute();
        }
    }
}
