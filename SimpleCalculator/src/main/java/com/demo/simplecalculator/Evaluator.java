/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.demo.simplecalculator;

import java.util.Stack;

/**
 *
 * @author virginia
 */
public class Evaluator {
    private String s;
    
    public Evaluator(String s) {
        this.s = s;
    }
    
    public double getResult() {
        
        // TODO: Error checking for imbalanced operators

        // Stack to hold operands and operators
        Stack<Double> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // If the character is a digit, parse the number
                StringBuilder numBuilder = new StringBuilder();
                while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                    numBuilder.append(s.charAt(i));
                    i++;
                }
                i--; // Move back one index to adjust for loop increment
                double operand = Double.parseDouble(numBuilder.toString());
                operands.push(operand);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // If the character is an operator, push it to the operator stack
                while (!operators.isEmpty() && precedence(c) <= precedence(operators.peek())) {
                    applyOperator(operands, operators);
                }
                operators.push(c);
            }
        }

        // Apply remaining operators in the stack
        while (!operators.isEmpty()) {
            applyOperator(operands, operators);
        }

        // The final result is the top element of the operands stack
        return operands.pop();
    }

    private static int precedence(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        }; // Parentheses have highest precedence
    }

    private static void applyOperator(Stack<Double> operands, Stack<Character> operators) {
        char operator = operators.pop();
        double operand2 = operands.pop();
        double operand1 = operands.pop();
        double result = 0.0;

        switch (operator) {
            case '+' -> result = operand1 + operand2;
            case '-' -> result = operand1 - operand2;
            case '*' -> result = operand1 * operand2;
            case '/' -> result = operand1 / operand2;
        }

        operands.push(result);
    }
        
}
