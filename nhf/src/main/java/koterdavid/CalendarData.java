package koterdavid;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CalendarData extends AbstractTableModel {
    private List<CalendarEntity> calendarEntities = new ArrayList<CalendarEntity>();
    public CalendarData() {
    }

    public int getRowCount() {
        return 1;
    }
    public int getColumnCount() {
        return 1;
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
