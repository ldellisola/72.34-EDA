
import java.util.*;

public class Evaluator {
    Stack<Double> stack;
    Map<String,Boolean> table;
    Map<String, Double> var;

    public Evaluator() {

        stack=new Stack<Double>();
        createTable();
    }
    public Evaluator(Map variables) {
         var=variables;
        stack=new Stack<Double>();
        createTable();
    }

    private void createTable(){
        table=new HashMap<String, Boolean>();
        table.put("+?+",true);
        table.put("+?-",true);
        table.put("+?*",false);
        table.put("+?/",false);
        table.put("+?^",false);
        table.put("+?(",false);
        table.put("+?)",true);


        table.put("-?-",true);
        table.put("-?+",true);
        table.put("-?*",false);
        table.put("-?/",false);
        table.put("-?^",false);
        table.put("-?(",false);
        table.put("-?)",true);

        table.put("*?*",true);
        table.put("*?/",true);
        table.put("*?+",true);
        table.put("*?-",true);
        table.put("*?^",false);
        table.put("*?(",false);
        table.put("*?)",true);


        table.put("/?/",true);
        table.put("/?*",true);
        table.put("/?+",true);
        table.put("/?-",true);
        table.put("/?^",false);
        table.put("/?(",false);
        table.put("/?)",true);

        table.put("^?^",false);
        table.put("^?+",true);
        table.put("^?-",true);
        table.put("^?*",true);
        table.put("^?/",true);
        table.put("^?(",false);
        table.put("^?)",true);


        table.put("(?(",false);
        table.put("(?^",false);
        table.put("(?+",false);
        table.put("(?-",false);
        table.put("(?*",false);
        table.put("(?/",false);
        table.put("(?)",false);

    }


    Double evaluateInfija(String ec){
//        System.out.print("Introduzca la expresión en notación postfija: ");
       Scanner postfija=new Scanner(infijaToPostfija(ec));
        if(! postfija.hasNextLine())
            throw new RuntimeException("no hay nada que evaluar");

        return evaluate(postfija.nextLine());

    }
    private  String infijaToPostfija(String ec){
        Scanner inputScanner = new Scanner(ec).useDelimiter("\\n");
        Stack<String> opert=new Stack<String>();
        String rta="";
        if(! inputScanner.hasNextLine())
            throw new RuntimeException("No hay nada");

        Scanner lineScanner = new Scanner(inputScanner.next()).useDelimiter("\\s+");
        while (lineScanner.hasNext()) {
            String aux=lineScanner.next();
            // Si viene un numero lo agrego directamente
            if(aux.matches("-?[0-9]+.?[0-9]*")){
               rta=rta.concat(aux+" ");
            }
            // Si viene otro caracter tengo que procesarlo
            else if(aux.matches("\\+|-|/|\\*|\\^|\\)|\\(")){
            	// Dependiendo de la prioridad del simbolo y si esta vacio, lo agrego al string
                while( opert.size()!=0 && table.get(opert.peek().concat("?").concat(aux))) {
                   rta= rta.concat(opert.pop()+" ");
                }
                // Para poder cerrar un parentesis, en el stack tiene que aparecer un parentesis
                // abierto
                if(aux.matches("\\)")) {
                    if (opert.size() != 0 && opert.peek().matches("\\("))
                        opert.pop();
                    else
                        throw new RuntimeException("No cierran los parentesis");

                }
                // Si el simbolo no es un parentesis de cierre, lo mando al stack
                else
                    opert.push(aux);
            }
            // Si no es un numero o un simbolo, es una varaible.
            else{
                Double num=var.get(aux);
                if(num==null)
                    throw new RuntimeException("no existe la variable");
                rta=rta.concat(num+" ");
            }

        }
        // Saco los parentesis abiertos
        while (opert.size()>0){
        	// Si me quedo un parentesis abierto es por que el usuarui puso de mas, entonces es invalido
            if(opert.peek().matches("\\("))
            {
            	throw new RuntimeException("Missing");
            }
            else
                rta= rta.concat(opert.pop()+" ");
        }
    return rta;
    }

    Double evaluate(String line){
        Scanner lineScanner = new Scanner(line).useDelimiter("\\s+");
        while (lineScanner.hasNext()){
            String aux=lineScanner.next();
            if(aux.matches("-?[0-9]+.?[0-9]*")){
                stack.push(Double.parseDouble(aux));
            }
            else if(aux.matches("\\+|-|/|\\*|\\^")){
                if(stack.size()<2)
                    throw new EmptyStackException();

                stack.push(operations(stack.pop(),stack.pop(),aux));
            }
            else
                throw new IllegalArgumentException();
        }
        if(stack.size()!=1)
            throw new IllegalArgumentException();
        return stack.pop();
    }
    Double operations(double b,double a,String aux){
        if(aux.matches("\\+"))
            return a + b;
        if(aux.matches("-"))
            return a-b;
        if(aux.matches("\\*"))
            return a*b;
        if(aux.matches("\\^"))
            return Math.pow(a,b);
        else
            return a/b;
    }
}
