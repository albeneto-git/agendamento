package br.com.alura.conf;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*
 * Necessário para chamada via JAXRS
 */
@ApplicationPath("resources")
public class JAXRSConfiguration extends Application{
	
}
