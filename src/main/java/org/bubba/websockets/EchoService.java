package org.bubba.websockets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

@ServerEndpoint("/echo") 
public class EchoService
{
    /**
     * @OnOpen allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
        System.out.println(session.getId() + " has opened a connection"); 
        try {
            session.getBasicRemote().sendText("Connection Established");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * When a user sends a message to the server, this method will intercept the message
     * and allow us to react to it. For now the message is read as a String.
     */
    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("Message from " + session.getId() + ": " + message);
		
		
		ObjectMapper mapper = new ObjectMapper();
		Yorkie y = new Yorkie();
		try
		{
			y = mapper.readValue(message, Yorkie.class);
		}catch(Exception ex)
		{
			Logger.getLogger(EchoService.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		System.err.println("\n\nface " + y.getFace() + "\nfinger " + y.getFinger() 
			+ "\ntaco " + y.getTaco() + "\nhowMany " + y.getHowMany() 
			+ "\nittm " + y.isIsThatTooMany());
        try {
            session.getBasicRemote().sendText(getYorkie(y));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
 
    /**
     * The user closes the connection.
     * 
     * Note: you can't send messages to the client from this method
     */
    @OnClose
    public void onClose(Session session){
        System.out.println("Session " +session.getId()+" has ended");
    }
	
	String getYorkie(int i)
	{
		Yorkie y = new Yorkie("furry", "none", "love", i, true);
		String result = "";
			
		ObjectMapper mapper = new ObjectMapper();
		
		try
		{
			result = mapper.writeValueAsString(y);
			System.err.println("'" + result + "'");
		}
		catch(IOException ex)
		{
			Logger.getLogger(EchoService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
	
	String getYorkie(Yorkie y)
	{
//		Yorkie y = new Yorkie("furry", "none", "love", i, true);
		String result = "";
			
		ObjectMapper mapper = new ObjectMapper();
		
		try
		{
			result = mapper.writeValueAsString(y);
			System.err.println("'" + result + "'");
		}
		catch(IOException ex)
		{
			Logger.getLogger(EchoService.class.getName()).log(Level.SEVERE, null, ex);
		}
		return result;
	}
}