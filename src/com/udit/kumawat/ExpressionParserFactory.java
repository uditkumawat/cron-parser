package com.udit.kumawat;

/**
 * ExpressionParser factory, return object on type of string passed
 * and creates the object with expression string
 */
public class ExpressionParserFactory {

    public static ExpressionParser expressionParser = null;

    public static ExpressionParser getExpressionParser(String type,String expression){
        if(ParserUtil.MINUTE.equals(type)){
            expressionParser = new MinuteParser(expression);
        }
        else if(ParserUtil.HOUR.equals(type)){
            expressionParser = new HourParser(expression);
        }
        else if(ParserUtil.DAY_OF_MONTH.equals(type)){
            expressionParser = new DayOfMonthParser(expression);
        }
        else if(ParserUtil.DAY_OF_WEEK.equals(type)){
            expressionParser = new DayOfWeekParser(expression);
        }
        else if(ParserUtil.MONTH.equals(type)){
            expressionParser = new MonthParser(expression);
        }
        else{
            System.out.println("Invalid type");
            return expressionParser;
        }
        return expressionParser;
    }
}
