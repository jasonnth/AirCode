package jumio.p317nv.core;

import com.jumio.core.mvp.presenter.Presenter;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.models.MerchantSettingsModel;
import com.jumio.p311nv.models.SelectionModel;
import com.jumio.p311nv.models.ServerSettingsModel;
import com.jumio.p311nv.view.interactors.CountryListView;
import com.jumio.persistence.DataAccess;
import java.util.Collections;
import java.util.List;

/* renamed from: jumio.nv.core.af */
/* compiled from: CountryListPresenter */
public class C4898af extends Presenter<CountryListView> {

    /* renamed from: a */
    private ServerSettingsModel f4740a;

    /* renamed from: b */
    private SelectionModel f4741b;

    /* renamed from: c */
    private MerchantSettingsModel f4742c;

    /* access modifiers changed from: protected */
    public void onCreate() {
        super.onCreate();
        this.f4740a = (ServerSettingsModel) DataAccess.load(((CountryListView) getView()).getContext(), ServerSettingsModel.class);
        this.f4742c = (MerchantSettingsModel) DataAccess.load(((CountryListView) getView()).getContext(), MerchantSettingsModel.class);
        this.f4741b = (SelectionModel) DataAccess.load(((CountryListView) getView()).getContext(), SelectionModel.class);
    }

    /* access modifiers changed from: protected */
    public void onStart() {
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        DataAccess.update(((CountryListView) getView()).getContext(), SelectionModel.class, this.f4741b);
    }

    /* renamed from: a */
    public List<Country> mo46812a() {
        List<Country> countriesFor = this.f4740a.getCountryModel().getCountriesFor(this.f4741b.isVerificationRequired(), !this.f4741b.isVerificationRequired() && this.f4742c.isDataExtractionOnMobileOnly(), this.f4742c.getSupportedDocumentTypes(), this.f4742c.getDocumentVariant());
        Collections.sort(countriesFor);
        return countriesFor;
    }

    /* renamed from: a */
    public void mo46813a(Country country) {
        this.f4741b.setSelectedCountry(country);
    }
}
