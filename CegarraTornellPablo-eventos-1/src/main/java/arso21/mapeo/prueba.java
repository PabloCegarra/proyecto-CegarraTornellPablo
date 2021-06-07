package arso21.mapeo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class prueba {
	
	public static void main(String[] args) throws JAXBException {
		RootElement root = new RootElement();
		
		Map<String, String> mapa = new HashMap<String, String>();
		mapa.put("key1", "value1");
		mapa.put("key2", "value2");
		mapa.put("key3", "value3");
		mapa.put("key4", "value4");
		root.setMapProperty(mapa);
		
		
		JAXBContext contexto = JAXBContext.newInstance(RootElement.class);
		Marshaller marshaller = contexto.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
		
		marshaller.marshal(root, new File("xml/mapaIndice.xml"));
		
		Unmarshaller unmarshaller = contexto.createUnmarshaller();
		RootElement map = (RootElement) unmarshaller.unmarshal(new File("xml/mapaIndice.xml"));
		
		System.out.println(map);
	}

}
