package paralelo1.poo.sossesolidario.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import paralelo1.poo.sossesolidario.MyNecesidadRecyclerViewAdapter;
import paralelo1.poo.sossesolidario.R;
import paralelo1.poo.sossesolidario.objects.Necesidad;
import paralelo1.poo.sossesolidario.server.Rest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class NecesidadFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 3;
    private OnListFragmentInteractionListener mListener;
    private MyNecesidadRecyclerViewAdapter adapter1;
    private MyNecesidadRecyclerViewAdapter adapter;
    private RecyclerView recyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public NecesidadFragment() {
    }

    @SuppressWarnings("unused")
    public static NecesidadFragment newInstance(int columnCount) {
        NecesidadFragment fragment = new NecesidadFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_necesidad_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            Rest.get().service().getNecesidades().enqueue(new Callback<List<Necesidad>>() {
                @Override
                public void onResponse(Call<List<Necesidad>> call, Response<List<Necesidad>> response) {
                    adapter = new MyNecesidadRecyclerViewAdapter(response.body(), mListener);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<List<Necesidad>> call, Throwable t) {

                }
            });


        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter == null)
            return;
        Rest.get().service().getNecesidades().enqueue(new Callback<List<Necesidad>>() {
            @Override
            public void onResponse(Call<List<Necesidad>> call, Response<List<Necesidad>> response) {
                adapter.swapData(response.body());
            }

            @Override
            public void onFailure(Call<List<Necesidad>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Necesidad item);
    }
}
