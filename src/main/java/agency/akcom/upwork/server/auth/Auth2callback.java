package agency.akcom.upwork.server.auth;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import agency.akcom.upwork.domain.AppUser;
import agency.akcom.upwork.server.ServerUtils;
import agency.akcom.upwork.server.dao.AppUserDao;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;


import com.google.api.services.sheets.v4.Sheets;
import com.google.gwt.user.client.Window;

import static agency.akcom.upwork.server.ServerUtils.APPLICATION_NAME;

@SuppressWarnings("serial")
public class Auth2callback extends HttpServlet {



    @Inject
    protected CurrentAppUserProvider currentAppUserProvider;

    public  Credential credential;



    protected void logininGoogle(HttpServletResponse resp) throws IOException { //функция которая отправляет первый get запрос

        try {
            resp.sendRedirect(CredentialManager.getAuthorizationUrl(true));

        } catch (IOException e) {
            throw new RuntimeException("Can't redirect to auth page");
        }
    }


    //@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");//Получаем параметр code из get ответа гугла


        Credential credential = CredentialManager.retrieveCredential(code);
        Oauth2 outh2Service = ServerUtils.getOauth2Service(credential);
        Userinfoplus about = outh2Service.userinfo().get().execute();


        try {
            AppUserDao appUserDao =  new AppUserDao();

            if(appUserDao.findByGoogleId(about.getId()) == null)
            {
                AppUser appUser = new AppUser();
                appUser.setLogin(about.getName());
                appUser.setEmail(about.getEmail());
                appUser.setGoogleId(about.getId());
                appUser.setAdmin(true);
                appUser.setPictureURL(about.getPicture());

                appUserDao.saveAndReturn(appUser);
                currentAppUserProvider.set(appUser);
                CredentialManager.saveCredential(about.getId(), credential);

            } else{
                AppUser appUser = appUserDao.findByGoogleId(about.getId());

                appUserDao.saveAndReturn(appUser);
                currentAppUserProvider.set(appUser);
                CredentialManager.saveCredential(about.getId(), credential);

            }


        } catch (Exception e) {
            resp.getWriter().write(("Error for user with email " + " : "
                    + e.getMessage()));
            e.printStackTrace();
        }

        resp.sendRedirect("/#admin"); //пересылка пользователя на начальную страниц
    }

    public static Sheets getSheetsService(String id) throws IOException {
        System.out.println("id : " + id);
        return new Sheets.Builder(CredentialManager.TRANSPORT, CredentialManager.JSON_FACTORY, CredentialManager.getCredential(id)).setApplicationName(APPLICATION_NAME).build();
    }



}

