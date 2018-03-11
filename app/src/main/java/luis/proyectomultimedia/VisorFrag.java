package luis.proyectomultimedia;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class VisorFrag extends Fragment {


    public VisorFrag() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_visor, container, false);

        ImageView iv = (ImageView)vista.findViewById(R.id.iv_grande);
        Bundle bundle = this.getArguments();
        if (bundle != null){
            String ruta = bundle.getString("Ruta");
            Bitmap bitmap = BitmapFactory.decodeFile(ruta);
            iv.setImageBitmap(bitmap);
        }

        return vista;
    }

}
