package net.trivo.totem;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(net.trivo.totem.TrivoMod.MODID)
public class TrivoMod {
    public static final String MODID = "whattotem";
    public TrivoMod(IEventBus modEventBus) {
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        event.remove(Items.TOTEM_OF_UNDYING.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }
}
