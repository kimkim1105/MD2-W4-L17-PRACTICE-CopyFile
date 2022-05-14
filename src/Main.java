import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        File source = new File("source.txt");
        BufferedWriter file = new BufferedWriter(new FileWriter(source));
        file.write("13265465413");
        file.newLine();
        file.write("656865666");
        file.newLine();
        file.write("8795465132");
        file.close();
        File dest = new File("dest.txt");
        try {
            copyFileUsingJava7Files(source,dest);
            System.out.println("Copy completed");
        }catch (IOException ioe){
            System.out.printf("Can't copy that file");
            System.out.printf(ioe.getMessage());
        }
    }
    private static void copyFileUsingJava7Files(File source, File dest) throws IOException{
        Files.copy(source.toPath(),dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    private static void copyFileUsingStream(File source,File dest) throws IOException{
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length= is.read(buffer))>0){
                os.write(buffer,0,length);
            }
        }finally {
            is.close();
            os.close();
        }
    }
}
