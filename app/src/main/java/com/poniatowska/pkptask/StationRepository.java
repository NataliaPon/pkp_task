package com.poniatowska.pkptask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StationRepository {
    private MutableLiveData<ArrayList<Station>> allStationListFromRepo = new MutableLiveData<>();

    private final String jsonStation = "[{\"ibnrCode\":5555,\"epaCode\":2121,\"name\":\"Zakopane\",\"stationType\":\"normalna\"},{\"ibnrCode\":1111,\"name\":\"Katowice\",\"stationType\":\"metastacja\"},{\"ibnrCode\":3344,\"epaCode\":3213,\"name\":\"Sopot\",\"stationType\":\"normalna\"},{\"ibnrCode\":9966,\"name\":\"Gdynia\",\"stationType\":\"normalna\"}]";

    public StationRepository(){
        ArrayList<Station> stationList = parseJsonToArray(jsonStation);

        stationList.add(new Station(1234,1111,"Warszawa",StationType.metastacja));
        stationList.add(new Station(2020,1222,"Gdańsk",StationType.normalna));
        stationList.add(new Station(2442,"Toruń",StationType.normalna));
        stationList.add(new Station(8888, 3333, "Szczecin",StationType.metastacja));
        stationList.add(new Station(7878, 5555, "Kołobrzeg",StationType.metastacja));
        stationList.add(new Station(1313,"Radom",StationType.normalna));

        allStationListFromRepo.postValue(stationList);
    }
    public LiveData<ArrayList<Station>> getAllStationListFromRepo(){
        return allStationListFromRepo;
    }

    public ArrayList<Station> parseJsonToArray(String jsonString){
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Station>>(){}.getType();
        try {
            return gson.fromJson(jsonString, listType);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
