import java.sql.*;
public class LoginDemo{
 static final String URL="jdbc:mariadb://localhost:3306/csa09_test",USER="root",PASS="change_me";
 static String unsafeLogin(String u,String p)throws SQLException{String sql="SELECT username,role FROM app_user WHERE username='"+u+"' AND password='"+p+"'";try(Connection c=DriverManager.getConnection(URL,USER,PASS);Statement s=c.createStatement();ResultSet r=s.executeQuery(sql)){return r.next()?r.getString("username")+"/"+r.getString("role"):null;}}
 static String safeLogin(String u,String p)throws SQLException{String sql="SELECT username,role FROM app_user WHERE username=? AND password=?";try(Connection c=DriverManager.getConnection(URL,USER,PASS);PreparedStatement s=c.prepareStatement(sql)){s.setString(1,u);s.setString(2,p);try(ResultSet r=s.executeQuery()){return r.next()?r.getString("username")+"/"+r.getString("role"):null;}}}
 public static void main(String[]a)throws Exception{String[][]tests={{"vignesh","java123"},{"vignesh","wrong"},{"unknown","x"},{"vignesh' -- ","x"},{"' OR '1'='1","' OR '1'='1"},{"admin' -- ","x"}};for(String[]t:tests){System.out.printf("%-22s unsafe=%-18s safe=%s%n",t[0],unsafeLogin(t[0],t[1]),safeLogin(t[0],t[1]));}}
}
