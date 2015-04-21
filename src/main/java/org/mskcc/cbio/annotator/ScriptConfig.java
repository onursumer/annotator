package org.mskcc.cbio.annotator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.mskcc.cbio.annotator.AnnotatorConfig;

public class ScriptConfig
{
	String DEFAULT_PROPERTIES_FILE = "annotator.properties";
	
	public AnnotatorConfig loadConfig(String configFilename) throws IOException
	{
		Properties props = loadProperties(getResourceStream(configFilename));

		AnnotatorConfig config = new AnnotatorConfig();

		config.setMaf2maf(props.getProperty("annotator.maf2maf"));
		config.setVcf2maf(props.getProperty("annotator.vcf2maf"));
		config.setVepPath(props.getProperty("annotator.vepPath"));
		config.setVepData(props.getProperty("annotator.vepData"));
		config.setVepForks(props.getProperty("annotator.vepForks"));
		config.setRefFasta(props.getProperty("annotator.refFasta"));
		config.setExcludeCols(props.getProperty("annotator.excludeCols"));
		config.setIntermediateMaf(props.getProperty("annotator.intermediateMaf"));
		config.setTmpDir(props.getProperty("annotator.tmpDir"));
		config.setClusterSp(props.getProperty("annotator.clusterSp"));
		config.setClusterNode(props.getProperty("annotator.clusterNode"));

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
