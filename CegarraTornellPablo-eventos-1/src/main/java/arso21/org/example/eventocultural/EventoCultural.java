//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.04.08 a las 12:37:33 AM CEST 
//


package arso21.org.example.eventocultural;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="resumen" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="urlWikipedia" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&gt;
 *         &lt;element name="localizacion" type="{http://www.w3.org/2001/XMLSchema}anyType"/&gt;
 *         &lt;element name="coordenadaLongitud" type="{http://www.example.org/EventoCultural}tipoCoordenada"/&gt;
 *         &lt;element name="coordenadaLatitud" type="{http://www.example.org/EventoCultural}tipoCoordenada"/&gt;
 *         &lt;element name="actuaciones" type="{http://www.example.org/EventoCultural}tipoActuaciones" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "nombre",
    "fechaInicio",
    "fechaFin",
    "resumen",
    "url",
    "urlWikipedia",
    "localizacion",
    "coordenadaLongitud",
    "coordenadaLatitud",
    "actuaciones"
})
@XmlRootElement(name = "eventoCultural")
public class EventoCultural {

    @XmlElement(required = true)
    protected Object nombre;
    @XmlElement(required = true)
    protected Object fechaInicio;
    @XmlElement(required = true)
    protected Object fechaFin;
    @XmlElement(required = true)
    protected Object resumen;
    @XmlElement(required = true)
    protected Object url;
    protected Object urlWikipedia;
    @XmlElement(required = true)
    protected Object localizacion;
    protected double coordenadaLongitud;
    protected double coordenadaLatitud;
    protected TipoActuaciones actuaciones;
    @XmlAttribute(name = "id", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String id;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setNombre(Object value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicio.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Define el valor de la propiedad fechaInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFechaInicio(Object value) {
        this.fechaInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFin.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFechaFin() {
        return fechaFin;
    }

    /**
     * Define el valor de la propiedad fechaFin.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFechaFin(Object value) {
        this.fechaFin = value;
    }

    /**
     * Obtiene el valor de la propiedad resumen.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getResumen() {
        return resumen;
    }

    /**
     * Define el valor de la propiedad resumen.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setResumen(Object value) {
        this.resumen = value;
    }

    /**
     * Obtiene el valor de la propiedad url.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getUrl() {
        return url;
    }

    /**
     * Define el valor de la propiedad url.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setUrl(Object value) {
        this.url = value;
    }

    /**
     * Obtiene el valor de la propiedad urlWikipedia.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getUrlWikipedia() {
        return urlWikipedia;
    }

    /**
     * Define el valor de la propiedad urlWikipedia.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setUrlWikipedia(Object value) {
        this.urlWikipedia = value;
    }

    /**
     * Obtiene el valor de la propiedad localizacion.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLocalizacion() {
        return localizacion;
    }

    /**
     * Define el valor de la propiedad localizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLocalizacion(Object value) {
        this.localizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad coordenadaLongitud.
     * 
     */
    public double getCoordenadaLongitud() {
        return coordenadaLongitud;
    }

    /**
     * Define el valor de la propiedad coordenadaLongitud.
     * 
     */
    public void setCoordenadaLongitud(double value) {
        this.coordenadaLongitud = value;
    }

    /**
     * Obtiene el valor de la propiedad coordenadaLatitud.
     * 
     */
    public double getCoordenadaLatitud() {
        return coordenadaLatitud;
    }

    /**
     * Define el valor de la propiedad coordenadaLatitud.
     * 
     */
    public void setCoordenadaLatitud(double value) {
        this.coordenadaLatitud = value;
    }

    /**
     * Obtiene el valor de la propiedad actuaciones.
     * 
     * @return
     *     possible object is
     *     {@link TipoActuaciones }
     *     
     */
    public TipoActuaciones getActuaciones() {
        return actuaciones;
    }

    /**
     * Define el valor de la propiedad actuaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoActuaciones }
     *     
     */
    public void setActuaciones(TipoActuaciones value) {
        this.actuaciones = value;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

}
