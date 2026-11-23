package Task04;
class PackingWorker implements Runnable {
 private final int workerId;PackingWorker(int workerId){this.workerId=workerId;}
 public void run(){int parcels=6;try{Thread.sleep(150);}catch(InterruptedException e){Thread.currentThread().interrupt();return;}
  System.out.println("Worker "+workerId+" packed "+parcels+" parcels");}
}
public class PackingLine {
 public static void main(String[]args)throws InterruptedException{Thread[]workers=new Thread[4];
  for(int i=0;i<workers.length;i++){workers[i]=new Thread(new PackingWorker(i+1));workers[i].start();}
  for(Thread t:workers)t.join();System.out.println("Packing line finished");}
}