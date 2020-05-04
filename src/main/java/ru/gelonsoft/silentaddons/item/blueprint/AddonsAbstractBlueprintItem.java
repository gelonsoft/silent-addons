package ru.gelonsoft.silentaddons.item.blueprint;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.silentchaos512.gear.item.blueprint.BlueprintType;

public abstract class AddonsAbstractBlueprintItem  extends Item {
    final boolean singleUse;

    public AddonsAbstractBlueprintItem(Properties properties, boolean singleUse) {
        super(properties);
        this.singleUse = singleUse;
    }

    protected abstract ITextComponent getCraftedName(ItemStack stack);

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        return itemStack.copy();
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return !this.singleUse;
    }

    boolean isDisabled() {
        return false;
        /*BlueprintType config = Config.GENERAL.blueprintTypes.get();
        return this.singleUse && !config.allowTemplate()
                || !this.singleUse && !config.allowBlueprint();*/
    }

    @Override
    public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) {
        if (!this.isDisabled()) {
            super.fillItemGroup(group, items);
        }
    }

    @Override
    public ITextComponent getDisplayName(ItemStack stack) {
        String key = "item.silentaddons." + (this.singleUse ? "template" : "blueprint");
        return new TranslationTextComponent(key, this.getCraftedName(stack));
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return this.singleUse ? Rarity.COMMON : Rarity.UNCOMMON;
    }
}