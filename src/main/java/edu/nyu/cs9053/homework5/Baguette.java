package edu.nyu.cs9053.homework5;

public class Baguette implements Recipe {

    private double timeToCook;

    private final int foodVolume;

    private final double exponentialRate;

    private final Oven oven;

    private double timeLeftSeconds;

    public Baguette(Oven oven) {
        this.oven = oven;
        this.initializeFromOven(oven);
        this.foodVolume = 2000;
        this.exponentialRate = 0.5d;
    }

    @Override public void initializeFromOven(Oven oven) {
        this.timeToCook = Math.log(0.01d / oven.getSetTemperature()) / (- exponentialRate);
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
        if (unit == Time.Seconds) {
            amount = amount / 60;
        }
        double timeAdjustment = (double) (ovenTemperature * Math.exp(-exponentialRate * amount));
        this.timeLeftSeconds = this.timeLeftSeconds - timeAdjustment;
    }

    @Override public boolean isRecipeDone() {
    	if (getRemainingSecondsUntilDone() <= 0.01d) {
            return true;
    	}
    	else {
            return false;
    	}
    }
}