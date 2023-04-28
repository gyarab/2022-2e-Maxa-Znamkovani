package connectivity;
import java.sql.Connection;
import java.sql.DriverManager;


//SPOJENI S MYSQL DATABAZI
public class ConnectionClass {
    public Connection connection;
    public Connection getConnection(){

        String dbName= "znamkovani";
        String userName="root";
        String password="root";
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/znamkovani", "root", "root");
      }catch (Exception e){
          e.printStackTrace();
      }
        return connection;
    }
}
