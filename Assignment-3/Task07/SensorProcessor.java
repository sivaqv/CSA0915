package Task07;
import java.nio.file.*;import java.util.*;import java.util.concurrent.*;
public class SensorProcessor {
 static class Reading{final String file;final double average;Reading(String f,double a){file=f;average=a;}}
 static Reading analyse(Path path)throws Exception{List<String>lines=Files.readAllLines(path);if(lines.isEmpty())throw new IllegalArgumentException("Empty file");double sum=0;for(String line:lines)sum+=Double.parseDouble(line.trim());return new Reading(path.getFileName().toString(),sum/lines.size());}
 static void runPool(int poolSize,int fileCount)throws InterruptedException{
  ExecutorService pool=Executors.newFixedThreadPool(poolSize);List<Path>files=new ArrayList<>();List<Future<Reading>>futures=new ArrayList<>();
  for(int i=1;i<=fileCount;i++)files.add(Paths.get("sensors","sensor"+i+".txt"));long start=System.nanoTime();
  for(Path file:files)futures.add(pool.submit(()->analyse(file)));int success=0,failure=0;
  for(int i=0;i<futures.size();i++){try{futures.get(i).get();success++;}catch(ExecutionException e){failure++;System.out.println("Failed: "+files.get(i)+" - "+e.getCause().getMessage());}}
  pool.shutdown();pool.awaitTermination(1,TimeUnit.MINUTES);long elapsed=(System.nanoTime()-start)/1_000_000;
  System.out.printf("Pool size: %d | Success: %d | Failure: %d | Time: %d ms%n",poolSize,success,failure,elapsed);
 }
 public static void main(String[]args)throws InterruptedException{int count=args.length>0?Integer.parseInt(args[0]):8000;for(int p:new int[]{1,2,4,8})runPool(p,count);}
}