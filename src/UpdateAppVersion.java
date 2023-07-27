import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents a query to update the version of an app on a specific platform
 */
public class UpdateAppVersion {
    Connection conn;
    Scanner scan;
    public UpdateAppVersion(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Updates the version number of an app on a specific platform
     */
    public void Execute() {
        CommonQueries cq = new CommonQueries(conn, scan);
        cq.getApps();
        System.out.print("Enter the name of the app you want to update: ");
        final String app = scan.nextLine();
        cq.getPlatform(app);
        System.out.print("Enter which platform hosting the app you'd like to version: ");
        final String platform = scan.nextLine();
        System.out.print("Enter the new version: ");
        final String newVersion = scan.nextLine();

        final String sql = "UPDATE AvailableOn " +
                "SET Version = ? " +
                "WHERE AppName = ? " +
                "AND Platform = ?;";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            // set params
            stmt.setString(1, newVersion);
            stmt.setString(2, app);
            stmt.setString(3, platform);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                System.out.printf("%s has been updated to version %s on %s.", app, newVersion, platform);
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
