import java.util.List;

public class Main {

    public static void main(String[] args) {
        ClausesList clausesList = new ClausesList("C:\\cnf\\uufcnf.cnf");
        int numParticles=10, pConst=100, gConst=100, inertiaWeight=300, maxIterations = 3000;
        long startTime = System.nanoTime();
        Solution solution = Swarm.search(clausesList, numParticles,
                    pConst, gConst, inertiaWeight, maxIterations, 10000);
        long endTime = System.nanoTime();
        List list = clausesList.getClauses();
        System.out.println(list);
        System.out.printf("Время выполнения %d мс%n",(endTime - startTime + 500_000L) / 1_000_000L);
        System.out.println("Количество единиц " + solution.satisfiedClauses(clausesList));
        System.out.println("Количество дизъюнктов " + clausesList.getNumberClause());
        if (solution.isSolution(clausesList)){
            System.out.println("Задача выполнима");
            System.out.println(solution);
        } else {
            System.out.println("Решение не найдено или задача не выполнима");
        }
    }

}
