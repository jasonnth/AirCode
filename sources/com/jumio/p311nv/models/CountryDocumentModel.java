package com.jumio.p311nv.models;

import com.jumio.core.data.document.DocumentScanMode;
import com.jumio.core.mvp.model.StaticModel;
import com.jumio.core.plugins.PluginRegistry;
import com.jumio.core.plugins.PluginRegistry.PluginMode;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.DocumentType;
import com.jumio.p311nv.data.document.NVDocumentType;
import com.jumio.p311nv.data.document.NVDocumentVariant;
import java.util.LinkedList;
import java.util.List;
import jumio.p317nv.core.C4932l;
import jumio.p317nv.core.C4933m;

/* renamed from: com.jumio.nv.models.CountryDocumentModel */
public class CountryDocumentModel implements StaticModel {

    /* renamed from: a */
    private C4932l f3386a;

    public CountryDocumentModel() {
        this.f3386a = new C4933m();
    }

    public CountryDocumentModel(C4932l lVar) {
        this.f3386a = lVar;
    }

    public void add(Country country, DocumentType... documentTypeArr) {
        this.f3386a.mo46876a(country, documentTypeArr);
    }

    public List<DocumentType> getDocumentTypesFor(Country country, boolean z, boolean z2, List<NVDocumentType> list, NVDocumentVariant nVDocumentVariant) {
        DocumentType documentType;
        boolean z3;
        LinkedList linkedList = new LinkedList();
        for (DocumentType documentType2 : this.f3386a.mo46874a(country)) {
            boolean z4 = true;
            if (!list.contains(documentType2.getId())) {
                z4 = false;
            }
            if (z) {
                if (documentType2.getParts() == 0) {
                    documentType = documentType2;
                    z3 = false;
                } else {
                    boolean z5 = z4;
                    documentType = documentType2;
                    z3 = z5;
                }
            } else if (documentType2.hasExtractionMethod()) {
                DocumentType documentType3 = new DocumentType(documentType2);
                if (!ServerSettingsModel.GERMAN_ISO3.equals(country.getIsoCode()) || documentType3.getId() != NVDocumentType.IDENTITY_CARD) {
                    documentType3.setPaperVariant(false);
                    z3 = z4;
                } else {
                    if (z2) {
                        documentType3.setDocumentScanMode(DocumentScanMode.TD1);
                        if (!PluginRegistry.hasPlugin(PluginMode.MRZ)) {
                            z3 = false;
                            documentType3.setPaperVariant(PluginRegistry.hasPlugin(PluginMode.MRZ));
                        }
                    }
                    z3 = z4;
                    documentType3.setPaperVariant(PluginRegistry.hasPlugin(PluginMode.MRZ));
                }
                if (!z2) {
                    documentType = documentType3;
                } else if (documentType3.getDocumentScanMode() == DocumentScanMode.CSSN) {
                    z3 = false;
                    documentType = documentType3;
                } else {
                    documentType3.setFallbackScan(false);
                    documentType = documentType3;
                }
            } else {
                documentType = documentType2;
                z3 = false;
            }
            if (nVDocumentVariant != null && nVDocumentVariant.equals(NVDocumentVariant.PAPER) && !documentType.hasPaperVariant()) {
                z3 = false;
            }
            if (z3) {
                linkedList.add(documentType);
            }
        }
        return linkedList;
    }

    public List<Country> getCountriesFor(boolean z, boolean z2, List<NVDocumentType> list, NVDocumentVariant nVDocumentVariant) {
        LinkedList linkedList = new LinkedList();
        for (Country country : this.f3386a.mo46875a((NVDocumentType[]) list.toArray(new NVDocumentType[list.size()]))) {
            if (getDocumentTypesFor(country, z, z2, list, nVDocumentVariant).size() > 0) {
                linkedList.add(country);
            }
        }
        return linkedList;
    }

    public Country getCountryForIso3(String str) {
        return this.f3386a.mo46873a(str);
    }

    public Country getCountryForIso2(String str) {
        return this.f3386a.mo46878b(str);
    }

    public boolean contains(Country country) {
        return this.f3386a.mo46880b(country);
    }

    public boolean isEmpty() {
        return this.f3386a.mo46877a();
    }

    public List<Country> getCountries() {
        return this.f3386a.mo46879b();
    }
}
