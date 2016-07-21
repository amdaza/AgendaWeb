
package agenda.modelo.dao;

import agenda.modelo.AgendaBean;
import java.util.List;

/**
 * <p>Título: IAgendaDAO</p>
 * <p>Descripción: Interfaz para AgendaDAO</p>
 * <p>Copyright: Copyright (c) curso Java EE</p>
 * @author Alicia Daza
 * @version 1.0
 */
public interface IAgendaDAO {
    public int crear(AgendaBean agenda);
    public int borrar(int id);
    public int actualizar(AgendaBean agenda);
    public AgendaBean leer(int id);
    public List<AgendaBean> leerTodos(String userag);
    public List<AgendaBean> buscar(String busqueda);
}
