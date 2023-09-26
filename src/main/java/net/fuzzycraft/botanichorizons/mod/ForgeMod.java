package net.fuzzycraft.botanichorizons.mod;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.fuzzycraft.botanichorizons.addons.Blocks;
import net.fuzzycraft.botanichorizons.patches.*;

@Mod(modid = ForgeMod.MOD_ID, name = ForgeMod.MOD_NAME, version = ForgeMod.VERSION, dependencies = ForgeMod.DEPENDENCIES)
public class ForgeMod {
    public static final String MOD_ID = "botanichorizons";
    public static final String MOD_NAME = MOD_ID;
    public static final String VERSION = "GRADLETOKEN_VERSION";
    public static final String DEPENDENCIES = "required-after:Baubles;required-after:Thaumcraft;required-after:Botania;required-after:gregtech;after:witchery;after:BiomesOPlenty;after:dreamcraft;required-after:TConstruct;required-after:Avaritia;after:chisel";
    //public static final String DEPENDENCIES = "required-after:Botania"; // developer mode

    @Mod.Instance(MOD_ID)
    public static ForgeMod instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Blocks.initBlocks();

        OredictPatches.applyPatches();

        AlfheimPatches.applyPatches();
        if (!isPackMode()) return;
        CraftingPatches.applyPatches();
        ApothecaryPatches.applyPatches();
        DaisyPatches.applyPatches();
        ManaInfusionPatches.applyPatches();
        RunicAltarPatches.applyPatches();
        BreweryPatches.applyPatches();

        GregtechPatches.applyPatches();
        AvaritiaPatches.applyPatches();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (!isPackMode()) return;

        ThaumcraftAspects.registerAspects();
        ThaumcraftPatches.applyPatches();
        if (Loader.isModLoaded("chisel")) {
            ChiselPatches.applyPatches();
        }
    }

    public static boolean isPackMode() {
        return
            Loader.isModLoaded("Baubles") &&
            Loader.isModLoaded("Thaumcraft") &&
            Loader.isModLoaded("gregtech") &&
            Loader.isModLoaded("Avaritia") &&
            Loader.isModLoaded("TConstruct");
    }
}
