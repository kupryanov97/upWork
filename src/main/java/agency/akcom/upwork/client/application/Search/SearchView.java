
package agency.akcom.upwork.client.application.Search;


import agency.akcom.upwork.client.widget.table.TablePresenter;
import agency.akcom.upwork.shared.domain.JobQueryDto;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import org.gwtbootstrap3.client.ui.*;

import java.util.HashMap;
import java.util.List;


public class SearchView extends ViewWithUiHandlers<SearchUiHandlers> implements SearchPresenter.MyView {
    interface Binder extends UiBinder<Widget, SearchView> {
    }

    @UiField
    Collapse collapseOne;

    @UiField
    Collapse collapSettings;

    @UiField //border-radius: 0px
    Button settings;

    @UiField //margin-left: 0px
    Button search;

    @UiField //margin-left: 0px
    Button get;

    @UiField
    TextBox maintext;

    @UiField
    TablePresenter test;
    //form setting
    //Job Type
    @UiField
    RadioButton jobAnyJobType;
    @UiField
    RadioButton jobHourly;
    @UiField
    RadioButton jobFixedPrice;

    //Experience Level
    @UiField
    RadioButton expAnyExperienceLevel;
    @UiField
    RadioButton expEntryLevel;
    @UiField
    RadioButton expIntermediate;
    @UiField
    RadioButton expExpert;

    //Client History
    @UiField
    RadioButton clientHisExpert;
    @UiField
    RadioButton clientHisNohires;
    @UiField
    RadioButton clientHis1to9hires;
    @UiField
    RadioButton clientHis10hires;

    //Client Info
    @UiField
    RadioButton clientMyPreviousClients;
    @UiField
    RadioButton clientPaymentVerified;

    //Number of Proposals
    @UiField
    RadioButton numberAnyNumber;
    @UiField
    RadioButton numberLessThan5;
    @UiField
    RadioButton number5To10;
    @UiField
    RadioButton number10To15;
    @UiField
    RadioButton number15To20;
    @UiField
    RadioButton number20To50;

    //Budget
    @UiField
    RadioButton budgetAny;
    @UiField
    RadioButton budget100;
    @UiField
    RadioButton budget500;
    @UiField
    RadioButton budget1k;
    @UiField
    RadioButton budget5k;
    @UiField
    RadioButton budget5kup;

    //Hours Per Week
    @UiField
    RadioButton hoursAny;
    @UiField
    RadioButton hoursLess30hrs;
    @UiField
    RadioButton hoursMore30hrs;

    //Project Length
    @UiField
    RadioButton projectAny;
    @UiField
    RadioButton projectLessthan1month;
    @UiField
    RadioButton project1to3months;
    @UiField
    RadioButton projectMorethan3months;


    @Inject
    SearchView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
        collapSettings.setVisible(false);
        collapseOne.setVisible(false);
    }

    @UiHandler("settings")
    public void Settings(ClickEvent eventfirst) {

        if (collapSettings.isShown()) {
            collapSettings.hide();
        } else {
            collapSettings.show();
        }

    }

    @UiHandler("get")
    public void Get(ClickEvent eventfirst) {
        getUiHandlers().setSetting(createList());
    }


    @UiHandler("search")
    public void Search(ClickEvent eventfirst) {
        if (collapSettings.isShown()) {
            collapSettings.hide();
        }

        if (collapseOne.isShown()) {
            collapseOne.hide();
        } else {
            collapseOne.show();
        }
    }


    public void HideMy() { //скривает началные колапсы на странице , чтобы это было нормально
        collapSettings.hide();
        collapseOne.hide();
        collapSettings.setVisible(true);
        collapseOne.setVisible(true);
    }

    public HashMap<String, String> createList() { //Быдлокод , FIX THIS

        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();

        HashMap<String, String> hashMap2 = new HashMap<String, String>();

        hashMap2.put("q", maintext.getText());
//        hashMap2.put("client_hires",getHires());
//        hashMap2.put("workload",getWorkload());
//        hashMap2.put("budget",getBudget());


        hashMap.put(0, maintext.getText());
        //Job Type
        hashMap.put(1, jobAnyJobType.getValue().toString());
        hashMap.put(2, jobHourly.getValue().toString());
        hashMap.put(3, jobFixedPrice.getValue().toString());

        //Experience Level
        hashMap.put(4, expAnyExperienceLevel.getValue().toString()); // нет в запросе
        hashMap.put(5, expEntryLevel.getValue().toString()); //contractor_tier=1
        hashMap.put(6, expIntermediate.getValue().toString()); //contractor_tier=2 //contractor_tier=1,2,3
        hashMap.put(7, expExpert.getValue().toString()); //contractor_tier=3

        //Client History
        hashMap.put(8, clientHisExpert.getValue().toString());
        hashMap.put(9, clientHisNohires.getValue().toString()); //client_hires=0
        hashMap.put(10, clientHis1to9hires.getValue().toString());//client_hires=1-9,10-
        hashMap.put(11, clientHis10hires.getValue().toString()); //client_hires=10-


        //Client Info
        hashMap.put(12, clientMyPreviousClients.getValue().toString());//previous_clients=all
        hashMap.put(13, clientPaymentVerified.getValue().toString()); //payment_verified=1


        //Number of Proposals
        hashMap.put(14, numberAnyNumber.getValue().toString());
        hashMap.put(15, numberLessThan5.getValue().toString());//proposals=0-4
        hashMap.put(16, number5To10.getValue().toString());//proposals=5-9
        hashMap.put(17, number10To15.getValue().toString());//proposals=10-14
        hashMap.put(18, number15To20.getValue().toString());//proposals=15-19
        hashMap.put(19, number20To50.getValue().toString());//proposals=20-49


        //Budget
        hashMap.put(20, budgetAny.getValue().toString());
        hashMap.put(21, budget100.getValue().toString());//amount=0-99
        hashMap.put(22, budget500.getValue().toString());//amount=100-499
        hashMap.put(23, budget1k.getValue().toString());//amount=500-999
        hashMap.put(24, budget5k.getValue().toString());//amount=1000-4999
        hashMap.put(25, budget5kup.getValue().toString());//amount=5000-

        //Hours Per Week
        hashMap.put(26, hoursAny.getValue().toString());
        hashMap.put(27, hoursLess30hrs.getValue().toString());//workload=as_needed
        hashMap.put(28, hoursMore30hrs.getValue().toString());//workload=full_time

        //Project Length
        hashMap.put(29, projectAny.getValue().toString());
        hashMap.put(30, projectLessthan1month.getValue().toString());//duration_v2=weeks
        hashMap.put(31, project1to3months.getValue().toString());//duration_v2=months
        hashMap.put(32, projectMorethan3months.getValue().toString());//duration_v2=ongoing

        return hashMap2;
    }

    public void initRadioButton(List<String> a) { //
        //Можно получить назад настройки
    }

    //Client History
    public String getHires() {
        if(clientHisNohires.getValue())//client_hires=0
            return "0";
        if(clientHis1to9hires.getValue())//client_hires=0
            return "1-9";
        if(clientHis10hires.getValue())//client_hires=0
            return "10-";
      return null;
    }

    //Hours Per Week
    public String getWorkload(){
        if(hoursLess30hrs.getValue())
            return "as_needed";
        if(hoursMore30hrs.getValue())
            return "full_time";
      return null;
    }

    //Budget
    public String getBudget(){
        if(budget100.getValue())
            return "0-99";
        if(budget500.getValue())
            return "100-499";
        if(budget1k.getValue())
            return "500-999";
        if(budget5k.getValue())
            return "1000-4999";
        if(budget5kup.getValue())
            return "5000-";
        return null;
    }

}
