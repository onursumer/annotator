package org.mskcc.cbio.annotator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ScriptConfig
{
	String DEFAULT_PROPERTIES_FILE = "annotator.properties";
	
	public AnnotatorConfig loadConfig(String configFilename) throws IOException
	{
		Properties props = loadProperties(getResourceStream(configFilename));

		AnnotatorConfig config = new AnnotatorConfig();

		config.setAnnotator(props.getProperty("annotator.annotator"));
		config.setPath(props.getProperty("annotator.path"));
		config.setPerl(props.getProperty("annotator.perl_bin"));
		config.setPerlLib(props.getProperty("annotator.perl_lib"));
		config.setMaf2maf(props.getProperty("annotator.maf2maf"));
		config.setVcf2maf(props.getProperty("annotator.vcf2maf"));
		config.setVepPath(props.getProperty("annotator.vep_path"));
		config.setVepData(props.getProperty("annotator.vep_data"));
		config.setVepForks(props.getProperty("annotator.vep_forks"));
		config.setRefFasta(props.getProperty("annotator.ref_fasta"));
		config.setExcludeCols(props.getProperty("annotator.exclude_cols"));
		config.setIntermediateMaf(props.getProperty("annotator.intermediate_maf"));
		config.setTmpDir(props.getProperty("annotator.tmp_dir"));
		config.setMode(props.getProperty("annotator.mode"));
		config.setClusterSp(props.getProperty("annotator.cluster_sp"));
		config.setClusterNode(props.getProperty("annotator.cluster_node"));
		config.setCustomEnst(props.getProperty("annotator.custom_enst"));
		config.setOverrideURI(props.getProperty("annotator.overrides_uri"));

		return config;
	}

	public AnnotatorConfig loadConfig() throws IOException
	{
		return loadConfig(DEFAULT_PROPERTIES_FILE);
	}

	private static Properties loadProperties(InputStream resourceInputStream) throws IOException
    {
        Properties properties = new Properties();

        properties.load(resourceInputStream);
        resourceInputStream.close();

        return properties;
    }
	
    private static InputStream getResourceStream(String resourceFilename)
    {
    	InputStream resourceFIS = null;

    	try {
    		resourceFIS = new FileInputStream(resourceFilename);
    	}
    	catch (FileNotFoundException e) {            
    		// Failed to read properties file: resourceFilename
    	}
 
    	return resourceFIS;
    }
}
