package com.springbootprojects.batch.performers;

import org.springframework.batch.item.ItemProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemProcessorImpl implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		log.info("Inside the processor method");
		return "KINGSHUK KILLED " + item.toUpperCase();
	}

}
