package eventos.servicio;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;


import es.um.eventocultural.EventoCultural;
import eventos.repositorio.FactoriaRepositorioEventoCultural;
import eventos.repositorio.RepositorioEventoCultural;

public class ConsumerFavoritos extends DefaultConsumer{
	
	private IEventosService serviceEventos;
	private RepositorioEventoCultural repo;

	public ConsumerFavoritos(Channel channel, IEventosService serviceEventos) {
		super(channel);
		this.serviceEventos = serviceEventos;
		this.repo = FactoriaRepositorioEventoCultural.getRepositorio();;
	}
	
	
	@Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
            byte[] body) throws IOException {
        
        String routingKey = envelope.getRoutingKey();
        String contentType = properties.getContentType();
        long deliveryTag = envelope.getDeliveryTag();

        String contenido = new String(body, "UTF-8");
               
        //funcionalidad: pasar a json y llamar servicio
        Favorito favorito = new ObjectMapper().readValue(contenido, Favorito.class);
        
        if(favorito.getUrl().contains("https://datos.madrid.es/egob/catalogo/tipo/evento/")){
			try {
				EventoCultural evento = serviceEventos.getInfoEvento(favorito.getUrl());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        // Confirma el procesamiento
        this.getChannel().basicAck(deliveryTag, false);
    }

}
