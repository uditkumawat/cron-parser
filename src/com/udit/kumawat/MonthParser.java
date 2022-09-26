package com.udit.kumawat;

public class MonthParser extends ExpressionParser{

    public static final int MAX_MONTHS = 12;
    public static final int MIN_MONTHS = 1;

    public MonthParser(String expression){
        super(expression);
        setMaxRange(MAX_MONTHS);
        setMinRange(MIN_MONTHS);
    }

    @Override
    public boolean isValid(String month){
        boolean isValid = true;

        if(isAsterikOrQuestionMark(month)){
            isValid = true;
        }
        else if(hasComma(month) || hasHyphen(month) || hasSlash(month)){
            String delimiter = getDelimiterType(month);
            String tokens[] = splitDelimiterSeperatedValues(month,delimiter);
            isValid = isIntegerWithMonthRange(tokens);
        }
        else{
            isValid = isInteger(month);
        }
        return isValid;
    }

    private boolean isIntegerWithMonthRange(String tokens[]){

        boolean isValid = true;
        for(String token:tokens){
            if(!isInteger(token)){
                isValid = false;
                break;
            }
            int num = Integer.parseInt(token);
            if(!isInMonthRange(num)){
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    private boolean isInMonthRange(int month){
        return month>=MIN_MONTHS && month<=MAX_MONTHS;
    }
}
