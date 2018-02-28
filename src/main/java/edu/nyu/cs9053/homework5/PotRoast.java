package edu.nyu.cs9053.homework5;

public class PotRoast implements Recipe {

    private double timeToCook;

    private final int foodVolume;

    private final Oven oven;

    private double timeLeftSeconds;

    public PotRoast(Oven oven) {
    	this.oven = oven;
    	this.initializeFromOven(oven);
    	this.foodVolume = 10000;
    }

    @Override public void initializeFromOven(Oven oven) {
    	this.timeToCook = oven.getSetTemperature() / 5;
    	this.timeLeftSeconds = this.timeToCook * 60d;
    }

    @Override public int getVolumeCubicInches() {
        return this.foodVolume;
    }

    @Override public Double getRemainingSecondsUntilDone() {
    	return timeLeftSeconds;
    }

    @Override public void adjust(Time unit, int amount, int ovenTemperature) {
    	if (unit == null) {
            throw new IllegalArgumentException("unit cannot be null");
        }
    	double timeAdjustment = (double) ((ovenTemperature * amount) / oven.getSetTemperature());
    	if (unit == Time.Minutes) {
    	    timeAdjustment = (double) timeAdjustment * 60d;
    	}
    	this.timeLeftSeconds = this.timeLeftSeconds - timeAdjustment;
    }

    @Override public boolean isRecipeDone() {
    	if (getRemainingSecondsUntilDone() <= 0) {
    		return true;
    	}
    	else {
    		return false;
    	}
   	}
}
