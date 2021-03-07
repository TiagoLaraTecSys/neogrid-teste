package com.laratecsys.neogrid.entities;

import java.util.ArrayList;
import java.util.List;

public class AssemblyLine {

	Integer assemblyNumber;

	List<ProductionProcess> assemblyActivities = new ArrayList<ProductionProcess>();

	public AssemblyLine() {
		super();
	}

	public AssemblyLine(Integer assemblyNumber) {
		super();
		this.assemblyNumber = assemblyNumber;
	}

	public AssemblyLine(Integer assemblyNumber, List<ProductionProcess> assemblyActivities) {
		super();
		this.assemblyNumber = assemblyNumber;
		this.assemblyActivities = assemblyActivities;
	}

	public Integer getAssemblyNumber() {
		return assemblyNumber;
	}

	public void setAssemblyNumber(Integer assemblyNumber) {
		this.assemblyNumber = assemblyNumber;
	}

	public List<ProductionProcess> getAssemblyActivities() {
		return assemblyActivities;
	}

	public void setAssemblyActivities(List<ProductionProcess> assemblyActivities) {
		this.assemblyActivities = assemblyActivities;
	}

}
