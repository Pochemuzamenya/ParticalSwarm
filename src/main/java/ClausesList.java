import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Клоз это дизъюнкция литералов
 */
public class ClausesList {
    private List<Clause> clauses = new ArrayList<>();
    private int clauseSize;
    private int numberVariables;

    /**
     * Конструктор для чтения cnf файла
     * @param cnfFilePath путь к файлу
     */
    public ClausesList(String cnfFilePath){
        File file = new File(cnfFilePath);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ignore){}

        String line; //Для временного сохранения строки из файла
        String actualClause[]; //Получить литералы текущего клоза
        List<Integer> literalsClause = new ArrayList<>(); // Сохранить литералы текущего клоза

        try {
            while (! (line = br.readLine()).equalsIgnoreCase("%")){//читаем до символа %

                switch ((line = line.trim()).charAt(0)){
                    case 'c': //эта строка комментарий
                        break;
                    case 'p':{//получаем кол-во переменных
                            this.numberVariables = Integer.parseInt(line.replaceAll("[^0-9 ]", "")
                                    .replaceAll(" ", " ").trim().split(" ")[0]);
                            break;
                        }
                    default:{
                        actualClause = line.replaceAll("0$", "")
                                .split(" "); //Разделяем литералы предложения

                        if(this.clauseSize==0)
                            this.clauseSize = actualClause.length; // Определяем количество литералов

                        for (int i = 0; i < clauseSize; i++)
                            literalsClause.add(Integer.parseInt(actualClause[i])); //Извлекаем литералы

                        this.clauses.add(new Clause(literalsClause)); //Добавляем клоз в лист для алгоритма
                        literalsClause.clear();
                    }
                }
            }
        } catch (NumberFormatException ignore){

        } catch (IOException ignore){}

        try{
            br.close();
        } catch (IOException ignore){}
    }

    public Clause getClause(int position){
        return this.clauses.get(position);
    }

    public int getClauseSize() {
        return clauseSize;
    }

    public int getNumberVariables() {
        return numberVariables;
    }

    public int getNumberClause(){
        return this.clauses.size();
    }

    public List getClauses(){
        return this.clauses;
    }

    @Override
    public String toString(){
        String string = "Лист клозов: \n";

        for (int i = 0; i <getNumberClause(); i++)
            string += i + ". " + this.clauses.get(i) + "\n";

        return string;
    }
}
