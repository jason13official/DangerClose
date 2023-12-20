package net.jason13.dangerclose.optional;

import crystalspider.soulfired.api.FireManager;
import crystalspider.soulfired.api.type.FireTyped;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Proxy for Soul Fire'd mod.
 */
public final class SoulFired {
    /**
     * Sets on fire the given entity with the correct Fire Type.
     * 
     * @param entity
     * @param fireTypedEntity
     */
    public static void setOnFire(Entity entity, Entity fireTypedEntity) {
        FireManager.setOnFire(entity, 2, ((FireTyped) fireTypedEntity).getFireType());
    }

    /**
     * Sets on fire the given entity with the correct Fire Type.
     * 
     * @param entity
     * @param fireTypedBlock
     */
    public static void setOnFire(Entity entity, BlockState fireTypedBlock) {
        FireManager.setOnFire(entity, 2, fireTypedBlock.getBlock() instanceof FireTyped ? ((FireTyped) fireTypedBlock.getBlock()).getFireType() : FireManager.DEFAULT_FIRE_TYPE);
    }

    /**
     * Sets on fire the given entity with the correct Fire Type.
     * 
     * @param entity
     * @param isSoulFire
     */
    public static void setOnFire(Entity entity, Boolean isSoulFire) {
        FireManager.setOnFire(entity, 2, isSoulFire ? FireManager.SOUL_FIRE_TYPE : FireManager.DEFAULT_FIRE_TYPE);
    }
}
