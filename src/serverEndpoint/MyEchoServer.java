package serverEndpoint;
import java.io.*;  
import java.nio.ByteBuffer;
import java.util.*;

import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;


import javax.websocket.Session;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.OnMessage;
import javax.websocket.OnError;
import javax.websocket.OnClose;
import javax.websocket.CloseReason;

@ServerEndpoint("/MyEchoServer/{myName}")
public class MyEchoServer {
	
private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
private static final Map<Session, String> map = Collections.synchronizedMap(new HashMap<>());



	@OnOpen
	public void onOpen(@PathParam("myName") String myName,  Session userSession) throws IOException {
		allSessions.add(userSession);
		map.put(userSession, myName);
		System.out.println(userSession.getId() + ": 已連線");
		System.out.println(myName + ": 已連線");
//		System.out.println(myRoom + ": 房號");
//		userSession.getBasicRemote().sendText("WebSocket 連線成功");
	}

	
	@OnMessage
	public void onMessage(@PathParam("myName") String myName, Session userSession, String message) {
		

			 
		System.out.println("Message recei: " + message);
	
		
		
		for (Session session : allSessions) {
			if (session.isOpen() && map.get(session).equals(myName))
				session.getAsyncRemote().sendText(message);
//				session.getAsyncRemote().sendText(js.toString());
		}
		System.out.println("Message received: " + message);
	}
	

	
	@OnError
	public void onError(Session userSession, Throwable e){
//		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		allSessions.remove(userSession);
		System.out.println(userSession.getId() + ": Disconnected: " + Integer.toString(reason.getCloseCode().getCode()));
	}

 
}
