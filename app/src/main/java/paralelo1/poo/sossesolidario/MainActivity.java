package paralelo1.poo.sossesolidario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView nam;
        nam = (TextView) findViewById(R.id.name);
        nam.setText("ke vin");
    }
}
