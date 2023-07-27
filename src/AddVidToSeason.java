import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Represents a query to add a new Show Episode to the database.
 */
public class AddVidToSeason {
    Connection conn;
    Scanner scan;
    public AddVidToSeason(Connection conn, Scanner scan){
        this.conn = conn;
        this.scan = scan;
    }

    /**
     * Adds the newest episode of a show to the current season
     */
    public void Execute() {
        // getting params
        CommonQueries cq = new CommonQueries(conn, scan);
        System.out.printf("Enter the new video's title: ");
        final String newTitle = scan.nextLine();

        cq.getShows();
        System.out.printf("Enter id of the show this video belongs to: ");
        int showId = 0;
        try{
            showId = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e){
            System.out.printf("You must enter an integer show id. Please try again: ");
            try{
                showId = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e2){
                System.out.printf("You did not enter a valid number. Please start over.");
                this.Execute();
            }
        }

        System.out.printf("Enter the description of the video: ");
        final String description = scan.nextLine();

        System.out.printf("Enter the release date of the episode in the format YYYY-MM-DD: ");
        final String date = scan.nextLine();

        System.out.printf("Enter the video's duration in seconds: ");
        int duration = 0;
        try{
            duration = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e){
            System.out.printf("You must enter an integer representing duration in seconds. Please try again: ");
            try{
                duration = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e2){
                System.out.printf("You did not enter a valid duration. Please start over.");
                this.Execute();
            }
        }

        System.out.printf("Enter 1 if you can access the video for free, 0 otherwise: ");
        int free = 0;
        try{
            free = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e){
            System.out.printf("You must enter either 1 or 0. Please try again: ");
            try{
                free = Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e2){
                System.out.printf("You did not enter a valid integer. Please start over.");
                this.Execute();
            }
        }

        //sets auto commit to false - allows for transaction grouping
        try{
            conn.setAutoCommit(false);
        } catch (SQLException e){
            e.printStackTrace();
        }

        // params for queries
        int curSeason;
        int nextEp;
        int nextVidId;
        int[] params = this.getIndexedValues(showId);
        curSeason = params[0];
        nextEp = params[1];
        nextVidId = params[2];
        // setting outer query
        final String getApps = "SELECT DISTINCT v.AppHostedOn FROM Videos v " +
                "JOIN Episode e " +
                "ON e.VideoId = v.Id " +
                "WHERE e.ShowId = ?;";
        try (final PreparedStatement stmtApps = conn.prepareStatement(getApps)){
            stmtApps.setInt(1, showId);
            try (final ResultSet res = stmtApps.executeQuery()){
                while (res.next()){
                    // done for each app which hosts the show
                    String app = res.getString("AppHostedOn");
                    final String sql1 = "INSERT INTO Videos(Id, Title, Description, AppHostedOn, ReleaseDate, Seconds, isFree) " +
                            "VALUES " +
                            "(?,?, ?, ?, ?, ?, ?);";
                    final String sql2 = "INSERT INTO Episode(ShowId, SeasonNumber, EpisodeNumber, VideoId) " +
                            "VALUES (?, ?, ?, ?);";

                    try (final PreparedStatement stmt = conn.prepareStatement(sql1);
                    final PreparedStatement stmt4 = conn.prepareStatement(sql2))
                    {
                        // set params for the first query
                        stmt.setInt(1, nextVidId);
                        stmt.setString(2, newTitle);
                        stmt.setString(3, description);
                        stmt.setString(4, app);
                        stmt.setString(5, date);
                        stmt.setInt(6, duration);
                        stmt.setInt(7, free);

                        // set params for second query
                        stmt4.setInt(1, showId);
                        stmt4.setInt(2, curSeason);
                        stmt4.setInt(3, nextEp);
                        stmt4.setInt(4, nextVidId);

                        try(final ResultSet res1 = stmt.executeQuery();
                        final ResultSet res2 = stmt4.executeQuery()){
                            System.out.printf("%s has been inserted into season %d of show with id %d on app %s.%n",
                                    newTitle, curSeason, showId, app);
                        }
                        nextVidId++;

                    }  catch (SQLException e) {
                        System.out.printf("Error connecting to db: %s%n", e.getMessage());
                        System.out.println();
                        System.out.printf("**********INVALID INPUTS, TRY AGAIN.*********%n");
                        System.out.println();
                        this.Execute();
                    }


                }
                //commits the transaction
                conn.commit();
                // good practice to set it back to default true
                conn.setAutoCommit(true);
                System.out.printf("All videos successfully added.");
            }
        } catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.out.println();
            System.out.printf("**********INVALID INPUTS, TRY AGAIN.*********%n");
            System.out.println();
            this.Execute();
        }

    }

    /**
     * Grabs the relevant indices for the given query
     * @param showId the numeric id of the show we're adding an episode to
     * @return the season number, next incremented episode number, and next incremented video id
     */
    int[] getIndexedValues(int showId){

        //default values for the expected outputs
        int curSeason = 1;
        int nextEp = 1;
        int nextVidId = 0;

        //sql to get current season number
        final String ssnSql = "SELECT MAX(SeasonNumber) AS ssNum FROM Season " +
                "WHERE ShowId = ?";
        //sql to get next video id
        final String sqlVidId = "SELECT MAX(Id) + 1 AS maxId FROM Videos;";

        //prepare season num and vid id sql
        try(final PreparedStatement ssnStmt = conn.prepareStatement(ssnSql);
            final PreparedStatement vidIdStmt = conn.prepareStatement(sqlVidId)){
            // set params for the season num query
            ssnStmt.setInt(1,showId);

            try(final ResultSet ssnRes = ssnStmt.executeQuery();
                final ResultSet vidIdRes = vidIdStmt.executeQuery()){
                //grabbing next free vid id
                while (vidIdRes.next()){
                    nextVidId = vidIdRes.getInt("maxId");
                }
                //grabbing current season
                while(ssnRes.next()) {
                    curSeason = ssnRes.getInt("ssNum");
                }
                // sql to get the next episode number in that season
                final String nxtEpSql = "SELECT MAX(EpisodeNumber) AS newEpNum FROM Episode " +
                        "WHERE ShowId = ? " +
                        "AND SeasonNumber = ?";

                // preparing to query next episode
                try(final PreparedStatement nxtEpStmt = conn.prepareStatement(nxtEpSql)){
                    //set params
                    nxtEpStmt.setInt(1, showId);
                    nxtEpStmt.setInt(2, curSeason);

                    try(final ResultSet nxtEpRes = nxtEpStmt.executeQuery()){
                        // grab next episode number
                        while(nxtEpRes.next()) {
                            nextEp = nxtEpRes.getInt("newEpNum");
                            nextEp += 1;
                        }
                    }
                }  catch (SQLException e) {
                    System.out.printf("Error connecting to db: %s%n", e.getMessage());
                    System.out.println();
                    System.out.printf("**********INVALID SHOW ID, TRY AGAIN.*********%n");
                    System.out.println();
                    this.Execute();
                }
            }
        }  catch (SQLException e) {
            System.out.printf("Error connecting to db: %s%n", e.getMessage());
            System.out.println();
            System.out.printf("**********INVALID SHOW ID, TRY AGAIN.*********%n");
            System.out.println();
            this.Execute();
        }
        int[] results = new int[3];
        results[0] = curSeason;
        results[1] = nextEp;
        results[2] = nextVidId;
        return results;
    }

}
