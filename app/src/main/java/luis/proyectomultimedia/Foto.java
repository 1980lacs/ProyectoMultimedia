package luis.proyectomultimedia;

import android.graphics.Bitmap;
import android.net.Uri;

import java.net.URI;

/**
 * Created by lacs8 on 08/02/2018.
 */

public class Foto {

    private String nombre;
    //private Bitmap imagen;
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

    /*public Bitmap getImagen(){
        return imagen;
    }*/

    public void setRuta(String ruta){
        this.ruta = ruta;
    }

    /*public void setImagen(Bitmap imagen){
        this.imagen = imagen;
    }*/

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}
