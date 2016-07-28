import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


public class FileUtil {
    
    private static int max_length;
    private static String folder_name;
    private static String thefilename;
    private static String filename;
    private static String suffix;
    private static String default_suffix = "txt";
    private static String date_format;
    private int number_of_roundrobin_files;
    private Type type;
    public static enum Type {
        ROUNDROBIN, DAILY, WEEKLY, MONTHLY,YEARLY
    }
    public FileUtil(String filename, String folder_name, Type type) {
        super();
        this.filename = filename;
        this.folder_name = folder_name;
        this.date_format = "dd-MM-yyyy";
        this.type = type;
        this.max_length = 2000;
        number_of_roundrobin_files = 2;
        mainFileName(type);
    }
    public FileUtil(String filename, String folder_name, Type type, String date_format) {
        super();
        this.filename = filename;
        this.folder_name = folder_name;
        this.date_format = date_format;
        this.type = type;
        mainFileName(type);
    }
    public FileUtil(String filename, String folder_name, Type type,int max_length,int number_of_roundrobin_files) {
        super();
        this.max_length = max_length;
        this.filename = filename;
        this.folder_name = folder_name;
        this.type = type;
        this.number_of_roundrobin_files = number_of_roundrobin_files;
        mainFileName(type);
    }
    private void mainFileName(Type type){
        switch (type) {
            case ROUNDROBIN:
                chooseFilename(number_of_roundrobin_files);
                break;
            case DAILY: case WEEKLY: case MONTHLY: case YEARLY:
                chooseFilename();
                break;
        }
    }
    private  void chooseFilename()
    {
        File root = new File("", folder_name);
        if (!root.exists()) {root.mkdirs();}
        if(filename.indexOf('.')>0){
            suffix=filename.substring(filename.lastIndexOf('.')+1);
            filename=filename.substring(0, filename.lastIndexOf('.'));
        }
        else
        {
            suffix=default_suffix;
        }
        File f = null;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(this.date_format);//eg "dd.MM.yyyy"
        switch (this.type) {
            case DAILY:
                f = new File(root, sdf.format(cal.getTime())+filename+".txt");
                break;
            case WEEKLY:
                cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
                f = new File(root, sdf.format(cal.getTime())+filename+".txt");
                break;
            case MONTHLY:
                f = new File(root, "01."+String.format("%02d", (cal.get(Calendar.MONTH))+1)+"."+cal.get(Calendar.YEAR)+filename+".txt");
                break;
            case YEARLY:
                f = new File(root, "0101"+cal.get(Calendar.YEAR)+filename+".txt");
                break;
        }
        
        thefilename = f.toString();
        
    }
    private static void chooseFilename(int number)
    {
        File root = new File("", folder_name);
        if (!root.exists()) {root.mkdirs();}
        if(filename.indexOf('.')>0){
            suffix=filename.substring(filename.lastIndexOf('.')+1);
            filename=filename.substring(0, filename.lastIndexOf('.'));
        }
        else
        {
            suffix=default_suffix;
        }
        if(number==1){
            File f = new File(folder_name,filename+"."+suffix);
            thefilename=f.toString();
            return;
        }
        List<File> fileList = new ArrayList<>();
        for(int i=0;i<number;i++)
        {
            File f = new File(folder_name,filename+(i+1)+"."+suffix);
            fileList.add(f);
        }
        
        int[] lines = new int[number];
        try {
            for(int i=0;i<number;i++)
            {
                if(fileList.get(i).exists()) {
                    BufferedReader reader = new BufferedReader(new FileReader(fileList.get(i)));
                    while (reader.readLine() != null) lines[i]++;
                    reader.close();
                }
            }
            
            
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        boolean found=false;
        if(lines[0] < max_length )
        {
            thefilename = fileList.get(0).toString();
            found=true;
        }
        else {
            for(int i=0;i<number-1;i++){
                if(lines[i] > max_length && lines[i+1] < max_length )
                {
                    thefilename = fileList.get(i+1).toString();
                    found=true;
                    break;
                }
                
            }
        }
        
        if(!found)
        {
            List<Long> lastmodifieds = new ArrayList<>();
            for(int i=0;i<number;i++){
                lastmodifieds.add(fileList.get(i).lastModified());
            }
            
            int index = lastmodifieds.indexOf(Collections.min(lastmodifieds));
            thefilename =  fileList.get(index).toString();
            fileList.get(index).delete();
            
            
        }
    }
    
    public void writeToFile(String text){
        try {	       
            File gpxfile = new File(thefilename);
            BufferedWriter bW;
            bW = new BufferedWriter(new FileWriter(gpxfile, true));
            bW.write(text);
            bW.newLine();
            bW.flush();
            bW.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
