package com.unoveo.securityjwt.calculator;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/calculation")
public class calculatorService extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
// resp.setHeader("Access-Control-Allow-Methods", "http://localhost:4200");
//// // resp.setHeader("Access-Control-Allow-Origin", "*");
// resp.setHeader("Access-Control-Allow-Methods", "POST");
// resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
// resp.setHeader("Access-Control-Allow-Credentials", "true");

        if(!req.isUserInRole("ROLE_ADMIN")){
            resp.sendError(403,"Admin Access Required.");
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }else {

// Read the JSON string from the request
            BufferedReader reader = req.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String jsonString = sb.toString();

// Parse the JSON using Gson library
            Gson gson = new Gson();
            ExpressionRequest[] expressionRequest = gson.fromJson(jsonString,
                    ExpressionRequest[].class);

// Extract the expression from the request
            double result = convertExpressionArrayToString(expressionRequest);

// Evaluate the expression

// Create the response
            ExpressionResponse expressionResponse = new ExpressionResponse((int)result);
            String jsonResponse = gson.toJson(expressionResponse);

// Send the response back to Postman
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(jsonResponse);
            writer.flush();
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    private double convertExpressionArrayToString(ExpressionRequest[]
                                                          expressionRequest) {
        double firstNo = 0;
        String op = "";
        boolean isNegative = false;

        for (ExpressionRequest exp : expressionRequest) {
            if (exp.getType().equals("num")) {
                double num;

                if (isNegative) {
                    num = -Double.parseDouble(exp.getValue());
                    isNegative = false;
                } else {
                    num = Double.parseDouble(exp.getValue());
                }
                if (op.equals("")) {
                    firstNo = num;
                    System.out.println(firstNo);
                } else {
                    if (op.equals("-")) {
                        num = -num;
                    }
                    firstNo = evaluateExpression(firstNo, num, op);
                    System.out.println(op);
                }
            }
            else if(exp.getType().equals("op"))
            {
                op=exp.getValue();
                System.out.println(op);
                if(op.equals("-"))
                {
                    isNegative=true;
                }
            }

        }
        return firstNo;
    }


    private double evaluateExpression(double numOne, double numTwo, String op) {

        double result = 0;
        switch (op) {
            case "+":
                result = numOne + numTwo;
                break;
            case "-":
                result = numOne - numTwo;
                break;
            case "*":
                result = numOne * numTwo;
                break;
            case "/":
                result = numOne / numTwo;
                break;
        }

        return result;

    }
}


