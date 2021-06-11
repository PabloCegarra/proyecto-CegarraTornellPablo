package eventos.mapeo;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class RootElement {

    private Map<String, String> mapProperty;

    public RootElement() {
        mapProperty = new HashMap<String, String>();
    }

    @XmlJavaTypeAdapter(MapAdapter.class)
    public Map<String, String> getMapProperty() {
        return mapProperty;
    }

    public void setMapProperty(Map<String, String> map) {
        this.mapProperty = map;
    }

}