package ru.gelonsoft.silentaddons.item.blueprint;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.silentchaos512.gear.api.item.ICoreItem;
import net.silentchaos512.gear.api.item.ICoreTool;
import net.silentchaos512.gear.item.blueprint.AbstractBlueprintItem;
import ru.gelonsoft.silentaddons.SilentAddons;

import javax.annotation.Nullable;
import java.util.List;


public class AddonsGearBlueprintItem extends AddonsAbstractBlueprintItem {

    public AddonsGearBlueprintItem(boolean singleUse, ICoreItem gearItem) {
        super(new Properties().group(SilentAddons.ITEM_GROUP), singleUse);
        this.gearItem = gearItem;
    }

    private final ICoreItem gearItem;


    @Override
    protected ITextComponent getCraftedName(ItemStack stack) {
        return new TranslationTextComponent(this.gearItem.asItem().getTranslationKey());
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> list, ITooltipFlag flag) {
        String itemClass = this.gearItem.getGearType().getName();

        // Flavor text
        if (this.gearItem instanceof ICoreTool) {
            list.add(new TranslationTextComponent("item.silentaddons.blueprint." + itemClass + ".desc").applyTextStyle(TextFormatting.ITALIC));
        }

        // Single use or multiple uses? Or disabled?
        if (isDisabled()) {
            list.add(new TranslationTextComponent("item.silentaddons.blueprint.disabled").applyTextStyle(TextFormatting.DARK_RED));
        } else if (this.singleUse) {
            list.add(new TranslationTextComponent("item.silentaddons.blueprint.singleUse").applyTextStyle(TextFormatting.RED));
        } else {
            list.add(new TranslationTextComponent("item.silentaddons.blueprint.multiUse").applyTextStyle(TextFormatting.GREEN));
        }

        // Is mixed material allowed in this GUI?
/*        if (Minecraft.getInstance().currentScreen instanceof CraftingStationScreen) {
            list.add(new TranslationTextComponent("item.silentgear.blueprint.canMix")
                    .applyTextStyle(TextFormatting.GREEN));
        } else {
            list.add(new TranslationTextComponent("item.silentgear.blueprint.noMixing")
                    .applyTextStyle(TextFormatting.RED));
        }*/
    }
}
