# Using Database System for Sports Management in NBA 
Erioluwa Akinpelu and Belen Tesfaye

### Abstract
There is an evident need for an integrated database system in sports management. This well-designed database system will include various datasets created to capture the complexity of the database management system, providing interlinked multidimensional datasets that exceed the limitations of standalone tables. The project aims to offer a dataset solution essential to sports management, specifically in the NBA.
The database system will comprise sets of interconnected tables, each specially designed to represent and store data elements such as athlete profiles, teams, team finances, sport events, and sponsorships. The relationships between these tables will demonstrate the interdependence essential for functional sports management. The established relationships will further enable sports managers, coaches, or those responsible for or affected by teams' financial situations to have better insights.
The project will consider security and data privacy through the implementation of user roles and access controls. In addition to privacy and data security, the database system will be created to ensure data integrity. User-friendly interfaces, proper query mechanisms, and a reporting system will be included to supply a comprehensive analysis. The aim is not only to create a database solution for sports management but also to rethink how sports management operates.
 

### Background and Significance
In professional sports, proper and effective management is essential for success. For the National Basketball Association (NBA), a sophisticated database system is needed to create a multifaceted environment of interconnected elements. Hopefully, create a better understanding of how database design can revolutionize sports management.
The NBA sports management landscape involves an array of data, from athlete profiles to finances, team dynamic, sport events, and sponsorship. When using standalone tables, it’s impossible to fully capture the multidimensional nature of these datasets. This project aims to design an integrated database system that will surpass these limitations and supply a comprehensive view of the NBA’s management. The database system will feature an intricate network of interconnected tables designed to store specific data elements. The interconnect tables prove the interdependence inherent in functional sport management and restructure the decision-making processes within the NBA.
A user-friendly interface will be used to ensure users can easily navigate, query, and analyze data. Extensive measures will confirm and support data accuracy. This database system was created to prioritize data integrity to guarantee information reliability. Using a query mechanism and robust reporting system, users will be provided with comprehensive analysis. This will allow sets of strategies, perfect resource allocation, and enhance the overall efficiency in NBA management. Addressing data complexities ensures data security and privacy, and fosters a data driven culture. Hopefully, this project will further elevate standards in NBA sport management and potentially serve as a model database management system for other support leagues worldwide.

### Methodology
<u> Creating database design </u>
<br>
A database schema for sport management is created. Seven tables are created: NBATeam, Athlete_Profile,  TeamFinance, SportEvent,  Sponsorship, and Investors.
The relationship and dependence between the tables and their attributes of the table is set up to reflect the real-world dynamics of NBA sports management. ER diagrams will be used for this visual representation. For these tables, a primary key, foreign keys, and attributes will be defined based on the ER diagram.
Table purposes and Interconnected Relationship are also drawn on the ER diagram to be not only the multifaceted nature of the database system but the real-world dynamics. To prevent redundant data and ensure efficiency of the database design normalization techniques are applied. A minimal number of attributes will be used by only using certain attributes only once across tables.
<br> <u> Table and attribute relationship </u> <br>
A Team has many athletes, but an athlete can only belong to one team. A team can have one finance record therefore a team finance record can only belong to one team. A sport event can only be associated with one team, but a team can have many sport events. A sponsorship can only be associated with one team while a team can have many sport events. There is only one team an athlete can belong to, but a team can have multiple athletes. There is only one financial record per team. A sponsorship can only be associated with one team, but a team can have multiple sponsorship. A team can have multiple investors and an investor can invest in multiple teams. 

### Analysis
The first queries were on team-specific attributes such as conference, total revenue, and coach income. This set of queries supplies a detailed picture of the team’s financial health and overall performance. Queries that explored investor engagement highlight investors who have invested in multiple teams, provided information on injured athletes, highlighted teams with highest revenue, and supplied events held at a specific location. These queries shine light to the financial dynamics surrounding the NBA and how events contribute to team revenues. Moreover, the last set of queries were on financial analysis, examined overall team performance, found teams with the highest income, and covered the distribution of investments across teams. These queries aided with comprehensive understanding of the NBA’s financial health.

<br> <u>Output example 1: Retrieve team names and conferences for teams in the Eastern Conference </u> <br>

| Team_Name      | Conference |
| ----------- | ----------- |
| Boston Celtics      | Eastern       |
| Cleveland Cavaliers   | Eastern        |
| Washington Wizards  | Eastern        |

<br> <u> Output example 2: Show the teams with the highest revenue </u> <br>

| Team_Name      | HighestRevenue |
| ----------- | ----------- |
| Boston Celtics      | 63402000       |
| Cleveland Cavaliers   | 52001000        |
| Washington Wizards  | 44000000        |
| Boston Celtics  | 41300000        |
| Cleveland Cavaliers  | 32140000        |

<br> <u> Output example 3: Overall performance of teams </u> <br>

| Team_Name      | Revenue | Expenses |NetIncome|
| ----------- | ----------- |-----------|-----------|
| Boston Celtics      | 41300000       |30000000|10000000|
| Cleveland Cavaliers   | 32140000        |22000000|3000000|
| Washington Wizards  | 52001000        |45000000|5000000|
| Boston Celtics  | 44000000        |38000000|7000000|
| Cleveland Cavaliers  | 63402000        |55000000|5000000|

### Conclusion
The queries created show the extent of the NBA database system to extract and analyze meaningful data, offering useful insights into various aspects of the league. These queries cover topics such as team information, financial performance, athlete status, coach earnings, investor activities, and sponsorship impact. The first part of the queries focuses on the team-related information, filtering teams based on conference affiliation, revenue of a given year, and athlete profiles of specific teams (e.g., the Golden State Warriors). Further queries provided a complex analysis including identifying coaches with higher earnings, investors who invested in multiple teams, displaying injured athletes, and teams with highest revenue. This is meant to address the project’s goal to aid investors with decision making, and database systems ability to revolutionize sport management through data analysis.
Overall, the queries taken in multiple data and analyze various aspects of the NBA database. The queries contribute to the comprehensive understanding of the NBA’s multifaceted management landscape, and the system's ability to handle complex relationships between tables through joins proves its efficacy in supplied a thorough analysis, essentially aiding decision-making processes from coaches to investors. And, most importantly, setting a standard for database management system in sport leagues worldwide. 

### Reference
All teams. NBA Teams & Rosters | NBA.com. (n.d.). https://www.nba.com/teams