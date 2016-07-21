
package agenda.modelo;

/**
 * <p>Título: AgendaBean</p>
 * <p>Descripción: Bean con la información de un campo de la agenda</p>
 * <p>Copyright: Copyright (c) curso Java EE</p>
 * @author Alicia Daza
 * @version 1.0
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
