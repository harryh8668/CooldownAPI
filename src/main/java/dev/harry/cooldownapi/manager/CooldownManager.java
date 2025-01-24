package dev.harry.cooldownapi.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<UUID, Map<String, Long>> cooldowns = new HashMap<>();

    public void setCooldown(UUID playerId, String item, long duration) {
        cooldowns.computeIfAbsent(playerId, k -> new HashMap<>()).put(item, System.currentTimeMillis() + duration);
    }

    public boolean hasCooldown(UUID playerId, String item) {
        if (!cooldowns.containsKey(playerId) || !cooldowns.get(playerId).containsKey(item)) {
            return false;
        }
        long cooldownEnd = cooldowns.get(playerId).get(item);
        if (System.currentTimeMillis() > cooldownEnd) {
            cooldowns.get(playerId).remove(item);
            if (cooldowns.get(playerId).isEmpty()) {
                cooldowns.remove(playerId);
            }
            return false;
        }
        return true;
    }

    public long getRemainingCooldown(UUID playerId, String item) {
        if (!hasCooldown(playerId, item)) {
            return 0;
        }
        return cooldowns.get(playerId).get(item) - System.currentTimeMillis();
    }
}