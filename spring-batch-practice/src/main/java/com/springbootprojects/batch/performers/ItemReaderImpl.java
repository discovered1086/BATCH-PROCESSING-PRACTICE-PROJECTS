package com.springbootprojects.batch.performers;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ItemReaderImpl implements ItemReader<String> {
	private String[] names = { "Kingshuk", "Deeksha", "Amit", "Mayuri" };
	private int count;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (count < names.length) {
			return names[count++];
		} else {
			count = 0;
		}

		return null;
	}

}
