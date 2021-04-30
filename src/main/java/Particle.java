import java.util.ArrayList;
import java.util.Random;

public class Particle {

    int velocity;
    Solution solution;
    Solution pBest; // координата лучшего решения конкретной частицы

    public Particle(int positionSize){
        solution = new Solution(positionSize);
        solution.randomSolution();
        this.pBest = solution;

        Random random = new Random();
        velocity = random.nextInt();
    }

    public Solution getSolution(){
        return solution;
    }

    public Solution getpBest() {
        return pBest;
    }

    public int getVelocity() {
        return velocity;
    }

    /**
     * Меняем скорость
     * @param gBest координата лучшего решения для всех частиц за итерацию
     * @param inertiaWeight коэфициент инерции
     * @param pConst весовой коэфициент
     * @param gConst весовой коэфициент
     */
    public void updateVelocity(Solution gBest, int inertiaWeight, int pConst, int gConst){
        double internalMove = inertiaWeight * velocity;
        double cognitiveMove = pConst* Math.random() * pBest.difference(this.solution);
        double socialMove = gConst * Math.random() * gBest.difference(this.solution);

        velocity = ((int)(internalMove + cognitiveMove + socialMove))%this.solution.getSolutionSize();
    }

    public void updatePosition() {
        ArrayList<Integer> availableLiterals = new ArrayList<Integer>();
        Random random = new Random();

        for(int i=0; i<this.solution.getSolutionSize(); i++)
            availableLiterals.add(i);

        for(int i=0; i<velocity; i++)
            this.solution.invertLiteral(availableLiterals.remove(random.nextInt(availableLiterals.size())));
    }

    public void updatePBest(ClausesList clausesList) {
        if(solution.satisfiedClauses(clausesList) > pBest.satisfiedClauses(clausesList))
            pBest = solution;
    }
}
