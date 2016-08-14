package paralelo1.poo.sossesolidario;



        import android.os.Bundle;
        import android.app.Activity;
        import android.view.Menu;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

public class EditCA extends Activity {
    EditText eText1, eText2, eText3, eText4, eText5;
    Button btn1, btn2;

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
            public void onClick(View v) {
                String str = eText1.getText().toString();
                Toast msg = Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG);
                msg.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar,if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}