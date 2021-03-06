package net.fuzzycraft.botanichorizons.mod;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.fuzzycraft.botanichorizons.patches.ApothecaryPatches;
import net.fuzzycraft.botanichorizons.patches.CraftingPatches;
import net.fuzzycraft.botanichorizons.patches.OredictPatches;

@Mod(modid = ForgeMod.MOD_ID, name = ForgeMod.MOD_NAME, version = ForgeMod.VERSION, dependencies = ForgeMod.DEPENDENCIES)
public class ForgeMod {
    public static final String MOD_ID = "BotanicHorizons";
    public static final String MOD_NAME = MOD_ID;
    public static final String BUILD = "GRADLE:BUILD";
    public static final String VERSION = "GRADLE:VERSION-" + BUILD;
    public static final String DEPENDENCIES = "required-after:Baubles;required-after:Thaumcraft;required-after:Botania";

    @Mod.Instance(MOD_ID)
    public static ForgeMod instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("Initializer called");

        ApothecaryPatches.applyPatches();
        CraftingPatches.applyPatches();
        OredictPatches.applyPatches();
    }


}
