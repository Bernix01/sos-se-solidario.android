package paralelo1.poo.sossesolidario.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import paralelo1.poo.sossesolidario.R;
import paralelo1.poo.sossesolidario.fragments.NecesidadAdapter;
import paralelo1.poo.sossesolidario.objects.CA;
import paralelo1.poo.sossesolidario.objects.Necesidad;

public class DetalleCentroAcopio extends AppCompatActivity {

    private List<Necesidad> necesidadList;
    private RecyclerView recyclerView;
    private NecesidadAdapter nAdapter;
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

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView nombre = (TextView) findViewById(R.id.nombre);
        TextView descripcion = (TextView) findViewById(R.id.dscr);
        TextView direccion = (TextView) findViewById(R.id.direccion);
        TextView fb = (TextView) findViewById(R.id.facebook);
        TextView tw = (TextView) findViewById(R.id.tw);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            CA value = extras.getParcelable("nombre");
            //The key argument here must match that used in the other activity
            nombre.setText(value.getNombre());
            descripcion.setText(value.getDscr());
            direccion.setText(value.getDireccion());
            fb.setText(value.getFb());
            tw.setText(value.getTw());
            necesidadList = value.getNecesidades();
        }
        nAdapter = new NecesidadAdapter(necesidadList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(nAdapter);

        prepareMovieData();

    }

    private void prepareMovieData() {
        nAdapter.notifyDataSetChanged();
    }


}
