package agenda;

import agenda.modelo.AgendaBean;
import agenda.modelo.dao.AgendaDAO;
import local.*;
import java.util.List;
import java.util.Iterator;

/**
 * <p>Título: </p>
 * <p>Descripción: </p>
 * <p>Copyright: Copyright (c) curso Java EE</p>
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */

public class TestAgenda {
    
    static AgendaDAO ao = new AgendaDAO(false);
    
    public static void main(String[] args) {
        int opcion;
        try {
            do {
                System.out.println("AGENDA v1.0");
                System.out.println("===========");
                System.out.println("1 - Crear registro");
                System.out.println("2 - Borrar registro");
                System.out.println("3 - Listar registro");
                System.out.println("4 - Listar todo");
                System.out.println("0 - Terminar");
                opcion = Leer.datoInt("Opcion ==> ");
                switch (opcion) {
                    case 1:
                           // crear();
                            break;
                    case 2:
                         //   borrar();
                            break;
                    case 3:
                            leer();
                            break;
                    case 4:
                            listarTodos();
                            break;
                    case 0:
                            break;
                    default:
                            System.out.println("Opcion invalida");
                            break;
                }
            } while (opcion != 0);

        }
        catch (Exception ex) {
            ApW.error("TestAgenda",ex);
        }
        finally {
            System.out.println("Aplicacion finalizada");
        }
    }

    /*
    public static void crear () {
        String user, nombre, email;
        long   telef;
        try {
            user =   Leer.dato(    "Usuario ==> ");
            nombre = Leer.dato(    "Nombre ==> ");
            telef =  Leer.datoLong("Telef. ==> ");
            if (telef < 0)
                telef=0;
            email =  Leer.dato(    "E-mail ==> ");
            AgendaBean ag = new AgendaBean(user, nombre,telef,email);
            if (ao.crear(ag))
                System.out.println("Registro creado: "+ag);
            else
                System.out.println("Error al crear registro");
            continuar();
        }
        catch (Exception ex) {
            ApW.error("crear",ex);
        }
    }
*/
    public static void leer () {
        String idString;
        try {
            idString =   Leer.dato(    "Id  ==> ");
            int id = Integer.parseInt(idString);
            AgendaBean agenda = ao.leer(id);
            if (agenda.getId() > 0)
                System.out.println("Registro leído: "+agenda);
            else
                System.out.println("Error al crear registro");
            continuar();  
        }
        catch (Exception ex) {
            ApW.error("crear",ex);
        }
    }
    
    public static void listarTodos () {
        String user;
        try {
            user = Leer.dato(    "Usuario ==> ");
            List<AgendaBean> lista = ao.leerTodos(user);
            Iterator it = lista.iterator();
            while (it.hasNext()){
                System.out.println("Registros: "+it.next());
            }         
            continuar();
        }
        catch (Exception ex) {
            ApW.error("crear",ex);
        }
    }
    
    //
    public static void continuar () {
        String r = Leer.dato("\nPulse N para terminar u otra tecla para continuar: ");
        if (r.equalsIgnoreCase("N")) {
            System.out.println("Aplicacion finalizada");
            System.exit(0);
        }
    }
}
