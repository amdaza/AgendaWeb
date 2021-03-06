package agenda;

import agenda.modelo.AgendaBean;
import agenda.modelo.dao.AgendaDAO;
import local.*;
import java.util.List;
import java.util.Iterator;

/**
 * <p>T�tulo: Test Agenda</p>
 * <p>Descripci�n: Pruebas para el funcionamiento de AgendaBean</p>
 * <p>Copyright: Copyright (c) curso Java EE</p>
 * @author Alicia Daza
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
                System.out.println("5 - Modificar");
                System.out.println("6 - Buscar");
                System.out.println("0 - Terminar");
                opcion = Leer.datoInt("Opcion ==> ");
                switch (opcion) {
                    case 1:
                            crear();
                            break;
                    case 2:
                            borrar();
                            break;
                    case 3:
                            leer();
                            break;
                    case 4:
                            listarTodos();
                            break;
                    case 5:
                            modificar();
                            break;
                    case 6:
                            buscar();
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

    
    public static void crear () {
        String user, nombre, email;
        long   telef;
        try {
            user =   Leer.dato(    "Usuario ==> ");
            nombre = Leer.dato(    "Nombre ==> ");
            telef =  Leer.datoLong("    Telef. ==> ");
            
            // Verificar tel�fono
            if (telef < 0)
                telef=0;
            
            email =  Leer.dato(    "E-mail ==> ");
            AgendaBean ag = new AgendaBean(user, nombre,telef,email);
            int res = ao.crear(ag);
            if (res>0)
                System.out.println("Registro creado: "+res);
            else
                System.out.println("Error al crear registro");
            continuar();
        }
        catch (Exception ex) {
            ApW.error("crear",ex);
        }
    }

    public static void leer () {
        String idString;
        try {
            idString =   Leer.dato(    "Id  ==> ");
            int id = Integer.parseInt(idString);
            AgendaBean agenda = ao.leer(id);
            if (agenda.getId() > 0)
                System.out.println("Registro le�do: "+agenda);
            else
                System.out.println("Error al crear registro");
            continuar();  
        }
        catch (Exception ex) {
            ApW.error("leer",ex);
        }
    }
    
    public static void borrar () {
        String idString;
        int res = 0;
        try {
            idString =   Leer.dato(    "Id  ==> ");
            int id = Integer.parseInt(idString);
            res = ao.borrar(id);
            if (res > 0)
                System.out.println("Registro borrado: "+res);
            else
                System.out.println("Error al crear registro");
            continuar();  
        }
        catch (Exception ex) {
            ApW.error("borrar",ex);
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
            ApW.error("listarTodos",ex);
        }
    }
    
    public static void modificar () {
        String user, nombre, email;
        int id;
        long   telef;
        try {
            id =   Leer.datoInt(    "Id ==> ");
            user =   Leer.dato(    "Usuario ==> ");
            nombre = Leer.dato(    "Nombre ==> ");
            telef =  Leer.datoLong("Telef. ==> ");
            
            // Verificar tel�fono
            if (telef < 0)
                telef=0;
            
            email =  Leer.dato(    "E-mail ==> ");
            AgendaBean ag = new AgendaBean(id, user, nombre,telef,email);
            int res = ao.actualizar(ag);
            if (res>0)
                System.out.println("Registro modificado: "+res);
            else
                System.out.println("Error al modificar registro");
            continuar();
        }
        catch (Exception ex) {
            ApW.error("modificar",ex);
        }
    }
    
    public static void buscar () {
        String busqueda;
        try {
            busqueda =   Leer.dato(    "String de busqueda  ==> ");
            List<AgendaBean> lista = ao.buscar(busqueda);
            Iterator it = lista.iterator();
            while (it.hasNext()){
                System.out.println("Resultados: "+it.next());
            }         
            continuar();
        }
        catch (Exception ex) {
            ApW.error("buscar",ex);
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
