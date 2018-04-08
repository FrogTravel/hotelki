package nekono.inno.hotelki;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapFragment;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapOverlayType;
import com.here.android.mpa.mapping.MapScreenMarker;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2011-2017 HERE Europe B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * This class encapsulates the properties and functionality of the Map view.
 */
public class MapFragmentView {
    private MapFragment m_mapFragment;
    private Activity m_activity;
    private Map m_map;
    private Image m_marker_image;
    private List<MapMarker> mapScreenMarkers;
    private boolean isMarker = false;


    public MapFragmentView(Activity activity) {
        m_activity = activity;
        initMapFragment();

        mapScreenMarkers = new ArrayList<>();
    }


    private void initMapFragment() {
        /* Locate the mapFragment UI element */
        m_mapFragment = (MapFragment) m_activity.getFragmentManager()
                .findFragmentById(R.id.mapfragment);

        // Set path of isolated disk cache
        String diskCacheRoot = Environment.getExternalStorageDirectory().getPath()
                + File.separator + ".isolated-here-maps";
        // Retrieve intent name from manifest
        String intentName = "";
        try {
            ApplicationInfo ai = m_activity.getPackageManager().getApplicationInfo(m_activity.getPackageName(),
                    PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            intentName = bundle.getString("INTENT_NAME");
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(this.getClass().toString(), "Failed to find intent name, NameNotFound: " + e.getMessage());
        }

        boolean success = com.here.android.mpa.common.MapSettings.setIsolatedDiskCacheRootPath(diskCacheRoot,
                intentName);
        if (!success) {
            // Setting the isolated disk cache was not successful, please check if the path is valid and
            // ensure that it does not match the default location
            // (getExternalStorageDirectory()/.here-maps).
            // Also, ensure the provided intent name does not match the default intent name.
            Log.d("Test", "Fail success" + intentName);

        } else {
            if (m_mapFragment != null) {
            /* Initialize the MapFragment, results will be given via the called back. */
                m_mapFragment.init(new OnEngineInitListener() {
                    @Override
                    public void onEngineInitializationCompleted(OnEngineInitListener.Error error) {

                        if (error == Error.NONE) {
                        /*
                         * If no error returned from map fragment initialization, the map will be
                         * rendered on screen at this moment.Further actions on map can be provided
                         * by calling Map APIs.
                         */
                            Log.d("Test", "Map is ready");

                            m_map = m_mapFragment.getMap();

                            m_marker_image = new Image();

                            try {
                                m_marker_image.setImageResource(R.drawable.marker);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            m_mapFragment.getMapGesture()
                                    .addOnGestureListener(new MapGesture.OnGestureListener() {
                                        @Override
                                        public void onPanStart() {
                                        }

                                        @Override
                                        public void onPanEnd() {
                                        /* show toast message for onPanEnd gesture callback */

                                        }

                                        @Override
                                        public void onMultiFingerManipulationStart() {

                                        }

                                        @Override
                                        public void onMultiFingerManipulationEnd() {

                                        }

                                        @Override
                                        public boolean onMapObjectsSelected(List<ViewObject> list) {
                                            for (ViewObject viewObj : list) {
                                                if (viewObj.getBaseType() == ViewObject.Type.USER_OBJECT) {
                                                    if (((MapObject) viewObj).getType() == MapObject.Type.MARKER) {
                                                        // At this point we have the originally added
                                                        // map marker, so we can do something with it
                                                        // (like change the visibility, or more
                                                        // marker-specific actions)
                                                        MapMarker marker = (MapMarker) viewObj;

                                                        DialogFragment dialogFragment = new DescriptionDialogFragment();

                                                        Bundle bundle = new Bundle();
                                                        bundle.putDouble("lat", marker.getCoordinate().getLatitude());
                                                        bundle.putDouble("lng", marker.getCoordinate().getLongitude());

                                                        dialogFragment.setArguments(bundle);
                                                        dialogFragment.show(m_mapFragment.getActivity().getFragmentManager(), "mysupertag");
                                                    }

                                                    isMarker = true;

                                                }
                                            }

                                            return false;
                                        }

                                        @Override
                                        public boolean onTapEvent(PointF pointF) {
                                        /* show toast message for onPanEnd gesture callback */
                                            // showMsg("onTapEvent");
//                                            if(isMarker){
//                                                showMsg("MARKER TAPPED!!!!");
//                                                isMarker = false;
//                                            }

                                            return false;
                                        }

                                        @Override
                                        public boolean onDoubleTapEvent(PointF pointF) {
                                            return false;
                                        }

                                        @Override
                                        public void onPinchLocked() {

                                        }

                                        @Override
                                        public boolean onPinchZoomEvent(float v, PointF pointF) {
                                            return false;
                                        }

                                        @Override
                                        public void onRotateLocked() {

                                        }

                                        @Override
                                        public boolean onRotateEvent(float v) {
                                        /* show toast message for onRotateEvent gesture callback */
                                            return false;
                                        }

                                        @Override
                                        public boolean onTiltEvent(float v) {
                                            return false;
                                        }

                                        @Override
                                        public boolean onLongPressEvent(PointF pointF) {

                                            MapMarker m_tap_marker = new MapMarker(m_map.pixelToGeo(pointF),
                                                    m_marker_image);

                                            m_map.addMapObject(m_tap_marker);


                                            Intent intent = new Intent(m_mapFragment.getActivity(), IdeaAddActivity.class);
                                            intent.putExtra("lat", m_map.pixelToGeo(pointF).getLatitude());
                                            intent.putExtra("lng", m_map.pixelToGeo(pointF).getLongitude());

                                            m_mapFragment.startActivity(intent);
                                            return false;
                                        }

                                        @Override
                                        public void onLongPressRelease() {

                                        }

                                        @Override
                                        public boolean onTwoFingerTapEvent(PointF pointF) {
                                            return false;
                                        }
                                    });

                            /*
                             * Map center can be set to a desired location at this point.
                             * It also can be set to the current location ,which needs to be delivered by the PositioningManager.
                             * Please refer to the user guide for how to get the real-time location.
                             */

                            m_map.setCenter(new GeoCoordinate(49.258576, -123.008268), Map.Animation.NONE);

                        } else {
                            Toast.makeText(m_activity,
                                    "ERROR: Cannot initialize Map with error " + error,
                                    Toast.LENGTH_LONG).show();
                            Log.d("Test", "Some error: " + error);

                        }
                    }
                });
            }
        }

    }

    private void showMsg(String msg) {
        final Toast msgToast = Toast.makeText(m_activity, msg, Toast.LENGTH_SHORT);

        msgToast.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                msgToast.cancel();
            }
        }, 1000);

    }


}