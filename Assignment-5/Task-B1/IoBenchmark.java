import java.io.*;import java.nio.file.*;
public class IoBenchmark{
 static long countByteByByte(Path p)throws Exception{long n=0;try(InputStream in=Files.newInputStream(p)){int b;while((b=in.read())!=-1)if(b=='\\n')n++;}return n;}
 static long countBuffered(Path p)throws Exception{long n=0;try(InputStream in=new BufferedInputStream(Files.newInputStream(p),8192)){int b;while((b=in.read())!=-1)if(b=='\\n')n++;}return n;}
 public static void main(String[]a)throws Exception{Path p=Path.of(a.length>0?a[0]:"attendance.log");if(!Files.exists(p)){try(BufferedWriter w=Files.newBufferedWriter(p)){for(int i=0;i<100000;i++){w.write("attendance record "+i);w.newLine();}}}for(int i=0;i<2;i++){long t=System.nanoTime(),n=countByteByByte(p);System.out.printf("Byte reads: %d lines, %.2f ms%n",n,(System.nanoTime()-t)/1e6);t=System.nanoTime();long m=countBuffered(p);System.out.printf("Buffered: %d lines, %.2f ms%n",m,(System.nanoTime()-t)/1e6);}}
}
