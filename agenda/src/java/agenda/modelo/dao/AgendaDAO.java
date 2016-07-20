/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.modelo.dao;

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

    // una buena estrategia seria guardar dbc en el request
    private Dbcon dbc = null;

    public AgendaDAO () {
    }
    
    public AgendaDAO (boolean b) {
        DbAgenda.setDS(b);
    }
    
    @Override
    public AgendaBean leer(int id) {
        String sql;
        AgendaBean agenda= new AgendaBean();
        
        try {
            DbAgenda dbAgenda = new DbAgenda();
            
            dbc = dbAgenda.conecta(dbc);
            Connection conn = dbc.getConn();
            Statement stm = conn.createStatement();
            sql =
                "SELECT id, userag, nombre, telefono, email "
                    + "FROM agenda "
                    + "WHERE id="
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
            ApW.error("AgendaBean.leer", ex);
        }
            return agenda;
    }

    @Override
    public List<AgendaBean> leerTodos(String userag) {
        String sql;
        ApW.log("userag: "+userag);
        List<AgendaBean> lista = new ArrayList<AgendaBean>();
        try {
            dbc = (new DbAgenda()).conecta(dbc);
            Connection conn = dbc.getConn();
            Statement stm = conn.createStatement();
            sql =
                "SELECT id, userag, nombre, telefono, email "
                    + "FROM agenda "
                    + "WHERE userag ="
                    + dbc.litsql(userag)
                    + dbc.endsql();
            ApW.trace(sql);
            ApW.log(sql);
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                AgendaBean agenda = new AgendaBean(rs.getInt(1), rs.getString(2),
                        rs.getString(3), Long.parseLong(rs.getString(4)), rs.getString(5));
                lista.add(agenda);
            }
            dbc.commit();
            dbc.close();
        } catch (Exception ex) {
            ApW.error("AgendaBean.leerAll", ex);
        }
        return lista;
    }

    @Override
    public int crear(AgendaBean c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int borrar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int actualizar(AgendaBean nuevaAgenda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
