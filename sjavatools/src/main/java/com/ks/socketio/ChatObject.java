package com.ks.socketio;
/**
 * @author pks
 * @version 2018年5月10日
 */
public class ChatObject {
	private Long userId;
    private String userName;
    private String message;

    public ChatObject() {
    }

    public ChatObject(Long userId,String userName, String message) {
        super();
        this.userName = userName;
        this.message = message;
    }

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

