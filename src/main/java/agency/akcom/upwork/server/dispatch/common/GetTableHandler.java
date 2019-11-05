package agency.akcom.upwork.server.dispatch.common;


import agency.akcom.upwork.domain.AppUser;
import agency.akcom.upwork.server.auth.SpreadsheetSnippets;
import agency.akcom.upwork.server.dao.AppUserDao;
import agency.akcom.upwork.server.dispatch.MyAbstractActionHandler;
import agency.akcom.upwork.shared.action.GetTableUsersAction;
import agency.akcom.upwork.shared.action.GetTableUsersResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

import javax.inject.Inject;
import java.util.List;

import static agency.akcom.upwork.server.auth.Auth2callback.getSheetsService;


public class GetTableHandler extends MyAbstractActionHandler<GetTableUsersAction, GetTableUsersResult> {


    @Inject
    public GetTableHandler() {
        super(GetTableUsersAction.class);
    }


    @Override
    public GetTableUsersResult execute(GetTableUsersAction action, ExecutionContext context) throws ActionException {


        try {
            List<AppUser> appUsers = new AppUserDao().listAll();
            SpreadsheetSnippets spreadsheetSnippets = new SpreadsheetSnippets(getSheetsService(action.getGoogleid()));
            String spreadsheetId = spreadsheetSnippets.create("UkraineSecretDoc");
            spreadsheetSnippets.updateValues(spreadsheetId,"RAW", appUsers);

        }catch (Exception e){
            e.printStackTrace();
            return new GetTableUsersResult(false);
        }
        return new GetTableUsersResult(true);
    }


}