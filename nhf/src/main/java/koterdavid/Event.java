package koterdavid;

import java.util.Date;

public class Event extends CalendarEntity{
    public Event(String name, String location){
        super(name, location);
    }
    private String location;
    private String type;
    private Date begin;
    private Date end;
    private boolean allDay;
}
