// Copyright (C) 2023 Jason13 https://github.com/jason13official
// Provided under the GNU LGPL v2.1 License, provided as files 'LICENSE' and 'LICENSE.txt' in the root directory

package net.jason13.dangerclose.event;

import net.jason13.dangerclose.DangerClose;
import net.jason13.dangerclose.config.Config;
import net.jason13.dangerclose.init.Tags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = DangerClose.MOD_ID)
public class DangerDetector {

    private static void setOnFire(Entity entity) {
        entity.setSecondsOnFire(2);
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {

        if (Config.ENABLE_DANGER_CLOSE.get()) {

            LivingEntity entity = event.getEntity();
            Level level = entity.level();
            BlockPos pos = entity.blockPosition();
            BlockState blockstate = level.getBlockState(pos);
            BlockState blockstateBelow = level.getBlockState(pos.below());

            List<LivingEntity> nearby = level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, entity, entity.getBoundingBox());
            List<String> nearby_names = new ArrayList<>();

            for (LivingEntity nearbyEntity : nearby) {
                if (nearbyEntity.isOnFire()) {
                    setOnFire(entity);
                }
                else if (entity.isOnFire()) {
                    setOnFire(nearbyEntity);
                }
                nearby_names.add(nearbyEntity.getName().getString());
            }

            if (Config.ENABLE_BLAZE_DAMAGE.get() && nearby_names.contains("Blaze")) {
                setOnFire(entity);
            }
            if (Config.ENABLE_MAGMA_SLIME_DAMAGE.get() && nearby_names.contains("Magma Cube")) {
                setOnFire(entity);
            }

            /* ENTITIES ^ | v BLOCKS */

            if (Config.ENABLE_MAGMA_BLOCK_DAMAGE.get()) {
                if (blockstate.is(Blocks.MAGMA_BLOCK) || blockstateBelow.is(Blocks.MAGMA_BLOCK)) {
                    setOnFire(entity);
                }
            }
            if (Config.ENABLE_TORCH_BURN_DAMAGE.get()) {
                if (blockstate.getTags().anyMatch(key -> { return key == Tags.Blocks.TORCH_BURN_DANGER;})) {
                    setOnFire(entity);
                }
                else if (blockstateBelow.getTags().anyMatch(key -> { return key == Tags.Blocks.TORCH_BURN_DANGER;})) {
                    setOnFire(entity);
                }
            }
            if (Config.ENABLE_CAMPFIRE_BURN_DAMAGE.get()) {
                if (blockstate.getTags().anyMatch(key -> { return key == Tags.Blocks.CAMPFIRE_BURN_DANGER;}) && blockstate.getValue(BlockStateProperties.LIT)) {
                    setOnFire(entity);
                }
                else if (blockstateBelow.getTags().anyMatch(key -> { return key == Tags.Blocks.CAMPFIRE_BURN_DANGER;}) && blockstateBelow.getValue(BlockStateProperties.LIT)) {
                    setOnFire(entity);
                }
            }
            if (Config.ENABLE_STONECUTTER_DAMAGE.get()) {
                if (blockstate.getTags().anyMatch(key -> { return key == Tags.Blocks.CUTTING_DANGER;})) {
                    entity.hurt(level.damageSources().cactus(), 6);
                }
                else if (blockstateBelow.getTags().anyMatch(key -> { return key == Tags.Blocks.CUTTING_DANGER;})) {
                    entity.hurt(level.damageSources().cactus(), 6);
                }
            }
        }
    }
}
