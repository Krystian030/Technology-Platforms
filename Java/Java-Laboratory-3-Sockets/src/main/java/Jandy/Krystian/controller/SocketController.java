package Jandy.Krystian.controller;

public interface SocketController {
    boolean accepts(String uri);
    String prepareResponse(String uri);

}
