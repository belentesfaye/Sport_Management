import java.sql.*;

public class MySQL {
  public static void main(String[] args) throws SQLException {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/NBA", "root", "new_password");
      retrieveEasternConferenceTeams(conn);
      calculateTotalRevenueForATeam(conn, 5);
      retrieveAthleteProfiles(conn,"Golden State Warriors");
      listCoachesInvestment(conn);
      twoTeamInvestor(conn);
      injuredAthletes(conn);
      highestRevenue(conn);
      eventLocation(conn, "Capital One Arena");
      preformanceOfTeams(conn);
      highestNetIncome(conn);
      investmentDistribution(conn);
      financialTrends(conn, "Boston Celtics");
      sponsorshipImpact(conn);
      investorsPortfolio(conn);
    } catch (Exception e) {
      e.printStackTrace();    }
  }
  /**
   * Retrieve team names and conferences for teams in the Eastern Conference
   */
  private static void retrieveEasternConferenceTeams(Connection conn) throws SQLException {
    try (Statement statement = conn.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT Team_Name, Conference FROM NBATeam WHERE Conference = 'Eastern';");
      System.out.println("\nRetrieve team names and conferences for teams in the Eastern Conference");
      while (resultSet.next()) {
        System.out.println(resultSet.getString("Team_Name") + " - " + resultSet.getString("Conference"));
      }
    }
  }

  /**
   * Calculate the total revenue for a specific team in the year 2023 (Replace 5 with the desired Team_Id)
   */
  private static void calculateTotalRevenueForATeam(Connection conn, int teamId) throws SQLException {
    try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT Team_Id, SUM(Revenue) AS TotalRevenue\n" +
            "FROM TeamFinance\n" +
            "WHERE FinancialYear = 2023 AND Team_Id = ?")) {
      preparedStatement.setInt(1, teamId);
      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        System.out.println("Team Id: " + resultSet.getInt("Team_Id") + ", Total Revenue: " + resultSet.getDouble("TotalRevenue"));
      }
    }
  }
  /**
   * Retrieve athlete profiles for a specific team (Golden State Warriors)
   */
  private static void retrieveAthleteProfiles(Connection conn, String teamName) throws SQLException {
    try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Athlete_Profile WHERE Team_Id = (SELECT Team_Id FROM NBATeam WHERE Team_Name = ?);\n")) {
      preparedStatement.setString(1, teamName);
      ResultSet resultSet = preparedStatement.executeQuery();
      System.out.println("\nRetrieve athlete profiles for a specific team (Golden State Warriors)");
      while (resultSet.next()) {
        System.out.println("Athlete ID: " + resultSet.getInt("Athlete_Id") + "\n"
                + "First Name: " + resultSet.getString("FirstName"));
      }
    }
  }
  /**
   * List out the coaches that got the most invested money, only show coaches that earned >= 7000000.00
   */
  private static void listCoachesInvestment(Connection conn) throws SQLException {
    try (Statement statement = conn.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT HeadCoach, SUM(t.NetIncome) AS TotalEarnings\n" +
              "FROM NBATeam n\n" +
              "JOIN TeamFinance t ON n.Team_Id = t.Team_Id\n" +
              "WHERE t.NetIncome >= 7000000.00\n" +
              "GROUP BY HeadCoach\n" +
              "ORDER BY TotalEarnings DESC;");
      System.out.println("\nList out the coaches that got the most invested money, only show coaches that earned >= 7000000.00");
      while (resultSet.next()) {
        System.out.println("Head Coach: " + resultSet.getString("HeadCoach") + "\n"
                + "Total Earnings: " + resultSet.getInt("TotalEarnings"));
      }
    }
  }

  /**
   * Show Investors Who Invested in at Least 2 Teams with Total Amount
   */
  private static void twoTeamInvestor(Connection conn) throws SQLException {
    try (Statement statement = conn.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT DISTINCT i.InvestorName, COUNT(i.InvestedTeamID) AS NumInvestments, SUM(i.InvestmentAmount) AS TotalInvested\n" +
              "FROM Investors i\n" +
              "JOIN Investors j ON i.InvestorID != j.InvestorID AND i.InvestedTeamID != j.InvestedTeamID\n" +
              "GROUP BY i.InvestorName\n" +
              "HAVING NumInvestments >= 2;");
      System.out.println("\nShow Investors Who Invested in at Least 2 Teams with Total Amount ");
      while (resultSet.next()) {
        System.out.println("Investor Name: " + resultSet.getString("InvestorName") + "\n"
                + "Number of Investments: " + resultSet.getInt("NumInvestments") + "\n"
                + "Total Invested: " + resultSet.getInt("TotalInvested"));
      }
    }
  }

  /**
   *  Show the athletes who are currently injured
   */
  private static void injuredAthletes(Connection conn) throws SQLException {
    try (Statement statement = conn.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT a.FirstName, a.LastName, a.Injury_description\n" +
              "FROM Athlete_Profile a\n" +
              "WHERE a.Injured = TRUE;");
      System.out.println("\nShow the athletes who are currently injured ");
      while (resultSet.next()) {
        System.out.println("First Name: " + resultSet.getString("FirstName") + "\n"
                + "Last Name: " + resultSet.getString("LastName") + "\n"
                + "Injury description: " + resultSet.getString("Injury_description"));
      }
    }
  }

  /**
   * Show the teams with the highest revenue
   */
  private static void highestRevenue(Connection conn) throws SQLException {
    try (Statement statement = conn.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT n.Team_Name, MAX(t.Revenue) AS HighestRevenue\n" +
              "FROM NBATeam n\n" +
              "JOIN TeamFinance t ON n.Team_Id = t.Team_Id\n" +
              "GROUP BY n.Team_Name\n" +
              "ORDER BY HighestRevenue desc;");
      System.out.println("\nShow the teams with the highest revenue ");
      while (resultSet.next()) {
        System.out.println("Team Name: " + resultSet.getString("Team_Name")
                + " | Revenue: $ " + resultSet.getInt("HighestRevenue"));
      }
    }
  }

  /**
   * Show the events held in a specific location
   */
  private static void eventLocation(Connection conn, String teamName) throws Exception {
    try (PreparedStatement preparedStatement = conn.prepareStatement("SELECT s.EventName, s.EventType, s.EventDate\n" +
            "FROM SportEvent s\n" +
            "JOIN NBATeam t ON s.WinnerTeamID = t.Team_Id\n" +
            "WHERE s.Location = ?;")){
      preparedStatement.setString(1, teamName);
      ResultSet resultSet = preparedStatement.executeQuery();
      System.out.println("\nShow the events held in a specific location ");
      while (resultSet.next()) {
        System.out.println("Event Name: " + resultSet.getString("EventName") + "| Event Type: " + resultSet.getString("EventType")
                + " | Event Date: " + resultSet.getDate("EventDate"));
      }
    }
  }
  /**
   * Overall performance of teams
   */
  private static void preformanceOfTeams(Connection conn) throws SQLException {
    try (Statement statement = conn.createStatement()) {
      ResultSet resultSet = statement.executeQuery("select n.Team_Name, t.Revenue, t.Expenses, t.NetIncome\n" +
              "from NBATeam n\n" +
              "join TeamFinance t on n.Team_Id = t.Team_Id;");
      System.out.println("\nOverall performance of teams");
      while (resultSet.next()) {
        System.out.println("Team Name: " + resultSet.getString("Team_Name")
                + " | Revenue: $ " + resultSet.getInt("Revenue")
                + " | Expenses: $ " + resultSet.getInt("Expenses")
                + " | Net Income: $ " + resultSet.getInt("NetIncome"));
      }
    }
  }

  /**
   * Identify teams with the highest net income
   */
  private static void highestNetIncome(Connection conn) throws SQLException {
    try (Statement statement = conn.createStatement()) {
      ResultSet resultSet = statement.executeQuery("select n.Team_Name, t.NetIncome\n" +
              "from NBATeam n\n" +
              "join TeamFinance t on n.Team_Id = t.Team_Id\n" +
              "order by t.NetIncome desc\n" +
              "limit 5; ");
      System.out.println("\nShow the teams with the highest revenue ");
      while (resultSet.next()) {
        System.out.println("Team Name: " + resultSet.getString("Team_Name")
                + " | Net Income: $ " + resultSet.getInt("NetIncome"));
      }
    }
  }

  /**
   * Show the distribution of investment across different teams.
   */
  private static void investmentDistribution(Connection conn) throws SQLException {
    try (Statement statement = conn.createStatement()) {
      ResultSet resultSet = statement.executeQuery("select n.Team_Name, sum(i.InvestmentAmount) as TotalInvestment\n" +
              "from NBATeam n\n" +
              "join Investors i on n.Team_Id = i.InvestedTeamID\n" +
              "group by n.Team_Name\n" +
              "order by TotalInvestment desc;");
      System.out.println("\nShow the distribution of investment across different teams.");
      while (resultSet.next()) {
        System.out.println("Team Name: " + resultSet.getString("Team_Name")
                + " | Total Investment: $ " + resultSet.getInt("TotalInvestment"));
      }
    }
  }

  /**
   * Analyze financial trends for a specific team over the years.
   */
  private static void financialTrends(Connection conn, String teamName) throws Exception {
    try (PreparedStatement preparedStatement = conn.prepareStatement(
            "SELECT n.Team_Name, t.FinancialYear, t.Revenue, t.Expenses, t.NetIncome " +
                    "FROM NBATeam n " +
                    "JOIN TeamFinance t ON n.Team_Id = t.Team_Id " +
                    "WHERE n.Team_Name = ? " +
                    "ORDER BY t.FinancialYear")) {
      preparedStatement.setString(1, teamName);
      ResultSet resultSet = preparedStatement.executeQuery();
      System.out.println("\nAnalyze financial trends for a specific team over the years");
      while (resultSet.next()) {
        System.out.println("Team Name: " + resultSet.getString("Team_Name")
                + " | Financial Year: " + resultSet.getInt("FinancialYear")
                + " | Revenue: $ " + resultSet.getInt("Revenue")
                + " | Expenses: $ " + resultSet.getInt("Expenses")
                + " | Net Income: $ " + resultSet.getInt("NetIncome"));
      }
    }
  }
  /**
   * Assess the impact of sponsorships on team finances
   */
  private static void sponsorshipImpact(Connection conn) throws Exception {
    try (Statement statement = conn.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT n.Team_Name, s.SponsorName, s.ContractValue\n" +
              "FROM NBATeam n\n" +
              "JOIN Sponsorship s ON n.Team_Id = s.Team_Id\n" +
              "ORDER BY s.ContractValue DESC;\n");
      System.out.println("\nAssess the impact of sponsorships on team finances");
      while (resultSet.next()) {
        System.out.println("Team Name: " + resultSet.getString("Team_Name")
                + " | Sponsor Name: " + resultSet.getString("SponsorName")
                + " | Contract Value $" + resultSet.getInt("ContractValue"));
      }
    }
  }
  /**
   * Provide an overview of  investors portfolio
   */
  private static void investorsPortfolio(Connection conn) throws SQLException {
    try (Statement statement = conn.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT i.InvestorName, COUNT(distinct i.InvestedTeamID) " +
              "AS NumberOfTeams, SUM(i.InvestmentAmount) AS TotalInvestment\n" +
              "FROM Investors i\n" +
              "GROUP BY i.InvestorName\n" +
              "ORDER BY TotalInvestment DESC;");
      System.out.println("\nProvide an overview of  investors portfolio");
      while (resultSet.next()) {
        System.out.println("Investor Name: " + resultSet.getString("InvestorName")
                + " | Number of Teams: " + resultSet.getInt("NumberOfTeams")
                + " | Total Investment $" + resultSet.getInt("TotalInvestment"));
      }

    }
  }

}
