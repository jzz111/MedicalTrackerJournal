package net.jghost.medicaltrackerjournal.view.listview;

import java.util.Calendar;

/**
 * Interface for a ListView Item.
 */
public interface ListviewItem {
	int getViewType();
	Calendar getDate();
}
