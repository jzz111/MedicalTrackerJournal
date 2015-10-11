package net.jghost.medicaltrackerjournal.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Journal entry model
 */
public class JournalEntry implements Comparable<JournalEntry> {
    private String title;
    private String content;
    private Calendar date;
    private List<Event> events;
    private List<Metric> metrics;

    public JournalEntry() {
        date = new GregorianCalendar();
        date.setTime(new Date());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
    }

    @Override
    public int compareTo(JournalEntry another) {
        return date.compareTo(another.getDate());
    }
}
