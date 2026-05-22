package business_logic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Utility class that uses Java Reflection to populate a JTable with data from a list of objects.
 * <p>
 * This is especially useful for dynamically rendering table data without hardcoding column names or object properties.
 */
public class ReflectionTable {
    /**
     * Populates the given JTable with the data from a provided list of objects.
     * <p>
     * This method uses reflection to read all declared fields of the class and uses their names as column headers.
     *
     * @param table is the JTable to populated.
     * @param objectList is the list of objects to be displayed in the table.
     * @param <T> is the type of objects contained in the list.
     */
    public static <T> void populateTableFromList(JTable table, List<T> objectList) {
        if (objectList == null || objectList.isEmpty()) return;

        Class<?> clazz = objectList.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();

        String[] columnNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            columnNames[i] = fields[i].getName();
        }

        Object[][] data = new Object[objectList.size()][fields.length];
        for (int row = 0; row < objectList.size(); row++) {
            T obj = objectList.get(row);
            for (int col = 0; col < fields.length; col++) {
                try {
                    data[row][col] = fields[col].get(obj);
                } catch (IllegalAccessException e) {
                    data[row][col] = "Error";
                }
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);
    }
}