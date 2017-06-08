package com.prolificinteractive.materialcalendarview.sample2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.OnWeekChangedListener;
import com.prolificinteractive.materialcalendarview.WeekDayRange;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Shows off the most basic usage
 */
public class BasicActivity extends AppCompatActivity implements OnDateSelectedListener, OnMonthChangedListener, OnWeekChangedListener {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    @BindView(R.id.calendarView)
    MaterialCalendarView widget;

    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        ButterKnife.bind(this);

        widget.setOnDateChangedListener(this);
        widget.setOnMonthChangedListener(this);
        widget.setOnWeekChangedListener(this);
        widget.setDynamicHeightEnabled(true);
        widget.setCurrentDate(CalendarDay.from(Calendar.getInstance()));

        //Setup initial text
        textView.setText(getSelectedDatesString());

        CalendarDay test = CalendarDay.from(Calendar.getInstance());
        widget.setSelectedDate(test);

        Toast.makeText(this, widget.getInitStartDate() + "", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, widget.getInitEndDate() + "", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @Nullable CalendarDay date, boolean selected) {
        textView.setText(getSelectedDatesString());
    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        //noinspection ConstantConditions
        getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));
    }

    private String getSelectedDatesString() {
        CalendarDay date = widget.getSelectedDate();
        if (date == null) {
            return "No Selection";
        }
        return FORMATTER.format(date.getDate());
    }

    @Override
    public void onWeekChanged(MaterialCalendarView view, WeekDayRange weekDayRange) {
        Toast.makeText(this, weekDayRange.startDate + " - " + weekDayRange.endDate, Toast.LENGTH_SHORT).show();
    }
}
