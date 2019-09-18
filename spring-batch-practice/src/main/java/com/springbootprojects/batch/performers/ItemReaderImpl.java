package com.springbootprojects.batch.performers;

import org.springframework.batch.item.ItemReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemReaderImpl implements ItemReader<String> {
	private String[] names = { "Kingshuk", "Deeksha", "Amit", "Mayuri" };
	private int count;

	@Override
	public String read() throws Exception {
		log.info("Inside the reader method");
		if (count < names.length) {
			return names[count++];
		} else {
			count = 0;
		}

		return null;
	}

}
