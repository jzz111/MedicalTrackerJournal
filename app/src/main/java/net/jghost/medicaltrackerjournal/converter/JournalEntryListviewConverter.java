package net.jghost.medicaltrackerjournal.converter;

import net.jghost.medicaltrackerjournal.Constants;
import net.jghost.medicaltrackerjournal.model.JournalEntry;
import net.jghost.medicaltrackerjournal.view.listview.JournalEntryItem;
import net.jghost.medicaltrackerjournal.view.listview.ListviewItem;

/**
 * Converter for journal entry to listview.
 */
public class JournalEntryListviewConverter extends AbstractConverter<JournalEntry, ListviewItem> {
	@Override
	public ListviewItem convert(JournalEntry obj) {
		JournalEntryItem item = new JournalEntryItem();
		item.setTitle(obj.getTitle());
		item.setDate(obj.getDate());

		String content = obj.getContent();
		if (content.length() > Constants.LISTVIEW_EVENT_CONTENT_MAX_LENGTH) {
			content = content.substring(0, Constants.LISTVIEW_EVENT_CONTENT_MAX_LENGTH - 1);
		}
		item.setDetails(content);

		return item;
	}

	@Override
	public JournalEntry extract(ListviewItem obj) {
		throw new UnsupportedOperationException("Listview does not support extraction.");
	}
}
