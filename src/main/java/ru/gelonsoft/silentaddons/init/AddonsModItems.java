package ru.gelonsoft.silentaddons.init;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.silentchaos512.gear.api.item.ICoreItem;
import net.silentchaos512.gear.api.item.ICoreTool;
import net.silentchaos512.gear.item.blueprint.GearBlueprintItem;
import ru.gelonsoft.silentaddons.SilentAddons;
import ru.gelonsoft.silentaddons.item.blueprint.AddonsGearBlueprintItem;
import ru.gelonsoft.silentaddons.item.gear.CoreMegaExcavator;
import ru.gelonsoft.silentaddons.item.gear.CoreMegaHammer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class AddonsModItems {
    public static final Map<String, ICoreTool> toolClasses = new LinkedHashMap<>();
    public static final Map<String, ICoreItem> gearClasses = new LinkedHashMap<>();
    public static final List<AddonsGearBlueprintItem> blueprints = new ArrayList<>();
    static final Map<String, BlockItem> blocksToRegister = new LinkedHashMap<>();

    public static CoreMegaHammer megahammer = new CoreMegaHammer();
    public static CoreMegaExcavator megaexcavator = new CoreMegaExcavator();

    static {
        // Seems colors events can fire before items are initialized in some case?
        // So we need to construct the items right now...
        initializeGear();

    }

    private AddonsModItems() {}

    public static void registerAll(RegistryEvent.Register<Item> event) {
        if (!event.getName().equals(ForgeRegistries.ITEMS.getRegistryName())) return;

        blocksToRegister.forEach(AddonsModItems::register);

        // Initializes, but does not register gear classes, fills maps
        initializeGear();

        // Blueprints/templates
        registerBlueprints("blueprint", false);
        registerBlueprints("template", true);


        // Register gear classes
        gearClasses.forEach((key, item) -> register(key, item.asItem()));

    }

    private static Item.Properties getBaseProperties() {
        return new Item.Properties().group(SilentAddons.ITEM_GROUP);
    }

    private static void initializeGear() {
        // Build gear maps now because blueprints need them
        toolClasses.put("megahammer", megahammer);
        toolClasses.put("megaexcavator", megaexcavator);

//        gearClasses.put("shield", shield);
        gearClasses.putAll(toolClasses);
    }

    private static <T extends Item> T register(String name, T item) {
        ResourceLocation id = new ResourceLocation(SilentAddons.MOD_ID, name);
        item.setRegistryName(id);
        ForgeRegistries.ITEMS.register(item);
        return item;
    }

    private static void registerBlueprints(String name, boolean singleUse) {
        gearClasses.forEach((key, item) -> {
            AddonsGearBlueprintItem blueprint = new AddonsGearBlueprintItem(singleUse, item);
            blueprints.add(blueprint);
            register(name + "_" + key, blueprint);
        });

    }
}
