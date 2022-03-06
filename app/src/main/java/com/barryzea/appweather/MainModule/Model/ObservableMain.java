package com.barryzea.appweather.MainModule.Model;

import android.app.Activity;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.barryzea.appweather.model.CurrentWeather;
import com.barryzea.appweather.model.Geolocale;
import com.barryzea.appweather.model.LocationMap;
import com.barryzea.appweather.model.Pollution;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;

public class ObservableMain {
    private MainRepositoryInterface mainRepository;

    public ObservableMain() {
        mainRepository=new MainRepositoryClass();
    }
    public void callCurrentWeather(Activity contextActivity, LatLng position){
        mainRepository.callCurrentWeather( contextActivity, position);
    }
    public MutableLiveData<CurrentWeather> getWeatherForecast(){
        return mainRepository.getWeatherForecast();
    }
    public void callPollutionAirQuality(LatLng position){
        mainRepository.callPollutionAirQuality(position);
    }
    public MutableLiveData<Pollution> getPollutionForecast(){
        return mainRepository.getPollutionAirQuality();
    }
    public void callPositionOfLocationSearch(Activity ctx, String positionCity){
        mainRepository.callPositionOfLocationSearch(ctx,positionCity);
    }
    public MutableLiveData<LocationMap> getLocationMapOfSearch(){
        return mainRepository.getLocationMapOfSearch();
    }
    public void callGeocoderLocation(LatLng position)throws IOException {
        mainRepository.callGeocoder(position);
    }
    public MutableLiveData<Geolocale> getGeocoderLocation(){
        return mainRepository.getGeocoder();
    }


}
