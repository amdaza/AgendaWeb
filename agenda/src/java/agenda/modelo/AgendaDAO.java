/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.modelo;

import agenda.modelo.AgendaBean;
import agenda.modelo.DbAgenda;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import local.ApW;
import local.Dbcon;

/**
 *
 * @author Alicia Daza
 */
public class AgendaDAO implements IAgendaDAO {

    @Override
    public AgendaBean leer(int id) {
        String sql;
        AgendaBean agenda= new AgendaBean();
        
        try {
            DbAgenda dbAgenda = new DbAgenda();
            
            Dbcon dbc = dbAgenda.getDbc();
            Connection conn = dbc.getConn();
            Statement stm = conn.createStatement();
            sql =
                "SELECT id, userag, nombre, telefono, email "
                    + "FROM agenda "
                    + "WHERE id_prog="
                    + id
                    + dbc.endsql();
            ApW.trace(sql);
            ResultSet rs = stm.executeQuery(sql);
            if (rs.next()) {
                agenda.setId(Integer.parseInt(rs.getString(1)));
                agenda.setUserag(rs.getString(2));
                agenda.setNombre(rs.getString(3));
                agenda.setTelefono(Long.parseLong(rs.getString(4)));
                agenda.setEmail(rs.getString(5));
            }
            dbc.commit();
            
        } catch (Exception ex) {
            ApW.error("ProgramaBean.leer", ex);
        }
            return agenda;
    }

    @Override
    public List<AgendaBean> leerTodos(String userag) {
        String sql;
        List<AgendaBean> lista = new ArrayList<AgendaBean>();
        try {
            Dbcon dbc = (new DbAgenda()).getDbc();
            Connection conn = dbc.getConn();
            Statement stm = conn.createStatement();
            sql =
                "SELECT id, userag, nombre, telefono, email "
                    + "FROM agenda "
                    + "WHERE userag ="
                    + userag
                    + dbc.endsql();
            ApW.trace(sql);
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                AgendaBean agenda = new AgendaBean(rs.getInt(1), rs.getString(2),
                        rs.getString(3), Long.parseLong(rs.getString(4)), rs.getString(5));
                lista.add(agenda);
            }
            dbc.commit();
            dbc.close();
        } catch (Exception ex) {
            ApW.error("ProgramaBean.leerAll", ex);
        }
        return lista;
    }
    
}
