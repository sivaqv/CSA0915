package Task06;
public class OrderCounter {
 private final int[]slots=new int[4];private int filled=0,head=0,tail=0;
 public synchronized void put(int slip)throws InterruptedException{while(filled==slots.length)wait();slots[tail]=slip;tail=(tail+1)%slots.length;filled++;notifyAll();}
 public synchronized int take()throws InterruptedException{while(filled==0)wait();int slip=slots[head];head=(head+1)%slots.length;filled--;notifyAll();return slip;}
 public static void main(String[]args)throws InterruptedException{OrderCounter counter=new OrderCounter();int[]collected=new int[3];
  Thread chef=new Thread(()->{try{for(int s=1;s<=24;s++)counter.put(s);}catch(InterruptedException e){Thread.currentThread().interrupt();}});
  Thread[]waiters=new Thread[3];for(int w=0;w<3;w++){final int id=w;waiters[w]=new Thread(()->{try{for(int k=0;k<8;k++)collected[id]+=counter.take();}catch(InterruptedException e){Thread.currentThread().interrupt();}});}
  chef.start();for(Thread t:waiters)t.start();chef.join();for(Thread t:waiters)t.join();
  System.out.println("Sum of slips collected: "+(collected[0]+collected[1]+collected[2])+" (expected 300)");}
}