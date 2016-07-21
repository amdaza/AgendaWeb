/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.modelo.dao;

import agenda.modelo.AgendaBean;
import agenda.modelo.DbAgenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            dbc.rollback();
        }
        
        return agenda;
    }

    @Override
    public List<AgendaBean> leerTodos(String userag) {
        String sql;
        ApW.log("userag: "+userag);
        List<AgendaBean> lista = new ArrayList<AgendaBean>();
        
        try {
            DbAgenda dbAgenda = new DbAgenda();            
            dbc = dbAgenda.conecta(dbc);
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
            dbc.rollback();
        }
        return lista;
    }

    @Override
    public int crear(AgendaBean nuevaAgenda) {
        String sql;
        int result = 0;
        try {
            DbAgenda dbAgenda = new DbAgenda();
            dbc = dbAgenda.conecta(dbc);
            Connection conn = dbc.getConn();
            sql =
                "INSERT INTO agenda (userag, nombre, telefono, email) "
                    + "VALUES (?, ?, ?, ?) "
                    + dbc.endsql();
            ApW.trace(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevaAgenda.getUserag());
            ps.setString(2, nuevaAgenda.getNombre());
            ps.setString(3, Long.toString(nuevaAgenda.getTelefono()));
            ps.setString(4, nuevaAgenda.getEmail());
                        
            result = ps.executeUpdate();
            dbc.commit();
            
        } catch (Exception ex) {
            ApW.error("AgendaBean.crear", ex);
            dbc.rollback();
        }
        
        return result;
    }

    @Override
    public int borrar(int id) {
        String sql;
        int result = 0;
        try {
            DbAgenda dbAgenda = new DbAgenda();
            dbc = dbAgenda.conecta(dbc);
            Connection conn = dbc.getConn();
            sql =
                "DELETE FROM agenda "
                    + "WHERE id = ?"
                    + dbc.endsql();
            ApW.trace(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
                        
            result = ps.executeUpdate();
            dbc.commit();
            
        } catch (Exception ex) {
            ApW.error("AgendaBean.borrar", ex);
            dbc.rollback();
        }
        
        return result;
    }

    @Override
    public int actualizar(AgendaBean agenda) {
        if (agenda.getId() <= 0) {
            // Sin id no se puede actualizar ningún campo
            return -1;
        }
        
        String sql;
        int result = 0;
        try {
            DbAgenda dbAgenda = new DbAgenda();
            dbc = dbAgenda.conecta(dbc);
            Connection conn = dbc.getConn();
            
            Statement stm = conn.createStatement();
            sql = "UPDATE agenda SET ";
            
            if (agenda.getUserag() != null) {
                sql += "userag =  " + dbc.litsql(agenda.getUserag());
            }
            
            if (agenda.getNombre() != null) {
                sql += ",nombre = " + dbc.litsql(agenda.getNombre());
            }
            
            if(agenda.getTelefono() > 0) {
                sql += ",telefono = " + Long.toString(agenda.getTelefono());
            }
            
            if (agenda.getEmail() != null) {
                sql += ",email = " + dbc.litsql(agenda.getEmail());
            }
            
            sql += " WHERE id = " + agenda.getId() +
                    dbc.endsql();
            ApW.trace(sql);
            ApW.log(sql);
            result = stm.executeUpdate(sql);
           
            dbc.commit();
            
        } catch (Exception ex) {
            ApW.error("AgendaBean.borrar", ex);
            dbc.rollback();
        }
        
        return result;
    }
    
}
