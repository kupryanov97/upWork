package agency.akcom.upwork.server.auth;

import agency.akcom.upwork.domain.AppUser;
import agency.akcom.upwork.server.ServerUtils;
import agency.akcom.upwork.server.dao.AppUserDao;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


import static agency.akcom.upwork.server.dispatch.common.GetTokenUpWorkHandler.hash;



public class GetServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {


        String oauth_token = req.getParameter("oauth_token");
        String oauth_verifier = req.getParameter("oauth_verifier");

        try {
            AppUserDao appUserDao = new AppUserDao();
            AppUser appUser = appUserDao.findByToken(oauth_token);

            HashMap<String,String> a = hash.get(appUser.getId().toString()).getOAuthClient().getAccessTokenSet(oauth_verifier); //FIX THIS

            appUser.setSecret(a.get("secret"));
            appUser.setToken(a.get("token"));

            ServerUtils.createUserDto(true, appUserDao.saveAndReturn(appUser));
            hash.remove(appUser.getId().toString());

            System.out.println("HASH SIZW" + hash.size());

        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/#Error");
        }
        resp.sendRedirect("/#main"); //пересылка пользователя на начальную страниц

    }

}


