package jpa;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "events_01")
public class Event {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "events_id")
	private Integer id;
    @Column(name = "events_title", nullable =false)
	private String title;
    @Column(name = "events_description")
	private String description;
    @Column(name = "events_beginDate")
    @Temporal(TemporalType.DATE)
	private Calendar beginDate;
    @Column(name = "events_allDay")
	private boolean allDay;
    
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the beginDate
	 */
	public Calendar getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Calendar beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * @return the allDay
	 */
	public boolean isAllDay() {
		return allDay;
	}

	/**
	 * @param allDay the allDay to set
	 */
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	
	public Event()
	{
		
	}
}
