package com.poniatowska.pkptask;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
@RunWith(MockitoJUnitRunner.class)
public class StationRepositoryTest {
    @Mock
    StationRepository stationRepository = mock(StationRepository.class);
    @Test
    public void isStationRepositoryParseIsCorrectly(){
        // given
        ArrayList<Station> stationArrayList;
        String jsonStation = "[{\"ibnrCode\":5555,\"epaCode\":2121,\"name\":\"Zakopane\",\"stationType\":\"normalna\"},{\"ibnrCode\":1111,\"name\":\"Katowice\",\"stationType\":\"metastacja\"},{\"ibnrCode\":3344,\"epaCode\":3213,\"name\":\"Sopot\",\"stationType\":\"normalna\"},{\"ibnrCode\":9966,\"name\":\"Gdynia\",\"stationType\":\"normalna\"}]";

        // when
        stationArrayList = stationRepository.parseJsonToArray(jsonStation);

        // then
        assertNotNull(String.valueOf(stationArrayList), "Station list should not be null");
    }

    @Test
    public void isStationRepositoryParseIsCorrectlyResponseWhenWrongData(){
        // given
        ArrayList<Station> stationArrayList;
        String jsonStation = "[{\"ibnrKode\":5555,\"epaKode\":2121,\"name\":\"Zakopane\",\"stationType\":\"normalna\"}]";

        // when
        stationArrayList = stationRepository.parseJsonToArray(jsonStation);

        // then
        assertTrue( "Station list should be empty", stationArrayList.isEmpty());
    }

}