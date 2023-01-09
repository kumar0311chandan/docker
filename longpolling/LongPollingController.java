package com.chandan.webservices.restfulwebservices.longpolling;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class LongPollingController {

	private static final List<Messages> messageStore = new ArrayList<>();

	@Autowired
	LongPollingServiceImpl longPollingImpl;

	@GetMapping("/getMessages/{id}")
	public Messages getMessages(@PathVariable String id) {

		return longPollingImpl.findById(id);
	}

	@PostMapping("/getMessages")
	public ResponseEntity<List<Messages>> getMessage(@RequestBody Messages input) throws InterruptedException {

		List<Messages> msgList = getMessages(input);
		return ResponseEntity.ok(msgList);

	}

	private List<Messages> getMessages(Messages input) {
		if (lastStoredMessage().isPresent() && !lastStoredMessage().get().getId().equals(input.getId())) {
			List<Messages> output = new ArrayList<>();
			for (String index = input.getId(); index < messageStore.size(); index++) {
				output.add(messageStore.get(index));
			}
			output.add(msg);
			return output;
		}

		return keepPolling(input);
	}

	private List<Messages> keepPolling(Messages input) throws InterruptedException {
		Thread.sleep(5000);
		Messages msg = longPollingImpl.findById(input.getId());
		messageStore.add(msg);

		return messageStore;
	}

	private Optional<Messages> lastStoredMessage() {

		return messageStore.isEmpty() ? Optional.empty() : Optional.of(messageStore.get(messageStore.size() - 1));
	}

}
