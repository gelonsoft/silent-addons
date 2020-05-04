package ru.gelonsoft.silentaddons;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import ru.gelonsoft.silentaddons.client.AddonsColorHandlers;
import ru.gelonsoft.silentaddons.client.models.AddonsToolModel;
import ru.gelonsoft.silentaddons.init.AddonsModItems;


import javax.annotation.Nullable;

class SideProxy implements IProxy {
    @Nullable private static MinecraftServer server;

    SideProxy() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(SideProxy::imcEnqueue);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(SideProxy::imcProcess);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(AddonsModItems::registerAll);


        MinecraftForge.EVENT_BUS.addListener(SideProxy::serverAboutToStart);
        MinecraftForge.EVENT_BUS.addListener(SideProxy::serverStopping);

    }

    private static void imcEnqueue(InterModEnqueueEvent event) {}

    private static void imcProcess(InterModProcessEvent event) {}

    private static void serverAboutToStart(FMLServerAboutToStartEvent event) {
        IReloadableResourceManager resourceManager = event.getServer().getResourceManager();

    }


    private static void serverStopping(FMLServerStoppingEvent event) {
        server = null;
    }

    @Nullable
    @Override
    public PlayerEntity getClientPlayer() {
        return null;
    }

    @Nullable
    @Override
    public MinecraftServer getServer() {
        return server;
    }

    static class Client extends SideProxy {
        Client() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(AddonsColorHandlers::onItemColors);
            MinecraftForge.EVENT_BUS.addListener(this::onPlayerLoggedIn);
            ModelLoaderRegistry.registerLoader(AddonsToolModel.Loader.INSTANCE);
        }


        private void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {

        }

        @Nullable
        @Override
        public PlayerEntity getClientPlayer() {
            return Minecraft.getInstance().player;
        }
    }

    static class Server extends SideProxy {
        Server() {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::serverSetup);
        }

        private void serverSetup(FMLDedicatedServerSetupEvent event) {}
    }
}
