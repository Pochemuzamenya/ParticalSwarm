import java.util.ArrayList;
import java.util.List;
/**
 * Решения или позиции?
 */
public class Solution {
    /**
     * Пространство решений
     */
    private List<Integer> solution = new ArrayList<>();

    //пустое решение
    public Solution(int size){
        for(int i = 0; i < size; i++)
            this.solution.add(0);
    }

    /**
     * Случайное решение
     */
    public void randomSolution(){
        int literalValue; //значение литерала
        for(int i = 0; i< this.getSolutionSize(); i++){
            literalValue = (int)(Math.random()*10)%2;
            this.solution.set(i, ((i+1)*(literalValue == 0 ? -1 : 1)));
        }
    }

    public int getSolutionSize(){
        return this.solution.size();
    }

    public int getLiteral(int position){
        return this.solution.get(position);
    }

    /**
     * Количество позиций с разными значениями между двумя решениями
     */
    public Integer difference(Solution solution){
        int numDifference = 0;
        if(this.getSolutionSize() != solution.getSolutionSize())
            return null; //если размер разный то мы не можем сравнивать
        for (int i = 0; i < this.getSolutionSize(); i++)
            if(this.getLiteral(i) != solution.getLiteral(i))
                numDifference++;
            return numDifference;
    }

    public int satisfiedClauses(ClausesList clausesList){
        int count = 0;
        int literal;

        for (int i = 0; i < clausesList.getNumberClause(); i++){
            for(int j = 0; j < clausesList.getClauseSize(); j++){
                literal = clausesList.getClause(i).getLiteral(j);

                if (literal == this.getLiteral(Math.abs(literal) - 1)){
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public boolean isSolution(ClausesList clausesList) { // Проверьте, удовлетворяет ли это решение ВСЕМ ПОЛОЖЕНИЯМ в "наборе предложений"
        return clausesList.getNumberClause() == this.satisfiedClauses(clausesList);
    }

    @Override
    public boolean equals(Object obj) { // Проверить равенство двух решений
        Solution otherSol = (Solution) obj;

        if(this.getSolutionSize() != otherSol.getSolutionSize())
            return false; // Если размер двух решений различается, то два решения НЕ равны.

        for(int i=0; i<this.getSolutionSize(); i++)
            if(this.getLiteral(i) != otherSol.getLiteral(i))
                return false; // Есть хотя бы один другой буквальный

        return true; // Одинаковый размер И одинаковый литерал ==> Равные решения
    }

    public boolean invertLiteral(int position) { //"разворачиваем" литерал, с 0 на 1 и наоборот
        if((position < 0) || (position >= this.solution.size())) // аут оф баундс
            return false;

        this.solution.set(position, - this.solution.get(position));

        return true;
    }


    @Override
    public String toString() {
        return "Solution is : "+this.solution;
    }
}
