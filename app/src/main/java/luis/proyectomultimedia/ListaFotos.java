package luis.proyectomultimedia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class ListaFotos extends Fragment {

    private final String CARPETA_RAIZ = "misImagenesPruebas/";
    private final String RUTA_IMAGEN = CARPETA_RAIZ + "misFotos";
    private MyArrayAdapter adapter;
    private final int elemento = R.id.lv_fotos;
    private final String ruta = Environment.getExternalStorageDirectory() + File.separator + RUTA_IMAGEN;
    ListView listView;
    ArrayList<Foto> fotos = new ArrayList<>();
    String path;

    public ListaFotos() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_lista_fotos, container, false);
        listView = vista.findViewById(R.id.lv_fotos);
        fotos = rellenarLista(ruta, fotos);
        adapter = new MyArrayAdapter(getContext(), fotos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Foto item = (Foto)adapter.getItem(position);
                final File f = new File(item.getRuta());
                if(f.exists()){
                    f.getName();

                    Activity act = getActivity();
                    ((SeleccionLista)act).seleccionado(position,String.valueOf(f));
                }
            }
        });

        registerForContextMenu(listView);
        return vista;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_context_fotos, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.mn_renombrar:
                renombrar(info.position);
                return true;
            case R.id.mn_borrar:
                borrarFoto(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void renombrar(int position) {
        final Foto item = (Foto)adapter.getItem(position);
        final File f = new File(item.getRuta());
        if(f.exists()){
            f.getName();

            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            LayoutInflater inflater = getLayoutInflater();

            final View v = inflater.inflate(R.layout.layout_diaalog_renombrar,null);
            builder.setView(v)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            EditText et = (EditText)v.findViewById(R.id.et_renombrar);
                            f.renameTo(new File(ruta + File.separator + et.getText() + ".jpg"));
                            resetearAdapter();
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
            builder.show();
        }
    }

    private void borrarFoto(int pos) {
        Foto item = (Foto)adapter.getItem(pos);
        File f = new File(item.getRuta());
        if(f.exists()){
            f.delete();
        }
        resetearAdapter();
    }

    private void resetearAdapter() {
        fotos = rellenarLista(ruta, fotos);
        adapter = new MyArrayAdapter(getContext(),fotos);
        listView.setAdapter(adapter);
    }

    public ArrayList<Foto> rellenarLista(String ruta, ArrayList<Foto> item) {
        try {
            File f = new File(ruta);
            item = new ArrayList<>();
            File[] files = f.listFiles();

            for (File file : files) {
                path = file.getCanonicalPath();
                Foto foto = new Foto(file.getName(),path);
                item.add(foto);
            }
        } catch (Exception e) {
            Toast.makeText(getContext(), "No se encuentra " + ruta, Toast.LENGTH_SHORT).show();
        }
        return item;
    }

}

class MyArrayAdapter extends ArrayAdapter {

    private final Context context;
    private final ArrayList<Foto> valuesList;

    private ImageLoader imageLoader = ImageLoader.getInstance();

    public MyArrayAdapter(Context context, ArrayList valuesList) {
        super(context, R.layout.lista_foto, valuesList);
        this.context = context;
        this.valuesList = valuesList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Holder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.lista_foto, null);
            holder = new Holder();
            holder.nombre = convertView.findViewById(R.id.tv_nombre_imagen);
            holder.texto = convertView.findViewById(R.id.tv_nombre_imagen2);
            holder.thumb = convertView.findViewById(R.id.iv_miniatura);
            holder.pos = position;
            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }
        Foto person = valuesList.get(holder.pos);
        holder.nombre.setText(person.getNombre());

        File file = new File(person.getRuta());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(file.lastModified());

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        int mhour = calendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = calendar.get(Calendar.MINUTE);
        int mSecond = calendar.get(Calendar.SECOND);
        holder.texto.setText("Dia " + mDay + "/" + mMonth + "/" + mYear +
                " a las " + mhour + ":" + mMinute + ":" + mSecond);

        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .imageScaleType(ImageScaleType.EXACTLY)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getContext())
                .defaultDisplayImageOptions(options)
                .threadPoolSize(5)
                .threadPriority(Thread.MAX_PRIORITY)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .diskCacheExtraOptions(480, 320,null)
                .build();
        imageLoader.init(config);
        imageLoader.displayImage("file://" + person.getRuta(),holder.thumb);

        return convertView;
    }
}

class Holder{
    ImageView thumb;
    TextView nombre;
    TextView texto;
    int pos;
}


