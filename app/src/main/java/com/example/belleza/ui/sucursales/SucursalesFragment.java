package com.example.belleza.ui.sucursales;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.belleza.FormMapsActivity;
import com.example.belleza.R;
import com.example.belleza.casos_uso.CasoUsoSucursal;
import com.example.belleza.databinding.FragmentSucursalesBinding;
import com.example.belleza.datos.ApiOracle;
import com.example.belleza.datos.DBHelper;
import com.example.belleza.modelos.Sucursal;

import java.util.ArrayList;

public class SucursalesFragment extends Fragment {

    private FragmentSucursalesBinding binding;

    private String TABLE_NAME = "SUCURSALES";
    private CasoUsoSucursal casoUsoSucursal;
    private GridView gridView;
    private ProgressBar progressBar;
    private DBHelper dbHelper;
    private ApiOracle apiOracle;
    private ArrayList<Sucursal> sucursales;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_sucursales, container,false);
        try{
            casoUsoSucursal = new CasoUsoSucursal();
            apiOracle = new ApiOracle(root.getContext());
            gridView = (GridView) root.findViewById(R.id.gridSucursal);
            progressBar = (ProgressBar) root.findViewById(R.id.progressBarSuc);
            apiOracle.getAllSucursales(gridView, progressBar);

            // ****** SQLITE ******
            //dbHelper = new DBHelper(getContext());
            //Cursor cursor = dbHelper.getData(TABLE_NAME);
            //sucursales = casoUsoSucursal.llenarListaSucursales(cursor);
            //SucursalAdapter sucursalAdapter = new SucursalAdapter(root.getContext(), sucursales);
            //gridView.setAdapter(sucursalAdapter);
            // ****** ****** ******


        }catch (Exception e){
            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
            Log.w("Error ->>>", e.toString());
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                try {
                    Intent intent = new Intent(getContext(), FormMapsActivity.class);
                    getActivity().startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

                //Toast.makeText(getContext(), "Hola Sucursales", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
