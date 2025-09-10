package net.trivo.mod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Mod(net.trivo.mod.TrivoMod.MODID)
public class TrivoMod {
    public static final String MODID = "whatnetherite";
    public static List<ItemStack> combatItemList = new ArrayList<>();

    public TrivoMod(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        CREATIVE_MODE_TABS.register(modEventBus);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        for (ItemStack stack : event.getSearchEntries()) {
            if (stack.getItem().toString().contains("netherite")) {
                event.remove(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
            if (stack.getItem().equals(Items.ANCIENT_DEBRIS)) {
                event.remove(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            }
            if (event.getTabKey() == CreativeModeTabs.COMBAT && combatItemList.isEmpty()) {
                combatItemList.addAll(event.getSearchEntries());
                for (ItemStack item : combatItemList) {
                    event.remove(item, CreativeModeTab.TabVisibility.PARENT_TAB_ONLY);
                }
            }
        }
    }

    @SubscribeEvent
    public void onCommand(net.neoforged.neoforge.event.CommandEvent event) {
        int random = new Random().nextInt(3);
        String input = event.getParseResults().getReader().getString();
        if (input.contains("minecraft:netherite") || input.contains("minecraft:ancient_debris")) {
            event.setCanceled(true);
            if (random == 1) {
                event.getParseResults().getContext().getSource().sendFailure(
                        net.minecraft.network.chat.Component.literal("What netherite?"));
            } else if (random == 2){
                event.getParseResults().getContext().getSource().sendFailure(
                        net.minecraft.network.chat.Component.literal("I don't even think that's a real item."));
            } else {
                event.getParseResults().getContext().getSource().sendFailure(
                        net.minecraft.network.chat.Component.literal("Huh?"));
            }
        }
    }

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final Supplier<CreativeModeTab> COMBAT_TAB = CREATIVE_MODE_TABS.register("combat",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.MACE))
                    .title(Component.translatable("itemGroup.combat"))
                    .displayItems((params, output) -> {
                        combatItemList.forEach(output::accept);
                    })
                    .withTabsBefore(CreativeModeTabs.COMBAT.location())
                    .build());
}
