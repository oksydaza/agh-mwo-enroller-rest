package com.company.enroller.controllers;

import com.company.enroller.model.Meeting;
import com.company.enroller.persistence.MeetingService;
import com.company.enroller.persistence.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/meetings")
public class MeetingRestController {

	@Autowired
	MeetingService meetingService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getMeettings() {
		Collection<Meeting> meetings = meetingService.getAll();
		return new ResponseEntity<Collection<Meeting>>(meetings, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMeeting(@PathVariable("id") Long id) {
		Meeting meeting = meetingService.getId(id);
		if (meeting == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> addMeeting(@RequestBody Meeting meeting) {
		meetingService.add(meeting);
		return new ResponseEntity<Meeting>(meeting, HttpStatus.CREATED);

	}
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<?> getParticipant1(@PathVariable("id") String login) {
//		Participant participant = participantService.findByLogin(login);
//		if (participant == null) {
//			return new ResponseEntity(HttpStatus.NOT_FOUND);
//		}
//		participantService.delete(participant);
//		return new ResponseEntity<Participant>(participant, HttpStatus.OK);
//	}


}

