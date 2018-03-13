package luis.proyectomultimedia;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class GaleriaSelect extends Fragment {

    final int ITEMS[]={
            R.id.iv_imagenes,
            R.id.iv_audios,
            R.id.iv_videos
    };

    public GaleriaSelect() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View menu = inflater.inflate(R.layout.fragment_galeria_select, container, false);

        for (int i = 0 ; i < ITEMS.length ; i++){

            ImageView boton = menu.findViewById(ITEMS[i]);
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
