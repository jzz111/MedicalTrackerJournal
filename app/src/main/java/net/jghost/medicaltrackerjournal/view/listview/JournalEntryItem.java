package net.jghost.medicaltrackerjournal.view.listview;

import net.jghost.medicaltrackerjournal.Constants;
import net.jghost.medicaltrackerjournal.model.JournalEntry;

import java.io.InvalidObjectException;
import java.util.Calendar;

/**
 * View model for a journal entry in list view.
 */
public class JournalEntryItem implements ListviewItem {
	private String title;
	private String details;
	private Calendar date;
	@Override
	public int getViewType() {
		return Constants.LISTVIEW_ENTRY_TYPE_ITEM;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		if (details.length() > Constants.LISTVIEW_EVENT_CONTENT_MAX_LENGTH) {
			this.details = details.substring(0, Constants.LISTVIEW_EVENT_CONTENT_MAX_LENGTH-1);
		} else {
			this.details = details;
		}
	}

	@Override
	public Calendar getDate() {
		return date;
	}

	@Override
	public void setDate(Calendar date) {
		this.date = date;
	}

	@Override
	public int compareTo(ListviewItem another) {
		if (another instanceof JournalEntryItem) {
			return date.compareTo(another.getDate());
		} else {
			if (Integer.compare(date.get(Calendar.YEAR), another.getDate().get(Calendar.YEAR)) == 0) {
				return Integer.compare(date.get(Calendar.MONTH), another.getDate().get(Calendar.MONTH));
			} else {
				return Integer.compare(date.get(Calendar.YEAR), another.getDate().get(Calendar.YEAR));
			}
		}
	}
}
