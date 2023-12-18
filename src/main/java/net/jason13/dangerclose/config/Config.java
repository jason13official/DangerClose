// Copyright (C) 2023 Jason13 https://github.com/jason13official
// Provided under the GNU LGPL v2.1 License, provided as files 'LICENSE' and 'LICENSE.txt' in the root directory

package net.jason13.dangerclose.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.nio.file.Path;

public class Config {

    public static void init(Path file)
    {
        final CommentedFileConfig configData = CommentedFileConfig.builder(file)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        SPEC.setConfig(configData);
    }

    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final String CATEGORY_COMMON = "common";
    private static final String CATEGORY_COMMON_COMMENT = "These settings will affect Client Server and Dedicated Server";

    public static final ModConfigSpec.BooleanValue ENABLE_TORCH_BURN_DAMAGE;
    public static final boolean ENABLE_TORCH_BURN_DAMAGE_DEFAULT = false;
    public static final String ENABLE_TORCH_BURN_DAMAGE_NAME = "enable_torch_burn_damage";
    public static final String ENABLE_TORCH_BURN_DAMAGE_COMMENT = "entities take burning damage from torches";

    public static final ModConfigSpec.BooleanValue ENABLE_CAMPFIRE_BURN_DAMAGE;
    public static final boolean ENABLE_CAMPFIRE_BURN_DAMAGE_DEFAULT = true;
    public static final String ENABLE_CAMPFIRE_BURN_DAMAGE_NAME = "enable_campfire_burn_damage";
    public static final String ENABLE_CAMPFIRE_BURN_DAMAGE_COMMENT = "entities take burning damage from campfires";

    public static final ModConfigSpec.BooleanValue ENABLE_STONECUTTER_DAMAGE;
    public static final boolean ENABLE_STONECUTTER_DAMAGE_DEFAULT = true;
    public static final String ENABLE_STONECUTTER_DAMAGE_NAME = "enable_stonecutter_damage";
    public static final String ENABLE_STONECUTTER_DAMAGE_COMMENT = "entities take cutting damage from stonecutters";

    public static final ModConfigSpec.BooleanValue ENABLE_BLAZE_DAMAGE;
    public static final boolean ENABLE_BLAZE_DAMAGE_DEFAULT = true;
    public static final String ENABLE_BLAZE_DAMAGE_NAME = "enable_blaze_damage";
    public static final String ENABLE_BLAZE_DAMAGE_COMMENT = "entities take burning damage from blaze entities";

    public static final ModConfigSpec.BooleanValue ENABLE_MAGMA_SLIME_DAMAGE;
    public static final boolean ENABLE_MAGMA_SLIME_DAMAGE_DEFAULT = true;
    public static final String ENABLE_MAGMA_SLIME_DAMAGE_NAME = "enable_magma_slime_damage";
    public static final String ENABLE_MAGMA_SLIME_DAMAGE_COMMENT = "entities take burning damage from magma slime entities";

    public static final ModConfigSpec.BooleanValue ENABLE_MAGMA_BLOCK_DAMAGE;
    public static final boolean ENABLE_MAGMA_BLOCK_DAMAGE_DEFAULT = false;
    public static final String ENABLE_MAGMA_BLOCK_DAMAGE_NAME = "enable_magma_block_damage";
    public static final String ENABLE_MAGMA_BLOCK_DAMAGE_COMMENT = "entities take burning damage from magma blocks";

    public static final ModConfigSpec.BooleanValue ENABLE_DANGER_CLOSE;
    public static final boolean ENABLE_DANGER_CLOSE_DEFAULT = true;
    public static final String ENABLE_DANGER_CLOSE_NAME = "enable_danger_close";
    public static final String ENABLE_DANGER_CLOSE_COMMENT = "enables or disables the mod";

    static {
        BUILDER.push(CATEGORY_COMMON);

        // Untested
        // BUILDER.comment(CATEGORY_COMMON_COMMENT);

        ENABLE_TORCH_BURN_DAMAGE = BUILDER.comment(ENABLE_TORCH_BURN_DAMAGE_COMMENT)
                .define(ENABLE_TORCH_BURN_DAMAGE_NAME, ENABLE_TORCH_BURN_DAMAGE_DEFAULT);

        ENABLE_CAMPFIRE_BURN_DAMAGE = BUILDER.comment(ENABLE_CAMPFIRE_BURN_DAMAGE_COMMENT)
                .define(ENABLE_CAMPFIRE_BURN_DAMAGE_NAME, ENABLE_CAMPFIRE_BURN_DAMAGE_DEFAULT);

        ENABLE_STONECUTTER_DAMAGE = BUILDER.comment(ENABLE_STONECUTTER_DAMAGE_COMMENT)
                .define(ENABLE_STONECUTTER_DAMAGE_NAME, ENABLE_STONECUTTER_DAMAGE_DEFAULT);

        ENABLE_BLAZE_DAMAGE = BUILDER.comment(ENABLE_BLAZE_DAMAGE_COMMENT)
                .define(ENABLE_BLAZE_DAMAGE_NAME, ENABLE_BLAZE_DAMAGE_DEFAULT);

        ENABLE_MAGMA_SLIME_DAMAGE = BUILDER.comment(ENABLE_MAGMA_SLIME_DAMAGE_COMMENT)
                .define(ENABLE_MAGMA_SLIME_DAMAGE_NAME, ENABLE_MAGMA_SLIME_DAMAGE_DEFAULT);

        ENABLE_MAGMA_BLOCK_DAMAGE = BUILDER.comment(ENABLE_MAGMA_BLOCK_DAMAGE_COMMENT)
                .define(ENABLE_MAGMA_BLOCK_DAMAGE_NAME, ENABLE_MAGMA_BLOCK_DAMAGE_DEFAULT);

        ENABLE_DANGER_CLOSE = BUILDER.comment(ENABLE_DANGER_CLOSE_COMMENT)
                .define(ENABLE_DANGER_CLOSE_NAME, ENABLE_DANGER_CLOSE_DEFAULT);

        BUILDER.pop();
    }

    public static final ModConfigSpec SPEC = BUILDER.build();
}
