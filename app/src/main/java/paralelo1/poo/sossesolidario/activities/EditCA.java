package paralelo1.poo.sossesolidario.activities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.strongloop.android.loopback.callbacks.VoidCallback;

import paralelo1.poo.sossesolidario.R;
import paralelo1.poo.sossesolidario.objects.CA;

public class EditCA extends Activity {
    EditText eText1, eText2, eText3, eText4, eText5;
    Button btn1, btn2;
    Intent intent = getIntent();
    Bundle extra = intent.getExtras();
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
        eText1 = (EditText) findViewById(R.id.edit_nombre);
        eText2 = (EditText) findViewById(R.id.edit_descripcion);
        eText3 = (EditText) findViewById(R.id.edit_direccion);
        eText4 = (EditText) findViewById(R.id.edit_facebook);
        eText5 = (EditText) findViewById(R.id.edit_twitter);
        btn1 = (Button) findViewById(R.id.btnActualizar);
        btn2 = (Button) findViewById(R.id.btnEliminar);


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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar,if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setEdicion(boolean opcion)
    {
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

        ca.save(new VoidCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Throwable t) {

            }
        });

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
}