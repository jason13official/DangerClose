// Copyright (C) 2023 Jason13 https://github.com/jason13official
// Provided under the GNU LGPL v2.1 License, provided as files 'LICENSE' and 'LICENSE.txt' in the root directory

package net.jason13.dangerclose.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(CampfireBlock.class)
public abstract class CampfireBlockMixin {

    /**
     * @author Jason13
     * @reason Disabled damaging entity inside Campfire Block
     */
    @Inject(method = "entityInside", at = @At(value = "HEAD"), cancellable = true)
    private void onEntityInside(BlockState state, Level world, BlockPos pos, Entity entity, CallbackInfo ci) {
        ci.cancel();
    }
}
