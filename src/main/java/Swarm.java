import java.util.ArrayList;
import java.util.List;

public abstract class Swarm {
    /**
     *
     * @param clausesList лист с клозами, КНФ, целевая функция и тд
     * @param numParticles количество частиц
     * @param pConst весовой коэфициент
     * @param gConst весовой коэфициент
     * @param inertiaWeight коэфициент инерции
     * @param maxIterations количество итераций
     * @param execTime допустимое время выполнения
     * @return возвращаем лучшее решение для роя
     */
    public static Solution search(ClausesList clausesList,
                                  int numParticles, int pConst, int gConst,
                                  int inertiaWeight, int maxIterations, long execTime){
        List<Particle> particles = new ArrayList<>();
        Solution gBest = new Solution(clausesList.getNumberVariables());

        long startTime = System.currentTimeMillis(); //сохраняем время начала поиска

        for (int i = 0; i < numParticles; i++) {
            particles.add(new Particle(clausesList.getNumberVariables()));
            if (gBest.satisfiedClauses(clausesList) < particles.get(i).getSolution().satisfiedClauses(clausesList))
                gBest = particles.get(i).getSolution();
        }

        for (int i = 0; i < maxIterations; i++){
            if((System.currentTimeMillis() - startTime) >= execTime)
                break;

            for (Particle p: particles){
                p.updateVelocity(gBest, inertiaWeight, pConst, gConst);
                p.updatePosition();
                p.updatePBest(clausesList);
            }

            for (Particle p : particles)
                if(gBest.satisfiedClauses(clausesList) < p.getSolution().satisfiedClauses(clausesList))
                    gBest = p.getSolution();

                if (gBest.isSolution(clausesList))
                    break;
        }
        return gBest;
    }
}
