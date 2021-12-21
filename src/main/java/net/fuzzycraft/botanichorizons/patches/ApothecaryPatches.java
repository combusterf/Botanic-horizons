package net.fuzzycraft.botanichorizons.patches;

import net.fuzzycraft.botanichorizons.mod.OreDict;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.common.Botania;
import vazkii.botania.common.CustomBotaniaAPI;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.crafting.ModPetalRecipes;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibBlockNames;
import vazkii.botania.common.lib.LibOreDict;

import java.util.Arrays;

public class ApothecaryPatches {
    public static final String white = OreDict.FLOWER_INGREDIENT[0], orange = OreDict.FLOWER_INGREDIENT[1], magenta = OreDict.FLOWER_INGREDIENT[2], lightBlue = OreDict.FLOWER_INGREDIENT[3], yellow = OreDict.FLOWER_INGREDIENT[4], lime = OreDict.FLOWER_INGREDIENT[5], pink = OreDict.FLOWER_INGREDIENT[6], gray = OreDict.FLOWER_INGREDIENT[7], lightGray = OreDict.FLOWER_INGREDIENT[8], cyan = OreDict.FLOWER_INGREDIENT[9], purple = OreDict.FLOWER_INGREDIENT[10], blue = OreDict.FLOWER_INGREDIENT[11], brown = OreDict.FLOWER_INGREDIENT[12], green = OreDict.FLOWER_INGREDIENT[13], red = OreDict.FLOWER_INGREDIENT[14], black = OreDict.FLOWER_INGREDIENT[15];
    public static final String runeWater = LibOreDict.RUNE[0], runeFire = LibOreDict.RUNE[1], runeEarth = LibOreDict.RUNE[2], runeAir = LibOreDict.RUNE[3], runeSpring = LibOreDict.RUNE[4], runeSummer = LibOreDict.RUNE[5], runeAutumn = LibOreDict.RUNE[6], runeWinter = LibOreDict.RUNE[7], runeMana = LibOreDict.RUNE[8], runeLust = LibOreDict.RUNE[9], runeGluttony = LibOreDict.RUNE[10], runeGreed = LibOreDict.RUNE[11], runeSloth = LibOreDict.RUNE[12], runeWrath = LibOreDict.RUNE[13], runeEnvy = LibOreDict.RUNE[14], runePride = LibOreDict.RUNE[15];

    public static void applyPatches() {
        // Add handlers for things that can go in the apothecary
        CustomBotaniaAPI.extraFlowerComponents.put(Item.getItemFromBlock(ModBlocks.mushroom), alwaysAllowHandler);

        // TODO: update these
        ModPetalRecipes.pureDaisyRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_PUREDAISY),
                white, white, white, white);
        ModPetalRecipes.manastarRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_MANASTAR),
                lightBlue, green, red, cyan);

        ModPetalRecipes.daybloomRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DAYBLOOM),
                yellow, yellow, orange, lightBlue);
        ModPetalRecipes.nightshadeRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_NIGHTSHADE),
                black, black, purple, gray);
        ModPetalRecipes.endoflameRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ENDOFLAME),
                brown, brown, red, lightGray, ModPetalRecipes.manaPowder);
        ModPetalRecipes.hydroangeasRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_HYDROANGEAS),
                blue, blue, cyan, cyan, ModPetalRecipes.manaPowder);
        ModPetalRecipes.thermalilyRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_THERMALILY),
                red, orange, orange, runeEarth, runeFire);
        ModPetalRecipes.arcaneRoseRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ARCANE_ROSE),
                pink, pink, purple, purple, lime, runeMana);
        ModPetalRecipes.munchdewRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_MUNCHDEW),
                lime, lime, red, red, green, runeGluttony);
        ModPetalRecipes.entropinnyumRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ENTROPINNYUM),
                red, red, gray, gray, white, white, runeWrath, runeFire);
        ModPetalRecipes.kekimurusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_KEKIMURUS),
                white, white, orange, orange, brown, brown,runeGluttony, ModPetalRecipes.pixieDust);
        ModPetalRecipes.gourmaryllisRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_GOURMARYLLIS),
                lightGray, lightGray, yellow, yellow, red, runeFire, runeSummer);
        ModPetalRecipes.narslimmusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_NARSLIMMUS),
                lime, lime, green, green, black, runeSummer, runeWater);
        ModPetalRecipes.spectrolusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_SPECTROLUS),
                red, red, green, green, blue, blue, white, white, runeWinter, runeAir, ModPetalRecipes.pixieDust);
        ModPetalRecipes.rafflowsiaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_RAFFLOWSIA),
                purple, purple, green, green, black, runeEarth, runePride, ModPetalRecipes.pixieDust);
        ModPetalRecipes.dandelifeonRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DANDELIFEON),
                purple, purple, lime, green, runeWater, runeFire, runeEarth, runeAir, ModPetalRecipes.gaiaSpirit);

        ModPetalRecipes.jadedAmaranthusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_JADED_AMARANTHUS),
                purple, lime, green, runeSpring, ModPetalRecipes.redstoneRoot);
        ModPetalRecipes.bellethorneRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_BELLETHORN),
                red, red, red, cyan, cyan, ModPetalRecipes.redstoneRoot);
        ModPetalRecipes.dreadthorneRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DREADTHORN),
                black, black, black, cyan, cyan, ModPetalRecipes.redstoneRoot);
        ModPetalRecipes.heiseiDreamRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_HEISEI_DREAM),
                magenta, magenta, purple, pink, runeWrath, ModPetalRecipes.pixieDust);
        ModPetalRecipes.tigerseyeRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_TIGERSEYE),
                yellow, brown, orange, lime, runeAutumn);

        ModPetalRecipes.orechidRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ORECHID),
                gray, gray, yellow, green, red, runePride, runeGreed, ModPetalRecipes.redstoneRoot, ModPetalRecipes.pixieDust);

        ModPetalRecipes.orechidIgnemRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_ORECHID_IGNEM),
                red, red, white, white, pink, runePride, runeGreed, ModPetalRecipes.redstoneRoot, ModPetalRecipes.pixieDust);
        ModPetalRecipes.fallenKanadeRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_FALLEN_KANADE),
                white, white, yellow, yellow, orange, runeSpring);
        ModPetalRecipes.exoflameRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_EXOFLAME),
                red, red, gray, lightGray, runeFire, runeSummer);
        ModPetalRecipes.agricarnationRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_AGRICARNATION),
                lime, lime, green, yellow, runeSpring, ModPetalRecipes.redstoneRoot);
        ModPetalRecipes.hopperhockRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_HOPPERHOCK),
                gray, gray, lightGray, lightGray, runeAir, ModPetalRecipes.redstoneRoot);
        ModPetalRecipes.tangleberrieRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_TANGLEBERRIE),
                cyan, cyan, gray, lightGray, runeAir, runeEarth);
        ModPetalRecipes.jiyuuliaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_JIYUULIA),
                pink, pink, purple, lightGray, runeWater, runeAir);
        ModPetalRecipes.rannuncarpusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_RANNUNCARPUS),
                orange, orange, yellow, runeEarth, ModPetalRecipes.redstoneRoot);
        ModPetalRecipes.hyacidusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_HYACIDUS),
                purple, purple, magenta, magenta, green, runeWater, runeAutumn, ModPetalRecipes.redstoneRoot);
        ModPetalRecipes.pollidisiacRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_POLLIDISIAC),
                red, red, pink, pink, orange, runeLust, runeFire);
        ModPetalRecipes.clayconiaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_CLAYCONIA),
                lightGray, lightGray, gray, cyan, runeEarth);
        ModPetalRecipes.looniumRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_LOONIUM),
                green, green, green, green, gray, runeSloth, runeGluttony, runeEnvy, ModPetalRecipes.redstoneRoot, ModPetalRecipes.pixieDust);
        ModPetalRecipes.daffomillRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_DAFFOMILL),
                white, white, brown, yellow, runeAir, ModPetalRecipes.redstoneRoot);
        ModPetalRecipes.vinculotusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_VINCULOTUS),
                black, black, purple, purple, green, runeWater, runeSloth, runeLust, ModPetalRecipes.redstoneRoot);
        ModPetalRecipes.spectranthemumRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_SPECTRANTHEMUM),
                white, white, lightGray, lightGray, cyan, runeEnvy, runeWater, ModPetalRecipes.redstoneRoot, ModPetalRecipes.pixieDust);
        ModPetalRecipes.medumoneRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_MEDUMONE),
                brown, brown, gray, gray, runeEarth, ModPetalRecipes.redstoneRoot);
        ModPetalRecipes.marimorphosisRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_MARIMORPHOSIS),
                gray, yellow, green, red, runeEarth, runeFire, ModPetalRecipes.redstoneRoot);
        ModPetalRecipes.bubbellRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_BUBBELL),
                cyan, cyan, lightBlue, lightBlue, blue, blue, runeWater, runeSummer, ModPetalRecipes.pixieDust);
        ModPetalRecipes.solegnoliaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType(LibBlockNames.SUBTILE_SOLEGNOLIA),
                brown, brown, red, blue, ModPetalRecipes.redstoneRoot);

        // Secret skull recipe
        ItemStack stack = new ItemStack(Items.skull, 1, 3);
        ItemNBTHelper.setString(stack, "SkullOwner", "Vazkii");
        Object[] inputs = new Object[16];
        Arrays.fill(inputs, pink);
        BotaniaAPI.registerPetalRecipe(stack, inputs);
    }

    private static IFlowerComponent alwaysAllowHandler = new IFlowerComponent() {
        @Override
        public boolean canFit(ItemStack itemStack, IInventory iInventory) {
            return true;
        }

        @Override
        public int getParticleColor(ItemStack stack) {
            return 0;
        }
    };
}
