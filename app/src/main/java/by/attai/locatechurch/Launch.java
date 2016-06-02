package by.attai.locatechurch;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class Launch extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final String DB_URL = "https://locatemass-v1.firebaseio.com/City_1/Church_1";
    Button buttonSave;
    EditText editChurchName, editLocation, editRite, editAddress, editGPS, editMassTimes, editOtherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ArrayList<SearchResults> searchResults = GetSearchResults();

        final ListView lv1 = (ListView) findViewById(R.id.ListView01);
        lv1.setAdapter(new MyCustomBaseAdapter(this, searchResults));

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv1.getItemAtPosition(position);
                SearchResults fullObject = (SearchResults) o;
                Toast.makeText(Launch.this, "You have chosen: " + " " + fullObject.getName(), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setTitle("Add New Church");
        dialog.setContentView(R.layout.dialog_layout);
        editChurchName = (EditText) findViewById(R.id.editChurchName);
        editLocation = (EditText) findViewById(R.id.editLocation);
        editRite = (EditText) findViewById(R.id.editRite);
        editAddress = (EditText) findViewById(R.id.editAddress);
        editGPS = (EditText) findViewById(R.id.editGPS);
        editMassTimes = (EditText) findViewById(R.id.editMassTimes);
        editOtherService = (EditText) findViewById(R.id.editOtherServices);

        dialog.show();
    }

    /*
        private void populateUserInfoList() {
            userInfoList = new ArrayList<Churches>();
            firebase = new Firebase("https://....firebaseio.com");
            firebase.child("users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    HashMap<String, Object> users = (HashMap<String, Object>) snapshot.getValue();
                    for (Object user : users.values()) {
                        HashMap<String, Object> userMap = (HashMap<String, Object>) user;
                        String userNumber = (String) userMap.remove("number");
                        if (!userInfoList.contains(userNumber)) {
                            String name = (String) userMap.remove("username");
                            String pic = (String) userMap.remove("profile_picture");
                            UserInfo info = new UserInfo(userNumber, name, pic);
                            userInfoList.add(info);
                        }
                    }
                    // thread executing here can get info from database and make subsequent call
                    Collections.addAll(userInfoList);
                    populateFriendsListView();
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    String message = "Server error. Refresh page";
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.launch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private ArrayList<SearchResults> GetSearchResults() {
        ArrayList<SearchResults> results = new ArrayList<>();

        SearchResults sr1 = new SearchResults();
        sr1.setName("John Smith");
        sr1.setCityState("Dallas, TX");
        sr1.setPhone("214-555-1234");
        results.add(sr1);

        sr1 = new SearchResults();
        sr1.setName("Jane Doe");
        sr1.setCityState("Atlanta, GA");
        sr1.setPhone("469-555-2587");
        results.add(sr1);

        sr1 = new SearchResults();
        sr1.setName("Steve Young");
        sr1.setCityState("Miami, FL");
        sr1.setPhone("305-555-7895");
        results.add(sr1);

        sr1 = new SearchResults();
        sr1.setName("Fred Jones");
        sr1.setCityState("Las Vegas, NV");
        sr1.setPhone("612-555-8214");
        results.add(sr1);

        return results;
    }

    public void saveChurchDetails(View view) {
        Firebase myFirebaseRef = new Firebase(DB_URL);
        Firebase.setAndroidContext(getApplicationContext());

        myFirebaseRef.child("church_name").setValue(editChurchName.getText().toString());
        myFirebaseRef.child("location").setValue(editLocation.getText().toString());
        myFirebaseRef.child("rite").setValue(editRite.getText().toString());
        myFirebaseRef.child("address").setValue(editAddress.getText().toString());
        myFirebaseRef.child("gps").setValue(editGPS.getText().toString());
        myFirebaseRef.child("mass_times").setValue(editGPS.getText().toString());
        myFirebaseRef.child("other_services").setValue(editOtherService.getText().toString());
        Toast.makeText(getApplicationContext(), "New Church Added", Toast.LENGTH_SHORT).show();
    }
}
