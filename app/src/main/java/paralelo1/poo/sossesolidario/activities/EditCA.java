package paralelo1.poo.sossesolidario.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class EditCA extends AppCompatActivity {
    EditText eText1, eText2, eText3, eText4, eText5;
    Button btn1, btn2;
    private long id;
    private CA ca = new CA();

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

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar,if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setEdicion(boolean opcion) {
        eText1.setEnabled(opcion);
        eText2.setEnabled(opcion);
        eText3.setEnabled(opcion);
        eText4.setEnabled(opcion);
        eText5.setEnabled(opcion);


        // Controlamos visibilidad de botonera
        LinearLayout v = (LinearLayout) findViewById(R.id.fab);

        if (opcion)
            v.setVisibility(View.VISIBLE);

        else
            v.setVisibility(View.GONE);
    }

    private void guardar() {
        ca.setNombre(eText1.getText().toString());
        ca.setDscr(eText2.getText().toString());
        ca.setDireccion(eText3.getText().toString());
        ca.setFb(eText4.getText().toString());
        ca.setTw(eText5.getText().toString());


        //
        // Devolvemos el control
        //
        setResult(RESULT_OK);
        finish();
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
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.hipoteca_formulario_editar, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//
//        switch (item.getItemId())
//        {
//            case R.id.menu_eliminar:
//                borrar(id);
//                return true;
//
//            case R.id.menu_cancelar:
//                cancelar();
//                return true;
//
//            case R.id.menu_guardar:
//                guardar();
//                return true;
//
//            case R.id.menu_editar:
//                establecerModo(HipotecaActivity.C_EDITAR);
//                return true;
//        }
//
//        return super.onMenuItemSelected(featureId, item);
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        client.connect();
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "EditCA Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://paralelo1.poo.sossesolidario.activities/http/host/path")
//        );
//        AppIndex.AppIndexApi.start(client, viewAction);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.
//        Action viewAction = Action.newAction(
//                Action.TYPE_VIEW, // TODO: choose an action type.
//                "EditCA Page", // TODO: Define a title for the content shown.
//                // TODO: If you have web page content that matches this app activity's content,
//                // make sure this auto-generated web page URL is correct.
//                // Otherwise, set the URL to null.
//                Uri.parse("http://host/path"),
//                // TODO: Make sure this auto-generated app URL is correct.
//                Uri.parse("android-app://paralelo1.poo.sossesolidario.activities/http/host/path")
//        );
//
//        AppIndex.AppIndexApi.end(client, viewAction);
//        client.disconnect();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btnEliminar:
                borrar(ca.getId());
                return true;

            case R.id.btnActualizar:
                guardar();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}