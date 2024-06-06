package mainClasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Represents an academic assignment.
 * An assignment has a subject, a deadline, and an active status.
 */
@XmlRootElement
public class Assignment implements Serializable {
    private String subject; // The subject of the assignment
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd "); // Date format for deadlines
    private Date deadLine; // The deadline for completing the assignment
    private boolean Active = false; // Indicates whether the assignment is currently active

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

    @XmlElement
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @XmlTransient
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    @XmlElement
    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    @XmlAttribute
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

    @Override
    public String toString() {
        return "Assignment{" +
                "subject='" + subject + '\'' +
                ", dateFormat=" + dateFormat +
                ", deadLine=" + deadLine +
                ", Active=" + Active +
                '}';
    }
}
