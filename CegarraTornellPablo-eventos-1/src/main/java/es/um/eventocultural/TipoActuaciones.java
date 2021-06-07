//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.06.07 a las 12:07:37 AM CEST 
//


package es.um.eventocultural;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoActuaciones complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="tipoActuaciones"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="frecuencia" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dias" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="intervalo" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoActuaciones", propOrder = {
    "frecuencia",
    "dias",
    "intervalo"
})
public class TipoActuaciones {

    @XmlElement(required = true)
    protected String frecuencia;
    @XmlElement(required = true)
    protected String dias;
    @XmlElement(required = true)
    protected BigInteger intervalo;

    /**
     * Obtiene el valor de la propiedad frecuencia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrecuencia() {
        return frecuencia;
    }

    /**
     * Define el valor de la propiedad frecuencia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrecuencia(String value) {
        this.frecuencia = value;
    }

    /**
     * Obtiene el valor de la propiedad dias.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDias() {
        return dias;
    }

    /**
     * Define el valor de la propiedad dias.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDias(String value) {
        this.dias = value;
    }

    /**
     * Obtiene el valor de la propiedad intervalo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIntervalo() {
        return intervalo;
    }

    /**
     * Define el valor de la propiedad intervalo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIntervalo(BigInteger value) {
        this.intervalo = value;
    }

}
