package com.udit.kumawat;

public class DayOfMonthParser extends ExpressionParser{

    public static final int MAX_DAY_OF_MONTH = 31;
    public static final int MIN_DAY_OF_MONTH = 1;

    public DayOfMonthParser(String expression){
        super(expression);
        setMaxRange(MAX_DAY_OF_MONTH);
        setMinRange(MIN_DAY_OF_MONTH);
    }

    @Override
    public boolean isValid(String dayOfMonth){
        boolean isValid = true;

        if(isAsterikOrQuestionMark(dayOfMonth)){
            isValid = true;
        }
        else if(hasComma(dayOfMonth) || hasHyphen(dayOfMonth) || hasSlash(dayOfMonth)){
            String delimiter = getDelimiterType(dayOfMonth);
            String tokens[] = splitDelimiterSeperatedValues(dayOfMonth,delimiter);
            isValid = isIntegerWithDayOfMonthRange(tokens);
        }
        else{
            isValid = isInteger(dayOfMonth);
        }
        return isValid;
    }

    private boolean isInDayOfMonthRange(int num){
        return num>=MIN_DAY_OF_MONTH && num<=MAX_DAY_OF_MONTH;
    }

    private boolean isIntegerWithDayOfMonthRange(String tokens[]){

        boolean isValid = true;
        for(String token:tokens){
            if(!isInteger(token)){
                isValid = false;
                break;
            }
            int num = Integer.parseInt(token);
            if(!isInDayOfMonthRange(num)){
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}
