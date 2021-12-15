package com.gemini.learning;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * com.gemini.learning.ReversePolishNotation
 * 1.从左到右扫描每一个字符，如果是操作数（如a、b、c等），直接输出
 * 2.如果扫描到的是一个操作符：
 * <p>
 * 1）如果堆栈为空，直接入栈
 * <p>
 * 2）如果栈内有操作符，我们要判断栈顶已有的操作符的优先级和需要判断的操作符的优先级的大小。
 * 栈内<栈外：入栈操作符
 * 　　  栈内>栈外：出栈操作符
 * 　　  栈内=栈外：出栈栈内的操作符并输出，并入栈操作符
 * 3.如果遇到的操作符是左括号'(',就直接将该操作符入栈。
 * 4.如果遇到操作符是右括号')'后丢弃该操作符,并将栈中的操作符出栈并且输出，直到遇到左括号'(',出栈左括号丢弃。继续扫描下一个字符
 *
 * @author zhanghailin
 */
public class ReversePolishNotation {

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        //注意使用nextLine方法来接收可能的空格
        char[] s = "1+6/3".toCharArray();
		/*for(int i = 0; i < s.length; i++){
			System.out.println(s[i]);
		}*/
        long res = solve(s);
        System.out.println(res);
    }

    private static long solve(char[] s) {
        Stack<Character> stack = new Stack<>();
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < s.length; ) {
            if (s[i] == ' ') {
                i++;
                continue;
            } else if (s[i] >= '0' && s[i] <= '9') {
                int sum = 0;
                while (i < s.length && s[i] >= '0' && s[i] <= '9') {
                    sum = sum * 10 + s[i] - '0';
                    i++;
                }
                queue.add(Integer.toString(sum));
            } else if (s[i] == '(') {
                stack.add(s[i]);
                i++;
            } else if (s[i] == ')') {
                // 如果遇到操作符是右括号')'后丢弃该操作符,并将栈中的操作符出栈并且输出，直到遇到左括号'(',出栈左括号丢弃。继续扫描下一个字符
                while (!stack.isEmpty() && stack.peek() != '(') {
                    queue.add(String.valueOf(stack.pop()));
                }
                if (stack.peek() == '(')
                    stack.pop();
                i++;
            } else {
                // 如果扫描到的是一个操作符：
                //
                // 1）如果堆栈为空，直接入栈
                //
                // 2）如果栈内有操作符，我们要判断栈顶已有的操作符的优先级和需要判断的操作符的优先级的大小。
                //	  栈内<栈外：入栈操作符
                //　　 栈内>栈外：出栈操作符
                //　　 栈内=栈外：出栈栈内的操作符并输出，并入栈操作符
                while (!stack.empty() && prec(s[i]) <= prec(stack.peek())) {
                    queue.add(String.valueOf(stack.pop()));
                }
                stack.add(s[i]);
                i++;
            }
        }
        while (!stack.isEmpty()) {
            queue.add(String.valueOf(stack.pop()));
        }
        //必须要使用.equals方法才正确使用 == 不正确
        Stack<Integer> res = new Stack<>();
        while (!queue.isEmpty()) {
            String t = queue.poll();
            if (t.equals("+") || t.equals("-") || t.equals("*") || t.equals("/")) {
                int a = res.pop();
                int b = res.pop();
                int result = cal(b, a, t);
                res.push(result);
            } else {
                res.add(Integer.parseInt(t));
            }
        }
        return res.pop();
    }

    private static int cal(int a, int b, String t) {
        //计算
        if (t.equals("+")) {
            return a + b;
        } else if (t.equals("-")) {
            return a - b;
        } else if (t.equals("*")) {
            return a * b;
        } else {
            return a / b;
        }
    }

    private static int prec(char c) {
        if (c == '^')
            return 3;
        else if (c == '*' || c == '/')
            return 2;
        else if (c == '+' || c == '-')
            return 1;
        else
            return -1;
    }

//    private static int compare(char peek, char c) {
//        if (peek == '(' || c == '(') return 1;
//        if (c == '+' || c == '-') return -1;
//        if (c == '*' && (peek == '*' || peek == '/')) return -1;
//        if (c == '/' && (peek == '*' || peek == '/')) return -1;
//        return 1;
//    }
}
