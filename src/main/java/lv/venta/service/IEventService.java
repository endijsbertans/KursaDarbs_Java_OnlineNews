package lv.venta.service;

import lv.venta.model.Event;

import java.util.ArrayList;
import java.util.List;

public interface IEventService {
    ArrayList<Event> selectAllEvents();
    Event selectEventById(long id) throws Exception;
    Event insertNewEvent(Event event) throws Exception;
    Event deleteEventById(long id) throws Exception;
    Event updateEventById(long id, Event event) throws Exception;
    List<Event> getAllEventsSortedByStartDate();
}
