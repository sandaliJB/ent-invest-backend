package com.enterprise.ent_invest_backend.Implementation;

import com.enterprise.ent_invest_backend.Model.Meeting;
import com.enterprise.ent_invest_backend.Repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    public Meeting createMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    public Meeting getMeetingById(String id) {
        return meetingRepository.findById(id).orElse(null);
    }

    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }
}