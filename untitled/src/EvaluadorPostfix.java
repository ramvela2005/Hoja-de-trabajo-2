import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class EvaluadorPostfix {

    public static int evaluarExpresion(String expresion) {
        Stack<Integer> pila = new Stack<>();

        String[] elementos = expresion.split(" ");

        for (String elemento : elementos) {
            if (esOperando(elemento)) {
                pila.push(Integer.parseInt(elemento));
            } else if (esOperador(elemento)) {
                int operandoB = pila.pop();
                int operandoA = pila.pop();
                int resultado = aplicarOperacion(operandoA, operandoB, elemento);
                pila.push(resultado);
            }
        }

        return pila.pop();
    }

    private static boolean esOperando(String elemento) {
        return elemento.matches("\\d+");
    }

    private static boolean esOperador(String elemento) {
        return elemento.matches("[+\\-*/]");
    }

    private static int aplicarOperacion(int operandoA, int operandoB, String operador) {
        switch (operador) {
            case "+":
                return operandoA + operandoB;
            case "-":
                return operandoA - operandoB;
            case "*":
                return operandoA * operandoB;
            case "/":
                if (operandoB != 0) {
                    return operandoA / operandoB;
                } else {
                    throw new ArithmeticException("División por cero");
                }
            default:
                throw new IllegalArgumentException("Operador no reconocido: " + operador);
        }
    }
}


