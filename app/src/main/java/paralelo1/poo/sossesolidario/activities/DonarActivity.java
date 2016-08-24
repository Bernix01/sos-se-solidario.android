package paralelo1.poo.sossesolidario.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import paralelo1.poo.sossesolidario.R;
import paralelo1.poo.sossesolidario.fragments.NecesidadAdapter;
import paralelo1.poo.sossesolidario.objects.Necesidad;
import paralelo1.poo.sossesolidario.server.Rest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonarActivity extends AppCompatActivity {

    private NecesidadAdapter necesidadAdapter;
    private Necesidad necesidad;
    private boolean isAdmin;
    private TextView tipo;
    private TextView cant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        necesidad = getIntent().getExtras().getParcelable("necesidad");
        tipo = (TextView) findViewById(R.id.tipo);
        cant = (TextView) findViewById(R.id.cantidad);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final CoordinatorLayout cl = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        displayData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(cl, "Donación recibida, muchas gracias", Snackbar.LENGTH_LONG).show();

            }
        });

        isAdmin = getSharedPreferences("sos", Context.MODE_PRIVATE).getBoolean("tipo", false);
    }

    private void displayData() {

        getSupportActionBar().setTitle(necesidad.getNombre());
        tipo.setText(necesidad.getTipo());
        cant.setText(String.valueOf(necesidad.getCantidad()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detalle_centro_acopio, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Rest.get().service().getNecesidad(necesidad.getId()).enqueue(new Callback<Necesidad>() {
            @Override
            public void onResponse(Call<Necesidad> call, Response<Necesidad> response) {
                necesidad = response.body();
                if (necesidad == null) {
                    finish();
                    return;
                }
                displayData();
            }

            @Override
            public void onFailure(Call<Necesidad> call, Throwable t) {

            }
        });
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
                Intent i = new Intent(getApplicationContext(), EditNecesidad.class);
                i.putExtra("necesidad", necesidad);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
