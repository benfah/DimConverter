package me.benfah.dimconverter.mixin;

import java.nio.file.Path;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.benfah.dimconverter.DimConverter;
import net.minecraft.world.level.storage.LevelStorage;

@Mixin(LevelStorage.Session.class)
public class SessionMixin
{

	@Shadow
	private Path directory;

	@Inject(method = "<init>", at = @At("TAIL"))
	public void onInitSession(LevelStorage levelStorage, String levelName, CallbackInfo info)
	{
		DimConverter.handleConversion(directory);
	}

}
