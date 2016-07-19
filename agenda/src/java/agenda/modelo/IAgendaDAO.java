/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.modelo;

import agenda.modelo.AgendaBean;
import java.util.List;

/**
 *
 * @author Alicia Daza
 */
public interface IAgendaDAO {
    
    public AgendaBean leer(int id);
    public List<AgendaBean> leerTodos(String userag);
}
