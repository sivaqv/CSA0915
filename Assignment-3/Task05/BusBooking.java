package Task05;
public class BusBooking {
 private final boolean[]reserved;private int seatsSold=0;
 BusBooking(int seats){reserved=new boolean[seats];}
 public synchronized void reserve(int seat){if(!reserved[seat]){reserved[seat]=true;seatsSold++;}}
 public static void main(String[]args)throws InterruptedException{BusBooking bus=new BusBooking(12);Thread[]passengers=new Thread[120];
  for(int i=0;i<passengers.length;i++){final int seat=i%12;passengers[i]=new Thread(()->bus.reserve(seat));passengers[i].start();}
  for(Thread t:passengers)t.join();System.out.println("Seats sold: "+bus.seatsSold);}
}