package net.jghost.medicaltrackerjournal.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;

import net.jghost.medicaltrackerjournal.R;
import net.jghost.medicaltrackerjournal.adapter.JournalEntryPreviewAdapter;
import net.jghost.medicaltrackerjournal.converter.JournalEntryListviewConverter;
import net.jghost.medicaltrackerjournal.model.JournalEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends ListActivity {

    private JournalEntryPreviewAdapter adapter;
    private ListView entryList;

    private JournalEntryListviewConverter journalConverter = new JournalEntryListviewConverter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        List<JournalEntry> fakeJournalEntries = new ArrayList<>();
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
        adapter = new JournalEntryPreviewAdapter(this, R.layout.listview_journal_entry_row,
                journalConverter.convertAll(fakeJournalEntries));

        entryList = (ListView) findViewById(android.R.id.list);
        entryList.setAdapter(adapter);
    }
}
