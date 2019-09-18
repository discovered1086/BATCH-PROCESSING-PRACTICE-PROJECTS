package com.springbootprojects.batch.performers;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemWriterImpl implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		log.info("Inside write method");

		items.forEach(log::info);
	}

}
