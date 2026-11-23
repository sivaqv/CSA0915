import java.time.LocalDate;
public record BusPass(String passId,String registerNo,String name,String routeNo,String boardingStop,LocalDate validUntil,long amountPaise){
 public static class DamagedRecordException extends Exception{public DamagedRecordException(String m){super(m);}}
 public static BusPass parse(String line)throws DamagedRecordException{
  String[] f=line.split("\\|",-1);if(f.length!=7)throw new DamagedRecordException("expected 7 fields, found "+f.length);
  try{if(!f[0].matches("P\\d{4,7}"))throw new IllegalArgumentException("bad pass ID");if(!f[1].matches("\\d{9}"))throw new IllegalArgumentException("register number is not 9 digits");if(!f[2].matches("[A-Za-z .]{2,80}"))throw new IllegalArgumentException("bad name");if(!f[3].matches("R\\d+"))throw new IllegalArgumentException("bad route");if(f[4].isBlank()||f[4].contains("|"))throw new IllegalArgumentException("bad boarding stop");LocalDate d=LocalDate.parse(f[5]);long a=Long.parseLong(f[6]);if(a<=0)throw new IllegalArgumentException("amount is not positive");return new BusPass(f[0],f[1],f[2],f[3],f[4],d,a);}catch(RuntimeException e){throw new DamagedRecordException(e.getMessage());}
 }
 public String toLine(){return String.join("|",passId,registerNo,name,routeNo,boardingStop,validUntil.toString(),Long.toString(amountPaise));}
}
