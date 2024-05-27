package Main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Represents an academic assignment.
 * An assignment has a subject, a deadline, and an active status.
 */
public class Assignment {
    private String subject;// The subject of the assignment
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");// Date format for deadlines
    private Date deadLine; // The deadline for completing the assignment
    private boolean Active = false;// Indicates whether the assignment is currently active

    /**
     * Constructs an assignment with the given subject.
     *
     * @param subject The subject of the assignment.
     */
    public Assignment(String subject) {
        this.subject = subject;
    }

    public Assignment(String subject, Date deadLine, boolean active) {
        this.subject = subject;
        this.deadLine = deadLine;
        Active = active;
    }

    public Assignment() {
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(subject);
    }
}
