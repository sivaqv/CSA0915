package Task02;
import java.io.IOException;
class CourseException extends Exception {CourseException(String m){super(m);}CourseException(String m,Throwable c){super(m,c);}}
class RecordStorageException extends CourseException {RecordStorageException(String m,Throwable c){super(m,c);}}
class RegistrationDao {
 void saveEnrolment(String courseCode,int recordBytes)throws RecordStorageException{
  try{if(recordBytes>2048)throw new IOException("Record exceeds column limit");System.out.println("Enrolment saved for "+courseCode);}
  catch(IOException e){throw new RecordStorageException("Could not save enrolment for "+courseCode,e);}
 }
}
public class PortalDemo {
 public static void main(String[]args){RegistrationDao dao=new RegistrationDao();try{dao.saveEnrolment("CSA0915",900);dao.saveEnrolment("CSA0916",5000);}
  catch(CourseException e){System.out.println("Error: "+e.getMessage());System.out.println("Cause: "+e.getCause().getMessage());}}
}