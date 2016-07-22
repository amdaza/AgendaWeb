
import local.*;
import agenda.modelo.*;
import java.util.List;

/**
 * <p>
 * Título: </p>
 * <p>
 * Descripción: </p>
 * <p>
 * Copyright: Copyright (c) curso Java EE</p>
 * <p>
 * Empresa: </p>
 *
 * @author sin atribuir
 * @version 1.0
 */
public class TestAgenda {

    static AgendaDao dao = new AgendaDao(false);

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
                System.out.println("5 - Buscar por id");
                System.out.println("6 - Modificar registro");
                System.out.println("7 - Buscar registro con filtros");
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
                        listar();
                        break;
                    case 4:
                        listartodo();
                        break;
                    case 5:
                        buscarId();
                        break;
                    case 6:
                        modificar();
                        break;
                    case 7:
                        buscarFiltro();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opcion invalida");
                        break;
                }
            } while (opcion != 0);

        } catch (Exception ex) {
            ApW.error("TestAgenda", ex);
        } finally {
            dao.close();
            System.out.println("Aplicacion finalizada");
        }
    }

    //
    public static void crear() {
        String user, nombre, email;
        long telef;
        try {
            user = Leer.dato("Usuario ==> ");
            nombre = Leer.dato("Nombre  ==> ");
            telef = Leer.datoLong("Telef.  ==> ");
            if (telef < 0) {
                telef = 0;
            }
            email = Leer.dato("E-mail  ==> ");
            AgendaBean ag = new AgendaBean(user, nombre, telef, email);
            if (dao.saveAgenda(ag) > 0) {
                System.out.println("Registro creado: " + ag);
            } else {
                System.out.println("Error al crear registro");
            }
            continuar();
        } catch (Exception ex) {
            ApW.error("crear", ex);
        }
    }

    //
    public static void borrar() {
        try {
            int id = Leer.datoInt("ID del registro a Borrar ==> ");
            if (dao.deleteAgenda(id) > 0) {
                System.out.println("Registro borrado");
            } else {
                System.out.println("Error al borrar registro");
            }
            continuar();
        } catch (Exception ex) {
            ApW.error("borrar", ex);
        }
    }

    //
    public static void continuar() {
        String r = Leer.dato("\nPulse N para terminar u otra tecla para continuar: ");
        if (r.equalsIgnoreCase("N")) {
            System.out.println("Aplicacion finalizada");
            dao.close();
            System.exit(0);
        }
    }

    private static void listar() {
        String user;
        try {
            user = Leer.dato("Usuario ==> ");
            List<AgendaBean> lista = dao.getAgendaByUserag(user);
            if (lista.size() <= 0) {
                System.out.println("No se ha leido ningun registro");
            }
            for (AgendaBean a : lista) {
                System.out.println(a);
            }
            continuar();
        } catch (Exception ex) {
            ApW.error("listar", ex);
        }
    }

    private static void listartodo() {
        try {
            List<AgendaBean> lista = dao.getAll();
            if (lista.size() <= 0) {
                System.out.println("No se ha leido ningun registro");
            }
            for (AgendaBean a : lista) {
                System.out.println(a);
            }
            continuar();
        } catch (Exception ex) {
            ApW.error("listarTodo", ex);
        }
    }

    private static void buscarId() {

        try {
            int id = Leer.datoInt("ID del registro a buscar ==> ");
            AgendaBean ag = dao.getAgenda(id);
            if (ag.isEmpry()) {
                System.out.println("No se ha leido ningun registro");
            } else {
                System.out.println(ag);
            }

            continuar();
        } catch (Exception ex) {
            ApW.error("buscarId", ex);
        }
    }

    private static void modificar() {
        String user, nombre, email;
        long telef;
        try {
            int id = Leer.datoInt("ID del registro a modificar ==> ");
            AgendaBean ag = dao.getAgenda(id);
            if (ag.isEmpry()) {
                System.out.println("No se ha leido ningun registro");
            } else {
                System.out.println("Introduce los datos que quieras modificar. Si uno no lo quieres modificar pulta ENTER: ");
                user = Leer.dato("Usuario ==> ");
                if (!user.equals("")) {
                    ag.setUserag(user);
                }
                nombre = Leer.dato("Nombre  ==> ");
                if (!nombre.equals("")) {
                    ag.setNombre(nombre);
                }
                telef = Leer.datoLong("Telef.  ==> ");
                if (telef >= 0) {
                    ag.setTelefono(telef);
                }
                email = Leer.dato("E-mail  ==> ");
                if (!email.equals("")) {
                    ag.setEmail(email);
                }
                if (dao.updateAgenda(ag) > 0) {
                    System.out.println("Registro modificado: " + ag);
                } else {
                    System.out.println("Error al modificar registro");
                }
            }
            continuar();
        } catch (Exception ex) {
            ApW.error("modificar", ex);
        }
    }

    private static void buscarFiltro() {
        String user, nombre, email;
        long telef;
        try {
            AgendaBean ag= new AgendaBean();
            System.out.println("Introduce los datos por los que quieras buscar. Si no lo quieres introducir pulta ENTER: ");
            user = Leer.dato("Usuario ==> ");
            if (!user.equals("")) {
                ag.setUserag(user);
            }
            nombre = Leer.dato("Nombre (Se buscara que contenga la cadena)  ==> ");
            if (!nombre.equals("")) {
                ag.setNombre(nombre);
            }
            telef = Leer.datoLong("Telef.  ==> ");
            if (telef >= 0) {
                ag.setTelefono(telef);
            }
            email = Leer.dato("E-mail  ==> ");
            if (!email.equals("")) {
                ag.setEmail(email);
            }
            List<AgendaBean> lista = dao.getByFiltro(ag);
            if (lista.size() <= 0) {
                System.out.println("No se ha leido ningun registro");
            }
            for (AgendaBean a : lista) {
                System.out.println(a);
            }
            continuar();
        } catch (Exception ex) {
            ApW.error("modificar", ex);
        }
    }
}
