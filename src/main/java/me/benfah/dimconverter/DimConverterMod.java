package me.benfah.dimconverter;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class DimConverterMod implements ModInitializer
{

	@Override
	public void onInitialize()
	{
		DimConverter.registerDimension(new Identifier("doorsofinfinity", "infinity_dimension"));
	}

}