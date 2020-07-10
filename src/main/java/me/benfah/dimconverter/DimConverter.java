package me.benfah.dimconverter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.Identifier;

public class DimConverter
{

	private static Map<Identifier, Identifier> dimMap = new HashMap<>();
	private static final Logger LOGGER = LogManager.getLogger();

	private DimConverter()
	{
	}

	public static void registerDimension(Identifier oldDimId, Identifier newDim)
	{
		dimMap.put(oldDimId, newDim);
	}

	public static void registerDimension(Identifier dimId)
	{
		dimMap.put(dimId, dimId);
	}

	public static void handleConversion(Path worldFolder)
	{
		if (!dimMap.isEmpty())
		{
			LOGGER.info("Starting dimension conversion...");
			int totalConverted = 0;

			for (Entry<Identifier, Identifier> entry : dimMap.entrySet())
			{
				String oldDimName = "DIM_" + entry.getKey().getNamespace() + "_" + entry.getKey().getPath();
				File oldDimFolder = new File(worldFolder.toFile(), oldDimName);

				if (oldDimFolder.exists() && oldDimFolder.isDirectory())
				{
					File newDimFolder = new File(worldFolder.toFile(),
							"dimensions/" + entry.getValue().getNamespace() + "/" + entry.getValue().getPath());
					newDimFolder.getParentFile().mkdirs();

					try
					{
						FileUtils.moveDirectory(oldDimFolder, newDimFolder);
						totalConverted++;
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
			LOGGER.info("A total of {} dimensions were converted!", totalConverted);
		}
	}

}
