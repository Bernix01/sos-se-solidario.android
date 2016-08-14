package paralelo1.poo.sossesolidario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetalleCentroAcopio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_centro_acopio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView nombre = (TextView) findViewById(R.id.nombre);
        TextView descripcion = (TextView) findViewById(R.id.descripcion);
        TextView direccion = (TextView) findViewById(R.id.direccion);
        TextView fb = (TextView) findViewById(R.id.facebook);
        TextView tw = (TextView) findViewById(R.id.twitter);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            CA value = extras.getParcelable("nombre");
            //The key argument here must match that used in the other activity
            nombre.setText(value.getNombre());
            descripcion.setText(value.getDescripcion());
            direccion.setText(value.getDireccion());
            fb.setText(value.getFb());
            tw.setText(value.getTwitter());
        }


    }
}