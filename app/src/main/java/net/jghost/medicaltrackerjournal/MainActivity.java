package net.jghost.medicaltrackerjournal;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import net.jghost.medicaltrackerjournal.adapter.JournalEntryPreviewAdapter;
import net.jghost.medicaltrackerjournal.model.JournalEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends ListActivity {

    private JournalEntryPreviewAdapter adapter;
    private ListView entryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<JournalEntry> fakeJournalEntries = new ArrayList<JournalEntry>();
        JournalEntry e = new JournalEntry();
        e.setContent("Fake Content");
        e.setDate(new GregorianCalendar());
        e.getDate().setTime(new Date());
        e.setTitle("My new title");
        fakeJournalEntries.add(e);
        adapter = new JournalEntryPreviewAdapter(this, R.layout.listview_journal_entry_row, fakeJournalEntries);

        entryList = (ListView) findViewById(android.R.id.list);
        View header = getLayoutInflater().inflate(R.layout.listview_journal_month, null);
        entryList.addHeaderView(header);
        entryList.setAdapter(adapter);
    }
}
