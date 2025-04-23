package net.fuzzycraft.botanichorizons.lexicon;

import net.fuzzycraft.botanichorizons.addons.BHBlocks;
import net.fuzzycraft.botanichorizons.addons.Multiblocks;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.lexicon.BLexiconEntry;
import vazkii.botania.common.lexicon.page.PageMultiblock;

public final class BHLexicon {
    private BHLexicon() {}

    public static LexiconCategory multiblockCategory;

    public static void preInit() {
        multiblockCategory = new LexiconCategory("botanichorizons.lexicon.category.multiblock")
                .setPriority(2);
        BotaniaAPI.addCategory(multiblockCategory);
    }

    public static void init() {
        LexiconEntry gatewayEntry = new BLexiconEntry("botanichorizons.lexicon.gateway", multiblockCategory);
        gatewayEntry.addPage(new PageMultiblock("botanichorizons.lexicon.gateway.preview",
                Multiblocks.alfPortal.lexiconMultiblock(
                        0, 1, -2,
                        BHBlocks.autoPortal, 0
                )));
    }


}
