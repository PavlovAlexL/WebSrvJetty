package Main;

import Accounts.AccountService;
import Accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import Servlets.*;


public class Main {
    public static void main(String[] args) throws Exception {

        AccountService accountService = new AccountService();   //Добавляем сервис хранящий связку логин-профиль и сессия-профиль

        accountService.addNewUser(new UserProfile("admin"));    // Пользователи по уомолчанию
        accountService.addNewUser(new UserProfile("test"));


        //AllRequestsServlet allRequestsServlet = new AllRequestsServlet();   //Создаем сервлет.
        //MirrorServlet mirrorServlet = new MirrorServlet();

        //Создание Servlet контейнера, передаем туда то что должно обрабатывать запросы клиента
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        //Создаем обработчики запросов
        context.addServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");
        context.addServlet(new ServletHolder(new SignInServlet(accountService)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)), "/signup");

        //context.addServlet(new ServletHolder(allRequestsServlet), "/*");  //allRequestsServlet - этот обработчик должен обрабатывать запрос документа pathSpec, в данном случае все запросы ".*"
        //context.addServlet(new ServletHolder(mirrorServlet), "/mirror");

        ResourceHandler resourceHandler = new ResourceHandler();    //Обращение к статическим ресурсам
        resourceHandler.setResourceBase("public_html"); //указываем забирать статические данные из этой папки

        HandlerList handlers = new HandlerList();   //создаем список обработчиков
        handlers.setHandlers(new Handler[]{resourceHandler, context});

        Server server = new Server(8080);  //Создаем объект jetty сервера и говорим работать на порту 80080
        //server.setHandler(context); //передаем в него Хэндлер
        server.setHandler(handlers);    //Передаем в него список Хэндлеров

        server.start(); //запускаем сервер, создается Threadpool для обработки запросов, начигаем слушать порт

        //java.util.logging.Logger.getGlobal().info("Server started");
        System.out.println("Server started!");
        server.join(); //присоединяем его к Jetty


    }
}