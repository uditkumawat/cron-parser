package com.udit.kumawat;

public class MinuteParser extends ExpressionParser{

    public static final int MAX_MINUTES = 59;
    public static final int MIN_MINUTES = 0;

    public MinuteParser(String expression){
        super(expression);
        setMaxRange(MAX_MINUTES);
        setMinRange(MIN_MINUTES);
    }

    @Override
    public boolean isValid(String minute){
        boolean isValid = true;

        if(isAsterikOrQuestionMark(minute)){
            isValid = true;
        }
        else if(hasComma(minute) || hasHyphen(minute) || hasSlash(minute)){
            String delimiter = getDelimiterType(minute);
            String tokens[] = splitDelimiterSeperatedValues(minute,delimiter);
            isValid = isIntegerWithMinuteRange(tokens);
        }
        else{
            isValid = isInteger(minute);
        }
        return isValid;
    }

    private boolean isInMinuteRange(int num){
        return num>=MIN_MINUTES && num<=MAX_MINUTES;
    }

    private boolean isIntegerWithMinuteRange(String tokens[]){

        boolean isValid = true;
        for(String token:tokens){
            if(!isInteger(token)){
                isValid = false;
                break;
            }
            int num = Integer.parseInt(token);
            if(!isInMinuteRange(num)){
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}
