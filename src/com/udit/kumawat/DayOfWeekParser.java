package com.udit.kumawat;

public class DayOfWeekParser extends ExpressionParser{

    public static final int MAX_DAY_OF_WEEK = 6;
    public static final int MIN_DAY_OF_WEEK = 0;

    public DayOfWeekParser(String expression){
        super(expression);
        setMaxRange(MAX_DAY_OF_WEEK);
        setMinRange(MIN_DAY_OF_WEEK);
    }

    @Override
    public boolean isValid(String dayOfWeek){
        boolean isValid = true;

        if(isAsterikOrQuestionMark(dayOfWeek)){
            isValid = true;
        }
        else if(hasComma(dayOfWeek) || hasSlash(dayOfWeek) || hasHyphen(dayOfWeek)){
            String delimiter = getDelimiterType(dayOfWeek);
            String tokens[] = splitDelimiterSeperatedValues(dayOfWeek,delimiter);
            isValid = isIntegerWithDayOfWeekRange(tokens);
        }
        else{
            isValid = isInteger(dayOfWeek);
        }
        return isValid;
    }

    private boolean isIntegerWithDayOfWeekRange(String tokens[]){

        boolean isValid = true;
        for(String token:tokens){
            if(!isInteger(token)){
                isValid = false;
                break;
            }
            int num = Integer.parseInt(token);
            if(!isInDayOfWeekRange(num)){
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    private boolean isInDayOfWeekRange(int day){
        return day>=MIN_DAY_OF_WEEK && day<=MAX_DAY_OF_WEEK;
    }

}
