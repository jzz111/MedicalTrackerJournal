package net.jghost.medicaltrackerjournal.converter;

import net.jghost.medicaltrackerjournal.model.JournalEntry;
import net.jghost.medicaltrackerjournal.view.listview.JournalEntryItem;

/**
 * Converter for journal entry to listview.
 */
public class JournalEntryListviewConverter extends AbstractConverter<JournalEntry, JournalEntryItem> {
	@Override
	public JournalEntryItem convert(JournalEntry obj) {
		return null;
	}

	@Override
	public JournalEntry extract(JournalEntryItem obj) {
		return null;
	}
}
