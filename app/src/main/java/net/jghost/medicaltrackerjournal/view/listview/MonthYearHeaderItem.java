package net.jghost.medicaltrackerjournal.view.listview;

import net.jghost.medicaltrackerjournal.Constants;

import java.util.Calendar;
import java.util.Locale;

/**
 * UI model for listview journal entry header.
 */
public class MonthYearHeaderItem implements ListviewItem {
	private Calendar date;
	@Override
	public int getViewType() {
		return Constants.LISTVIEW_ENTRY_TYPE_HEADER;
	}

	@Override
	public void setDate(Calendar date) {
		this.date = date;
	}

	@Override
	public Calendar getDate() {
		return date;
	}

	public int getYear() {
		return date.get(Calendar.YEAR);
	}

	public String getMonth() {
		return date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
	}

	@Override
	public String toString() {
		return date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) + " " + date.get(Calendar.YEAR);
	}

	@Override
	public int compareTo(ListviewItem another) {
		return 0;
	}
}
