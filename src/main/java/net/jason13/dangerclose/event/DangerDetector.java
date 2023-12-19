// Copyright (C) 2023 Jason13 https://github.com/jason13official
// Provided under the GNU LGPL v2.1 License, provided as files 'LICENSE' and 'LICENSE.txt' in the root directory

package net.jason13.dangerclose.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import crystalspider.soulfired.api.FireManager;
import crystalspider.soulfired.api.type.FireTyped;
import net.jason13.dangerclose.DangerClose;
import net.jason13.dangerclose.config.Config;
import net.jason13.dangerclose.init.Tags;
import net.jason13.dangerclose.optional.SoulFired;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DangerClose.MOD_ID)
public class DangerDetector {

    private static final Supplier<Boolean> isSoulfiredInstalled = () -> ModList.get().isLoaded("soulfired");

    private static void setOnFire(Entity entity, ResourceLocation fireType) {
        if (isSoulfiredInstalled.get()) {
            SoulFired.setOnFire(entity, 2, fireType);
        } else {
            entity.setSecondsOnFire(2);
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {

        if (Config.ENABLE_DANGER_CLOSE.get()) {

            LivingEntity entity = event.getEntity();
            Level level = entity.getLevel();
            BlockPos pos = entity.blockPosition();
            BlockState blockstate = level.getBlockState(pos);
            BlockState blockstateBelow = level.getBlockState(pos.below());

            List<LivingEntity> nearby = level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, entity, entity.getBoundingBox());
            List<String> nearby_names = new ArrayList<>();

            for (LivingEntity nearbyEntity : nearby) {
                if (nearbyEntity.isOnFire()) {
                    setOnFire(entity, ((FireTyped) nearbyEntity).getFireType());
                }
                else if (entity.isOnFire()) {
                    setOnFire(nearbyEntity, ((FireTyped) entity).getFireType());
                }
                nearby_names.add(nearbyEntity.getName().getString());
            }

            if (Config.ENABLE_BLAZE_DAMAGE.get() && nearby_names.contains("Blaze")) {
                setOnFire(entity, FireManager.DEFAULT_FIRE_TYPE);
            }
            if (Config.ENABLE_MAGMA_SLIME_DAMAGE.get() && nearby_names.contains("Magma Cube")) {
                setOnFire(entity, FireManager.DEFAULT_FIRE_TYPE);
            }

            /* ENTITIES ^ | v BLOCKS */

            if (Config.ENABLE_MAGMA_BLOCK_DAMAGE.get()) {
                if (blockstate.is(Blocks.MAGMA_BLOCK) || blockstateBelow.is(Blocks.MAGMA_BLOCK)) {
                    setOnFire(entity, FireManager.DEFAULT_FIRE_TYPE);
                }
            }
            if (Config.ENABLE_TORCH_BURN_DAMAGE.get()) {
                if (blockstate.getTags().anyMatch(key -> key == Tags.Blocks.TORCH_BURN_DANGER)) {
                    setOnFire(entity, blockstate.getBlock() == Blocks.SOUL_TORCH ? FireManager.SOUL_FIRE_TYPE : FireManager.DEFAULT_FIRE_TYPE);
                } else if (blockstateBelow.getTags().anyMatch(key -> key == Tags.Blocks.TORCH_BURN_DANGER)) {
                    setOnFire(entity, blockstateBelow.getBlock() == Blocks.SOUL_TORCH ? FireManager.SOUL_FIRE_TYPE : FireManager.DEFAULT_FIRE_TYPE);
                }
            }
            if (Config.ENABLE_CAMPFIRE_BURN_DAMAGE.get()) {
                if ((blockstate.getTags().anyMatch(key -> key == Tags.Blocks.CAMPFIRE_BURN_DANGER) && blockstate.getValue(BlockStateProperties.LIT))) {
                    setOnFire(entity, ((FireTyped) blockstate.getBlock()).getFireType());
                }
                else if (blockstateBelow.getTags().anyMatch(key -> key == Tags.Blocks.CAMPFIRE_BURN_DANGER) && blockstateBelow.getValue(BlockStateProperties.LIT)) {
                    setOnFire(entity, ((FireTyped) blockstateBelow.getBlock()).getFireType());
                }
            }
            if (Config.ENABLE_STONECUTTER_DAMAGE.get()) {
                if (blockstate.getTags().anyMatch(key -> key == Tags.Blocks.CUTTING_DANGER)) {
                    entity.hurt(DamageSource.CACTUS, 6);
                }
                else if (blockstateBelow.getTags().anyMatch(key -> key == Tags.Blocks.CUTTING_DANGER)) {
                    entity.hurt(DamageSource.CACTUS, 6);
                }
            }
        }
    }

}
