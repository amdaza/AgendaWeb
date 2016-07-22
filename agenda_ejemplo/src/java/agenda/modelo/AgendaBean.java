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

    public AgendaBean(String user, String nombre, long telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.userag = user;
        this.id = -1;
    }

    public AgendaBean() {
        this.nombre = "";
        this.telefono = -1;
        this.email = "";
        this.userag = "";
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public String getUserag() {
        return userag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserag(String userag) {
        this.userag = userag;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEmpry() {
        if (this.nombre == "" && this.telefono < 0 && this.email == ""
                && this.userag == "" && this.id < 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        if (id > 0) {
            return "id=" + id + ", userag=" + userag + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email;
        } else {
            return "userag=" + userag + ", nombre=" + nombre + ", telefono=" + telefono + ", email=" + email;
        }
    }

}
