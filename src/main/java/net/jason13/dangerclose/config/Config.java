// Copyright (C) 2023 Jason13 https://github.com/jason13official
// Provided under the GNU LGPL v2.1 License, provided as files 'LICENSE' and 'LICENSE.txt' in the root directory

package net.jason13.dangerclose.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

//    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_TORCH_BURN_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_CAMPFIRE_BURN_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_STONECUTTER_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_BLAZE_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_MAGMA_SLIME_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_MAGMA_BLOCK_DAMAGE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENABLE_DANGER_CLOSE;

    static {
        BUILDER.push("Danger Close Common Configuration");

        ENABLE_TORCH_BURN_DAMAGE = BUILDER.define("ENTITIES TAKE BURNING DAMAGE FROM TORCHES", false);
        ENABLE_CAMPFIRE_BURN_DAMAGE = BUILDER.define("ENTITIES TAKE BURNING DAMAGE FROM CAMPFIRES", true);
        ENABLE_STONECUTTER_DAMAGE = BUILDER.define("ENTITIES TAKE CUTTING DAMAGE FROM STONECUTTERS", true);
        ENABLE_BLAZE_DAMAGE = BUILDER.define("ENTITIES TAKE BURNING DAMAGE FROM BLAZES", true);
        ENABLE_MAGMA_SLIME_DAMAGE = BUILDER.define("ENTITIES TAKE BURNING DAMAGE FROM MAGMA CUBES", true);
        ENABLE_MAGMA_BLOCK_DAMAGE = BUILDER.define("ENTITIES TAKE BURNING DAMAGE FROM MAGMA BLOCKS", false);
        ENABLE_DANGER_CLOSE = BUILDER.define("IS THE MOD ENABLED", true);

        BUILDER.pop();
    }

    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
