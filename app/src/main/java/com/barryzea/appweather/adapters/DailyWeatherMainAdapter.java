package com.barryzea.appweather.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barryzea.appweather.R;
import com.barryzea.appweather.common.Constants;
import com.barryzea.appweather.interfaces.OnClickInterface;
import com.barryzea.appweather.utils.Helpers;
import com.barryzea.appweather.databinding.ItemDayForecastMainBinding;
import com.barryzea.appweather.model.Daily;
import com.barryzea.appweather.myApp.myApp;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class DailyWeatherMainAdapter extends RecyclerView.Adapter<DailyWeatherMainAdapter.ViewHolder> {

    private ArrayList<Daily> dailyList=new ArrayList<>();
    private Context ctx;
    private ItemDayForecastMainBinding bind;
    private OnClickInterface onClick;

    public DailyWeatherMainAdapter(ArrayList<Daily> dailyList, Context ctx, OnClickInterface onClick) {
        this.dailyList = dailyList;
        this.ctx = ctx;
        this.onClick=onClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder;
        bind=ItemDayForecastMainBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        holder=new ViewHolder(bind);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(dailyList.get(position), ctx, onClick);
    }

    @Override
    public int getItemCount() {
        if(dailyList.size()>0){
            return dailyList.size();
        }
        else{
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemDayForecastMainBinding bind;
        String urlIcon="http://openweathermap.org/img/wn/";
        String fileType="@2x.png";
        String systemMeasure="";
        public ViewHolder(@NonNull ItemDayForecastMainBinding bind) {
            super(bind.getRoot());
            this.bind=bind;
            systemMeasure= myApp.myDefaultPrefs.getString(Constants.MEASURE_SYSTEM_KEY,Constants.METRIC_MEASURE);

        }
        public void onBind(Daily day, Context ctx, OnClickInterface onClickInterface){

            SimpleDateFormat myFormatCalendar=new SimpleDateFormat("dd/MM/yy", Locale.ROOT);
            Calendar calendar=Calendar.getInstance();

            String icon=day.getIcon();
            String urlComplete= urlIcon + icon + fileType;
            bind.tvMainNameDay.setText(Helpers.getNameDay(day.getDailyName(),ctx));
            if(myFormatCalendar.format(calendar.getTime()).equals(day.getDateFormatCalendar())){
                bind.tvMainNameDay.setText(ctx.getString(R.string.today));
            }
            Glide.with(ctx).load(urlComplete).into(bind.ivMainWeatherIconDay);
            if(systemMeasure.equals(Constants.METRIC_MEASURE)) {
                bind.tvMainDegreesDays.setText(String.format("%s°c", day.getDayTemp()));
            }
            else{
                bind.tvMainDegreesDays.setText(String.format("%s°f", day.getDayTemp()));

            }
            bind.getRoot().setOnClickListener(v->{onClickInterface.onClick(day);});
        }
    }
}
