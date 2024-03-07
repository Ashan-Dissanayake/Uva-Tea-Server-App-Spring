package lk.uva.uvateafactory.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
public class Activity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "date")
    private Date date;
    @Basic
    @Column(name = "time")
    private Time time;
    @ManyToOne
    @JoinColumn(name = "approveby_id", referencedColumnName = "id")
    private Employee approver;
    @ManyToOne
    @JoinColumn(name = "activitytype_id", referencedColumnName = "id", nullable = false)
    private Activitytype activitytype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id) && Objects.equals(date, activity.date) && Objects.equals(time, activity.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time);
    }

    public Employee getApprover() {
        return approver;
    }

    public void setApprover(Employee approver) {
        this.approver = approver;
    }

    public Activitytype getActivitytype() {
        return activitytype;
    }

    public void setActivitytype(Activitytype activitytype) {
        this.activitytype = activitytype;
    }
}
