package paralelo1.poo.sossesolidario.fragments;

/**
 * Created by roche on 14/08/2016.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import paralelo1.poo.sossesolidario.R;
import paralelo1.poo.sossesolidario.objects.Necesidad;

public class NecesidadAdapter extends RecyclerView.Adapter<NecesidadAdapter.MyViewHolder> {

    private List<Necesidad> necesidadlist;

    public NecesidadAdapter(List<Necesidad> necesidadlist) {
        this.necesidadlist = necesidadlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.necesida_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Necesidad nec = necesidadlist.get(position);
        //holder.title.setText(movie.getTitle());
        holder.prod.setText(nec.getNombre());
        String cantidad = "Cantidad: " + String.valueOf(nec.getCantidad());
        holder.cant.setText(cantidad);
    }

    @Override
    public int getItemCount() {
        return necesidadlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, prod, cant;

        public MyViewHolder(View view) {
            super(view);
            prod = (TextView) view.findViewById(R.id.producto);
            cant = (TextView) view.findViewById(R.id.cantidad);
        }
    }
}
