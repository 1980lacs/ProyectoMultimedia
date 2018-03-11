package luis.proyectomultimedia;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class GaleriaSelect extends Fragment {

    final int ITEMS[]={
            R.id.btn_fotos,
            R.id.btn_audio,
            R.id.btn_video
    };


    public GaleriaSelect() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View menu = inflater.inflate(R.layout.fragment_galeria_select, container, false);

        for (int i = 0 ; i < ITEMS.length ; i++){

            Button boton = menu.findViewById(ITEMS[i]);
            final int seleccion = i;

            boton.setOnClickListener(new View.OnClickListener() {
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
