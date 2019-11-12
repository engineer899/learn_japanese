package ink.zxu.learn_japanese.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
	public static Map<String, HttpSession> sessionPool = new HashMap<String,HttpSession>();
	/**
	 * 存储session
	 * @param session
	 */
	public static void saveSession(HttpSession session) {
		sessionPool.put(session.getId(),session);
	}	
	public static void saveSession(HttpSession session,String string) {
		sessionPool.put(string,session);
	}
	/**
	 * 根据sessionId获取session
	 * @param sid
	 * @return
	 */
	public static HttpSession getSession(String sid) {
		return sessionPool.get(sid);
	}
	/**
	 * 移除session
	 * @param sid
	 */
	public static void remove(String sid) {
		sessionPool.remove(sid);
	}


}