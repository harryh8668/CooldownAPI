package dev.harry.cooldownapi.manager;

public class Cooldown {
    private final long duration;

    public Cooldown(int weeks, int days, int hours, int minutes, int seconds) {
        this.duration = (weeks * 7L * 24 * 60 * 60 * 1000) +
                (days * 24L * 60 * 60 * 1000) +
                (hours * 60L * 60 * 1000) +
                (minutes * 60L * 1000) +
                (seconds * 1000L);
    }

    public long getDuration() {
        return duration;
    }
}