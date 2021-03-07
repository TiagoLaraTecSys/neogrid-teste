package com.laratecsys.neogrid.entities;

import java.util.Date;

public class ProductionProcess {

	String activityTitle;
	Date activityTime;

	public ProductionProcess() {
	}

	public ProductionProcess(String activityTitle, Date activityTime) {
		super();
		this.activityTitle = activityTitle;
		this.activityTime = activityTime;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public Date getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Date activityTime) {
		this.activityTime = activityTime;
	}

}
