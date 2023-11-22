package com.poniatowska.pkptask;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private LiveData<ArrayList<Station>> stationList;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        stationList = new StationRepository().getAllStationListFromRepo();
    }
    public LiveData<ArrayList<Station>> getStationList(){return stationList;}
}
