package net.jghost.medicaltrackerjournal.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.jghost.medicaltrackerjournal.Constants;
import net.jghost.medicaltrackerjournal.R;
import net.jghost.medicaltrackerjournal.model.JournalEntry;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Adapter to convert a journal entry to a preview layout.
 */
public class JournalEntryPreviewAdapter extends ArrayAdapter<JournalEntry> {
    private final int resource;
    private final Context context;
    private final List<JournalEntry> data;

    public JournalEntryPreviewAdapter(Context context, int resource, List<JournalEntry> data) {
        super(context, resource, data);
        this.resource = resource;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        JournalEntry entry = data.get(position);
        TextView date = (TextView) convertView.findViewById(R.id.event_date);
        date.setText(Integer.toString(entry.getDate().get(Calendar.DATE)));
        TextView dateOfWeek = (TextView) convertView.findViewById(R.id.event_day_of_week);
        dateOfWeek.setText(entry.getDate().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));
        TextView title = (TextView) convertView.findViewById(R.id.event_title);
        title.setText(entry.getTitle());
        TextView details = (TextView) convertView.findViewById(R.id.event_details);
        details.setText(entry.getContent());

        return convertView;
    }
}
