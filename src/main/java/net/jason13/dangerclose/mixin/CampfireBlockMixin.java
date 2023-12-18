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
public class CampfireBlockMixin {

    /**
     * @author Jason13
     * @reason Disabled damaging entity inside Campfire Block
     */
    @Overwrite
    public void entityInside(BlockState pBlockState, Level pLevel, BlockPos pBlockPos, Entity pEntity) {}
}
