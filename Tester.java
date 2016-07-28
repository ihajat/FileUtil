public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
		FileUtil logger = new FileUtil("test.txt", "/tmp/Development/testfolder", FileUtil.Type.ROUNDROBIN,20,2);
		for(int i=0;i<15;i++)logger.writeToFile("this is line: "+i);
	}
    
}
