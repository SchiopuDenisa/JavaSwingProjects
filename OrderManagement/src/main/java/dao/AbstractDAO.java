package dao;

import connection.ConnectionFactory;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Generic DAO class that uses Java Reflection to perform CRUD operations
 * on any given model class.
 */
public class AbstractDAO<T> {
    protected final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String getTableName() {
        String name = type.getSimpleName().toLowerCase();
        return name.equals("order") ? "\"order\"" : name;
    }

    /**
     * Retrieves all records from the table.
     */
    public List<T> findAll() {
        String query = "SELECT * FROM " + getTableName();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Inserts a new record into the database.
     * Assumes the first field in the model is the auto-generated ID and skips it.
     */
    public void add(T t) {
        Field[] fields = type.getDeclaredFields();
        StringBuilder query = new StringBuilder("INSERT INTO " + getTableName() + " (");
        StringBuilder values = new StringBuilder("VALUES (");

        for (int i = 1; i < fields.length; i++) {
            query.append(fields[i].getName()).append(i < fields.length - 1 ? ", " : ") ");
            values.append("?").append(i < fields.length - 1 ? ", " : ")");
        }

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString() + values.toString())) {

            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(i, fields[i].get(t));
            }
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a record by its ID.
     */
    public void delete(int id) {
        String query = "DELETE FROM " + getTableName() + " WHERE id = ?";
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to map a Database ResultSet to Java Objects using reflection.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Updates an existing record in the database.
     * Assumes the first field in the model is the ID and uses it for the WHERE clause.
     */
    public void update(T t) {
        Field[] fields = type.getDeclaredFields();
        StringBuilder query = new StringBuilder("UPDATE " + getTableName() + " SET ");

        for (int i = 1; i < fields.length; i++) {
            query.append(fields[i].getName()).append(" = ?");
            if (i < fields.length - 1) {
                query.append(", ");
            }
        }

        query.append(" WHERE ").append(fields[0].getName()).append(" = ?");

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query.toString())) {

            int paramIndex = 1;

            for (int i = 1; i < fields.length; i++) {
                fields[i].setAccessible(true);
                statement.setObject(paramIndex++, fields[i].get(t));
            }

            fields[0].setAccessible(true);
            statement.setObject(paramIndex, fields[0].get(t));

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}