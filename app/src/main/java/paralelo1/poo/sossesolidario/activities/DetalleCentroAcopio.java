package paralelo1.poo.sossesolidario.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import paralelo1.poo.sossesolidario.MyNecesidadRecyclerViewAdapter;
import paralelo1.poo.sossesolidario.R;
import paralelo1.poo.sossesolidario.fragments.NecesidadFragment;
import paralelo1.poo.sossesolidario.objects.CA;
import paralelo1.poo.sossesolidario.objects.Necesidad;
import paralelo1.poo.sossesolidario.server.Rest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleCentroAcopio extends AppCompatActivity {

    private List<Necesidad> necesidadList;
    private RecyclerView recyclerView;
    private MyNecesidadRecyclerViewAdapter nAdapter;
    private boolean isAdmin;
    private CA ca;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_centro_acopio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        TextView descripcion = (TextView) findViewById(R.id.dscr);
        TextView direccion = (TextView) findViewById(R.id.direccion);
        TextView fb = (TextView) findViewById(R.id.facebook);
        TextView tw = (TextView) findViewById(R.id.tw);
        Bundle extras;
        if (savedInstanceState != null) {
            extras = savedInstanceState;
            Toast.makeText(DetalleCentroAcopio.this, "savedInstance", Toast.LENGTH_SHORT).show();
        }else
            extras = getIntent().getExtras();
        if (extras == null)
            finish();
        ca = extras.getParcelable("nombre");
        //The key argument here must match that used in the other activity
        assert descripcion != null;
        if (ca == null) {
            finish();
            return;
        }
        descripcion.setText(ca.getDscr());
        getSupportActionBar().setTitle(ca.getNombre());
        assert direccion != null;
        direccion.setText(ca.getDireccion());
        assert fb != null;
        fb.setText(ca.getFb());
        assert tw != null;
        tw.setText(ca.getTw());


        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), DonarActivity.class);
                i.putExtra("ca", ca);
                startActivity(i);
            }
        });
        ca.getNecesidades(new Callback<List<Necesidad>>() {
            @Override
            public void onResponse(Call<List<Necesidad>> call, Response<List<Necesidad>> response) {
                necesidadList = response.body();
                if (necesidadList != null && necesidadList.size() > 0) {

                    nAdapter = new MyNecesidadRecyclerViewAdapter(necesidadList, new NecesidadFragment.OnListFragmentInteractionListener() {
                        @Override
                        public void onListFragmentInteraction(Necesidad item) {
                            Intent i = new Intent(getApplicationContext(), DonarActivity.class);
                            i.putExtra("cosa", item);
                            startActivity(i);
                        }
                    });
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(nAdapter);
                    prepareMovieData();
                }
            }

            @Override
            public void onFailure(Call<List<Necesidad>> call, Throwable t) {
                recyclerView.setVisibility(View.INVISIBLE);

            }
        });
        Rest.get().service().getCA(ca.getId()).enqueue(new Callback<CA>() {
            @Override
            public void onResponse(Call<CA> call, Response<CA> response) {
                ca = response.body();
            }

            @Override
            public void onFailure(Call<CA> call, Throwable t) {

            }
        });

        isAdmin = getSharedPreferences("sos", Context.MODE_PRIVATE).getBoolean("tipo", false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Rest.get().service().getCA(ca.getId()).enqueue(new Callback<CA>() {
            @Override
            public void onResponse(Call<CA> call, Response<CA> response) {
                ca = response.body();
                Toast.makeText(DetalleCentroAcopio.this, "onresume", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CA> call, Throwable t) {

            }
        });
    }

    private void prepareMovieData() {
        nAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalle_centro_acopio, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!isAdmin)
            menu.getItem(0).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        switch (id) {
            case R.id.action_edit:
                Intent i = new Intent(getApplicationContext(), EditCA.class);
                i.putExtra("ca", ca);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
