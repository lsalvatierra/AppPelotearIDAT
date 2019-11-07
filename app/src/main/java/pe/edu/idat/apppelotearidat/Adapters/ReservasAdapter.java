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
import pe.edu.idat.apppelotearidat.Modelo.Reserva;
import pe.edu.idat.apppelotearidat.R;

public class ReservasAdapter extends RecyclerView.Adapter<ReservasAdapter.ReservasViewHolder>  {

    private Context context;
    private ArrayList<Reserva> lista;

    public ReservasAdapter(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReservasViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(context)
                .inflate(R.layout.item_reserva,
                        viewGroup,false);
        return new ReservasAdapter.ReservasViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservasViewHolder reservasViewHolder, int i) {
        Reserva item = lista.get(i);
        reservasViewHolder.tvSede.setText(item.getNombreSede());
        reservasViewHolder.tvDesLosa.setText(item.getDescLosa());
        reservasViewHolder.tvHoraIni.setText("Inicio: "+item.getHoraIni());
        reservasViewHolder.tvHoraFin.setText("Fin: "+item.getHoraFin());

        String[] fechaarray = item.getFecha().substring(0,10).split("-");


        reservasViewHolder.tvFecha.setText(fechaarray[2]+"-"+fechaarray[1]+"-"+fechaarray[0]);
        Picasso.with(context).load(item.getUrlImgLosa())
                .fit().centerInside()
                .into(reservasViewHolder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ReservasViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagen;
        TextView tvSede, tvDesLosa, tvFecha, tvHoraIni, tvHoraFin;
        public ReservasViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvSede = itemView.findViewById(R.id.tvSede);
            tvDesLosa = itemView.findViewById(R.id.tvDesLosa);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvHoraIni = itemView.findViewById(R.id.tvHoraIni);
            tvHoraFin = itemView.findViewById(R.id.tvHoraFin);
        }
    }
    public void agregarElemento(ArrayList<Reserva> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }
}
