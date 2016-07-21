/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.modelo.dao;

import agenda.modelo.AgendaBean;
import java.util.List;

/**
 *
 * @author Alicia Daza
 */
public interface IAgendaDAO {
    public int crear(AgendaBean agenda);
    public int borrar(int id);
    public int actualizar(AgendaBean agenda);
    public AgendaBean leer(int id);
    public List<AgendaBean> leerTodos(String userag);
    public List<AgendaBean> buscar(String busqueda);
}
