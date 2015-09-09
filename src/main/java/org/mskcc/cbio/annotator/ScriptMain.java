package org.mskcc.cbio.annotator;

import org.mskcc.cbio.vep.VepAnnotator;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScriptMain
{
	public static void main(String[] args) throws IOException
	{
		// TODO check for config file!
		ScriptConfig config = new ScriptConfig();
		AnnotatorConfig annoConfig = config.loadConfig();

		// TODO add a parameter for regular vs cluster mode
		//MultiFileMaf2Maf annotator = new MultiFileClusterMaf2Maf(annoConfig);
		//MultiFileMaf2Maf annotator = new MultiFileMaf2Maf(annoConfig);
		VepAnnotator annotator = new VepAnnotator(annoConfig);
		MultiFileValidator validator = new MultiFileValidator();
		MultiFileSanitizer sanitizer = new MultiFileSanitizer();

		// TODO move these into properties

		// default parameters
		String source = "~/input-data/";
		String target = "~/output-data/";
		String mode = "--annotate";

		if (args.length > 0)
		{
			source = args[0];
		}
		
		if (args.length > 1)
		{
			target = args[1];
		}

		if (args.length > 2)
		{
			mode = args[2];
		}

		if (mode.equalsIgnoreCase("--validate-only"))
		{
			System.out.println("[" + new Date() + "] Started validating output...");
			validator.annotate(source, target);
		}
		else if (mode.equalsIgnoreCase("--sanitize-only"))
		{
			System.out.println("[" + new Date() + "] Started sanitizing input...");
			sanitizer.annotate(source, target);
		}
		else
		{
			try
			{
				//annotator.annotate(source, target);
				annotator.annotateFile(new File(source), new File(target));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			if (mode.equalsIgnoreCase("--validate"))
			{
				System.out.println("[" + new Date() + "] Started validating output...");
				validator.annotate(source, target);
			}
		}
	}
}
