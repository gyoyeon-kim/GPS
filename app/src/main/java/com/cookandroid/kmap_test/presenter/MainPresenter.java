package com.cookandroid.kmap_test.presenter;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cookandroid.kmap_test.EditActivity;
import com.cookandroid.kmap_test.contract.EditContract;
import com.github.tlaabs.timetableview.Schedule;

import java.util.ArrayList;
import java.util.Calendar;

import com.cookandroid.kmap_test.MainActivity;
import com.cookandroid.kmap_test.R;
import com.cookandroid.kmap_test.contract.MainContract;
import com.cookandroid.kmap_test.model.PrefManager;

public class MainPresenter implements MainContract.UserActions {
    final MainContract.View mainView;
    private PrefManager prefManager;

    public MainPresenter(MainContract.View mainView){
        this.mainView = mainView;
    }

    @Override
    public void setPrefManager(PrefManager prefManager) {
        this.prefManager = prefManager;
    }

    @Override
    public void addMenuClick() {
        mainView.startEditActivityForAdd();
    }

    @Override
    public void selectSticker(int idx, ArrayList<Schedule> schedules) {
        mainView.startEditActivityForEdit(idx,schedules);
    }









    @Override
    public void prepare() {
        String savedData = prefManager.get((Context)mainView);
        if(savedData == null || savedData.equals("")) return;
        mainView.restoreTimetable(savedData);

        mainView.setDayHighlight(today());
    }

    private int today() {
        /** DAY_OF_WEEK
         * 1 : Sun, 7 : Sat
         */
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK) -1;
        if(day > 0 && day < 6) return day;
        return -1;
    }

    @Override
    public void save(String data) {
        prefManager.save((Context)mainView,data);
    }
}
