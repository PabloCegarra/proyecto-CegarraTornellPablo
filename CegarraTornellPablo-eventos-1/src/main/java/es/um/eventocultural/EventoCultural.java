//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.04.10 a las 01:31:16 AM CEST 
//


package es.um.eventocultural;

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
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="resumen" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="urlWikipedia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="localizacion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="coordenadaLongitud" type="{http://www.um.es/EventoCultural}tipoCoordenada"/&gt;
 *         &lt;element name="coordenadaLatitud" type="{http://www.um.es/EventoCultural}tipoCoordenada"/&gt;
 *         &lt;element name="actuaciones" type="{http://www.um.es/EventoCultural}tipoActuaciones" minOccurs="0"/&gt;
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
    protected String nombre;
    @XmlElement(required = true)
    protected String fechaInicio;
    @XmlElement(required = true)
    protected String fechaFin;
    @XmlElement(required = true)
    protected String resumen;
    @XmlElement(required = true)
    protected String url;
    protected String urlWikipedia;
    @XmlElement(required = true)
    protected String localizacion;
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
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Define el valor de la propiedad fechaInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicio(String value) {
        this.fechaInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * Define el valor de la propiedad fechaFin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFin(String value) {
        this.fechaFin = value;
    }

    /**
     * Obtiene el valor de la propiedad resumen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResumen() {
        return resumen;
    }

    /**
     * Define el valor de la propiedad resumen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResumen(String value) {
        this.resumen = value;
    }

    /**
     * Obtiene el valor de la propiedad url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Define el valor de la propiedad url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Obtiene el valor de la propiedad urlWikipedia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlWikipedia() {
        return urlWikipedia;
    }

    /**
     * Define el valor de la propiedad urlWikipedia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlWikipedia(String value) {
        this.urlWikipedia = value;
    }

    /**
     * Obtiene el valor de la propiedad localizacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalizacion() {
        return localizacion;
    }

    /**
     * Define el valor de la propiedad localizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalizacion(String value) {
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
