import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents a query to add a show to a User's MyList.
 */
public class AddShow {
    Connection conn;
    Scanner scan;
    public AddShow(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Adds a given show to a User's MyList.
     */
    public void Execute() {
        CommonQueries cq = new CommonQueries(conn, scan);
        cq.getUsers();
        System.out.printf("Enter the user's email address: ");
        String email = scan.nextLine();
        cq.getShows(email);
        System.out.printf("Enter numeric show id of the show you'd like to subscribe %s to: ", email);
        int showId = 0;
        //validates that an int was entered
        try{
            showId = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e){
            System.out.printf("You must enter a valid integer. Try again: ");
            try{
                showId = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e2){
                System.out.printf("You did not enter a valid number. Please start over.%n");
                this.Execute();
            }
        }

        final String sql = "INSERT INTO ShowList(UserEmail, ShowId) " +
        "VALUES (?, ?);";

        // prepare statement
        try (final PreparedStatement stmt = conn.prepareStatement(sql)) {
            // set params
            stmt.setString(1, email);
            stmt.setInt(2, showId);
            // get results
            try (final ResultSet res = stmt.executeQuery()) {
                System.out.printf("User %s has added show with id %d to their list.%n", email, showId);
            }
        }  catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.out.println();
            System.out.printf("**********INVALID INPUT, TRY AGAIN.*********%n");
            System.out.println();
            this.Execute();
        }

    }
}
