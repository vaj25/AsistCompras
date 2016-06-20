package gr23.sv.ues.fia.asistcompras;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment1 extends Fragment{

    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Intent inte = new Intent(Fragment1.this, LugarConsultarActivity.class);
        //startActivity(inte);
        return inflater.inflate(R.layout.activity_nueva_oferta, container, false);
    }
}