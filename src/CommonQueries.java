import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Collection of frequent queries produced for improved functionality
 */
public class CommonQueries {
    Connection conn;
    Scanner scan;

    public CommonQueries(Connection conn, Scanner scan) {
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * prints out all user emails in the db
     */
    public void getUsers() {
        final String sql = "SELECT User.Email FROM User ORDER BY Email;";
        get(sql, "User.Email");
    }

    /**
     * prints out all of the available apps in the db
     */
    public void getApps() {
        final String sql = "SELECT App.Name FROM App ORDER BY Name;";
        get(sql, "App.Name");
    }

    /**
     * prints out all of the available platforms in the db that service the given app
     *
     * @param appName the app we want to filter by
     */
    public void getPlatform(String appName) {
        final String sql = "SELECT ao.Platform FROM AvailableOn ao WHERE ao.AppName = ? ORDER BY ao.Platform;";
        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            // set params
            stmt.setString(1, appName);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                int count = 0;
                while (res.next()) {
                    System.out.printf("  %s%n", res.getString("Platform"));
                    count ++;
                }
                if (count == 0){
                    System.out.println("This app is not currently available on any platforms. " +
                            "If you'd like to try again with a different app, please restart the program.");
                    System.exit(0);
                }
            }
        } catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.exit(0);
        }
    }

    /**
     * prints out all available platforms in the db
     */
    public void getPlatform() {
        final String sql = "SELECT Platform.Name FROM Platform ORDER BY Name;";
        get(sql, "Platform.Name");
    }

    /**
     * prints out all of the available countries in the db
     */
    public void getCountries() {
        final String sql = "SELECT DISTINCT User.Country FROM User ORDER BY Country;";
        get(sql, "User.Country");
    }

    /**
     * prints out all of the available shows and their matching ids in the db
     */
    public void getShows() {
        final String sql = "SELECT CONCAT(Shows.Title, ' - ', CAST(Shows.Id AS char)) AS data FROM Shows ORDER BY Shows.Title;";
        get(sql, "data");
    }

    /**
     * gets all of the shows the given user has subscription access to which are not already in their list
     * @param email user
     */
    public void getShows(String email) {
        final String sql = "SELECT CONCAT(sh.Title, ' - ', CAST(sh.Id AS char)) AS data from Episode e " +
                "JOIN (SELECT v.Id as vidID FROM Videos v " +
                "      JOIN Subscription s " +
                "      ON v.AppHostedOn = s.AppName " +
                "      WHERE s.UserEmail = ?) app " +
                "ON e.VideoId = app.vidId " +
                "JOIN Shows sh " +
                "ON sh.Id = e.ShowId " +
                "WHERE sh.Id NOT IN (SELECT sl.ShowId FROM ShowList sl WHERE sl.UserEmail = ?)" +
                "GROUP BY sh.Id;";
        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            // set params
            stmt.setString(1, email);
            stmt.setString(2, email);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                int count = 0;
                while (res.next()) {
                    System.out.printf("  %s%n", res.getString("data"));
                    count ++;
                }
                if (count == 0){
                    System.out.println("This user does not have any available shows to add to their list. " +
                            "If you'd like to try again with a different user, please restart the program.");
                    System.exit(0);
                }
            }
        } catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.exit(0);
        }
    }

    /**
     * prints out all of the available shows and their matching id's in the db
     */
    public void getTags() {
        final String sql = "SELECT DISTINCT(Tag) as tag FROM Tags ORDER BY tag;";
        get(sql, "tag");
    }

    /**
     * prints out the results of the given query in the attribute
     * @param input prepared sql query
     * @param attribute attribute title to search through
     */
    private void get(String input, String attribute) {
        final String sql = input;

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                int count = 0;
                while (res.next()) {
                    System.out.printf("  %s%n", res.getString(attribute));
                    count ++;
                }
                if (count == 0){
                    System.out.println("There was no data to display. Please restart the app and try again.");
                    System.exit(0);
                }
            }
        } catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.exit(0);
        }
    }
}
