UtilWrite is a simple mobile writer that provides the following features:
1. Round robin, allows configuration of max no of lines and no of files for the round robin.
2. Daily files, which creates a new log file each day. The date format is configurable.
3. Weekly files, which creates a new log file each day. The date format is configurable.
4. Monthly files, which creates a new log file each day.The date format is configurable. 
5. Yearly files, which creates a new log file each day. The date format is configurable. 
Usage
Round Robin
Logger logger = new Logger(“test.txt”, “/tmp/Development/testfolder", Logger.Type.ROUNDROBIN,20,3);
logger.writeToFile("this is a line”);
Daily Files
Logger logger = new Logger(“test.txt”, “/tmp/testfolder", Logger.Type.DAILY,"dd.MM.yyyy");
Weekly Files
Logger logger = new Logger(“test.txt”, “/tmp/testfolder", Logger.Type.WEEKLY,"dd.MM.yyyy");
Monthly Files
Logger logger = new Logger(“test.txt”, “/tmp/testfolder", Logger.Type.MONTHLY,"dd.MM.yyyy");
Yearly Files
Logger logger = new Logger(“test.txt”, “/tmp/testfolder", Logger.Type.YEARLY,"dd.MM.yyyy");
