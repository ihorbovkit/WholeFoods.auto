package framework.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

/**
 * XmlPropertyLoader for loading properties from xml property file.
 */
public class XmlPropertyLoader {

	/**
	 * Load property from src/main/resources/properties.xml file.
	 *
	 * @param propertyName the property name
	 * @return the string value
	 */
	public static String loadProperty(String propertyName) {
		Properties props = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					"src/main/resources/properties.xml");
			props.loadFromXML(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPropertiesFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return props.getProperty(propertyName);
	}

}
