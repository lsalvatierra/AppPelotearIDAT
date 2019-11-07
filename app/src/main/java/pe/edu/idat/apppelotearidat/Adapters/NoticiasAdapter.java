package pe.edu.idat.apppelotearidat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pe.edu.idat.apppelotearidat.Modelo.Noticia;
import pe.edu.idat.apppelotearidat.R;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.NoticiasViewHolder> {

    private Context context;
    private ArrayList<Noticia> lista;

    public NoticiasAdapter(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public NoticiasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(context)
                .inflate(R.layout.item_noticia,
                        viewGroup,false);
        return new NoticiasViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticiasViewHolder noticiasViewHolder, int i) {
        Noticia item = lista.get(i);
        noticiasViewHolder.tvNombre.setText(item.getTitulo());
        Picasso.with(context).load(item.getUrlImagen())
                .fit().centerInside()
                .into(noticiasViewHolder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class NoticiasViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvNombre;
        public NoticiasViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvNombre = itemView.findViewById(R.id.tvNombre);
        }
    }

    public void agregarElemento(ArrayList<Noticia> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }
}
