package paralelo1.poo.sossesolidario.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import paralelo1.poo.sossesolidario.R;
import paralelo1.poo.sossesolidario.fragments.NecesidadAdapter;

public class DonarActivity extends AppCompatActivity {

    private NecesidadAdapter necesidadAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);

    }
}
