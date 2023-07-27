import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents a query to get all free videos on a given platform.
 */
public class GetAllFree {
    Connection conn;
    Scanner scan;
    public GetAllFree(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Returns all free videos on a given platform
     */
    public void Execute() {
        CommonQueries cq = new CommonQueries(conn, scan);
        cq.getPlatform();
        System.out.printf("Enter the platform you want to search: ");
        final String platform = scan.nextLine();
        final String sql = "SELECT v.Title FROM Videos v " +
                "JOIN AvailableOn ao ON v.AppHostedOn = ao.AppName " +
                "JOIN Platform p ON ao.Platform = p.Name " +
                "WHERE p.Name = ? " +
                "AND v.isFree = true;";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            //set param
            stmt.setString(1, platform);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                int count = 0;
                    while (res.next()) {
                        System.out.printf("%s%n", res.getString("v.Title"));
                        count ++;
                    }
                if (count == 0){
                    System.out.println("There are no free videos on this platform.");
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
