package luis.proyectomultimedia;

//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GaleriaActivity extends FragmentActivity implements SeleccionItem{

    private Fragment fragmento;
    private int seleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        if (savedInstanceState == null) {
            fragmento = new GaleriaSelect();
            FragmentManager manejador = getSupportFragmentManager();
            FragmentTransaction trans = manejador.beginTransaction();
            trans.replace(R.id.frag_galeria_select, fragmento);
            trans.commit();
        } else {
            fragmento = new ListaFotos();
            FragmentManager manejador = getSupportFragmentManager();
            FragmentTransaction trans = manejador.beginTransaction();
            trans.replace(R.id.frag_galeria_select, fragmento);
            trans.commit();
        }
    }

    @Override
    public void pulsado(int item) {
        seleccion = item;
        switch (item) {
            case 0:
                fragmento = new ListaFotos();
                FragmentManager manejador = getSupportFragmentManager();
                FragmentTransaction trans = manejador.beginTransaction();
                trans.replace(R.id.frag_galeria_select,fragmento);
                trans.commit();
                Toast.makeText(getApplicationContext(),"Fotos",Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getApplicationContext(),"Audio",Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(),"Video",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("num", seleccion);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        seleccion = savedInstanceState.getInt("num");
    }
}
