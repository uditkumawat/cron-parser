package com.udit.kumawat;

/**
 * Main class which calls different parsers
 */
public class CronParser {

    public ExpressionParser minuteParser;
    public ExpressionParser hourParser;
    public ExpressionParser dayOfMonthParser;
    public ExpressionParser monthParser;
    public ExpressionParser dayOfWeekParser;

    public CronParser(){

    }

    public void initParsers(String args[]){
        minuteParser = ExpressionParserFactory.getExpressionParser(ParserUtil.MINUTE,args[0]);
        hourParser = ExpressionParserFactory.getExpressionParser(ParserUtil.HOUR,args[1]);
        dayOfMonthParser = ExpressionParserFactory.getExpressionParser(ParserUtil.DAY_OF_MONTH,args[2]);
        monthParser = ExpressionParserFactory.getExpressionParser(ParserUtil.MONTH,args[3]);
        dayOfWeekParser = ExpressionParserFactory.getExpressionParser(ParserUtil.DAY_OF_WEEK,args[4]);
    }

    public String parse(String args[]){
        initParsers(args);
        minuteParser.parse();
        hourParser.parse();
        dayOfMonthParser.parse();
        monthParser.parse();
        dayOfWeekParser.parse();
        String command = args[5];

        return format(minuteParser.toString(),hourParser.toString(),
                dayOfMonthParser.toString(),monthParser.toString(),dayOfWeekParser.toString(),command);
    }

    public boolean validateLength(String args[]){
        return args.length >=6  ? true:false;
    }

    public String format(String minute,String hour,String dayOfMonth,String month,String dayOfWeek,String command){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-14s","minute")).append(minute).append(ParserUtil.DELIMITER_NEW_LINE);
        sb.append(String.format("%-14s","hour")).append(hour).append(ParserUtil.DELIMITER_NEW_LINE);
        sb.append(String.format("%-14s","day of month")).append(dayOfMonth).append(ParserUtil.DELIMITER_NEW_LINE);
        sb.append(String.format("%-14s","month")).append(month).append(ParserUtil.DELIMITER_NEW_LINE);
        sb.append(String.format("%-14s","day of week")).append(dayOfWeek).append(ParserUtil.DELIMITER_NEW_LINE);
        sb.append(String.format("%-14s","command")).append(command).append(ParserUtil.DELIMITER_NEW_LINE);
        return sb.toString();
    }
}
