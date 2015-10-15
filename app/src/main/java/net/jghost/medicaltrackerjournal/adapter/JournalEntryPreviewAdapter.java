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
import net.jghost.medicaltrackerjournal.model.JournalEntry;
import net.jghost.medicaltrackerjournal.view.listview.JournalEntryItem;
import net.jghost.medicaltrackerjournal.view.listview.ListviewItem;
import net.jghost.medicaltrackerjournal.view.listview.MonthYearHeaderItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Adapter to convert a journal entry to a preview layout.
 */
public class JournalEntryPreviewAdapter extends ArrayAdapter<ListviewItem> {
	private final Context context;
	private final List<ListviewItem> data;

	public JournalEntryPreviewAdapter(Context context, int resource, List<ListviewItem> data) {
		super(context, resource, data);
		this.context = context;
		this.data = data;
		Collections.sort(data);
		updateData();
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
			switch (rowType) {
				case Constants.LISTVIEW_ENTRY_TYPE_ITEM:
					convertView = inflater.inflate(R.layout.listview_journal_entry_row, parent, false);
					break;
				case Constants.LISTVIEW_ENTRY_TYPE_HEADER:
					convertView = inflater.inflate(R.layout.listview_header_journal_month, parent, false);
					break;
			}
		}

		switch (rowType) {
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

	private void updateData() {
		List<ListviewItem> temp = new ArrayList(data);
		for (ListviewItem i : temp) {
			if (i instanceof JournalEntryItem) {
				if (data.indexOf(i) == 0) {
					MonthYearHeaderItem header = new MonthYearHeaderItem();
					header.setDate(i.getDate());
					data.add(0, header);
				} else if (data.indexOf(i) < data.size()-1) {
					JournalEntryItem current = (JournalEntryItem) i;
					int index = data.indexOf(i);
					if (data.get(index + 1) instanceof JournalEntryItem) {
						JournalEntryItem next = (JournalEntryItem) data.get(index + 1);
						if ((current.getDate().get(Calendar.MONTH) != next.getDate().get(Calendar.MONTH)) ||
								(current.getDate().get(Calendar.YEAR) != next.getDate().get(Calendar.YEAR))) {
							MonthYearHeaderItem header = new MonthYearHeaderItem();
							header.setDate(next.getDate());
							data.add(index + 1, header);
						}
					}
				}
			}
		}
	}
}
