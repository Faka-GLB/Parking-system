package com.onboarding.parkingsystemjava.listener;

import java.util.Calendar;

public interface DateTimeListener {
    void sendStartDateTime(Calendar dateTimeCalendar);
    void sendEndDateTime(Calendar dateTimeCalendar);
}
