
/**
 * Bonus Challenge (Optional): Mini Interpreter
 * 
 * Build a simple interpreter to evaluate let variable declarations and if conditions from input strings.
 * 
 * Supported syntax:
 * - let x = 5;
 * - if (x > 3) { ... }
 * 
 * Approach:
 * - Parse input lines.
 * - Maintain a variable environment.
 * - Evaluate expressions and conditions.
 * - Support basic integer variables and if conditions.
 * 
 * Includes test cases with sample input/output in main method.
 */

import java.util.*;
import java.util.regex.*;

public class MiniInterpreter {

    private Map<String, Integer> variables = new HashMap<>();

    public void interpret(List<String> lines) {
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("let ")) {
                handleLet(line);
            } else if (line.startsWith("if ")) {
                handleIf(line);
            } else {
                System.out.println("Unsupported statement: " + line);
            }
        }
    }

    private void handleLet(String line) {
        // Syntax: let x = 5;
        Pattern pattern = Pattern.compile("let\\s+(\\w+)\\s*=\\s*(\\d+)\\s*;");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            String var = matcher.group(1);
            int value = Integer.parseInt(matcher.group(2));
            variables.put(var, value);
            System.out.println("Variable " + var + " set to " + value);
        } else {
            System.out.println("Invalid let statement: " + line);
        }
    }

    private void handleIf(String line) {
        // Syntax: if (x > 3) { ... }
        Pattern pattern = Pattern.compile("if\\s*\\(([^)]+)\\)\\s*\\{([^}]*)\\}");
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            String condition = matcher.group(1).trim();
            String body = matcher.group(2).trim();
            if (evaluateCondition(condition)) {
                System.out.println("Condition true, executing body:");
                interpret(Arrays.asList(body.split(";")));
            } else {
                System.out.println("Condition false, skipping body");
            }
        } else {
            System.out.println("Invalid if statement: " + line);
        }
    }

    private boolean evaluateCondition(String condition) {
        // Support simple conditions like x > 3, x == 5, x < 10
        Pattern pattern = Pattern.compile("(\\w+)\\s*(==|!=|>|<|>=|<=)\\s*(\\d+)");
        Matcher matcher = pattern.matcher(condition);
        if (matcher.matches()) {
            String var = matcher.group(1);
            String op = matcher.group(2);
            int val = Integer.parseInt(matcher.group(3));
            int varVal = variables.getOrDefault(var, 0);
            switch (op) {
                case "==":
                    return varVal == val;
                case "!=":
                    return varVal != val;
                case ">":
                    return varVal > val;
                case "<":
                    return varVal < val;
                case ">=":
                    return varVal >= val;
                case "<=":
                    return varVal <= val;
                default:
                    return false;
            }
        }
        return false;
    }

    // Sample test cases
    public static void main(String[] args) {
        MiniInterpreter interpreter = new MiniInterpreter();
        List<String> program = Arrays.asList(
                "let x = 5;",
                "if (x > 3) { let y = 10; }",
                "if (x < 3) { let z = 20; }",
                "let a = 7;");
        interpreter.interpret(program);
    }
}
