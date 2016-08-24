package paralelo1.poo.sossesolidario.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.LinkedList;
import java.util.List;

import paralelo1.poo.sossesolidario.R;
import paralelo1.poo.sossesolidario.objects.CA;
import paralelo1.poo.sossesolidario.objects.Necesidad;
import paralelo1.poo.sossesolidario.server.Rest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditNecesidad extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CoordinatorLayout coordinatorLayout;
    EditText eText1, eText2, eText3;
    private Necesidad ca;
    private Spinner spinner;
    private List<CA> cas;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_necesidad);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        eText1 = (EditText) findViewById(R.id.edit_nombre);
        eText2 = (EditText) findViewById(R.id.edit_descripcion);
        eText3 = (EditText) findViewById(R.id.edit_direccion);

        spinner = (Spinner) findViewById(R.id.spinner);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            ca = extras.getParcelable("necesidad");
            //The key argument here must match that used in the other activity

            if (ca == null) {
                finish();
                return;
            }
            final EditNecesidad self = this;

            eText1.setText(ca.getNombre());
            eText2.setText(ca.getTipo());
            eText3.setText(String.valueOf(ca.getCantidad()));
        }
        final EditNecesidad self = this;
        Rest.get().service().getCAs().enqueue(new Callback<List<CA>>() {
            @Override
            public void onResponse(Call<List<CA>> call, Response<List<CA>> response) {
                list = new LinkedList<String>();

                for (CA ca1 : response.body()) {
                    list.add(ca1.getNombre());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(self, android.R.layout.simple_spinner_item, list);

                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                // attaching data adapter to spinner
                spinner.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<CA>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_edit_ca, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_save:
                guardar();
                return true;
            case R.id.action_delete:
                borrar();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void borrar() {
        ca.delete(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        for (CA ca1 : cas) {
            if (ca1.getNombre().equals(list.get(position)))
                ca.setFk_necesidad_ca(ca1.getId());
        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        ca.setFk_necesidad_ca(0);
    }

    private void guardar() {
        ca.setNombre(eText1.getText().toString());
        ca.setTipo(eText2.getText().toString());
        ca.setCantidad(Integer.parseInt(eText3.getText().toString()));
        ca.save(new Callback<Necesidad>() {
            @Override
            public void onResponse(Call<Necesidad> call, Response<Necesidad> response) {
                Log.e("asdasd", response.raw().toString());
                Snackbar.make(coordinatorLayout, "Información actualizada", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Necesidad> call, Throwable t) {

            }
        });

    }

}
