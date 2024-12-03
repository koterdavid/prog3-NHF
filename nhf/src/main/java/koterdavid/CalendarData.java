package koterdavid;

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A database class, storing a heterogeneous list of CalendarEntities,
 * for example Events and Tasks
 * <p>
 * Also provides a direct interface for displaying the contents of this list
 * in a JTable.
 */
public class CalendarData extends AbstractTableModel implements Serializable {
    private List<CalendarEntity> calendarEntities;
    public CalendarData() {
        calendarEntities = new ArrayList<>();
    }

    public int getRowCount() {
        return calendarEntities.size();
    }

    public int getColumnCount() {
        return 6;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        CalendarEntity calendarEntity = calendarEntities.get(rowIndex);
        switch(columnIndex){
            //Since we are always handling CalendarEntities here,
            // the shared attributes in theory should be handled the same,
            // no switches are needed:
            case 0: return calendarEntity.getName();
            case 1: return calendarEntity.getLocation();
            //The remaining columns heavily vary depending on the type.
            // When introducing a new child class of CalendarEntity,
            // all the following switches should be extended appropriately
            // and consistently:
            case 2: {
                switch(calendarEntity) {
                    case Event e: return e.getType();
                    case Task t: return "Task";
                    default: return "";
                }
            }
            case 3: {
                switch(calendarEntity){
                    case Event e: return e.isAllDay();
                    case Task t: return t.getPriority();
                    default: return "";
                }
            }
            case 4: {
                switch(calendarEntity){
                    case Event e: return e.getBegin();
                    case Task t: return t.getDeadline();
                    default: return "";
                }
            }
            case 5: {
                switch(calendarEntity){
                    case Event e: return e.getEnd();
                    case Task t: return t.getDescription();
                    default: return "";
                }
            }
            default: return -1;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch(column){
            case 0: return "<html><b>" + "Name" + "</b></html>";
            case 1: return "<html><b>" + "Location" + "</b></html>";
            case 2: return "<html><b>" + "Type" + "</b></html>";
            case 3: return "<html><b>" + "All Day? (Event)" +
                            "<br>Priority (Task)" + "</b></html>";
            case 4: return "<html><b>" + "Begin date (Event)\n" +
                            "<br>Deadline (Task)" + "</b></html>";
            case 5: return "<html><b>End date (Event)" +
                            "<br>Description (Task)" + "</b></html>";
            default: return "";
        }
    }

    public void add(CalendarEntity calendarEntity){
        calendarEntities.add(calendarEntity);
    }

    public CalendarEntity get(int idx){
        return calendarEntities.get(idx);
    }

    /**
     * Returns the list of CalendarEntities directly.
     * Needed for serialization, because an AbstractTableModel cannot be serialized,
     * as it is a Swing object containing references used by internal mechanisms.
     */
    public List<CalendarEntity> getCalendarEntities(){
        return calendarEntities;
    }

    /**
     * Overwrites the list of CalendarEntities directly.
     * Needed for deserialization, because an AbstractTableModel cannot be deserialized,
     * as it is a Swing object containing references used by internal mechanisms.
     */
    public void setCalendarEntities(List<CalendarEntity> list){
        calendarEntities = list;
    }

    public int size(){
        return calendarEntities.size();
    }

    public boolean isEmpty(){
        return calendarEntities.isEmpty();
    }

    public void remove(Object object){
        calendarEntities.remove(object);
    }

    public void remove(int idx){
        calendarEntities.remove(idx);
    }
}
