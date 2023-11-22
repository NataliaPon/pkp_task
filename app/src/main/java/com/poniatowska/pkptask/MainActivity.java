package com.poniatowska.pkptask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.poniatowska.pkptask.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context context;
    private StationListAdapter stationListAdapter;
    private MainActivityViewModel mainActivityViewModel;
    private Station checkedStation;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        context = this;

        setSupportActionBar(binding.toolbar);
        initRecyclerView();

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getStationList().observe(this, stationArrayList -> stationListAdapter.setData(stationArrayList));

        binding.searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ArrayList<Station> tempStationArrayList = mainActivityViewModel.getStationList().getValue();
                ArrayList<Station> filteredStationArrayList = new ArrayList<>();

                if(editable.length()>0) {
                    for (int j = 0; j < tempStationArrayList.size(); j++) {
                        if (tempStationArrayList.get(j).getName().toLowerCase().contains(editable.toString().toLowerCase()) || String.valueOf(tempStationArrayList.get(j).getIbnrCode()).contains(editable)) {
                            filteredStationArrayList.add(tempStationArrayList.get(j));
                        }
                        if (tempStationArrayList.get(j).getEpaCode() != null && String.valueOf(tempStationArrayList.get(j).getEpaCode()).contains(editable)) {
                            filteredStationArrayList.add(tempStationArrayList.get(j));
                        }
                    }
                    stationListAdapter.setData(filteredStationArrayList);
                }
                else{
                    filteredStationArrayList.clear();
                    stationListAdapter.setData(tempStationArrayList);
                }
            }
        });
    }

    private void initRecyclerView(){
        stationListAdapter = new StationListAdapter(context, (itemClicked) -> {
            Toast.makeText(context,"Wybrano stację: "+itemClicked.getName(),Toast.LENGTH_LONG).show();
            checkedStation = itemClicked;
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(stationListAdapter);
    }

}