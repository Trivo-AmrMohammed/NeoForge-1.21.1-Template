package net.trivo.totem;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.CommandEvent;

import java.util.Random;

@Mod(net.trivo.totem.TrivoMod.MODID)
public class TrivoMod {
    public static final String MODID = "whattotem";
    public TrivoMod(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        event.remove(Items.TOTEM_OF_UNDYING.getDefaultInstance(), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
    }

    @SubscribeEvent
    public void onCommand(CommandEvent event) {
        int random = new Random().nextInt(3);
        String input = event.getParseResults().getReader().getString();
        if (input.contains("minecraft:totem_of_undying")) {
            event.setCanceled(true);
            if (random == 1) {
                event.getParseResults().getContext().getSource().sendFailure(
                        net.minecraft.network.chat.Component.literal("What totem?"));
            } else if (random == 2){
                event.getParseResults().getContext().getSource().sendFailure(
                        net.minecraft.network.chat.Component.literal("I don't even think that's a real item."));
            } else {
                event.getParseResults().getContext().getSource().sendFailure(
                        net.minecraft.network.chat.Component.literal("Huh?"));
            }
        }
    }
}
