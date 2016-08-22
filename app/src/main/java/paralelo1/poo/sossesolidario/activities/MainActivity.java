package paralelo1.poo.sossesolidario.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import paralelo1.poo.sossesolidario.fragments.NecesidadFragment;
import paralelo1.poo.sossesolidario.R;
import paralelo1.poo.sossesolidario.fragments.CAFragment;
import paralelo1.poo.sossesolidario.fragments.MisDonacionesFragment;
import paralelo1.poo.sossesolidario.objects.CA;
import paralelo1.poo.sossesolidario.objects.Necesidad;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CAFragment.OnFragmentInteractionListener, NecesidadFragment.OnListFragmentInteractionListener, MisDonacionesFragment.OnFragmentInteractionListener, AdminCA.OnFragmentInteractionListener {

    private boolean isAdmin;

    protected View headerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = getSharedPreferences("sos",Context.MODE_PRIVATE);
        String correo= sharedPref.getString("correo","noExiste");

        String nombre= sharedPref.getString("nombre","noExixte");
        isAdmin= sharedPref.getBoolean("tipo",false);
        if (correo.equals("noExiste")) {
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        assert drawer != null;
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        if(isAdmin){
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main_drawer_admin);
        }
        navigationView.setNavigationItemSelectedListener(this);
        headerLayout = navigationView.getHeaderView(0);



        TextView menu_nombre = (TextView)headerLayout.findViewById(R.id.menu_nombre);
        TextView menu_correo = (TextView)headerLayout.findViewById(R.id.menu_correo);


        if (isAdmin){
            nombre= nombre+ "  Admi";
        }
        menu_nombre.setText(nombre);
        menu_correo.setText(correo);

        CAFragment ca = CAFragment.newInstance();
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, ca).commit();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        SharedPreferences sharedPref = getSharedPreferences("sos",Context.MODE_PRIVATE);
        String correo= sharedPref.getString("correo","noExiste");
        //ya



        String nombre= sharedPref.getString("nombre","noExixte");
        boolean tipo= sharedPref.getBoolean("tipo",false);

        TextView menu_nombre = (TextView)headerLayout.findViewById(R.id.menu_nombre);
        TextView menu_correo = (TextView)headerLayout.findViewById(R.id.menu_correo);


        if (tipo){
            nombre= nombre+ "  (Admin)";
        }
        menu_nombre.setText(nombre);
        menu_correo.setText(correo);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                return true;
            case R.id.cerrar_sesion:

                getSharedPreferences("sos",Context.MODE_PRIVATE).edit().clear().commit();

                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);



                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        Class fragmentClass;
        switch (id) {
            case R.id.nav_ca:
                fragmentClass = CAFragment.class;
                break;
            case R.id.nav_donar:
                fragmentClass = NecesidadFragment.class;
                break;
            case R.id.nav_mis_donaciones:
                fragmentClass = MisDonacionesFragment.class;
                break;
            case R.id.nav_admin_ca:
                fragmentClass = AdminCA.class;
                break;
            default:
                fragmentClass = CAFragment.class;
                break;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        item.setChecked(true);
        // Set action bar title
        setTitle(item.getTitle());
        // Close the navigation drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void abrirCA(CA centro) {
        Intent i = new Intent(this, DetalleCentroAcopio.class);
        i.putExtra("nombre", centro);
        startActivity(i);
    }

    @Override
    public void onListFragmentInteraction(Necesidad item) {
        Intent i = new Intent(getApplicationContext(),DonarActivity.class);
        i.putExtra("cosa",item);
        startActivity(i);
    }
}