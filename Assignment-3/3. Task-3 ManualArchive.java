package Task03;
import java.io.*;
public class ManualArchive {
 public static void main(String[]args){BufferedReader reader=null;BufferedWriter writer=null;
  try{reader=new BufferedReader(new FileReader("server.log"));writer=new BufferedWriter(new FileWriter("archive.log"));String entry;
   while((entry=reader.readLine())!=null){writer.write(entry);writer.newLine();}System.out.println("Archive created");
  }catch(IOException e){System.out.println("I/O problem: "+e.getMessage());}
  finally{try{if(reader!=null)reader.close();}catch(IOException e){System.out.println("Reader not closed");}
   try{if(writer!=null)writer.close();}catch(IOException e){System.out.println("Writer not closed");}}
 }
}