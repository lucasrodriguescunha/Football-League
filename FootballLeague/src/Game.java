import java.util.Random;

class Game {
    private Team team1;
    private Team team2;
    private int teamGoals1;
    private int teamGoals2;

    public Game(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.teamGoals1 = 0;
        this.teamGoals2 = 0;
    }

    public void simulate() {
        Random random = new Random();

        this.teamGoals1 = random.nextInt(6); // Gols aleatórios entre 0 e 5
        this.teamGoals2 = random.nextInt(6); // Gols aleatórios entre 0 e 5

        // Atualizar os pontos dos times de acordo com o resultado do jogo
        if (teamGoals1 > teamGoals2) {
            team1.setPoints(3); // Time 1 venceu
        } else if (teamGoals2 > teamGoals1) {
            team2.setPoints(3); // Time 2 venceu
        } else {
            // Empate
            team1.setPoints(1);
            team2.setPoints(1);
        }
    }


    public String getResult() {
        return team1.getName() + " " + teamGoals1 + " x " + teamGoals2 + " " + team2.getName();
    }
}