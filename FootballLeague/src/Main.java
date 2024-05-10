import javax.swing.*;

/**
 * A simple program to simulate and display standings of a sports league.
 * Um programa simples para simular e exibir a classificação de uma liga esportiva.
 */
public class Main {

    /**
     * Main method to run the program.
     * Método principal para executar o programa.
     *
     * @param args Command line arguments (not used)
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Initialize variables to store team information
        int choice; // Choice made by the user
        String[] teams = new String[10]; // Array to store the names of the teams
        int[] points = new int[10]; // Array to store the points of the teams
        int[][] results = new int[10][4]; // Array to store the results of each team's games: [wins, draws, losses, goals scored]
        boolean[][] alreadyPlayed = new boolean[10][10]; // Matrix to record the games already played

        // Main program loop
        while (true) {
            // Display options menu and get user input
            String input = JOptionPane.showInputDialog(null,
                    "[CHOOSE AN OPTION]\n1. Register teams\n2. Simulate a game\n3. View Standings\n4. EXIT\nOption:");
            choice = Integer.parseInt(input);

            // Switch based on user choice
            switch (choice) {
                case 1: // Register teams
                    JOptionPane.showMessageDialog(null, "[REGISTER TEAMS]");
                    for (int i = 0; i < 10; i++) {
                        teams[i] = JOptionPane.showInputDialog("Team " + (i + 1) + ":");
                        points[i] = 0; // Initialize each team's points as 0
                    }
                    JOptionPane.showMessageDialog(null, "[TEAMS REGISTERED]");
                    break;
                case 2: // Simulate a game
                    JOptionPane.showMessageDialog(null, "[SIMULATE A GAME]");
                    JOptionPane.showMessageDialog(null, "[CHOOSE TWO TEAMS FOR THE GAME]: ");
                    StringBuilder chosenTeams = new StringBuilder();
                    for (int i = 0; i < 10; i++) {
                        chosenTeams.append((i + 1)).append(". ").append(teams[i]).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, chosenTeams.toString());
                    int firstTeamIndex = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of the first team:")) - 1;
                    int secondTeamIndex = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of the second team:")) - 1;

                    // Check if the teams have played before
                    if (alreadyPlayed[firstTeamIndex][secondTeamIndex] || alreadyPlayed[secondTeamIndex][firstTeamIndex]) {
                        JOptionPane.showMessageDialog(null, "This game has already been played! Choose another pair of teams.");
                        break;
                    }

                    alreadyPlayed[firstTeamIndex][secondTeamIndex] = true;
                    alreadyPlayed[secondTeamIndex][firstTeamIndex] = true;

                    if (firstTeamIndex < 0 || firstTeamIndex >= 10 || secondTeamIndex < 0 || secondTeamIndex >= 10) {
                        JOptionPane.showMessageDialog(null, "Invalid team index.");
                        break;
                    }
                    String firstTeam = teams[firstTeamIndex];
                    String secondTeam = teams[secondTeamIndex];
                    JOptionPane.showMessageDialog(null, "[SIMULATING THE GAME]\nSimulating the game between " + firstTeam + " and " + secondTeam + "...");
                    double randomNumber = Math.random();
                    if (randomNumber < 0.5) {
                        points[firstTeamIndex] += 3; // Add 3 points to the winning team
                        results[firstTeamIndex][0]++; // Increment the number of wins for the team
                        results[secondTeamIndex][2]++; // Increment the number of losses for the other team
                        JOptionPane.showMessageDialog(null, "The winner of the game is: " + firstTeam);
                    } else {
                        points[secondTeamIndex] += 3; // Add 3 points to the winning team
                        results[secondTeamIndex][0]++; // Increment the number of wins for the team
                        results[firstTeamIndex][2]++; // Increment the number of losses for the other team
                        JOptionPane.showMessageDialog(null, "The winner of the game is: " + secondTeam);
                    }
                    results[firstTeamIndex][3] += 1; // Add 1 to the total goals scored by the team
                    results[secondTeamIndex][3] += 1; // Add 1 to the total goals scored by the other team
                    break;
                case 3: // View Standings
                    JOptionPane.showMessageDialog(null, "[STANDINGS]");
                    String[] columnNames = {"Team Name", "Total Games", "Wins", "Draws", "Losses", "Total Points", "Goals Scored", "Goals Conceded", "Goal Difference"};
                    Object[][] data = new Object[10][9];
                    for (int i = 0; i < 10; i++) {
                        int totalGames = results[i][0] + results[i][1] + results[i][2];
                        int draws = totalGames - results[i][0] - results[i][2];
                        int totalPoints = (results[i][0] * 3) + (draws * 1);
                        int goalsScored = results[i][3];
                        int goalsConceded = 0;
                        for (int j = 0; j < 10; j++) {
                            goalsConceded += results[j][3];
                        }
                        int goalDifference = goalsScored - goalsConceded;
                        data[i] = new Object[]{teams[i], totalGames, results[i][0], draws, results[i][2], totalPoints, goalsScored, goalsConceded, goalDifference};
                    }
                    JTable table = new JTable(data, columnNames);
                    table.setEnabled(false);
                    JOptionPane.showMessageDialog(null, new JScrollPane(table));
                    break;
                case 4: // Exit the program
                    JOptionPane.showMessageDialog(null, "[EXITING THE PROGRAM...]");
                    System.exit(0);
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please choose a valid option.");
            }
        }
    }
}
