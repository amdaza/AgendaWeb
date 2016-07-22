package agenda.modelo;

import local.*;
import java.sql.*;

/**
 * @author
 *
 */

public class DbAgenda {
	
    private static boolean DS = true;
    private final static String ds = "jdbc/agenda";

    private static String host = null;
    private static String dbname = null;
    private static String user = null;
    private static String pass = null;
    private static String driver = null;

    static {
        host = "192.168.1.88:1521";
        dbname = "curso";
        user = "alum05";
        pass = "alum05";
        driver = "oracle";
    }

   public static void setDS (boolean b) {
        DS=b;
   }

    /**
     * Reutiliza conexion existente o establece conexion.
     * Uso en inicio de transaccion
     */
  public static Dbcon conecta(Dbcon dbc) {
    try {
      if (dbc != null) {
          if (dbc.getConn() == null)
              dbc = null;
      }
      if (dbc == null) {
          if (DS)
              dbc = new Dbcon(ds);
          else
              dbc = new Dbcon(driver, host, dbname, user, pass);
      }
    } catch (Exception e) {
      ApW.error(e);
      dbc = null;
    }
    return dbc;
  }

}
