// Copyright (C) 2023 Jason13 https://github.com/jason13official
// Provided under the GNU LGPL v2.1 License, provided as files 'LICENSE' and 'LICENSE.txt' in the root directory

package net.jason13.dangerclose.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(CampfireBlock.class)
public abstract class CampfireBlockMixin {

    /**
     * @author Jason13
     * @reason Disabled damaging entity inside Campfire Block
     */
    @Overwrite
    public void entityInside(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, Entity pEntity) {}
    // @Overwrite
    // public void entityInside(BlockState p_51269_, Level p_51270_, BlockPos p_51271_, Entity p_51272_) {
    //     if ((Boolean)p_51269_.getValue(LIT) && p_51272_ instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)p_51272_)) {
    //         p_51272_.hurt(DamageSource.IN_FIRE, (float) ((CampfireBlock)(Object) this).fireDamage);
    //     }
    //
    //     ((CampfireBlock)(Object) this).entityInside(p_51269_, p_51270_, p_51271_, p_51272_);
    // }
    // @Inject(method = "entityInside", at = @At("HEAD"), cancellable = true)
    // public void entityInside(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, Entity pEntity, CallbackInfo pCallbackInfo) {
    //     if (!ModList.get().isLoaded("soulfired")) {
    //         pCallbackInfo.cancel();
    //     }
    // }
}
