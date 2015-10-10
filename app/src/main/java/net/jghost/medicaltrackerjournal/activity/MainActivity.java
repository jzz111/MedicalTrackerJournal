package net.jghost.medicaltrackerjournal.activity;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import net.jghost.medicaltrackerjournal.R;
import net.jghost.medicaltrackerjournal.adapter.JournalEntryPreviewAdapter;
import net.jghost.medicaltrackerjournal.model.JournalEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        List<JournalEntry> fakeJournalEntries = new ArrayList<JournalEntry>();
        JournalEntry e;
        for (int i = 0; i<10; i++) {
            e = new JournalEntry();
            e.setContent("Fake Content" + i);
            e.setDate(new GregorianCalendar());
            e.getDate().setTime(new Date());
            e.getDate().add(Calendar.DATE, i);
            e.setTitle("My new title");
            fakeJournalEntries.add(e);
        }
        LinearLayout october = displayMonth("October", 2015, fakeJournalEntries);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        addContentView(october, p);
    }

    private LinearLayout displayMonth(String month, int year, List<JournalEntry> entries) {
        JournalEntryPreviewAdapter adapter = new JournalEntryPreviewAdapter(this, R.layout.listview_journal_entry_row, entries);

        LinearLayout container = (LinearLayout) getLayoutInflater().inflate(R.layout.listview_journal_entry, null);
        ListView entryList = (ListView) container.findViewById(android.R.id.list);
        View header = getLayoutInflater().inflate(R.layout.listview_header_journal_month, null);
        TextView month_year = (TextView) header.findViewById(R.id.category_month_year);
        month_year.setText(month + year);
        entryList.addHeaderView(header);
        entryList.setAdapter(adapter);

        return container;
    }
}
