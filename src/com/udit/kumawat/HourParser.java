package com.udit.kumawat;

public class HourParser extends ExpressionParser{

    public static final int MAX_HOURS = 23;
    public static final int MIN_HOURS = 0;

    public HourParser(String expression){
        super(expression);
        setMaxRange(MAX_HOURS);
        setMinRange(MIN_HOURS);
    }

    @Override
    public boolean isValid(String hour){
        boolean isValid = true;

        if(isAsterikOrQuestionMark(hour)){
            isValid = true;
        }
        else if(hasComma(hour) || hasHyphen(hour) || hasSlash(hour)){
            String delimiter = getDelimiterType(hour);
            String tokens[] = splitDelimiterSeperatedValues(hour,delimiter);
            isValid = isIntegerWithHourRange(tokens);
        }
        else{
            isValid = isInteger(hour);
        }
        return isValid;
    }

    private boolean isIntegerWithHourRange(String tokens[]){

        boolean isValid = true;
        for(String token:tokens){
            if(!isInteger(token)){
                isValid = false;
                break;
            }
            int num = Integer.parseInt(token);
            if(!isInHourRange(num)){
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    private boolean isInHourRange(int hour){
        return hour>=MIN_HOURS && hour<=MAX_HOURS;
    }

}
