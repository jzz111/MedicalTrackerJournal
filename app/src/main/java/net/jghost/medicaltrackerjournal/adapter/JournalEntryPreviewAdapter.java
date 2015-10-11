package net.jghost.medicaltrackerjournal.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.jghost.medicaltrackerjournal.Constants;
import net.jghost.medicaltrackerjournal.R;
import net.jghost.medicaltrackerjournal.view.listview.JournalEntryItem;
import net.jghost.medicaltrackerjournal.view.listview.ListviewItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Adapter to convert a journal entry to a preview layout.
 */
public class JournalEntryPreviewAdapter extends ArrayAdapter<ListviewItem> {
    private final Context context;
    private final List<ListviewItem> data;

	private Map<String, List<ListviewItem>> cached_data;

    public JournalEntryPreviewAdapter(Context context, int resource, List<ListviewItem> data) {
        super(context, resource, data);
        this.context = context;
        this.data = data;
    }

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		Collections.sort(data);
		updateCache();
	}

	@Override
	public int getItemViewType(int position) {
		return data.get(position).getViewType();
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		int rowType = getItemViewType(position);
        if (convertView == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			switch(rowType) {
				case Constants.LISTVIEW_ENTRY_TYPE_ITEM:
					convertView = inflater.inflate(R.layout.listview_journal_entry_row, parent, false);
					break;
				case Constants.LISTVIEW_ENTRY_TYPE_HEADER:
					convertView = inflater.inflate(R.layout.listview_header_journal_month, parent, false);
					break;
			}
        }

		switch(rowType) {
			case Constants.LISTVIEW_ENTRY_TYPE_ITEM:
				JournalEntryItem entry = (JournalEntryItem) data.get(position);
				TextView date = (TextView) convertView.findViewById(R.id.event_date);
				date.setText(Integer.toString(entry.getDate().get(Calendar.DATE)));
				TextView dateOfWeek = (TextView) convertView.findViewById(R.id.event_day_of_week);
				dateOfWeek.setText(entry.getDate().getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.US));
				TextView title = (TextView) convertView.findViewById(R.id.event_title);
				title.setText(entry.getTitle());
				TextView details = (TextView) convertView.findViewById(R.id.event_details);
				details.setText(entry.getDetails());
				break;
			case Constants.LISTVIEW_ENTRY_TYPE_HEADER:
				break;
		}

        return convertView;
    }

	private void updateCache() {
		cached_data.clear();
		for (ListviewItem j : data) {
			String key = j.getDate().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " +
					Integer.toString(j.getDate().get(Calendar.DATE));
			if (cached_data.get(key) == null) {
				cached_data.put(key, new ArrayList<ListviewItem>(1));
			}
			cached_data.get(key).add(j);
		}
	}
}
