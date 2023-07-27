import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents a query to get all shows available to a User which have at least the given number of seasons
 */
/*
-- 1 pt strong motivation - A User can easily find a new show they have access to that will be able to fill up their
    free time sufficiently.
-- 1 pt subquery
-- 1 pt for where condition not on join
-- 1 pt for > 1 order by conditions
-- 1 pt aggregate function
-- 1 pt group by
-- 6 pts -> complex query
 */
public class GetBingeWorthyShows {
    Connection conn;
    Scanner scan;
    public GetBingeWorthyShows(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Prints a list of shows available to a User with at least a certain number of seasons.
     */
    public void Execute() {
        CommonQueries cq = new CommonQueries(conn, scan);
        cq.getUsers();
        System.out.printf("Enter the user: ");
        final String email = scan.nextLine();
        System.out.printf("Enter minimum number of seasons you want to search for: ");
        //verifies that an int was provided
        int min = 0;
        try{
            min = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e){
            System.out.printf("You must enter an integer. Please try again: ");
            try{
                min = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e2){
                System.out.printf("You did not enter a valid integer. Please start over.");
                this.Execute();
            }
        }

        final String sql = "SELECT Shows.Title, COUNT(s.SeasonNumber) AS seasonToWatch FROM Season s " +
                "JOIN (" +
                "SELECT DISTINCT(e.ShowId) AS showId FROM Episode e " +
                "JOIN ( " +
                "SELECT v.Id AS VideoId, v.AppHostedOn AS Apps FROM Videos v " +
                "JOIN Subscription s ON v.AppHostedOn = s.AppName " +
                "WHERE s.UserEmail = ?" +
                ") t " +
                "ON e.VideoId = t.VideoId " +
                ") sh " +
                "ON s.ShowId = sh.ShowId " +
                "JOIN Shows ON s.ShowId = Shows.Id " +
                "GROUP BY Shows.Title " +
                "HAVING COUNT(S.SeasonNumber) >= ? " +
                "ORDER BY COUNT(s.SeasonNumber) DESC, Shows.Title ASC";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            //set param
            stmt.setString(1, email);
            stmt.setInt(2, min);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                int count = 0;
                while(res.next()){
                    System.out.printf("%s, %d seasons%n", res.getString("Shows.Title"), res.getInt("seasonToWatch"));
                    count++;
                }
                if (count == 0){
                    System.out.printf("This user does not have any binge worthy shows with at least %d seasons.%n", min);
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
