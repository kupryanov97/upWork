package agency.akcom.upwork.server.auth;

import agency.akcom.upwork.domain.AppUser;
import com.gargoylesoftware.htmlunit.javascript.host.file.File;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.*;

public class SpreadsheetSnippets {
    private Sheets service;

    public SpreadsheetSnippets(Sheets service) {
        this.service = service;
    }

    public String create(String title) throws IOException {
        Sheets service = this.service;
        // [START sheets_create]
        Spreadsheet spreadsheet = new Spreadsheet()
                .setProperties(new SpreadsheetProperties()
                        .setTitle(title));
        spreadsheet = service.spreadsheets().create(spreadsheet)
                .setFields("spreadsheetId")
                .execute();
        System.out.println("Spreadsheet ID: " + spreadsheet.getSpreadsheetId());
        // [END sheets_create]
        return spreadsheet.getSpreadsheetId();
    }

    public UpdateValuesResponse updateValues(String spreadsheetId,String valueInputOption, List<AppUser> appUsers )
            throws IOException {

        Sheets service = this.service;
        String range = "A1";

        List<List<Object>> values23 = new ArrayList<>();
        values23.add(Arrays.asList("GoogleId" , "Login" , "Email" ,"Date Login"));

        for (AppUser appUser : appUsers) {
            values23.add(Arrays.asList(appUser.getGoogleId(),appUser.getLogin(),appUser.getEmail(),appUser.getDoCreated().toString()));
        }


        ValueRange body = new ValueRange()
                .setValues(values23);

        UpdateValuesResponse result =
                service.spreadsheets().values().update(spreadsheetId, range, body)
                        .setValueInputOption(valueInputOption) // Как интерпритировать текст(как формулу или как текст)
                        .execute();

        return result;
    }


}
