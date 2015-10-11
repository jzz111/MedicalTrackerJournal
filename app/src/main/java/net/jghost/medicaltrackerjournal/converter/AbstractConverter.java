package net.jghost.medicaltrackerjournal.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract converter for project.
 */
public abstract class AbstractConverter<T, K> {
	public abstract K convert(T obj);
	public abstract T extract(K obj);

	public List<K> convertAll(List<T> objs) {
		List<K> ret = new ArrayList<K>(objs.size());

		for (T obj : objs) {
			ret.add(convert(obj));
		}

		return ret;
	}

	public List<T> extractAll(List<K> objs) {
		List<T> ret = new ArrayList<T>(objs.size());

		for (K obj : objs) {
			ret.add(extract(obj));
		}

		return ret;
	}
}
