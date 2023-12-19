package net.jason13.dangerclose.optional;

import crystalspider.soulfired.api.FireManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

/**
 * Proxy for Soul Fire'd mod.
 */
public final class SoulFired {
    /**
     * Sets on fire the given entity, for the given seconds, with the correct Fire Type.
     * 
     * @param entity
     * @param seconds
     * @param fireType
     */
    public static void setOnFire(Entity entity, int seconds, ResourceLocation fireType) {
        FireManager.setOnFire(entity, seconds, fireType);
    }
}