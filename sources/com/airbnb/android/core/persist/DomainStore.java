package com.airbnb.android.core.persist;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.requests.DomainsRequest;
import com.airbnb.android.core.responses.DomainsResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.NetworkUtil;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import p032rx.Observer;

public class DomainStore {
    private static final String[] DEFAULT_DOMAINS = {"airbnb.com", "airbnb.de", "airbnb.it", "airbnb.es", "airbnb.fr", "airbnb.com.br", "airbnb.dk", "airbnb.co.uk", "airbnb.ru", "airbnb.pl", "airbnb.co.kr", "airbnb.cz", "airbnb.hu", "airbnb.at", "airbnb.pt", "airbnb.gr", "airbnb.com.tr", "airbnb.nl", "airbnb.se", "airbnb.com.tw", "airbnb.com.hk", "airbnb.com.sg", "airbnb.co.id", "airbnb.com.my", "airbnb.com.au", "airbnb.jp", "airbnb.is", "airbnb.no", "airbnb.ch", "airbnb.co.nz", "airbnb.ca", "airbnb.be", "airbnb.fi", "airbnb.ie", "airbnb.cat", "airbnb.co.in", "airbnb.mx", "airbnb.cl", "airbnb.co.cr", "airbnb.co.ve", "airbnb.com.ar", "airbnb.com.bo", "airbnb.com.bz", "airbnb.com.co", "airbnb.com.ec", "airbnb.com.gt", "airbnb.com.hn", "airbnb.com.ni", "airbnb.com.pa", "airbnb.com.pe", "airbnb.com.py", "airbnb.com.sv", "airbnb.com.mt", "airbnb.gy", "airbnb.ae"};
    private static final String TAG = DomainStore.class.getSimpleName();
    /* access modifiers changed from: private */
    public static final List<String> domainList = new ArrayList(Arrays.asList(DEFAULT_DOMAINS));
    private final Context context;
    private final SimpleRequestListener<DomainsResponse> domainRequestListener = new SimpleRequestListener<DomainsResponse>() {
        public void onResponse(DomainsResponse response) {
            List<String> domainsFromServer = FluentIterable.from((Iterable<E>) response.mDomains).transform(DomainStore$1$$Lambda$1.lambdaFactory$()).toList();
            if (BuildHelper.isDevelopmentBuild() && !response.metadata.isCached()) {
                DomainStore.this.checkForServerDomainDifferences(domainsFromServer);
            }
            DomainStore.domainList.clear();
            DomainStore.domainList.addAll(domainsFromServer);
        }
    };

    public DomainStore(Context context2) {
        this.context = context2;
    }

    public List<String> getDomainList() {
        return new ArrayList(domainList);
    }

    public void refreshDomainsFromServer() {
        new DomainsRequest().withListener((Observer) this.domainRequestListener).doubleResponse().execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void checkForServerDomainDifferences(List<String> serverDomains) {
        List<String> defaultLocalDomains = Arrays.asList(DEFAULT_DOMAINS);
        Set<String> localDifferences = new HashSet<>(defaultLocalDomains);
        Set<String> serverSet = new HashSet<>(serverDomains);
        localDifferences.removeAll(serverDomains);
        serverSet.removeAll(defaultLocalDomains);
        if (!localDifferences.isEmpty() || !serverSet.isEmpty()) {
            Toast.makeText(this.context, TAG + " is inconsistent with server TLD's. Update the local domain list to match", 1).show();
            C0715L.m1189d(TAG, "Local TLD's inconsistent with server.\nLocal differs from server with: " + localDifferences + "\nServer differs from local with: " + serverSet);
        }
    }

    public boolean isAirbnbDomain(String url) {
        String host = Uri.parse(url).getHost();
        if (TextUtils.isEmpty(host)) {
            return false;
        }
        for (String domainName : domainList) {
            if (!host.equalsIgnoreCase(domainName)) {
                if (host.endsWith("." + domainName)) {
                }
            }
            return true;
        }
        return false;
    }
}
