
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
public class TinyLang {

    private final Map<String, Integer> vars = new HashMap<>();

    /** Run every line in order */
    public void run(List<String> lines) {
        for (String line : lines) {
            line = line.trim();
            if (line.startsWith("let ")) {
                doLet(line);
            } else if (line.startsWith("if ")) {
                doIf(line);
            } else {
                System.out.println("?? " + line);
            }
        }
    }

    /** let x = 5; */
    private void doLet(String line) {
        Matcher m = Pattern.compile("let\\s+(\\w+)\\s*=\\s*(\\d+)\\s*;").matcher(line);
        if (m.matches()) {
            String name = m.group(1);
            int val = Integer.parseInt(m.group(2));
            vars.put(name, val);
            System.out.println(name + " = " + val);
        } else {
            System.out.println("bad let: " + line);
        }
    }

    /** if (cond) { body; }  – body may hold multiple ‘;’-separated stmts */
    private void doIf(String line) {
        Matcher m = Pattern.compile("if\\s*\\(([^)]+)\\)\\s*\\{([^}]*)}").matcher(line);
        if (m.matches()) {
            String cond = m.group(1).trim();
            String body = m.group(2).trim();
            if (check(cond)) {
                // split body by semicolons and recurse
                run(Arrays.asList(body.split("\\s*;\\s*")));
            }
        } else {
            System.out.println("bad if: " + line);
        }
    }

    /** Evaluate simple comparisons like x == 4 or x >= 2 */
    private boolean check(String cond) {
        Matcher m = Pattern.compile("(\\w+)\\s*(==|!=|>=|<=|>|<)\\s*(\\d+)").matcher(cond);
        if (!m.matches()) return false;

        int left = vars.getOrDefault(m.group(1), 0);
        int right = Integer.parseInt(m.group(3));
        String op = m.group(2);

        switch (op) {
            case "==": return left == right;
            case "!=": return left != right;
            case ">":  return left >  right;
            case "<":  return left <  right;
            case ">=": return left >= right;
            case "<=": return left <= right;
        }
        return false;
    }

    /** quick demo */
    public static void main(String[] args) {
        TinyLang t = new TinyLang();
        t.run(Arrays.asList(
            "let x = 5;",
            "if (x > 3) { let y = 10; }",
            "if (x < 3) { let z = 20; }",
            "let a = 7;"
        ));
    }
}

