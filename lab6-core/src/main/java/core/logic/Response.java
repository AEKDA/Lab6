package core.logic;

import java.io.Serializable;

public class Response implements Serializable {
    private ClientAction clientAction = ClientAction.Default;
    private String message = null;

    public Response(ClientAction clientAction, String message) {
        this.clientAction = clientAction;
        this.message = message;
    }

    public Response() {
    }

    public ClientAction getClientAction() {
        return clientAction;
    }

    public String getMessage() {
        return message;
    }

    public void setClientAction(ClientAction client) {
        clientAction = client;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
