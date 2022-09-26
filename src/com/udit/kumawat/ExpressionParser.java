package com.udit.kumawat;

/**
 * Abstract class containing all common functions for parsing
 */
public abstract class ExpressionParser {

    public int minRange;
    public int maxRange;
    public StringBuilder outcome;
    public String expression = "";

    public ExpressionParser(String expression){
        this.minRange = 0;
        this.maxRange = 0;
        this.expression = expression;
        this.outcome = new StringBuilder();
    }

    public int getMinRange(){
        return minRange;
    }

    public void setMinRange(int minRange){
        this.minRange = minRange;
    }

    public int getMaxRange(){
        return maxRange;
    }

    public void setMaxRange(int maxRange){
        this.maxRange = maxRange;
    }

    public String getExpression(){
        return expression;
    }

    public String getOutcome(){
        return outcome.toString();
    }

    public boolean isAsterikOrQuestionMark(String str){
        return ("*".equals(str) || "?".equals(str)) ? true:false;
    }

    public boolean hasComma(String str){
        return str.contains(",");
    }

    public boolean hasHyphen(String str){
        return str.contains("-");
    }

    public boolean hasSlash(String str){
        return str.contains("/");
    }

    public String[] splitDelimiterSeperatedValues(String str,String delimiter){
        return str.split(delimiter);
    }

    public boolean isInteger(String str){
        try{
            int num = Integer.parseInt(str);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

    public String getDelimiterType(String str){
        String delimiter = null;
        if(hasComma(str)){
            delimiter = ParserUtil.DELIMITER_COMMA;
        }
        else if(hasSlash(str)){
            delimiter = ParserUtil.DELIMITER_SLASH;
        }
        else{
            delimiter = ParserUtil.DELIMITER_HYPHEN;
        }
        return delimiter;
    }

    public abstract boolean isValid(String str);

    /**
     * Main function which iterates the expression and parses the information
     * @return
     */
    public String parse() {

        if(isAsterikOrQuestionMark(getExpression())){
            updateWithAllValues();
        }
        else if(hasComma(getExpression()) || hasHyphen(getExpression()) || hasSlash(getExpression())){
            String delimiter = getDelimiterType(getExpression());
            String tokens[] = splitDelimiterSeperatedValues(getExpression(),delimiter);
            if(hasComma(getExpression())){
                updateWithListValues(tokens);
            }
            else if(hasHyphen(getExpression())){
                updateWithRangeValues(tokens);
            }
            else{
                updateWithStepValue(tokens);
            }
        }
        else{
            updateSingleValue(getExpression());
        }

        return getOutcome();
    }

    private void updateSingleValue(String str){
        outcome.append(str).append(ParserUtil.DELIMITER_SPACE);
    }

    public void updateWithAllValues(){

        for(int i=getMinRange();i<=getMaxRange();i++){
            outcome.append(i).append(ParserUtil.DELIMITER_SPACE);
        }
    }

    private void updateWithListValues(String tokens[]){

        for(String token:tokens){
            outcome.append(token).append(ParserUtil.DELIMITER_SPACE);
        }
    }

    private void updateWithRangeValues(String tokens[]){

        int startValue = Integer.parseInt(tokens[0]);
        int endValue = Integer.parseInt(tokens[1]);

        for(int i=startValue;i<=endValue;i++){
            outcome.append(i).append(ParserUtil.DELIMITER_SPACE);
        }
    }

    public void updateWithStepValue(String tokens[]){
        int i = getMinRange();
        if(!"*".equals(tokens[0])){
            i = Integer.parseInt(tokens[0]);
        }
        int stepValue = Integer.parseInt(tokens[1]);
        for(;i<=getMaxRange();i=i+stepValue){
           outcome.append(i).append(ParserUtil.DELIMITER_SPACE);
        }
    }

    public String toString(){
        return getOutcome();
    }
}
