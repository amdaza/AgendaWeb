/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda.modelo;

/**
 *
 * @author cas
 */
public class AgendaBean {
    
    private int id;
    private String userag;
    private String nombre;
    private long telefono;
    private String email;

    public AgendaBean() {
    }

    public AgendaBean(int id, String userag, String nombre, long telefono, String email) {
        this.id = id;
        this.userag = userag;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }
    
    public AgendaBean(String userag, String nombre, long telefono, String email) {
        this.id = id;
        this.userag = userag;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserag() {
        return userag;
    }

    public void setUserag(String userag) {
        this.userag = userag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AgendaBean{" + "id=" + id + ", userag=" + userag + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email + '}';
    }
    
    
}
