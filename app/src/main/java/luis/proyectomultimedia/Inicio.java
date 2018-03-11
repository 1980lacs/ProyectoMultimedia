package luis.proyectomultimedia;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Inicio extends Fragment {

    final int IMAGENES[]={
            R.id.iv_fotos,
            R.id.iv_video,
            R.id.iv_audio,
            R.id.iv_galeria,
            R.id.iv_config,
            R.id.iv_salir
    };

    final int TEXTOS[]={
            R.id.tv_fotos,
            R.id.tv_video,
            R.id.tv_audio,
            R.id.tv_galeria,
            R.id.tv_configuracion,
            R.id.tv_salir
    };


    public Inicio() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View menu = inflater.inflate(R.layout.fragment_inicio, container, false);

        for (int i = 0 ; i < IMAGENES.length ; i++){

            ImageView imagen = menu.findViewById(IMAGENES[i]);
            final int seleccion = i;

            imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity act = getActivity();
                    ((SeleccionItem)act).pulsado(seleccion);
                }
            });
        }

        for (int i = 0 ; i < TEXTOS.length ; i++){

            TextView texto = menu.findViewById(TEXTOS[i]);
            final int seleccion = i;

            texto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Activity act = getActivity();
                    ((SeleccionItem)act).pulsado(seleccion);
                }
            });
        }


        return menu;
    }

}
