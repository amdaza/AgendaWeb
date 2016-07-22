/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import local.ApW;
import local.Dbcon;

/**
 *
 * @author cas
 */
public class AgendaDao {
    
    private static final String AGENDA_ID="SELECT id, userag, nombre, telefono, email FROM agenda WHERE id=? ";
    private static final String AGENDA_USERAG="SELECT id, userag, nombre, telefono, email FROM agenda WHERE userag=?";
    private static final String ALL_AGENDA="SELECT id, userag, nombre, telefono, email FROM agenda ";
    private static final String INSERT="Insert into agenda (userag, nombre, telefono, email) values (?,?,?,?)";
    private static final String UPDATE="Update agenda set userag=?, nombre=?, telefono=?, email=? where id=?";
    private static final String DELETE="Delete from agenda where id=?";
    
    private Dbcon dbc=null;
    
    public AgendaDao (){
        
    }
    public AgendaDao (boolean DS){
        DbAgenda.setDS(DS);
    }
    
    private AgendaBean crearAgenda (ResultSet rs) throws SQLException{
        AgendaBean agenda=new AgendaBean();
        agenda.setId(rs.getInt("id"));
        agenda.setUserag(rs.getString("userag"));
        agenda.setNombre(rs.getString("nombre"));
        agenda.setTelefono(rs.getLong("telefono"));
        agenda.setEmail(rs.getString("email"));
        return agenda;
        
    }
    
    private List<AgendaBean> getList (String sql, List<Object> params){
        List<AgendaBean> agenda=new ArrayList<>();
        try {
            dbc = DbAgenda.conecta(dbc);
            Connection conn = dbc.getConn();
            
            PreparedStatement stm = conn.prepareStatement(sql);
            int i=1;
            for (Object o: params){
                stm.setObject(i, o);
                i++;
            }
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                agenda.add(crearAgenda(rs));
            }
            dbc.commit();
        } catch (Exception ex) {
            if (dbc != null)
                dbc.rollback();
            ApW.error("AgendaDAO.leer: "+sql, ex);
        }
        return agenda;
    }
    public AgendaBean getAgenda(int id) {
        List<Object> params=new ArrayList<>();
        params.add(id);
        List<AgendaBean> agenda=this.getList(AGENDA_ID, params);
        if (agenda.isEmpty()){
            return new AgendaBean();
        }else{
            return agenda.get(0);
        }
    }
    public List<AgendaBean> getAgendaByUserag(String userag){
        List<Object> params=new ArrayList<>();
        params.add(userag);
        return this.getList(AGENDA_USERAG, params);
    }
    
     public List<AgendaBean> getAll(){
         List<Object> params=new ArrayList<>();
        return this.getList(ALL_AGENDA, params);
    }
    
    public List<AgendaBean> getByFiltro (AgendaBean filtro){
        if (filtro.isEmpry()){
            return getAll();
        }
         StringBuilder sql= new StringBuilder("SELECT id, userag, nombre, telefono, email "
                    + "FROM agenda "
                    + "WHERE ");
         String and="";
         List<Object> params= new ArrayList<>();
         
         if (!filtro.getUserag().equals("")){
             sql.append(and+"userag=? ");
             params.add(filtro.getUserag());
             and="and ";
         }
         if (!filtro.getNombre().equals("")){
             sql.append(and+"nombre like ? ");
             params.add("%"+filtro.getNombre()+"%");
             and="and ";
         }
         if (filtro.getTelefono()!=-1){
             sql.append(and+"telefono=? ");
             params.add(filtro.getTelefono());
             and="and ";
         }
         if (!filtro.getEmail().equals("")){
             sql.append(and+"email=? ");
             params.add(filtro.getEmail());
         }
         
         return getList(sql.toString(), params);
    }
    
    private int exeUpdate(String sql, List<Object> params){
          
        try {
            dbc = DbAgenda.conecta(dbc);
            Connection conn = dbc.getConn();

            PreparedStatement stm = conn.prepareStatement(sql);
             int i=1;
            for (Object o: params){
                stm.setObject(i, o);
                i++;
            }
           
            int n=stm.executeUpdate();
            dbc.commit();
            return n;
        } catch (Exception ex) {
             ApW.error("AgendaDAO.leer: "+sql, ex);
             dbc.rollback();
             return -1;
        }

    }
    public int saveAgenda (AgendaBean agenda){
        List<Object> params= new ArrayList<>();
        params.add(agenda.getUserag());
        params.add(agenda.getNombre());
        params.add(agenda.getTelefono());
        params.add(agenda.getEmail());
        return this.exeUpdate(INSERT, params);
        
    }
    
    public int updateAgenda (AgendaBean agenda){
        List<Object> params= new ArrayList<>();
        params.add(agenda.getUserag());
        params.add(agenda.getNombre());
        params.add(agenda.getTelefono());
        params.add(agenda.getEmail());
        params.add(agenda.getId());
        return this.exeUpdate(UPDATE, params);
    }
    public int deleteAgenda (int id){
        List<Object> params= new ArrayList<>();
        params.add(id);
        return this.exeUpdate(DELETE, params);
    }
    
    public void close(){
        if (dbc!=null)
            dbc.close();
    }
}
