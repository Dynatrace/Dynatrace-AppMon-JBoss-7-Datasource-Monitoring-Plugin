package com.jboss7.management.rest.monitor;

public class DSStatistics {
	
	// Note: variable names must match the JSON names (i.e., case-sensitve)
	private int ActiveCount = 0;
	private int AvailableCount = 0;
	private int AverageBlockingTime = 0;
	private int AverageCreationTime = 0;
	private int CreatedCount = 0;
	private int DestroyedCount = 0;
	private int MaxCreationTime = 0;
	private int MaxUsedCount = 0;
	private int MaxWaitTime = 0;
	private int TimedOut = 0;
	private int TotalBlockingTime = 0;
	private int TotalCreationTime = 0;
	
	
	public int getActiveCount() {
		return ActiveCount;
	}

	public int getAvailableCount() {
		return AvailableCount;
	}

	public int getAverageBlockingTime() {
		return AverageBlockingTime;
	}

	public int getAverageCreationTime() {
		return AverageCreationTime;
	}

	public int getCreatedCount() {
		return CreatedCount;
	}

	public int getDestroyedCount() {
		return DestroyedCount;
	}

	public int getMaxCreationTime() {
		return MaxCreationTime;
	}

	public int getMaxUsedCount() {
		return MaxUsedCount;
	}

	public int getMaxWaitTime() {
		return MaxWaitTime;
	}

	public int getTimedOut() {
		return TimedOut;
	}

	public int getTotalBlockingTime() {
		return TotalBlockingTime;
	}

	public int getTotalCreationTime() {
		return TotalCreationTime;
	}


	DSStatistics() {
		// no-arguments constructor
	}
	
	public String toString() {
		return "ActiveCount=" + ActiveCount + 
				" AvailableCount=" + AvailableCount + 
				" AverageBlockingTime=" + AverageBlockingTime +
				" AverageCreationTime=" + AverageCreationTime +
				" CreatedCount=" + CreatedCount +
				" DestroyedCount=" + DestroyedCount +
				" MaxCreationTime=" + MaxCreationTime +
				" MaxUsedCount=" + MaxUsedCount +
				" MaxWaitTime=" + MaxWaitTime +
				" TimedOut=" + TimedOut +
				" TotalBlockingTime=" + TotalBlockingTime +
				" TotalCreationTime=" + TotalCreationTime;
	}
	
}