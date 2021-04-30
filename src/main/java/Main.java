public class Main {

    public static void main(String[] args) {
        ClausesList clausesList = new ClausesList("C:\\uf50-02.cnf");
        Solution solution = Swarm.search(clausesList, 100,
                75, 20, 40, 3000, 3000);
        System.out.println(solution.satisfiedClauses(clausesList));
    }
}
