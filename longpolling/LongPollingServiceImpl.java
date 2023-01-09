package com.chandan.webservices.restfulwebservices.longpolling;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LongPollingServiceImpl implements LongPollingService {

	private static final DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss.SSSxxx");
	
	
	@Autowired
	LongPollingRepo repo;

	@Override
	public Messages findById(String id) {
		
		String output = OffsetDateTime.now(ZoneOffset.UTC).format(formatter3);
		System.out.println(output);
		
		Optional<Messages> message = repo.findById(id);
		Messages msg = message.get();
		return msg;
	}

}
