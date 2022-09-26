package com.udit.kumawat;

/**
 * Main class which takes cron expression as command line arguments and parse it.
 * @Author Udit Kumawat
 */
public class Main {

    public static void main(String[] args) {

        CronParser parser = new CronParser();
        Main obj = new Main();

        if(args.length==1 && "RunTests".equals(args[0])){
            obj.runTests(parser);
            return;
        }
        else if(!parser.validateLength(args)){
                System.out.println("Number of arguments is less than required, please validate");
                return;
        }

        String str = parser.parse(args);
        System.out.println(str);
    }

    private void runTests(CronParser parser){
        String cronJobs[] = {"1,2,3,4 * * * * /usr/bin/find",
                "1-5 * * * * /usr/bin/find",
                "4-3 * * * * /usr/bin/find",
                "6-5 * * * * /usr/bin/find",
                "* * * * * /usr/bin/find",
                "*/15 * * * * /usr/bin/find",
                "6/15 * * * * /usr/bin/find"};

        for(String job:cronJobs){
            try {
                System.out.println("Parsing job : " + job);
                System.out.println(parser.parse(job.split(" ")));
            }catch(Exception ex){
                System.out.println("Exception occured for : "+job+ " Exception : "+ex);
            }
        }
    }
}
