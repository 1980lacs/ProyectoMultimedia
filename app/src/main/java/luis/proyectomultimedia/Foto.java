package luis.proyectomultimedia;

public class Foto {

    private String nombre;
    private String ruta;
    private String descripcion;

    public Foto(String nombre, String ruta){
        this.nombre = nombre;
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

    public String getNombre(){
        return nombre;
    }

    public void setRuta(String ruta){
        this.ruta = ruta;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}
