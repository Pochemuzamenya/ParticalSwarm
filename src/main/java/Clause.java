import java.util.ArrayList;
import java.util.List;

public class Clause {
    private List<Integer> literals = new ArrayList<>();

    public Clause(List<Integer> literals){
        for(int l : literals)
            this.literals.add(l);
    }

    public int getLiteral(int position){
        return this.literals.get(position);
    }

    public  int getNumberLiterals(){
        return this.literals.size();
    }

    @Override
    public String toString(){
        return "Clause [literals= " + literals + "\n";
    }
}
