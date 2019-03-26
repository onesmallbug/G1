package ToolClasses;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@WebListener()
@ServerEndpoint("/ForTicketorderServlet_ws/{member_no}")
public class ForTicketorderServlet_ws implements ServletContextListener{
	private static final Map<String, Set<Session>> connectedSessions = new HashMap<String, Set<Session>>();

	private ServletContext sc;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sc = sce.getServletContext();
		sc.setAttribute("wsSessions", connectedSessions);
		System.out.println("i'm start.");
	}
	
	@OnOpen
	public void onOpen(@PathParam("member_no") String member_no, Session userSession) throws IOException {
		
		Set<Session> set  = connectedSessions.get(member_no);
		
		if(set == null) {
			set = new HashSet<Session>();
			connectedSessions.put(member_no, set);
		}
		
		set.add(userSession);
		
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), member_no);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		System.out.println("Message received: " + message);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		
		for(Set<Session> set : connectedSessions.values()) {
			set.remove(userSession);
		}
		
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
