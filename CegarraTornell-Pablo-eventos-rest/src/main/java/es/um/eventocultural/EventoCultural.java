//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.06.08 a las 12:31:52 AM CEST 
//


package es.um.eventocultural;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="resumen" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}anyURI"/&gt;
 *         &lt;element name="localizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="coordenadaLongitud" type="{http://www.um.es/EventoCultural}tipoCoordenada" minOccurs="0"/&gt;
 *         &lt;element name="coordenadaLatitud" type="{http://www.um.es/EventoCultural}tipoCoordenada" minOccurs="0"/&gt;
 *         &lt;element name="actuaciones" type="{http://www.um.es/EventoCultural}tipoActuaciones" minOccurs="0"/&gt;
 *         &lt;element name="googleBooks" type="{http://www.um.es/EventoCultural}tipoGoogleBook" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sitiosInteres" type="{http://www.um.es/EventoCultural}tipoSitiosInteres" minOccurs="0"/&gt;
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
    "tipo",
    "fechaInicio",
    "fechaFin",
    "resumen",
    "url",
    "localizacion",
    "coordenadaLongitud",
    "coordenadaLatitud",
    "actuaciones",
    "googleBooks",
    "sitiosInteres"
})
@XmlRootElement(name = "eventoCultural")
public class EventoCultural {

    @XmlElement(required = true)
    protected String nombre;
    @XmlElement(required = true)
    protected String tipo;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaInicio;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fechaFin;
    @XmlElement(required = true)
    protected String resumen;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String url;
    protected String localizacion;
    protected Double coordenadaLongitud;
    protected Double coordenadaLatitud;
    protected TipoActuaciones actuaciones;
    protected List<TipoGoogleBook> googleBooks;
    protected TipoSitiosInteres sitiosInteres;
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
     * Obtiene el valor de la propiedad tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define el valor de la propiedad tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Define el valor de la propiedad fechaInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaInicio(XMLGregorianCalendar value) {
        this.fechaInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFin.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaFin() {
        return fechaFin;
    }

    /**
     * Define el valor de la propiedad fechaFin.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaFin(XMLGregorianCalendar value) {
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
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCoordenadaLongitud() {
        return coordenadaLongitud;
    }

    /**
     * Define el valor de la propiedad coordenadaLongitud.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCoordenadaLongitud(Double value) {
        this.coordenadaLongitud = value;
    }

    /**
     * Obtiene el valor de la propiedad coordenadaLatitud.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCoordenadaLatitud() {
        return coordenadaLatitud;
    }

    /**
     * Define el valor de la propiedad coordenadaLatitud.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCoordenadaLatitud(Double value) {
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
     * Gets the value of the googleBooks property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the googleBooks property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGoogleBooks().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TipoGoogleBook }
     * 
     * 
     */
    public List<TipoGoogleBook> getGoogleBooks() {
        if (googleBooks == null) {
            googleBooks = new ArrayList<TipoGoogleBook>();
        }
        return this.googleBooks;
    }

    /**
     * Obtiene el valor de la propiedad sitiosInteres.
     * 
     * @return
     *     possible object is
     *     {@link TipoSitiosInteres }
     *     
     */
    public TipoSitiosInteres getSitiosInteres() {
        return sitiosInteres;
    }

    /**
     * Define el valor de la propiedad sitiosInteres.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoSitiosInteres }
     *     
     */
    public void setSitiosInteres(TipoSitiosInteres value) {
        this.sitiosInteres = value;
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
