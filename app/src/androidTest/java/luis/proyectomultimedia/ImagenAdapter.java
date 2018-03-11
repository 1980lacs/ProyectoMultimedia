package luis.proyectomultimedia;

import java.io.File;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import net.proyectosbeta.usarimageuniversalloader.R;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ImagenAdapter extends SimpleCursorAdapter {
	// Objetos de clase.
	private Cursor cursor;
	private Context contexto;
	private LayoutInflater mInflater;

	static DisplayImageOptions opcionesImageLoader = new DisplayImageOptions.Builder()
			.cacheInMemory().cacheOnDisc().build();

	static ImageLoaderConfiguration configuracionImageLoader;

	static ImageLoader imageLoader = ImageLoader.getInstance();

	static class ViewHolder {
		TextView textViewNombre;
		TextView textViewApellido;
		TextView textViewEdad;
		ImageView thumb_persona;
	}

	/**
	 * Constructor con 4 parametros.
	 * 
	 * @param contexto
	 * @param cursor
	 * @param from
	 * @param to
	 */
	public ImagenAdapter(Context contexto, Cursor cursor, String[] from,
			int[] to) {
		super(contexto, R.layout.fila_persona, cursor, from, to);
		this.contexto = contexto;
		this.cursor = cursor;
		this.mInflater = LayoutInflater.from(contexto);

		configuracionImageLoader = new ImageLoaderConfiguration.Builder(
				contexto).enableLogging().memoryCacheSize(41943040)
				.discCacheSize(104857600).threadPoolSize(10).build();

		imageLoader.init(configuracionImageLoader);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;

		if (convertView == null) {
			convertView = this.mInflater.inflate(R.layout.fila_persona, null);
			viewHolder = new ViewHolder();

			viewHolder.textViewNombre = (TextView) convertView
					.findViewById(R.id.textViewNombre);
			viewHolder.textViewApellido = (TextView) convertView
					.findViewById(R.id.textViewApellido);
			viewHolder.textViewEdad = (TextView) convertView
					.findViewById(R.id.textViewEdad);
			viewHolder.thumb_persona = (ImageView) convertView
					.findViewById(R.id.thumb_persona);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		this.cursor.moveToPosition(position);

		viewHolder.textViewNombre.setText(this.cursor.getString(this.cursor
				.getColumnIndex("nombre")));
		viewHolder.textViewApellido.setText(this.cursor.getString(this.cursor
				.getColumnIndex("apellido")));
		viewHolder.textViewEdad.setText(this.cursor.getString(this.cursor
				.getColumnIndex("edad")));

		// Se obtiene la ruta de la imagen.
		String ruta_imagen = cursor.getString(cursor
				.getColumnIndex("ruta_imagen"));

		if (!ruta_imagen.equals("")) {
			String imageUri = "file://"
					+ this.cursor.getString(this.cursor
							.getColumnIndex("ruta_imagen"));

			imageLoader.displayImage(imageUri, viewHolder.thumb_persona);
			viewHolder.thumb_persona.setVisibility(View.VISIBLE);

		} else {
			viewHolder.thumb_persona.setVisibility(View.GONE); // Esconde la
																// imagen.
			viewHolder.thumb_persona.invalidate(); // should call after changing
													// to GONE.
		}
		return convertView;
	}
}