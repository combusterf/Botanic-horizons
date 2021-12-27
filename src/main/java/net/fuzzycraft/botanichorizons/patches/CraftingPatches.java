package net.fuzzycraft.botanichorizons.patches;

import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.enums.ToolDictNames;
import gregtech.api.util.GT_Utility;
import net.fuzzycraft.botanichorizons.util.OreDict;
import net.fuzzycraft.botanichorizons.util.Constants;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.crafting.ModCraftingRecipes;
import vazkii.botania.common.item.ItemSignalFlare;
import vazkii.botania.common.item.ItemTwigWand;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibOreDict;

import java.util.Arrays;

public class CraftingPatches {
    public static void applyPatches() {

        // Lexicon Recipe
        addShapelessOreDictRecipe(new ItemStack(ModItems.lexicon), OreDict.MUSHROOM, Items.book);
        ModCraftingRecipes.recipeLexicon = BotaniaAPI.getLatestAddedRecipe();

        // Petal/Dye Recipes
        // Has 2x yield in extractor
        for(int i = 0; i < 16; i++)
            addShapelessOreDictRecipe(new ItemStack(ModItems.petal, 1, i), LibOreDict.FLOWER[i]);
        ModCraftingRecipes.recipesPetals = BotaniaAPI.getLatestAddedRecipes(16);

        // Wand of the Forest Recipes
        for(int i = 0; i < 16; i++)
            for(int j = 0; j < 16; j++) {
                addOreDictRecipe(ItemTwigWand.forColors(i, j),
                        " AS", " SB", "S  ",
                        'A', OreDict.FLOWER_INGREDIENT[i],
                        'B', OreDict.FLOWER_INGREDIENT[j],
                        'S', LibOreDict.LIVINGWOOD_TWIG);
            }
        ModCraftingRecipes.recipesTwigWand = BotaniaAPI.getLatestAddedRecipes(256);

        // Petal Apothecary Recipes
        for(int i = 0; i < 16; i++)
            addOreDictRecipe(new ItemStack(ModBlocks.altar),
                    "SPS", " U ", "CCC",
                    'S', "plateIron",
                    'P', OreDict.FLOWER_INGREDIENT[i],
                    'U', new ItemStack(Items.cauldron),
                    'C', "stone");
        ModCraftingRecipes.recipesApothecary = BotaniaAPI.getLatestAddedRecipes(16);

        // Petal block extraction -- see also GT extractor version
        for(int i = 0; i < 16; i++) {
            addShapelessRecipe(new ItemStack(ModItems.petal, 9, i), new ItemStack(ModBlocks.petalBlock, 1, i));
        }
        ModCraftingRecipes.recipesPetals = BotaniaAPI.getLatestAddedRecipes(16);

        // floral fertiliser using witchery, and a less burdensome version using T2 planet dust
        addShapelessOreDictRecipe(new ItemStack(ModItems.fertilizer), "itemMutandis", "dustBone"); // MV
        addShapelessOreDictRecipe(new ItemStack(ModItems.fertilizer), "dustDraconium", "dustBone");  // EV
        ModCraftingRecipes.recipeFertilizerPowder = BotaniaAPI.getLatestAddedRecipe(); // book can hold only one

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Dilute pool
        addOreDictRecipe(new ItemStack(ModBlocks.pool, 1, Constants.POOL_META_DILUTE),
                "R R", "RRR",
                'R', new ItemStack(ModFluffBlocks.livingrockSlab)
        );
        ModCraftingRecipes.recipePoolDiluted = BotaniaAPI.getLatestAddedRecipe();

        // Regular pool
        addOreDictRecipe(new ItemStack(ModBlocks.pool, 1, Constants.POOL_META_REGULAR),
                "RIR", "RCR", "RRR",
                'R', new ItemStack(ModFluffBlocks.livingrockSlab),
                'I', LibOreDict.MANA_STEEL,
                'C', Constants.thaumcraftCrucible()
        );
        ModCraftingRecipes.recipePool = BotaniaAPI.getLatestAddedRecipe();

        // Fancy pool (same stats as regular)
        addOreDictRecipe(new ItemStack(ModBlocks.pool, 1, Constants.POOL_META_REGULAR_FABULOUS),
                "RIR", "RCR", "RRR",
                'R', new ItemStack(ModFluffBlocks.shimmerrockSlab),
                'I', LibOreDict.MANA_STEEL,
                'C', Constants.thaumcraftCrucible()
        );
        ModCraftingRecipes.recipePoolFabulous = BotaniaAPI.getLatestAddedRecipe();

        // There is no elven mana pool in this version?

        // Creative pool
        addOreDictRecipe(new ItemStack(ModBlocks.pool, 1, Constants.POOL_META_CREATIVE),
                "RIR", "RCR", "RRR",
                'R', "plateInfinity",
                'I', LibOreDict.GAIA_INGOT,
                'C', new ItemStack(ModBlocks.pool, 1, Constants.POOL_META_REGULAR_FABULOUS)
        );
        ModCraftingRecipes.recipePoolFabulous = BotaniaAPI.getLatestAddedRecipe();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // regular spreader
        for (int i = 0; i < 16; i++) {
            addOreDictRecipe(new ItemStack(ModBlocks.spreader, 1, Constants.SPREADER_META_REGULAR),
                    "WWW", "WPR", "WWW",
                    'W', new ItemStack(ModFluffBlocks.livingwoodSlab),
                    'P', OreDict.FLOWER_INGREDIENT[i],
                    'R', "ringGold"
            );
        }
        ModCraftingRecipes.recipesSpreader = BotaniaAPI.getLatestAddedRecipes(16);

        // Elven spreader
        for (int i = 0; i < 16; i++) {
            addOreDictRecipe(new ItemStack(ModBlocks.spreader, 1, Constants.SPREADER_META_ALFHEIM),
                    "WWW", "SPR", "WWW",
                    'W', new ItemStack(ModFluffBlocks.dreamwoodSlab),
                    'S', new ItemStack(ModBlocks.spreader, 1, Constants.SPREADER_META_REGULAR),
                    'P', OreDict.FLOWER_INGREDIENT[i],
                    'R', "gemJade"
            );
        }
        ModCraftingRecipes.recipesDreamwoodSpreader = BotaniaAPI.getLatestAddedRecipes(16);

        // gaia spreader
        addOreDictRecipe(new ItemStack(ModBlocks.spreader, 1, Constants.SPREADER_META_GAIA),
                "WWW", "SPR", "WWW",
                'W', new ItemStack(ModFluffBlocks.dreamwoodSlab),
                'S', new ItemStack(ModBlocks.spreader, 1, Constants.SPREADER_META_ALFHEIM),
                'P', LibOreDict.GAIA_INGOT,
                'R', "gemOpal"
        );
        ModCraftingRecipes.recipeUltraSpreader = BotaniaAPI.getLatestAddedRecipe();

        // pulse spreader
        addShapelessOreDictRecipe(new ItemStack(ModBlocks.spreader, 1, Constants.SPREADER_META_PULSE),
                new ItemStack(ModBlocks.spreader, 1, Constants.SPREADER_META_REGULAR),
                LibOreDict.REDSTONE_ROOT,
                new ItemStack(Items.repeater)
        );
        ModCraftingRecipes.recipeRedstoneSpreader = BotaniaAPI.getLatestAddedRecipe();

        // Mana Distributor
        addOreDictRecipe(new ItemStack(ModBlocks.distributor),
                "RGR", "GSG", "RGR",
                'R', LibOreDict.LIVING_ROCK,
                'S', "plateSilver",
                'G', new ItemStack(ModBlocks.manaGlass));
        ModCraftingRecipes.recipeDistributor = BotaniaAPI.getLatestAddedRecipe();

        // Mana Void Recipe
        addOreDictRecipe(new ItemStack(ModBlocks.manaVoid),
                "SGS", "GOG", "SGS",
                'S', LibOreDict.LIVING_ROCK,
                'O', new ItemStack(Blocks.obsidian),
                'G', new ItemStack(ModBlocks.manaGlass));
        ModCraftingRecipes.recipeManaVoid = BotaniaAPI.getLatestAddedRecipe();

        // Spreader Turntable Recipe
        addOreDictRecipe(new ItemStack(ModBlocks.turntable),
                "GWG", "WPW", "GWG",
                'W', LibOreDict.LIVING_WOOD,
                'P', Blocks.sticky_piston,
                'G', "gearWood");
        ModCraftingRecipes.recipeTurntable = BotaniaAPI.getLatestAddedRecipe();

        // Mana Detector Recipe
        addOreDictRecipe(new ItemStack(ModBlocks.manaDetector),
                "RSR", "SCS", "RSR",
                'R', "dustRedstone",
                'C', new ItemStack(Items.comparator),
                'S', LibOreDict.LIVING_ROCK);
        ModCraftingRecipes.recipeManaDetector = BotaniaAPI.getLatestAddedRecipe();

        // Mana Mirror Recipe
        addOreDictRecipe(new ItemStack(ModItems.manaMirror),
                " PR", " SI", "T  ",
                'P', LibOreDict.MANA_PEARL,
                'R', LibOreDict.LIVING_ROCK,
                'S', LibOreDict.LIVINGWOOD_TWIG,
                'I', LibOreDict.TERRA_STEEL,
                'T', new ItemStack(ModItems.manaTablet, 1, Short.MAX_VALUE));
        ModCraftingRecipes.recipeManaMirror = BotaniaAPI.getLatestAddedRecipe();

        // Mana Tablet Recipe
        addOreDictRecipe(new ItemStack(ModItems.manaTablet, 1, 10000),
                "SSS", "SPS", "SSS",
                'S', LibOreDict.LIVING_ROCK,
                'P', LibOreDict.MANA_PEARL);
        addOreDictRecipe(new ItemStack(ModItems.manaTablet, 1, 10000),
                "SSS", "SDS", "SSS",
                'S', LibOreDict.LIVING_ROCK,
                'D', LibOreDict.MANA_DIAMOND);
        ModCraftingRecipes.recipesManaTablet = BotaniaAPI.getLatestAddedRecipes(2);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Blocks

        // Living- and dreamstuff chisel recipes
        addOreDictRecipe(new ItemStack(ModBlocks.livingwood, 4, Constants.LIVINGWOOD_META_FRAMED),
                "WW", "WW", 'W', new ItemStack(ModBlocks.livingwood, 1, Constants.LIVINGWOOD_META_PLANK));
        ModCraftingRecipes.recipeLivingwoodDecor3 = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModBlocks.livingwood, 4, Constants.LIVINGWOOD_META_PATTERNED),
                "WW", "WW", 'W', new ItemStack(ModBlocks.livingwood, 1, Constants.LIVINGWOOD_META_FRAMED));
        ModCraftingRecipes.recipeLivingwoodDecor4 = BotaniaAPI.getLatestAddedRecipe();
        addShapelessOreDictRecipe(new ItemStack(ModBlocks.livingwood, 1, Constants.LIVINGWOOD_META_MOSSY),
                "cropVine", new ItemStack(ModBlocks.livingwood, 1, Constants.LIVINGWOOD_META_PLANK));
        ModCraftingRecipes.recipeLivingwoodDecor2 = BotaniaAPI.getLatestAddedRecipe();

        addOreDictRecipe(new ItemStack(ModBlocks.dreamwood, 4, Constants.LIVINGWOOD_META_FRAMED),
                "WW", "WW", 'W', new ItemStack(ModBlocks.dreamwood, 1, Constants.LIVINGWOOD_META_PLANK));
        addOreDictRecipe(new ItemStack(ModBlocks.dreamwood, 4, Constants.LIVINGWOOD_META_PATTERNED),
                "WW", "WW", 'W', new ItemStack(ModBlocks.dreamwood, 1, Constants.LIVINGWOOD_META_FRAMED));
        addShapelessOreDictRecipe(new ItemStack(ModBlocks.dreamwood, 1, Constants.LIVINGWOOD_META_MOSSY),
                "cropVine", new ItemStack(ModBlocks.dreamwood, 1, Constants.LIVINGWOOD_META_PLANK));
        // no lexicon for fancy dreamwood

        addOreDictRecipe(new ItemStack(ModBlocks.livingrock, 4, Constants.LIVINGSTONE_META_BRICK),
                "WW", "WW", 'W', new ItemStack(ModBlocks.livingrock, 1, Constants.LIVINGSTONE_META_BLOCK));
        ModCraftingRecipes.recipeLivingrockDecor1 = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModBlocks.livingrock, 4, Constants.LIVINGSTONE_META_CHISELED),
                "WW", "WW", 'W', new ItemStack(ModBlocks.livingrock, 1, Constants.LIVINGSTONE_META_BRICK));
        ModCraftingRecipes.recipeLivingrockDecor4 = BotaniaAPI.getLatestAddedRecipe();
        addShapelessOreDictRecipe(new ItemStack(ModBlocks.livingrock, 1, Constants.LIVINGSTONE_META_MOSSY),
                "cropVine", new ItemStack(ModBlocks.livingrock, 1, Constants.LIVINGSTONE_META_BRICK));
        ModCraftingRecipes.recipeLivingrockDecor2 = BotaniaAPI.getLatestAddedRecipe();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Lenses

        // Mana lens and lenses
        addOreDictRecipe(new ItemStack(ModItems.lens),
                " S ", "SGS", " S ",
                'S', LibOreDict.MANA_STEEL,
                'G', new ItemStack(ModBlocks.manaGlass));
        ModCraftingRecipes.recipesManaLens = BotaniaAPI.getLatestAddedRecipes(1);

        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 1), new ItemStack(ModItems.lens), LibOreDict.RUNE[3]);
        ModCraftingRecipes.recipeLensVelocity = BotaniaAPI.getLatestAddedRecipe();

        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 2), new ItemStack(ModItems.lens), LibOreDict.RUNE[1]);
        ModCraftingRecipes.recipeLensPotency = BotaniaAPI.getLatestAddedRecipe();

        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 3), new ItemStack(ModItems.lens), LibOreDict.RUNE[2]);
        ModCraftingRecipes.recipeLensResistance = BotaniaAPI.getLatestAddedRecipe();

        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 4), new ItemStack(ModItems.lens), LibOreDict.RUNE[0]);
        ModCraftingRecipes.recipeLensEfficiency = BotaniaAPI.getLatestAddedRecipe();

        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 5), new ItemStack(ModItems.lens), "slimeball", "ingotAnyRubber");
        ModCraftingRecipes.recipeLensBounce = BotaniaAPI.getLatestAddedRecipe();

        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 6), new ItemStack(ModItems.lens), "blockObsidian", "compressedCobblestone1x");
        ModCraftingRecipes.recipeLensGravity = BotaniaAPI.getLatestAddedRecipe();

        addOreDictRecipe(new ItemStack(ModItems.lens, 1, 7),
                " P ", "ALA", " R ",
                'P', Item.itemRegistry.getObject("dreamcraft:item.DiamondDrillTip"),
                'R', "dustRedstone",
                'A', "gemLapis",
                'L', new ItemStack(ModItems.lens));
        ModCraftingRecipes.recipeLensBore = BotaniaAPI.getLatestAddedRecipe();

        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 8), new ItemStack(ModItems.lens), new ItemStack(Items.diamond_sword));
        ModCraftingRecipes.recipeLensDamaging = BotaniaAPI.getLatestAddedRecipe();

        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 9), new ItemStack(ModItems.lens), new ItemStack(ModBlocks.platform));
        ModCraftingRecipes.recipeLensPhantom = BotaniaAPI.getLatestAddedRecipe();

        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 10), new ItemStack(ModItems.lens), "stickIronMagnetic", "stickSteelMagnetic");
        ModCraftingRecipes.recipeLensMagnet = BotaniaAPI.getLatestAddedRecipe();

        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 11), new ItemStack(ModItems.lens), LibOreDict.RUNE[14]);
        ModCraftingRecipes.recipeLensExplosive = BotaniaAPI.getLatestAddedRecipe();

        // Influence Lens Recipe
        addOreDictRecipe(new ItemStack(ModItems.lens, 1, 12),
                " P ", "PLP", "PPP",
                'P', LibOreDict.PRISMARINE_SHARD,
                'L', new ItemStack(ModItems.lens));
        ModCraftingRecipes.recipeLensInfluence = BotaniaAPI.getLatestAddedRecipe();

        // Weight Lens Recipe
        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 13), new ItemStack(ModItems.lens), "ingotBedrockium");
        ModCraftingRecipes.recipeLensWeight = BotaniaAPI.getLatestAddedRecipe();

        // Paintslinger Lens Recipe
        addOreDictRecipe(new ItemStack(ModItems.lens, 1, 14),
                " I ", "WLW", " I ",
                'I', LibOreDict.MANA_STEEL,
                'W', new ItemStack(Blocks.wool, 1, Short.MAX_VALUE),
                'L', new ItemStack(ModItems.lens));
        ModCraftingRecipes.recipeLensPaint = BotaniaAPI.getLatestAddedRecipe();

        // Warp Lens Recipe
        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 18), new ItemStack(ModItems.lens), LibOreDict.PIXIE_DUST);
        ModCraftingRecipes.recipeLensWarp = BotaniaAPI.getLatestAddedRecipe();

        // Redirective Lens Recipe
        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 19), new ItemStack(ModItems.lens), LibOreDict.LIVING_WOOD, LibOreDict.ELEMENTIUM);
        ModCraftingRecipes.recipeLensRedirect = BotaniaAPI.getLatestAddedRecipe();

        // Celebratory Lens Recipe
        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 20), new ItemStack(ModItems.lens), new ItemStack(Items.fireworks), LibOreDict.ELEMENTIUM);
        ModCraftingRecipes.recipeLensFirework = BotaniaAPI.getLatestAddedRecipe();

        // Flare Lens Recipe
        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 21), new ItemStack(ModItems.lens), new ItemStack(ModBlocks.elfGlass), LibOreDict.ELEMENTIUM);
        ModCraftingRecipes.recipeLensFlare = BotaniaAPI.getLatestAddedRecipe();

        // Mana Lens: Kindle Recipe
        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 15), new ItemStack(ModItems.lens), new ItemStack(Items.fire_charge));
        ModCraftingRecipes.recipeLensFire = BotaniaAPI.getLatestAddedRecipe();

        // Mana Lens: Piston Recipe
        addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 16), new ItemStack(ModItems.lens), new ItemStack(ModBlocks.pistonRelay));
        ModCraftingRecipes.recipeLensPiston = BotaniaAPI.getLatestAddedRecipe();

        // Mana Lens: Flash Recipe
        addOreDictRecipe(new ItemStack(ModItems.lens, 1, 17),
                "GFG", "FLF", "GFG",
                'G', "glowstone",
                'F', new ItemStack(Items.fire_charge),
                'L', new ItemStack(ModItems.lens));
        addOreDictRecipe(new ItemStack(ModItems.lens, 1, 17),
                "FGF", "GLG", "FGF",
                'G', "glowstone",
                'F', new ItemStack(Items.fire_charge),
                'L', new ItemStack(ModItems.lens));
        ModCraftingRecipes.recipesLensFlash = BotaniaAPI.getLatestAddedRecipes(2);

        // Mana Prism Recipe
        addOreDictRecipe(new ItemStack(ModBlocks.prism),
                "GPG", "GSG", "GPG",
                'G', new ItemStack(ModBlocks.manaGlass),
                'P', LibOreDict.PRISMARINE_SHARD,
                'S', new ItemStack(ModBlocks.platform, 1, 1));
        ModCraftingRecipes.recipePrism = BotaniaAPI.getLatestAddedRecipe();

        // Lens Clip Recipe
        addOreDictRecipe(new ItemStack(ModItems.clip),
                " D ", "D D", "DD ",
                'D', LibOreDict.DREAM_WOOD);
        ModCraftingRecipes.recipeClip = BotaniaAPI.getLatestAddedRecipe();
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Rods and wands

        // Rod of the Lands Recipe
        addOreDictRecipe(new ItemStack(ModItems.dirtRod),
                " SD", "STS", "ES ",
                'D', new ItemStack(Blocks.dirt),
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'E', LibOreDict.RUNE[2],
                'S', "screwTitanium");
        ModCraftingRecipes.recipeDirtRod = BotaniaAPI.getLatestAddedRecipe();

        // Terra Firma Rod Recipe
        addOreDictRecipe(new ItemStack(ModItems.terraformRod),
                "sWT", "ARS", "GMs",
                'T', LibOreDict.TERRA_STEEL,
                'R', new ItemStack(ModItems.dirtRod),
                'G', new ItemStack(ModItems.grassSeeds),
                'W', LibOreDict.RUNE[7],
                'S', LibOreDict.RUNE[4],
                'M', LibOreDict.RUNE[5],
                'A', LibOreDict.RUNE[6],
                's', "screwNaquadahAlloy");
        ModCraftingRecipes.recipeTerraformRod = BotaniaAPI.getLatestAddedRecipe();

        // Rod of the Seas Recipe
        addOreDictRecipe(new ItemStack(ModItems.waterRod),
                " sB", "sTs", "Rs ",
                'B', new ItemStack(Items.potionitem),
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'R', LibOreDict.RUNE[0],
                's', "screwStainlessSteel");
        ModCraftingRecipes.recipeWaterRod = BotaniaAPI.getLatestAddedRecipe();

        // Rod of Bifrost Recipe
        addOreDictRecipe(new ItemStack(ModItems.rainbowRod),
                " PD", "sEP", "Es ",
                'P', LibOreDict.PIXIE_DUST,
                'E', LibOreDict.ELEMENTIUM,
                'D', LibOreDict.DRAGONSTONE,
                'S', "screwTitanium");
        ModCraftingRecipes.recipeRainbowRod = BotaniaAPI.getLatestAddedRecipe();

        // Rod of the Skies Recipe
        addOreDictRecipe(new ItemStack(ModItems.tornadoRod),
                " sF", "sTs", "Rs ",
                'F', new ItemStack(Items.feather),
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'R', LibOreDict.RUNE[3],
                's', "screwStainlessSteel");
        ModCraftingRecipes.recipeTornadoRod = BotaniaAPI.getLatestAddedRecipe();

        // Rod of the Hells Recipe
        addOreDictRecipe(new ItemStack(ModItems.fireRod),
                " sF", "sTs", "Rs ",
                'F', new ItemStack(Items.blaze_powder),
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'R', LibOreDict.RUNE[1],
                's', "screwTitanium");
        ModCraftingRecipes.recipeFireRod = BotaniaAPI.getLatestAddedRecipe();

        // Rod of the Highlands Recipe
        addShapelessOreDictRecipe(new ItemStack(ModItems.skyDirtRod), new ItemStack(ModItems.dirtRod), LibOreDict.PIXIE_DUST, LibOreDict.RUNE[3]);
        ModCraftingRecipes.recipeSkyDirtRod = BotaniaAPI.getLatestAddedRecipe();

        // Rod of the Unstable Reservoir
        addOreDictRecipe(new ItemStack(ModItems.missileRod),
                "GDD", "sTD", "TsG",
                'G', LibOreDict.LIFE_ESSENCE,
                'D', LibOreDict.DRAGONSTONE,
                'T', LibOreDict.DREAMWOOD_TWIG,
                's', "screwNaquadah");
        ModCraftingRecipes.recipeMissileRod = BotaniaAPI.getLatestAddedRecipe();

        // Rod of the Depths Recipe
        addOreDictRecipe(new ItemStack(ModItems.cobbleRod),
                " FC", "sTW", "Ts ",
                'F', LibOreDict.RUNE[1],
                'W', LibOreDict.RUNE[0],
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'C', "cobblestone",
                's', "screwStainlessSteel");
        ModCraftingRecipes.recipeCobbleRod = BotaniaAPI.getLatestAddedRecipe();

        // Rod of the Molten Core Recipe
        addOreDictRecipe(new ItemStack(ModItems.smeltRod),
                " BF", "sTB", "Ts ",
                'B', new ItemStack(Items.blaze_rod),
                'F', LibOreDict.RUNE[1],
                'T', LibOreDict.LIVINGWOOD_TWIG,
                's', "screwStainlessSteel");
        ModCraftingRecipes.recipeSmeltRod = BotaniaAPI.getLatestAddedRecipe();

        // Rod of the Plentiful Mantle
        addOreDictRecipe(new ItemStack(ModItems.diviningRod),
                " TD", "sTT", "Ts ",
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'D', LibOreDict.MANA_PEARL,
                's', "screwAluminium");
        ModCraftingRecipes.recipeDiviningRod = BotaniaAPI.getLatestAddedRecipe();

        // Rod of the Black Mesa Recipe
        addOreDictRecipe(new ItemStack(ModItems.gravityRod),
                " TD", "sWT", "Ts ",
                'T', LibOreDict.DREAMWOOD_TWIG,
                'W', "cropWheat",
                'D', LibOreDict.DRAGONSTONE,
                's', "screwTungstenSteel");
        ModCraftingRecipes.recipeGravityRod = BotaniaAPI.getLatestAddedRecipe();

        // Rod of the Shifting Crust Recipe
        addOreDictRecipe(new ItemStack(ModItems.exchangeRod),
                " sR", "sTs", "Ts ",
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'S', LibOreDict.MANA_PEARL,
                'R', LibOreDict.RUNE[12],
                's', "screwTitanium");
        ModCraftingRecipes.recipeExchangeRod = BotaniaAPI.getLatestAddedRecipe();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Standard armour and tools

        // Mana Armor & Tools Recipes
        addOreDictRecipe(new ItemStack(ModItems.manasteelHelm),
                "SSS", "ShS",
                'S', LibOreDict.MANA_STEEL,
                'h', ToolDictNames.craftingToolHardHammer.name());
        ModCraftingRecipes.recipeManasteelHelm = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.manasteelChest),
                "ShS", "SSS", "SSS",
                'S', LibOreDict.MANA_STEEL,
                'h', ToolDictNames.craftingToolHardHammer.name());
        ModCraftingRecipes.recipeManasteelChest = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.manasteelLegs),
                "SSS", "ShS", "S S",
                'S', LibOreDict.MANA_STEEL,
                'h', ToolDictNames.craftingToolHardHammer.name());
        ModCraftingRecipes.recipeManasteelLegs = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.manasteelBoots),
                "ShS", "S S",
                'S', LibOreDict.MANA_STEEL,
                'h', ToolDictNames.craftingToolHardHammer.name());
        ModCraftingRecipes.recipeManasteelBoots = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.manasteelPick),
                "SSS", "fTh", " T ",
                'S', LibOreDict.MANA_STEEL,
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeManasteelPick = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.manasteelShovel),
                " S ", "fTh", " T ",
                'S', LibOreDict.MANA_STEEL,
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeManasteelShovel = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.manasteelAxe),
                "SSh", "ST ", "fT ",
                'S', LibOreDict.MANA_STEEL,
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeManasteelAxe = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.manasteelSword),
                " S ", "fSh", " T ",
                'S', LibOreDict.MANA_STEEL,
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeManasteelSword = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.manasteelShears),
                "hS", "Sf",
                'S', LibOreDict.MANA_STEEL,
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeManasteelShears = BotaniaAPI.getLatestAddedRecipe();

        // Elementium Armor & Tools Recipes
        addOreDictRecipe(new ItemStack(ModItems.elementiumHelm),
                "SSS", "ShS",
                'S', LibOreDict.ELEMENTIUM,
                'h', ToolDictNames.craftingToolHardHammer.name());
        ModCraftingRecipes.recipeElementiumHelm = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.elementiumChest),
                "ShS", "SSS", "SSS",
                'S', LibOreDict.ELEMENTIUM,
                'h', ToolDictNames.craftingToolHardHammer.name());
        ModCraftingRecipes.recipeElementiumChest = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.elementiumLegs),
                "SSS", "ShS", "S S",
                'S', LibOreDict.ELEMENTIUM,
                'h', ToolDictNames.craftingToolHardHammer.name());
        ModCraftingRecipes.recipeElementiumLegs = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.elementiumBoots),
                "ShS", "S S",
                'S', LibOreDict.ELEMENTIUM,
                'h', ToolDictNames.craftingToolHardHammer.name());
        ModCraftingRecipes.recipeElementiumBoots = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.elementiumPick),
                "SSS", "fTh", " T ",
                'S', LibOreDict.ELEMENTIUM,
                'T', LibOreDict.DREAMWOOD_TWIG,
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeElementiumPick = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.elementiumShovel),
                " S ", "fTh", " T ",
                'S', LibOreDict.ELEMENTIUM,
                'T', LibOreDict.DREAMWOOD_TWIG,
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeElementiumShovel = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.elementiumAxe),
                "SSh", "ST ", "fT ",
                'S', LibOreDict.ELEMENTIUM,
                'T', LibOreDict.DREAMWOOD_TWIG,
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeElementiumAxe = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.elementiumSword),
                " S ", "fSh", " T ",
                'S', LibOreDict.ELEMENTIUM,
                'T', LibOreDict.DREAMWOOD_TWIG,
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeElementiumSword = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.elementiumShears),
                "hS", "Sf",
                'S', LibOreDict.ELEMENTIUM,
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeElementiumShears = BotaniaAPI.getLatestAddedRecipe();

        // Terrasteel Armor Recipes
        addOreDictRecipe(new ItemStack(ModItems.terrasteelHelmRevealing),
                "TRT", "SAS", "fSh",
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'S', LibOreDict.TERRA_STEEL,
                'R', LibOreDict.RUNE[4],
                'A', new ItemStack(ModItems.manasteelHelmRevealing),
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        addOreDictRecipe(new ItemStack(ModItems.terrasteelHelm),
                "TRT", "SAS", "fSh",
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'S', LibOreDict.TERRA_STEEL,
                'R', LibOreDict.RUNE[4],
                'A', new ItemStack(ModItems.manasteelHelm),
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeTerrasteelHelm = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.terrasteelChest),
                "TRT", "SAS", "fSh",
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'S', LibOreDict.TERRA_STEEL,
                'R', LibOreDict.RUNE[5],
                'A', new ItemStack(ModItems.manasteelChest),
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeTerrasteelChest = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.terrasteelLegs),
                "TRT", "SAS", "fSh",
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'S', LibOreDict.TERRA_STEEL,
                'R', LibOreDict.RUNE[6],
                'A', new ItemStack(ModItems.manasteelLegs),
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeTerrasteelLegs = BotaniaAPI.getLatestAddedRecipe();
        addOreDictRecipe(new ItemStack(ModItems.terrasteelBoots),
                "TRT", "SAS", "fSh",
                'T', LibOreDict.LIVINGWOOD_TWIG,
                'S', LibOreDict.TERRA_STEEL,
                'R', LibOreDict.RUNE[7],
                'A', new ItemStack(ModItems.manasteelBoots),
                'h', ToolDictNames.craftingToolHardHammer.name(),
                'f', ToolDictNames.craftingToolFile.name());
        ModCraftingRecipes.recipeTerrasteelBoots = BotaniaAPI.getLatestAddedRecipe();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Misc crafting items

        // Brewery vial
        addOreDictRecipe(new ItemStack(ModItems.vial, 1, Constants.VIAL_META_MANAGLASS),
                "GSG", "G G", "GGG",
                'G', new ItemStack(ModBlocks.manaGlass),
                'S', "springSmallAnyRubber"
        );
        ModCraftingRecipes.recipeVial = BotaniaAPI.getLatestAddedRecipe();

        // Gaia Spirit Ingot Recipe
        addOreDictRecipe(new ItemStack(ModItems.manaResource, 1, 14),
                " S ", "SIS", " S ",
                'S', LibOreDict.LIFE_ESSENCE,
                'I', LibOreDict.TERRA_STEEL);
        ModCraftingRecipes.recipeGaiaIngot = BotaniaAPI.getLatestAddedRecipe();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Misc utility items

        // Mana Blaster Recipe
        addOreDictRecipe(new ItemStack(ModItems.manaGun),
                "SMD", " WT", "  W",
                'S', new ItemStack(ModBlocks.spreader, 1, 1),
                'M', LibOreDict.RUNE[8],
                'D', LibOreDict.MANA_DIAMOND,
                'T', new ItemStack(Blocks.tnt),
                'W', LibOreDict.LIVING_WOOD);
        ModCraftingRecipes.recipeManaBlaster = BotaniaAPI.getLatestAddedRecipe();

        // Signal Flare Recipe
        for(int i = 0; i < 16; i++)
            addOreDictRecipe(ItemSignalFlare.forColor(i),
                    "I ", " B", "W ",
                    'B', new ItemStack(ModBlocks.manaBeacon, 1, i),
                    'I', "ingotIron",
                    'W', LibOreDict.LIVING_WOOD);
        ModCraftingRecipes.recipesSignalFlares = BotaniaAPI.getLatestAddedRecipes(16);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Misc blocks

        // Unstable Block Recipes
        for(int i = 0; i < 16; i++)
            addOreDictRecipe(new ItemStack(ModBlocks.unstableBlock, 2, i),
                    "OPO", "PMP", "OPO",
                    'O', new ItemStack(Blocks.obsidian),
                    'P', LibOreDict.PETAL[i],
                    'M', LibOreDict.MANA_PEARL);
        ModCraftingRecipes.recipesUnstableBlocks = BotaniaAPI.getLatestAddedRecipes(16);

        // Unstable Beacon Recipe
        for(int i = 0; i < 16; i++)
            addOreDictRecipe(new ItemStack(ModBlocks.manaBeacon, 1, i),
                    " B ", "BPB", " B ",
                    'B', new ItemStack(ModBlocks.unstableBlock, 1, i),
                    'P', new ItemStack(ModItems.lens, 1, 0));
        ModCraftingRecipes.recipesManaBeacons = BotaniaAPI.getLatestAddedRecipes(16);

    }

    private static void addOreDictRecipe(ItemStack output, Object... recipe) {
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(output, recipe));
    }

    private static void addShapelessOreDictRecipe(ItemStack output, Object... recipe) {
        CraftingManager.getInstance().getRecipeList().add(new ShapelessOreRecipe(output, recipe));
    }

    private static void addShapelessRecipe(ItemStack output, ItemStack... recipe) {
        CraftingManager.getInstance().getRecipeList().add(new ShapelessRecipes(output, Arrays.asList(recipe)));
    }
}
