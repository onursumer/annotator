package main;
import java.io.IOException;

import org.mskcc.cbio.annotator.AnnotatorConfig;
import org.mskcc.cbio.annotator.MultiFileMaf2Maf;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		// TODO check for config file!
		Config config = new Config();
		AnnotatorConfig annoConfig = config.loadConfig();
		MultiFileMaf2Maf annotator = new MultiFileMaf2Maf(annoConfig);

		// TODO move these into properties?
		// default source and target directories...
		String sourceDir = "/home/sos/CS/idea_worksapce/portal-data/";
		String targetDir = "/home/sos/MSKCC/portal/data/maf2maf/";
		
		if (args.length > 0)
		{
			sourceDir = args[0];
		}
		
		if (args.length > 1)
		{
			targetDir = args[1];
		}
		
		try {
			annotator.annotate(sourceDir, targetDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
