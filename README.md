FileUtil is a simple mobile writer that provides the following features:

1. Round robin, allows configuration of max no of lines and no of files for the round robin.
2. Daily files, which creates a new log file each day. The date format is configurable.
3. Weekly files, which creates a new log file each day. The date format is configurable.
4. Monthly files, which creates a new log file each day.The date format is configurable. 
5. Yearly files, which creates a new log file each day. The date format is configurable. 
6. 
Usage

Round Robin
FileUtil logger = new FileUtil(“test.txt”, “/tmp/Development/testfolder", FileUtil.Type.ROUNDROBIN,20,3);
logger.writeToFile("this is a line”);

Daily Files
FileUtil logger = new FileUtil(“test.txt”, “/tmp/testfolder", FileUtil.Type.DAILY,"dd.MM.yyyy");

Weekly Files
FileUtil logger = new FileUtil(“test.txt”, “/tmp/testfolder", FileUtil.Type.WEEKLY,"dd.MM.yyyy");

Monthly Files
FileUtil logger = new FileUtil(“test.txt”, “/tmp/testfolder", FileUtil.Type.MONTHLY,"dd.MM.yyyy");

Yearly Files
FileUtil logger = new FileUtil(“test.txt”, “/tmp/testfolder", FileUtil.Type.YEARLY,"dd.MM.yyyy");
