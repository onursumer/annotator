package main;
import java.io.IOException;

import org.mskcc.cbio.annotator.AnnotatorConfig;
import org.mskcc.cbio.annotator.MultiFileMaf2Maf;

public class Main
{
	public static AnnotatorConfig localConfig()
	{
		AnnotatorConfig config = new AnnotatorConfig();
		
		config.setMaf2maf("/home/sos/MSKCC/portal/vcf2maf/maf2maf.pl");
		config.setVcf2maf("/home/sos/MSKCC/portal/vcf2maf/vcf2maf.pl");
		config.setVepPath("/home/sos/MSKCC/portal/vep");
		config.setVepData("/home/sos/MSKCC/portal/vep");
		config.setRefFasta("/home/sos/MSKCC/portal/vep/homo_sapiens/76_GRCh37/Homo_sapiens.GRCh37.75.dna.primary_assembly.fa");
		config.setExcludeCols("oncotator");
		config.setIntermediateMaf("/home/sos/MSKCC/portal/annotator/annotator_out.maf");
		
		return config;
	}
	
	public static AnnotatorConfig remoteConfig()
	{
		AnnotatorConfig config = new AnnotatorConfig();
		
		config.setMaf2maf("/ssd-data/cmo/opt/vcf2maf/maf2maf.pl");
		config.setVcf2maf("/ssd-data/cmo/opt/vcf2maf/vcf2maf.pl");
		config.setVepPath("/ssd-data/cmo/opt/vep");
		config.setVepData("/ssd-data/cmo/opt/vep");
		config.setRefFasta("/ssd-data/cmo/srv/Homo_sapiens.GRCh37.75.dna.primary_assembly.fa");
		config.setExcludeCols("oncotator");
		config.setIntermediateMaf("/data/onur/annotator/annotator_out.maf");
		
		return config;
	}
	
	public static void main(String[] args)
	{
		//AnnotatorConfig config = localConfig();
		AnnotatorConfig config = remoteConfig();
		MultiFileMaf2Maf annotator = new MultiFileMaf2Maf(config);
		
		// TODO portal-data directory... 
		String sourceDir = "/home/sos/CS/idea_worksapce/portal-data/";
		
		// TODO somewhere out in space...
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
