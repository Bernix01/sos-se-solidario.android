package paralelo1.poo.sossesolidario;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import paralelo1.poo.sossesolidario.fragments.NecesidadFragment;
import paralelo1.poo.sossesolidario.objects.Necesidad;


public class MyNecesidadRecyclerViewAdapter extends RecyclerView.Adapter<MyNecesidadRecyclerViewAdapter.ViewHolder> {

    private final List<Necesidad> mValues;
    private final NecesidadFragment.OnListFragmentInteractionListener mListener;

    public MyNecesidadRecyclerViewAdapter(List<Necesidad> items, NecesidadFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.necesida_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(holder.mItem.getNombre());
        holder.mContentView.setText(String.valueOf(holder.mItem.getCantidad()));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Necesidad mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.producto);
            mContentView = (TextView) view.findViewById(R.id.cantidad);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
