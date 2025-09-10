package net.trivo.mod;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(net.trivo.mod.TrivoMod.MODID)
public class TrivoMod {
    public static final String MODID = "trivomod";
    public TrivoMod(IEventBus modEventBus) {
        modEventBus.addListener(this::addCreative);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    }
}
