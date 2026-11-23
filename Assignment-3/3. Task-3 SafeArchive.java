package Task03;
import java.io.*;
public class SafeArchive {
 public static void main(String[]args){
  try(BufferedReader reader=new BufferedReader(new FileReader("server.log"));BufferedWriter writer=new BufferedWriter(new FileWriter("archive.log"))){
   String entry;while((entry=reader.readLine())!=null){writer.write(entry);writer.newLine();}System.out.println("Archive created");
  }catch(IOException e){System.out.println("I/O problem: "+e.getMessage());}
 }
}