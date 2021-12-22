package com.example.justreadyproject;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.justreadyproject.databinding.ActivityFestivalMap2Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;

public class FestivalMap extends FragmentActivity implements OnMapReadyCallback {


    HashMap<String, String> markerMap = new HashMap<String, String>();
    private GoogleMap mMap;
    private ActivityFestivalMap2Binding binding;
    FusedLocationProviderClient client;
    double tvLatitude, tvLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFestivalMap2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));

        //Palco Principal


        LatLng PalcoPrincipal = new LatLng(37.555178285535796, -8.73184879007199);
        Marker markerOne = googleMap.addMarker(new MarkerOptions().position(PalcoPrincipal)
                .title("Palco Principal")
                .snippet("Recinto Principal"));
        String idOne = markerOne.getId();
        markerMap.put(idOne, "action_one");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(PalcoPrincipal)));


        //Palco Lg by MegaHits

        LatLng PalcoLGbyMegaHits = new LatLng(37.55487208820666, -8.731011940856725);
        Marker markerTwo = googleMap.addMarker(new MarkerOptions().position(PalcoLGbyMegaHits)
                .title("Palco Lg By MegaHits")
                .snippet("Recinto Principal"));
        String idTwo = markerTwo.getId();
        markerMap.put(idTwo, "action_two");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(PalcoLGbyMegaHits)));

        //Palco Moche Ring
        LatLng MocheRing = new LatLng(37.55436175652892, -8.730411126035508);
        Marker marker3 = googleMap.addMarker(new MarkerOptions().position(MocheRing)
                .title("Moche Ring")
                .snippet("Recinto Principal"));
        String id3 = marker3.getId();
        markerMap.put(id3, "action_three");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(MocheRing)));

        LatLng PalcoEDP = new LatLng(37.55520380190419, -8.731172873402626);
        Marker marker4 = googleMap.addMarker(new MarkerOptions().position(PalcoEDP)
                .title("PalcoEDP")
                .snippet("Recinto Principal"));
        String id4 = marker4.getId();
        markerMap.put(id4, "action_four");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(PalcoEDP)));

        LatLng ZonaVIP = new LatLng(37.55465670777997, -8.73219547013531);
        Marker marker5 = googleMap.addMarker(new MarkerOptions().position(ZonaVIP)
                .title("Zona VIP")
                .snippet("Recinto Principal"));
        String id5 = marker5.getId();
        markerMap.put(id5, "action_five");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(ZonaVIP)));

        LatLng Policia = new LatLng(37.5558993389624, -8.730663834381128);
        Marker marker6 = googleMap.addMarker(new MarkerOptions().position(Policia)
                .title("Policia")
                .snippet("Recinto Principal"));
        String id6 = marker5.getId();
        markerMap.put(id6, "action_six");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(Policia)));

        LatLng PostoMedico = new LatLng(37.555501699238455, -8.730654877446895);
        Marker marker7 = googleMap.addMarker(new MarkerOptions().position(PostoMedico)
                .title("Posto Medico")
                .snippet("Recinto Principal"));
        String id7 = marker7.getId();
        markerMap.put(id7, "action_seven");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(PostoMedico)));

        LatLng Merchandising = new LatLng(37.55434427153711, -8.731156465764053);
        Marker marker8 = googleMap.addMarker(new MarkerOptions().position(Merchandising)
                .title("Merchandising")
                .snippet("Recinto Principal"));
        String id8 = marker8.getId();
        markerMap.put(id8, "action_eight");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(Merchandising)));

        LatLng EntradadoCamping = new LatLng(37.55353477154171, -8.729705442417986);
        Marker marker9 = googleMap.addMarker(new MarkerOptions().position(EntradadoCamping)
                .title("Entrada do Camping")
                .snippet("Recinto Principal"));
        String id9 = marker9.getId();
        markerMap.put(id9, "action_nine");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(EntradadoCamping)));

        LatLng Multibanco = new LatLng(37.553300440954594, -8.729508389864817);
        Marker marker10 = googleMap.addMarker(new MarkerOptions().position(Multibanco)
                .title("Multibanco")
                .snippet("Recinto Principal"));
        String id10 = marker10.getId();
        markerMap.put(id10, "action_10");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(Multibanco)));

        LatLng Restauracao = new LatLng(37.55337267763061, -8.72805004601674);
        Marker marker11 = googleMap.addMarker(new MarkerOptions().position(Restauracao)
                .title("Restauração")
                .snippet("Recinto Principal"));
        String id11 = marker11.getId();
        markerMap.put(id11, "action_11");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(Restauracao)));

        LatLng CarCamping = new LatLng(37.55275914280232, -8.728504998588788);
        Marker marker12 = googleMap.addMarker(new MarkerOptions().position(CarCamping)
                .title("Car Camping")
                .snippet("Recinto Principal"));
        String id12 = marker12.getId();
        markerMap.put(id12, "action_12");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(CarCamping)));

        LatLng EntradaCamping2 = new LatLng(37.55220881406023, -8.728294510634335);
        Marker marker13 = googleMap.addMarker(new MarkerOptions().position(EntradaCamping2)
                .title("Entrada Camping 2")
                .snippet("Recinto Principal"));
        String id13 = marker13.getId();
        markerMap.put(id13, "action_13");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(EntradaCamping2)));

        LatLng PalcoSuperBock = new LatLng(37.55199223195271, -8.72790040553029);
        Marker marker14 = googleMap.addMarker(new MarkerOptions().position(PalcoSuperBock)
                .title("Palco Super Bock")
                .snippet("Recinto Principal"));
        String id14 = marker14.getId();
        markerMap.put(id14, "action_14");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(PalcoSuperBock)));

        LatLng LgCoolSpot = new LatLng(37.55392130455064, -8.726892671904046);
        Marker marker15 = googleMap.addMarker(new MarkerOptions().position(LgCoolSpot)
                .title("Lg Cool Spot")
                .snippet("Recinto Principal"));
        String id15 = marker15.getId();
        markerMap.put(id15, "action_15");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(LgCoolSpot)));

        LatLng CozinhaComunitaria = new LatLng(37.554996284901534, -8.72698193295411);
        Marker marker16 = googleMap.addMarker(new MarkerOptions().position(CozinhaComunitaria)
                .title("Cozinha Comunitaria")
                .snippet("Recinto Principal"));
        String id16 = marker16.getId();
        markerMap.put(id16, "action_16");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(CozinhaComunitaria)));

        LatLng Chuveiros = new LatLng(37.55516975120393, -8.727169485248652);
        Marker marker17 = googleMap.addMarker(new MarkerOptions().position(Chuveiros)
                .title("Chuveiros")
                .snippet("Recinto Principal"));
        String id17 = marker17.getId();
        markerMap.put(id17, "action_17");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(Chuveiros)));

        LatLng NomadCamping = new LatLng(37.55545135148807, -8.726646612185558);
        Marker marker18 = googleMap.addMarker(new MarkerOptions().position(NomadCamping)
                .title("Nomad Camping")
                .snippet("Recinto Principal"));
        String id18 = marker18.getId();
        markerMap.put(id18, "action_18");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(NomadCamping)));

        LatLng PatioSomersby = new LatLng(37.55433395529221, -8.72484213178317);
        Marker marker19 = googleMap.addMarker(new MarkerOptions().position(PatioSomersby)
                .title("Patio Somersby")
                .snippet("Recinto Principal"));
        String id19 = marker19.getId();
        markerMap.put(id19, "action_19");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(PatioSomersby)));

        LatLng Canal = new LatLng(37.55366260838664, -8.724600587161444);
        Marker marker20 = googleMap.addMarker(new MarkerOptions().position(Canal)
                .title("Canal")
                .snippet("Recinto Principal"));
        String id20 = marker20.getId();
        markerMap.put(id20, "action_20");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(Canal)));

        LatLng EstacionamentoZonaExclusiva = new LatLng(37.55495824771236, -8.733299560201232);
        Marker marker21 = googleMap.addMarker(new MarkerOptions().position(EstacionamentoZonaExclusiva)
                .title("Estacionamento Zona Exclusiva")
                .snippet("Recinto Principal"));
        String id21 = marker21.getId();
        markerMap.put(id21, "action_21");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(EstacionamentoZonaExclusiva)));

        LatLng Entrada = new LatLng(37.55268034299549, -8.731344182707902);
        Marker marker22 = googleMap.addMarker(new MarkerOptions().position(Entrada)
                .title("Entrada")
                .snippet("Recinto Principal"));
        String id22 = marker22.getId();
        markerMap.put(id22, "action_22");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(Entrada)));

        LatLng Bilheteira = new LatLng(37.55283080772934, -8.73148652582485);
        Marker marker23 = googleMap.addMarker(new MarkerOptions().position(Bilheteira)
                .title("Bilheteira")
                .snippet("Recinto Principal"));
        String id23 = marker23.getId();
        markerMap.put(id23, "action_23");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(Bilheteira)));

        LatLng Estacionamento = new LatLng(37.552593825635945, -8.73214130416281);
        Marker marker24 = googleMap.addMarker(new MarkerOptions().position(Estacionamento)
                .title("Estacionamento")
                .snippet("Recinto Principal"));
        String id24 = marker24.getId();
        markerMap.put(id24, "action_24");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(Estacionamento)));

        LatLng BusStop = new LatLng(37.55219887653799, -8.734351318141316);
        Marker marker25 = googleMap.addMarker(new MarkerOptions().position(BusStop)
                .title("Bus Stop")
                .snippet("Recinto Principal"));
        String id25 = marker25.getId();
        markerMap.put(id25, "action_25");
        mMap.moveCamera((CameraUpdateFactory.newLatLng(BusStop)));


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                String actionId = markerMap.get(marker.getId());

                if (actionId.equals("action_one")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Palcos.PalcoPrincipal.class);
                    startActivity(i);
                } else if (actionId.equals("action_two")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Palcos.PalcoLGbyMegaHits.class);
                    startActivity(i);
                } else if (actionId.equals("action_three")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Palcos.MocheRing.class);
                    startActivity(i);
                } else if (actionId.equals("action_four")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Palcos.PalcoEDP.class);
                    startActivity(i);
                } else if (actionId.equals("action_five")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Recinto.ZonaVIP.class);
                    startActivity(i);
                } else if (actionId.equals("action_nine")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Camping.EntradadoCamping.class);
                    startActivity(i);
                } else if (actionId.equals("action_11")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Camping.Restauracao.class);
                    startActivity(i);
                } else if (actionId.equals("action_12")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Camping.CarCamping.class);
                    startActivity(i);
                } else if (actionId.equals("action_14")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Palcos.PalcoSuperBock.class);
                    startActivity(i);
                } else if (actionId.equals("action_15")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Camping.LgCoolSpot.class);
                    startActivity(i);
                } else if (actionId.equals("action_16")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Camping.CozinhaComunitaria.class);
                    startActivity(i);
                } else if (actionId.equals("action_17")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Camping.Chuveiros.class);
                    startActivity(i);
                } else if (actionId.equals("action_18")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Camping.NomadCamping.class);
                    startActivity(i);
                } else if (actionId.equals("action_19")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Camping.PatioSomersby.class);
                    startActivity(i);
                } else if (actionId.equals("action_20")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Camping.Canal.class);
                    startActivity(i);
                } else if (actionId.equals("action_22")) {
                    Intent i = new Intent(FestivalMap.this
                            , com.example.justreadyproject.Recinto.Entrada.class);
                    startActivity(i);
                } else if (actionId.equals("action_25")) {
                    Intent i = new Intent(FestivalMap.this, com.example.justreadyproject.Recinto.BusStop.class);
                    startActivity(i);
                }

                return false;
            }
        });

    }

}