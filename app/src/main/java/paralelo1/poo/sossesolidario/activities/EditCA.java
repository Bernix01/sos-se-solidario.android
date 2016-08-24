package paralelo1.poo.sossesolidario.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import paralelo1.poo.sossesolidario.R;
import paralelo1.poo.sossesolidario.objects.CA;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCA extends AppCompatActivity {
    public static final int PLACE_PICKER_REQUEST = 1;
    EditText eText1, eText2, eText3, eText4, eText5;
    Spinner spinner;
    Button btn1, btn2;
    CoordinatorLayout coordinatorLayout;
    private CA ca;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_c);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        eText1 = (EditText) findViewById(R.id.edit_nombre);
        eText2 = (EditText) findViewById(R.id.edit_descripcion);
        eText3 = (EditText) findViewById(R.id.edit_direccion);
        eText4 = (EditText) findViewById(R.id.edit_facebook);
        eText5 = (EditText) findViewById(R.id.edit_twitter);
        btn1 = (Button) findViewById(R.id.btnUbicacion);
        btn2 = (Button) findViewById(R.id.btnEliminar);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            ca = extras.getParcelable("ca");
            //The key argument here must match that used in the other activity
            Log.d("asdasd", String.valueOf(ca.getId()));
            if (ca == null) {
                finish();
                return;
            }
            final EditCA self = this;
            btn1.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                    try {
                        startActivityForResult(builder.build(self), PLACE_PICKER_REQUEST);
                    } catch (GooglePlayServicesRepairableException e) {
                        e.printStackTrace();
                    } catch (GooglePlayServicesNotAvailableException e) {
                        Toast.makeText(EditCA.this, "Google play services unavailable.", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btn2.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    cancelar();
                }
            });

            eText1.setText(ca.getNombre());
            eText2.setText(ca.getDscr());
            eText3.setText(ca.getDireccion());
            eText4.setText(ca.getFb());
            eText5.setText(ca.getTw());


        }

    }


    private void guardar() {
        ca.setNombre(eText1.getText().toString());
        ca.setDscr(eText2.getText().toString());
        ca.setDireccion(eText3.getText().toString());
        ca.setFb(eText4.getText().toString());
        ca.setTw(eText5.getText().toString());

        ca.save(new Callback<CA>() {
            @Override
            public void onResponse(Call<CA> call, Response<CA> response) {
                Log.d("asdasd", response.raw().toString());
                Log.d("asdasd", response.message());
                Snackbar.make(coordinatorLayout,"Información actualizada",Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CA> call, Throwable t) {

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

    private void cancelar() {
        setResult(RESULT_CANCELED, null);
        finish();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getApplicationContext(), data);
                String toastMsg = String.format("Place: %s", place.getName());
                ca.setLatitud(place.getLatLng().latitude);
                ca.setLongitud(place.getLatLng().longitude);
                Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void borrar()
    {
		/*
		 * Borramos el registro con confirmación
		 */
        AlertDialog.Builder dialogEliminar = new AlertDialog.Builder(this);

        dialogEliminar.setIcon(android.R.drawable.ic_dialog_alert);
        dialogEliminar.setCancelable(false);

        dialogEliminar.setPositiveButton(getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int boton) {
                ca.delete();
				/*
				 * Devolvemos el control
				 */
                setResult(RESULT_OK);
                finish();
            }
        });

        dialogEliminar.setNegativeButton(android.R.string.no, null);

        dialogEliminar.show();

    }


}