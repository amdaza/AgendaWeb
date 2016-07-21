
package agenda.modelo;

import java.sql.Connection;
import local.ApW;
import local.Dbcon;
import local.ErrorDB;

/**
 * <p>T�tulo: DbAgenda</p>
 * <p>Descripci�n: Conexi�n con la base de datos de Agenda</p>
 * <p>Copyright: Copyright (c) curso Java EE</p>
 * @author Alicia Daza
 * @version 1.0
 */
public class DbAgenda {
    	
    private static boolean DS = false;
    private final static String ds = "jdbc/agenda";

    private static String host = null;
    private static String dbname = null;
    private static String user = null;
    private static String pass = null;
    private static String driver = null;

    protected Dbcon dbc = null;

    static {
        host = "192.168.1.161:1521";
        dbname = "curso";
        user = "alum05";
        pass = "alum05";
        driver = "oracle";
    }

    public DbAgenda() {
    }
    
    public static void setDS (boolean b){
        DS = b;
    }

    /**
     * Reutiliza conexion existente o establece conexion.
     * Uso en inicio de transaccion
     */
    public static Dbcon conecta(Dbcon dbc) {
        boolean rc = true;
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
                //ApW.trace(getDbInfo());
            }
        }
        catch (Exception ex) {
            ApW.error("conecta", ex);
            rc = false;
        }
        return dbc;
    }

    /**
     * Devuelve conexion existente.
     */
    protected Connection getConn() {
        if (dbc == null)
            throw new ErrorDB("Error conexi�n DB.");
        return dbc.getConn();
    }

    /**
     * @return
     */
    protected String getDbInfo() {
        if (dbc == null)
            return "No hay conexion";
        return dbc.info();
    }
}
