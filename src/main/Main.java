package main;
import java.io.IOException;
import java.util.Date;

import org.mskcc.cbio.annotator.AnnotatorConfig;
import org.mskcc.cbio.annotator.MultiFileMaf2Maf;
import org.mskcc.cbio.annotator.MultiFileValidator;
import org.mskcc.cbio.annotator.MultiFileSanitizer;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		// TODO check for config file!
		Config config = new Config();
		AnnotatorConfig annoConfig = config.loadConfig();
		MultiFileMaf2Maf annotator = new MultiFileMaf2Maf(annoConfig);
		MultiFileValidator validator = new MultiFileValidator();
		MultiFileSanitizer sanitizer = new MultiFileSanitizer();

		// TODO move these into properties?
		// default source and target directories...
		String sourceDir = "/home/sos/CS/idea_worksapce/portal-data/";
		String targetDir = "/home/sos/MSKCC/portal/data/maf2maf/";
		String mode = "--annotate";

		if (args.length > 0)
		{
			sourceDir = args[0];
		}
		
		if (args.length > 1)
		{
			targetDir = args[1];
		}

		if (args.length > 2)
		{
			mode = args[2];
		}

		if (mode.equalsIgnoreCase("--validate-only"))
		{
			System.out.println("[" + new Date() + "] Started validating output...");
			validator.annotate(sourceDir, targetDir);
		}
		else if (mode.equalsIgnoreCase("--sanitize-only"))
		{
			System.out.println("[" + new Date() + "] Started sanitizing input...");
			sanitizer.annotate(sourceDir, targetDir);
		}
		else
		{
			try
			{
				annotator.annotate(sourceDir, targetDir);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			if (mode.equalsIgnoreCase("--validate"))
			{
				System.out.println("[" + new Date() + "] Started validating output...");
				validator.annotate(sourceDir, targetDir);
			}
		}
	}
}
