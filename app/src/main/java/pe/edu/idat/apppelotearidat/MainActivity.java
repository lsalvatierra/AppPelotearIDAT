package pe.edu.idat.apppelotearidat;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
implements FragmentDashboard.OnFragmentInteractionListener,
FragmentPerfil.OnFragmentInteractionListener,
FragmentReserva.OnFragmentInteractionListener{

    FragmentManager adminFragment
            = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            /*FragmentManager adminFragment
                    = getSupportFragmentManager();
                    */

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    adminFragment.beginTransaction()
                            .replace(R.id.main_container,
                                    new FragmentReserva()).commit();
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    adminFragment.beginTransaction()
                            .replace(R.id.main_container,
                                    new FragmentDashboard()).commit();
                    return true;
                case R.id.navigation_notifications:
                    //mTextMessage.setText(R.string.title_notifications);
                    adminFragment.beginTransaction()
                            .replace(R.id.main_container,
                                    new FragmentPerfil()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adminFragment.beginTransaction()
                .add(R.id.main_container,
                        new FragmentDashboard()).commit();


        //mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
