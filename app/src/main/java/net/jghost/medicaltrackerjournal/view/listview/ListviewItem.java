package net.jghost.medicaltrackerjournal.view.listview;

import java.util.Calendar;

/**
 * Interface for a ListView Item.
 */
public interface ListviewItem extends Comparable<ListviewItem> {
	int getViewType();
	void setDate(Calendar date);
	Calendar getDate();
}
