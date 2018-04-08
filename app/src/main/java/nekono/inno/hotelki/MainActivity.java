package nekono.inno.hotelki;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private MapFragmentView m_mapFragmentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions();
    }

    /**
     * Only when the app's target SDK is 23 or higher, it requests each dangerous permissions it
     * needs when the app is running.
     */
    private void requestPermissions() {

        String[] RUNTIME_PERMISSIONS = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE};

        // check list of permissions
        boolean isPermissionsGranted = true;
        for (String permission : RUNTIME_PERMISSIONS) {
            Log.d("Test", "RUNTIME_PERMISSIONS");

            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                isPermissionsGranted = false;
                break;
            }
        }

//         return if all permissions already granted
        if (isPermissionsGranted) {
            Log.d("Test", "Permission Granted");
            createMapFragmentView();
            return;
        }
    }

//        @Override
//        public boolean onOptionsItemSelected(MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.action_settings:
//                    // User chose the "Settings" item, show the app settings UI...
//                    return true;
//
//                case R.id.action_favorite:
//                    // User chose the "Favorite" action, mark the current item
//                    // as a favorite...
//                    return true;
//
//                default:
//                    // If we got here, the user's action was not recognized.
//                    // Invoke the superclass to handle it.
//                    return super.onOptionsItemSelected(item);
//
//                // request permissions
//                if (android.os.Build.VERSION.SDK_INT >= 23) {
//                    ActivityCompat.requestPermissions(this,
//                            RUNTIME_PERMISSIONS,
//                            REQUEST_CODE_ASK_PERMISSIONS);
//
//                } else {
//                    Log.d("Test", "SDK FAIL");
//
//                }
//
//            }
//        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_business_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        startActivity(new Intent(this, PopItemsActivity.class));
        return true;
    }


    /**
     * Create map fragment view.
     * !!! Please note: the HERE SDK requires all permissions defined above to operate properly. !!!
     */
    void createMapFragmentView() {
        m_mapFragmentView = new MapFragmentView(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS: {
                for (int index = 0; index < permissions.length; index++) {
                    if (grantResults[index] != PackageManager.PERMISSION_GRANTED) {

                        /**
                         * If the user turned down the permission request in the past and chose the
                         * Don't ask again option in the permission request system dialog.
                         */
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                                permissions[index])) {
                            Toast.makeText(this,
                                    "Required permission " + permissions[index] + " not granted. "
                                            + "Please go to settings and turn on for sample app",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(this,
                                    "Required permission " + permissions[index] + " not granted",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                }

                /*
                 * All permission requests are being handled. Create map fragment view.
                 */
                createMapFragmentView();
                break;
            }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
