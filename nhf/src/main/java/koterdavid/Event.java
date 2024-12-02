package koterdavid;

import java.util.Date;

/**
 * A child class of CalendarEntity, extending it by a few attributes.
 * Focuses on having at least a date,
 * and preferably also a type to help grouping.
 */
public class Event extends CalendarEntity{
    public Event(String name, String location, String type, boolean allDay, Date begin, Date end){
        super(name, location);
        this.type=type;
        this.allDay=allDay;
        this.begin=begin;
        this.end=end;
    }

    /**
     * A short text.
     * <p>
     * Ideally there are only a few event types to help grouping,
     * but it is not enforced, the user if free to enter any text.
     */
    private String type;
    /**
     * Describes if the event is semantically considered to
     * occupy one or more full days, and that the exact
     * begin and end times are not that relevant.
     * <p>
     * Currently not specified, but in the future its value
     * may influence its display in table or other views;
     */
    private boolean allDay;
    /**
     * A Date field that MUST have a valid value.
     * <p>
     * Semantically it could mean several things,
     * like the begin of a time interval or
     * a point in time.
     * <p>
     * Should always be displayed to help identifying events,
     * because their names are not expected to be always
     * enough to easily identify them.
     */
    private Date begin;
    /**
     * An optional Date field.
     * <p>
     * If set, it must be a valid Date.
     * If not set, it must be null.
     * <p>
     * Like begin Date, semantically it could have
     * several meanings.
     * <p>
     * It is not expected to be necessary to identify an event,
     * may be not displayed when appropriate.
     */
    private Date end;

    /**
     * {#{@link #type}}
     */
    public String getType() {
        return type;
    }

    /**
     * {#{@link #type}}
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * {#{@link #allDay}}
     */
    public boolean isAllDay() {
        return allDay;
    }

    /**
     * {#{@link #allDay}}
     */
    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    /**
     * {#{@link #begin}}
     */
    public Date getBegin() {
        return begin;
    }

    /**
     * {#{@link #begin}}
     */
    public void setBegin(Date begin) {
        this.begin = begin;
    }

    /**
     * {#{@link #end}}
     */
    public Date getEnd() {
        return end;
    }

    /**
     * {#{@link #end}}
     */
    public void setEnd(Date end) {
        this.end = end;
    }
}
