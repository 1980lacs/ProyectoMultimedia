package luis.proyectomultimedia;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GaleriaActivity extends FragmentActivity implements SeleccionItem, SeleccionLista {

    private Fragment frag;
    private Fragment fragmento[] = {
            new GaleriaSelect(),
            new ListaFotos(),
            new VisorFrag()
    };
    private int seleccion;
    private boolean dosFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        //dosFrag = findViewById(R.id.frag_secundario) != null;
        if (savedInstanceState == null) {
            frag = fragmento[0];
            FragmentManager manejador = getSupportFragmentManager();
            FragmentTransaction trans = manejador.beginTransaction();
            trans.replace(R.id.frag_galeria_select, frag);
            trans.commit();
        } else {
            seleccion = savedInstanceState.getInt("num");
            frag = fragmento[seleccion];
            FragmentManager manejador = getSupportFragmentManager();
            FragmentTransaction trans = manejador.beginTransaction();
            trans.replace(R.id.frag_galeria_select, frag);
            trans.commit();
        }


    }

    @Override
    public void pulsado(int item) {
        switch (item) {
            case 0:
                frag = fragmento[1];
                seleccion = 1;
                FragmentManager manejador = getSupportFragmentManager();
                FragmentTransaction trans = manejador.beginTransaction();
                trans.replace(R.id.frag_galeria_select, frag);
                trans.commit();
                break;
            case 1:
                Toast.makeText(getApplicationContext(), "Proximamente", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getApplicationContext(), "Proximamente", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void seleccionado(int pos, String ruta) {
        frag = fragmento[2];
        FragmentManager manejador = getSupportFragmentManager();
        FragmentTransaction trans = manejador.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("Ruta", ruta);
        frag.setArguments(bundle);
        trans.replace(R.id.frag_galeria_select, frag);
        trans.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("num", seleccion);
        super.onSaveInstanceState(savedInstanceState);

    }
}
