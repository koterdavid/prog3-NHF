package koterdavid;

import java.io.Serializable;

/**
 * Abstract parent class for Event and Task, to possibly store them in a heterogeneous list
 * <p>
 * Contains the shared attributes name and location
 */
public class CalendarEntity implements Serializable {
    /**
     * A short text, expected to identify the CalendarEntity for the user
     * in the context.
     * Should always be displayed, preferably as the first attribute.
     * <p>
     * Still, it isn't required to be unique, and at least 1-2 more
     * attributes should also be displayed to help identifying a CalendarEntity
     * in the context.
     * <p>
     * Cannot be null, if not set it must be an empty string.
     * Yes, the name field can be empty, there is no point in forcing giving a name.
     */
    private String name;
    /**
     * A short text.
     * <p>
     * Expected to be important and always displayed,
     * but not expected to help identify a CalendarEvent,
     * so it doesn't need to be displayed for that purpose.
     * <p>
     * Cannot be null, if not set it must be an empty string.
     */
    private String location;
    public CalendarEntity(String name, String location){
        this.name=name;
        this.location=location;
    }

    /**
     * {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@link #location}
     */
    public String getLocation() {
        return location;
    }

    /**
     * {@link #location}
     */
    public void setLocation(String location) {
        this.location = location;
    }
}
