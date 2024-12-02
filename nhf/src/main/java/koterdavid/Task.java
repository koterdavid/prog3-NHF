package koterdavid;

import java.util.Date;

/**
 * A child class of CalendarEntity, extending it by a few attributes.
 * Focuses on describing a to-do task and its details.
 */
public class Task extends CalendarEntity {
    public Task(String name, String location, String priority, Date deadline, String description){
        super(name, location);
        this.priority=priority;
        this.deadline=deadline;
        this.description=description;
    }

    /**
     * The priority of the task.
     * <p>
     * Cannot be null, if not set it must be an empty string.
     * <p>
     * Ideally there are only a few possible priorities to help grouping and ordering,
     * but it is not enforced, the user if free to enter any text.
     * <p>
     * Its value CURRENTLY does not influence its display in table view.
     */
    private String priority;
    /**
     * The optional deadline of a task.
     * <p>
     * If set, it is always a Date.
     * If not set, it must be null.
     * <p>
     * Value may influence display in table view.
     */
    private Date deadline;
    /**
     * The details of the task.
     * Expected to be a long text, possibly consisting of a lot of lines.
     * The goal is to allocate to this attribute as much space on the screen as possible.
     * <p>
     * Cannot be null, if not set it must be an empty string.
     * <p>
     * Because its length is expected to vary in a huge range,
     * its content does not influence its display in table mode,
     * instead all remaining space should be allocated to display its cell.
     */
    private String description;

    /**
     * {@link #priority}
     */
    public String getPriority() {
        return priority;
    }

    /**
     * {@link #priority}
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * {@link #deadline}
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * {@link #deadline}
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     * {@link #description}
     */
    public String getDescription() {
        return description;
    }

    /**
     * {@link #description}
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
