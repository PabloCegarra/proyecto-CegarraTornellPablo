//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.06.05 a las 11:42:24 PM CEST 
//


package es.um.eventocultural;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.um.eventocultural package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.um.eventocultural
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EventoCultural }
     * 
     */
    public EventoCultural createEventoCultural() {
        return new EventoCultural();
    }

    /**
     * Create an instance of {@link TipoActuaciones }
     * 
     */
    public TipoActuaciones createTipoActuaciones() {
        return new TipoActuaciones();
    }

    /**
     * Create an instance of {@link TipoGoogleBook }
     * 
     */
    public TipoGoogleBook createTipoGoogleBook() {
        return new TipoGoogleBook();
    }

    /**
     * Create an instance of {@link TipoSitiosInteres }
     * 
     */
    public TipoSitiosInteres createTipoSitiosInteres() {
        return new TipoSitiosInteres();
    }

}
