import java.sql.*;
public class LoaderBenchmark{
 static final String URL="jdbc:mariadb://localhost:3306/csa09_test",USER="root",PASS="change_me";
 static Connection connect()throws SQLException{return DriverManager.getConnection(URL,USER,PASS);}
 static void clear()throws SQLException{try(Connection c=connect();Statement s=c.createStatement()){s.executeUpdate("DELETE FROM bus_pass");}}
 static String[]row(int i){return new String[]{String.format("P%04d",i),"192325001","Test Student","R12","Poonamallee","2027-03-31","450000"};}
 static void statement(int count)throws SQLException{try(Connection c=connect();Statement s=c.createStatement()){for(int i=1;i<=count;i++){String[]r=row(i);s.executeUpdate("INSERT INTO bus_pass VALUES('"+String.join("','",r)+"')");}}}
 static void prepared(int count)throws SQLException{try(Connection c=connect();PreparedStatement p=c.prepareStatement("INSERT INTO bus_pass VALUES(?,?,?,?,?,?,?)")){for(int i=1;i<=count;i++){bind(p,row(i));p.executeUpdate();}}}
 static void batch(int count,int size,boolean transaction)throws SQLException{try(Connection c=connect();PreparedStatement p=c.prepareStatement("INSERT INTO bus_pass VALUES(?,?,?,?,?,?,?)")){if(transaction)c.setAutoCommit(false);try{for(int i=1;i<=count;i++){bind(p,row(i));p.addBatch();if(i%size==0)p.executeBatch();}p.executeBatch();if(transaction)c.commit();}catch(SQLException e){if(transaction)c.rollback();throw e;}}}
 static void bind(PreparedStatement p,String[]r)throws SQLException{for(int i=0;i<7;i++)p.setString(i+1,r[i]);}
 static long rows()throws SQLException{try(Connection c=connect();Statement s=c.createStatement();ResultSet r=s.executeQuery("SELECT COUNT(*) FROM bus_pass")){r.next();return r.getLong(1);}}
 static void run(String name,SQLWork w)throws Exception{clear();long t=System.nanoTime();w.run();double ms=(System.nanoTime()-t)/1e6;System.out.printf("%s: %.2f ms; rows verified=%d%n",name,ms,rows());}
 interface SQLWork{void run()throws Exception;}
 public static void main(String[]a)throws Exception{int n=a.length>0?Integer.parseInt(a[0]):1000;run("Statement",()->statement(n));run("PreparedStatement row-by-row",()->prepared(n));run("PreparedStatement batch",()->batch(n,1000,true));}
}
