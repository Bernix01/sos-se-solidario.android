package paralelo1.poo.sossesolidario.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import paralelo1.poo.sossesolidario.R;
import paralelo1.poo.sossesolidario.objects.CA;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditCA extends AppCompatActivity {
    EditText eText1, eText2, eText3, eText4, eText5;
    Button btn1, btn2;
    private CA ca;
    CoordinatorLayout coordinatorLayout;

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
        btn1 = (Button) findViewById(R.id.btnActualizar);
        btn2 = (Button) findViewById(R.id.btnEliminar);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            ca = extras.getParcelable("ca");
            //The key argument here must match that used in the other activity

            if (ca == null) {
                finish();
                return;
            }
            btn1.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    guardar();
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
                Snackbar.make(coordinatorLayout,"Información actualizada",Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CA> call, Throwable t) {

            }
        });

    }

    private void cancelar() {
        setResult(RESULT_CANCELED, null);
        finish();
    }

    private void borrar(final long id)
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