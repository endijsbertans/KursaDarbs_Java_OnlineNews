package lv.venta.service.impl;

import lv.venta.model.*;
import lv.venta.repo.IEventRepo;
import lv.venta.repo.IMyUserRepo;
import lv.venta.repo.IRegisteredUserRepo;
import lv.venta.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EventServiceImpl implements IEventService {
    @Autowired
    private IEventRepo eventRepo;
    @Autowired
    private IMyUserRepo userRepo;
    @Override
    public ArrayList<Event> selectAllEvents() {
        return (ArrayList<Event>) eventRepo.findAll();
    }

    @Override
    public Event selectEventById(long id) throws Exception {
        if(id < 0) throw new Exception("Id should be positive");
        if(eventRepo.existsById(id))
        {
            return eventRepo.findById(id).get();
        }
        throw new Exception("Event with " + id + " is not found");
    }
    public MyUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            // Fetch the User entity from the database
            return userRepo.findByUsername(username);
        } else {
            return null;
        }
    }
    @Override
    public Event insertNewEvent(Event event) throws Exception {
        if(event == null) throw new Exception("Event is null");
        MyUser currentUser = getCurrentUser();

        // personRepo.save(customer.getPerson()); this should be added when person class completes
        Event eventFromDB = eventRepo.findByTitleAndPriceAndDescriptionAndStartDate(event.getTitle(), event.getPrice(), event.getDescription(), event.getStartDate());
        if(eventFromDB  != null) {
            throw new Exception("Event already exists");
        } else {
            event.setStartDate(LocalDateTime.now().plusDays(event.getStartTimeInDays()));
            if (currentUser != null)
                event.setAuthor(currentUser);
            return eventRepo.save(event);
        }
    }
    @Override
    public List<Event> getAllEventsSortedByStartDate() {
        List<Event> events = new ArrayList<>((Collection) eventRepo.findAll());
        Collections.sort(events, Comparator.comparing(Event::getStartDate));
        return events;
    }
    @Override
    public Event deleteEventById(long id) throws Exception {
        Event deleteProduct = selectEventById(id);
        eventRepo.delete(deleteProduct);
        return deleteProduct;
    }
    @Override
    public Event updateEventById(long id, Event event) throws Exception {
        Event eventUpdate = selectEventById(id);
        eventUpdate.setTitle(event.getTitle());
        eventUpdate.setPrice(event.getPrice());
        eventUpdate.setDescription(event.getDescription());
        eventUpdate.setStartDate(event.getStartDate());
        eventUpdate.setStartDate(LocalDateTime.now().plusDays(event.getStartTimeInDays()));
        return eventRepo.save(eventUpdate);
    }
}
