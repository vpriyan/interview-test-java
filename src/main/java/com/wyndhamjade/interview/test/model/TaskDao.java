package com.wyndhamjade.interview.test.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;

public class TaskDao {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Task Get(Long id) throws Exception {

        DataSource dataSource = ConnectionManager.getDatasource();


        try {
            Task task = null;

            conn = dataSource.getConnection();
            ps = conn.prepareStatement(""
                    + "select t.id, t.description, t.due, t.completed_at, "
                    + "       t.created_at as task_created_at, "
                    + "       t.assigned_to, a.name, a.email, "
                    + "       a.created_at as assignee_created_at "
                    + "  from tasks t "
                    + "  left outer join assignees a on t.assigned_to = a.id"
                    + "  where t.id = " + id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Assignee assignee = null;
                final long assigneeId = rs.getLong("assigned_to");
                if (!rs.wasNull()) {
                    assignee = new Assignee();
                    assignee.setId(assigneeId);
                    assignee.setName(rs.getString("name"));
                    assignee.setEmail(rs.getString("email"));
                    assignee.setCreatedAt(rs.getTimestamp("assignee_created_at"));
                }

                task = new Task();
                task.setId(rs.getLong("id"));
                task.setDescription(rs.getString("description"));
                task.setDue(rs.getTimestamp("due"));
                task.setCompletedAt(rs.getTimestamp("completed_at"));
                task.setCreatedAt(rs.getTimestamp("task_created_at"));
                task.setAssignee(assignee);

            }

            return (task);
        } catch (final SQLException e) {
            throw new Exception(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
        }
    }

    public List<Task> Get() throws Exception {
        DataSource dataSource = ConnectionManager.getDatasource();


        try {
            final List<Task> tasks = new LinkedList<Task>();

            conn = dataSource.getConnection();
            ps = conn.prepareStatement(""
                    + "select t.id, t.description, t.due, t.completed_at, "
                    + "       t.created_at as task_created_at, "
                    + "       t.assigned_to, a.name, a.email, "
                    + "       a.created_at as assignee_created_at "
                    + "  from tasks t "
                    + "  left outer join assignees a on t.assigned_to = a.id");
            rs = ps.executeQuery();

            while (rs.next()) {
                Assignee assignee = null;
                final long assigneeId = rs.getLong("assigned_to");
                if (!rs.wasNull()) {
                    assignee = new Assignee();
                    assignee.setId(assigneeId);
                    assignee.setName(rs.getString("name"));
                    assignee.setEmail(rs.getString("email"));
                    assignee.setCreatedAt(rs.getTimestamp("assignee_created_at"));
                }

                final Task task = new Task();
                task.setId(rs.getLong("id"));
                task.setDescription(rs.getString("description"));
                task.setDue(rs.getTimestamp("due"));
                task.setCompletedAt(rs.getTimestamp("completed_at"));
                task.setCreatedAt(rs.getTimestamp("task_created_at"));
                task.setAssignee(assignee);

                tasks.add(task);
            }

            return (tasks);
        } catch (final SQLException e) {
            throw new Exception(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
        }
    }

    public void remove(Long id) throws Exception {
        DataSource dataSource = ConnectionManager.getDatasource();


        try {

            conn = dataSource.getConnection();
            ps = conn.prepareStatement(""
                    + "delete from tasks t where t.id = " + id);
            ps.executeUpdate();

        } catch (final SQLException e) {
            throw new Exception(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
        }
    }

    public void add(Task task) throws Exception {
        DataSource dataSource = ConnectionManager.getDatasource();


        try {

            conn = dataSource.getConnection();
            ps = conn.prepareStatement(""
                    + "insert into tasks t (t.id,t.description,t.due,t.completed_at,t.created_at) Values "
                    + "(" + task.getId() + "," + task.getDescription() + " , "
                    + task.getDue() + ", " + task.getCompletedAt() + ", "
                    + task.getCreatedAt() + ")");

            ps.executeQuery();

        } catch (final SQLException e) {
            throw new Exception(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
        }
    }

    public void update(Task task) throws Exception {
        DataSource dataSource = ConnectionManager.getDatasource();


        try {

            conn = dataSource.getConnection();
            ps = conn.prepareStatement(""
                    + "update tasks t set t.description = '" + task.getDescription() + "' "
                    + "t.due = '" + task.getDue() + "' "
                    + "t.completed_at='" + task.getCompletedAt() + "' "
                    + "t.created_at='" + task.getCreatedAt() + "' "
                    + "where t.id = " + task.getId());

            ps.executeQuery();

        } catch (final SQLException e) {
            throw new Exception(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (final SQLException ignored) {
                    // ignored
                }
            }
        }
    }
}
