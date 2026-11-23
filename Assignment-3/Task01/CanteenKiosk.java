package Task01;
import java.util.*;
class LowBalanceException extends Exception { LowBalanceException(String msg){super(msg);} }
public class CanteenKiosk {
 static void charge(double balance,double price)throws LowBalanceException{
  if(price<=0)throw new IllegalArgumentException("Price must be above zero");
  if(price>balance)throw new LowBalanceException("Short by Rs. "+(price-balance));
  System.out.println("Meal paid. New balance: Rs. "+(balance-price));
 }
 public static void main(String[]args){Scanner in=new Scanner(System.in);double balance=250;
  try{System.out.print("Card number: ");String card=in.next();System.out.print("Meal price: ");
   if(!in.hasNextDouble())throw new InputMismatchException();double price=in.nextDouble();charge(balance,price);
  }catch(InputMismatchException e){System.out.println("Error: price must be a number.");}
   catch(IllegalArgumentException e){System.out.println("Error: "+e.getMessage());}
   catch(LowBalanceException e){System.out.println("Error: "+e.getMessage());}
  finally{System.out.println("Kiosk session closed.");in.close();}
 }
}