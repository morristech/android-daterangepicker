/*
 * Copyright (C) 2017 Angad Singh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.angads25.daterangepicker.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.angads25.daterangepicker.R;
import com.github.angads25.daterangepicker.model.CalendarGridItem;
import com.github.angads25.daterangepicker.model.Date;
import com.github.angads25.daterangepicker.utils.Utility;

import java.util.ArrayList;

/**
 * <p>
 * Created by Angad Singh on 21/12/17.
 * </p>
 */

public class CalendarPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;

    private ArrayList<Date> dates;
    private Pair<Date, Date> datePair;

    public CalendarPagerAdapter(Context context, ArrayList<Date> dates) {
        this.context = context;
        this.dates = dates;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = inflater.inflate(R.layout.item_calendar, container, false);
        RecyclerView recyclerView = itemView.findViewById(R.id.grid_calender);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 7));
        ArrayList<CalendarGridItem> gridItems = new ArrayList<>();
        int[] days = context.getResources().getIntArray(R.array.days_month);
        String[] days_week = context.getResources().getStringArray(R.array.days_week);
        for (String aDays_week : days_week) {
            CalendarGridItem gridItem = new CalendarGridItem();
            gridItem.setLabel(aDays_week);
            gridItems.add(gridItem);
        }
        for (int day : days) {
            CalendarGridItem gridItem = new CalendarGridItem();
            gridItem.setLabel(String.valueOf(day));
            gridItems.add(gridItem);
        }
        recyclerView.setAdapter(new CalendarGridAdapter(context, gridItems));
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayoutCompat) object);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        int year = Utility.getYearFromPosition(position);
        int month = Utility.getMonthFromPosition(position);

        String monthString = context.getResources().getStringArray(R.array.months)[month];
        return monthString + " " + year;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    public void setDatePair(Pair<Date, Date> datePair) {
        this.datePair = datePair;
    }
}
