package com.example.taskreminber;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Schedule {
    private String name;
    private String expected_start_date;
    private String expected_end_date;
    private String real_start_date;
    private String real_end_date;
    private String note;
    private boolean status;
    private boolean report;
    private boolean used;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpected_start_date() {
        return expected_start_date;
    }

    public void setExpected_start_date(String expected_start_date) {
        this.expected_start_date = expected_start_date;
    }

    public String getExpected_end_date() {
        return expected_end_date;
    }

    public void setExpected_end_date(String expected_end_date) {
        this.expected_end_date = expected_end_date;
    }

    public String getReal_start_date() {
        return real_start_date;
    }

    public void setReal_start_date(String real_start_date) {
        this.real_start_date = real_start_date;
    }

    public String getReal_end_date() {
        return real_end_date;
    }

    public void setReal_end_date(String real_end_date) {
        this.real_end_date = real_end_date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isReport() {
        return report;
    }

    public void setReport(boolean report) {
        this.report = report;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Schedule(String name, String expected_start_date, String expected_end_date,
                    String real_start_date, String real_end_date, String note, boolean status,
                    boolean report, boolean used) {
        this.name = name;
        this.expected_start_date = expected_start_date;
        this.expected_end_date = expected_end_date;
        this.real_start_date = real_start_date;
        this.real_end_date = real_end_date;
        this.note = note;
        this.status = status;
        this.report = report;
        this.used = used;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "name='" + name + '\'' +
                ", expected_start_date='" + expected_start_date + '\'' +
                ", expected_end_date='" + expected_end_date + '\'' +
                ", real_start_date='" + real_start_date + '\'' +
                ", real_end_date='" + real_end_date + '\'' +
                ", note='" + note + '\'' +
                ", status=" + status +
                ", report=" + report +
                ", used=" + used +
                '}';
    }
}
