package net.fuzzycraft.botanichorizons.addons.block;

import net.fuzzycraft.botanichorizons.addons.tileentity.TileAdvancedAlfPortal;
import net.fuzzycraft.botanichorizons.util.Facing2D;
import net.fuzzycraft.botanichorizons.util.multiblock.MultiblockStructure;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileAlfPortal;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockAdvancedAlfPortal extends BlockModContainer implements IWandable, ILexiconable {

    public static final String NAME = "automatedAlfPortal";

    public BlockAdvancedAlfPortal() {
        super(Material.wood);
        setHardness(10.0F);
        setStepSound(soundTypeWood);
        setBlockName(NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileAdvancedAlfPortal();
    }

    IIcon iconOff;
    IIcon iconOn;
    public static IIcon portalTex;

    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.iconOff = IconHelper.forBlock(par1IconRegister, this, 0);
        this.iconOn = IconHelper.forBlock(par1IconRegister, this, 1);
        portalTex = IconHelper.forBlock(par1IconRegister, this, "Inside");
    }

    public IIcon getIcon(int side, int meta) {
        return meta == 0 ? this.iconOff : this.iconOn;
    }

    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.alfhomancyIntro;
    }

    public void onBlockPlacedBy(World worldIn, int x, int y, int z, EntityLivingBase placer, ItemStack itemIn) {
        Facing2D facing = Facing2D.facingPlayer(placer);
        if (placer instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) placer;
            player.addChatMessage(new ChatComponentText("Facing index: " + facing.index + ": " + facing.name()));
        }
        worldIn.setBlockMetadataWithNotify(x, y, z, facing.index, 3);
    }


    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        boolean did = ((TileAdvancedAlfPortal)world.getTileEntity(x, y, z)).onWanded();
        if (did && player != null) {
            player.addStat(ModAchievements.elfPortalOpen, 1);
        }

        return did;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) == 0 ? 0 : 15;
    }
}
