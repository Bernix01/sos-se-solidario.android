package paralelo1.poo.sossesolidario;

/**
 * Created by roche on 14/08/2016.
 */

        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import java.util.List;
        public class NecesidadAdapter extends RecyclerView.Adapter<NecesidadAdapter.MyViewHolder>{

        private List<Necesidad> necesidadlist;

        public class MyViewHolder extends RecyclerView.ViewHolder {
                public TextView title, prod, cant;

                public MyViewHolder(View view) {
                        super(view);
                        prod = (TextView) view.findViewById(R.id.producto);
                        cant = (TextView) view.findViewById(R.id.cantidad);
                }
        }

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
                        holder.cant.setText(nec.getCantidad());
                }

                @Override
                public int getItemCount() {
                        return necesidadlist.size();
                }
}
