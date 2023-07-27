import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Represents a query to get all of the videos which were released this year and are at least 2700 seconds long.
 */
public class LongVideos {
    Connection conn;
    Scanner scan;
    public LongVideos(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Prints all long videos released this year.
     */
    public void Execute() {
        final String sql = "SELECT v.Title, v.Seconds FROM Videos v " +
                "LEFT JOIN Episode e ON v.Id = e.VideoId " +
                "WHERE e.ShowId IS NULL " +
                "AND YEAR(v.ReleaseDate) = ?" +
                "AND v.Seconds >= 2700;";

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        String yearInString = String.valueOf(year);

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            //set param
            stmt.setString(1, yearInString);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                int counter = 0;
                while(res.next()){
                    System.out.printf("%s%n", res.getString("v.Title"));
                    counter++;
                }
                if (counter == 0){
                    System.out.printf("There have been no long videos released this year.%n");
                }
            }
        }  catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.exit(0);
        }
    }
}
