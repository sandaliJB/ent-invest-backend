package com.enterprise.ent_invest_backend.Controller;

import com.enterprise.ent_invest_backend.Implementation.MeetingService;
import com.enterprise.ent_invest_backend.Model.Meeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping
    public Meeting createMeeting(@RequestBody Meeting meeting) {
        return meetingService.createMeeting(meeting);
    }

    @GetMapping("/{id}")
    public Meeting getMeeting(@PathVariable String id) {
        return meetingService.getMeetingById(id);
    }

    @GetMapping
    public List<Meeting> getAllMeetings() {
        return meetingService.getAllMeetings();
    }
}
