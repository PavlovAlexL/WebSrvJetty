package Main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import Servlets.AllRequestsServlet;
import Servlets.MirrorServlet;


public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();   //Создаем сервлет.
        MirrorServlet mirrorServlet = new MirrorServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);   //Создание Servlet контейнера, передаем туда то что должно обрабатывать запросы клиента
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");  //allRequestsServlet - этот обработчик должен обрабатывать запрос документа pathSpec, в данном случае все запросы ".*"
        context.addServlet(new ServletHolder(mirrorServlet), "/mirror");

        Server server = new Server(8080);  //Создаем объект jetty сервера и говорим работать на порту 80080
        server.setHandler(context); //передаем в него Хэндлер


        server.start(); //запускаем сервер, создается Threadpool для обработки запросов, начигаем слушать порт

        //java.util.logging.Logger.getGlobal().info("Server started");
        System.out.println("Server started!");
        server.join(); //присоединяем его к Jetty
    }
}