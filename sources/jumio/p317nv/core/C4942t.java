package jumio.p317nv.core;

import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.jumio.commons.utils.ScreenUtil;
import com.jumio.p311nv.C4430R;
import com.jumio.p311nv.data.country.Country;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: jumio.nv.core.t */
/* compiled from: CountryAdapter */
public class C4942t extends BaseAdapter implements ListAdapter, SectionIndexer {

    /* renamed from: a */
    private SparseArray<String> f4831a;

    /* renamed from: b */
    private String f4832b = "";

    /* renamed from: c */
    private List<Country> f4833c;

    /* renamed from: d */
    private List<Country> f4834d;

    /* renamed from: jumio.nv.core.t$a */
    /* compiled from: CountryAdapter */
    public class C4943a {

        /* renamed from: a */
        public Country f4835a;

        /* renamed from: b */
        public TextView f4836b;

        public C4943a() {
        }
    }

    public C4942t(List<Country> list) {
        this.f4834d = list;
        this.f4833c = m3086b("");
        m3085a();
    }

    public int getCount() {
        return this.f4833c.size() + this.f4831a.size();
    }

    public Object getItem(int i) {
        return this.f4833c.get((i - getSectionForPosition(i)) - 1);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C4943a aVar;
        int itemViewType = getItemViewType(i);
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C4430R.layout.countrylist_row, viewGroup, false);
            aVar = new C4943a();
            aVar.f4836b = (TextView) view.findViewById(C4430R.C4432id.countryNameTextView);
            view.setTag(aVar);
        } else {
            aVar = (C4943a) view.getTag();
        }
        if (itemViewType == 0) {
            aVar.f4836b.getLayoutParams().height = (int) ScreenUtil.dipToPx(viewGroup.getContext(), 28.0f);
            aVar.f4836b.setTypeface(Typeface.DEFAULT_BOLD);
            aVar.f4836b.setTextSize(2, 14.0f);
            aVar.f4836b.setText((CharSequence) this.f4831a.valueAt(getSectionForPosition(i)));
            aVar.f4835a = null;
            view.setContentDescription((CharSequence) this.f4831a.valueAt(getSectionForPosition(i)));
        } else {
            Country country = (Country) getItem(i);
            if (!TextUtils.isEmpty(country.getName())) {
                view.setContentDescription(country.getName());
            } else {
                view.setContentDescription(country.getIsoCode());
            }
            aVar.f4836b.getLayoutParams().height = (int) ScreenUtil.dipToPx(viewGroup.getContext(), 56.0f);
            aVar.f4836b.setTypeface(Typeface.DEFAULT);
            aVar.f4836b.setTextSize(2, 16.0f);
            aVar.f4836b.setText(country.getName());
            aVar.f4835a = country;
        }
        return view;
    }

    public Object[] getSections() {
        String[] strArr = new String[this.f4831a.size()];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.f4831a.size()) {
                return strArr;
            }
            strArr[i2] = (String) this.f4831a.valueAt(i2);
            i = i2 + 1;
        }
    }

    public int getPositionForSection(int i) {
        if (i < 0) {
            i = 0;
        }
        if (i > this.f4831a.size() - 1) {
            i = this.f4831a.size() - 1;
        }
        return this.f4831a.keyAt(i);
    }

    public int getSectionForPosition(int i) {
        for (int i2 = 0; i2 < this.f4831a.size(); i2++) {
            int keyAt = this.f4831a.keyAt(i2);
            int count = i2 + 1 < this.f4831a.size() ? this.f4831a.keyAt(i2 + 1) : getCount();
            if (i >= keyAt && i < count) {
                return i2;
            }
        }
        return -1;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int i) {
        return this.f4831a.indexOfKey(i) < 0 ? 1 : 0;
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int i) {
        return getItemViewType(i) != 0;
    }

    /* renamed from: b */
    private List<Country> m3086b(String str) {
        if (str != null) {
            Locale locale = Locale.getDefault();
            String upperCase = str.toUpperCase(locale);
            ArrayList arrayList = new ArrayList();
            for (Country country : this.f4834d) {
                if (country.getName().toUpperCase(locale).indexOf(upperCase) >= 0) {
                    arrayList.add(country);
                }
            }
            this.f4833c = arrayList;
        }
        return this.f4833c;
    }

    /* renamed from: a */
    private void m3085a() {
        int size = this.f4833c.size();
        this.f4831a = new SparseArray<>();
        for (int i = 0; i < size; i++) {
            String substring = ((Country) this.f4833c.get(i)).getName().substring(0, 1);
            if (substring.compareToIgnoreCase("Ä") == 0) {
                substring = "A";
            } else if (substring.compareToIgnoreCase("Å") == 0) {
                substring = "A";
            } else if (substring.compareToIgnoreCase("Ü") == 0) {
                substring = "U";
            } else if (substring.compareToIgnoreCase("Ö") == 0) {
                substring = "O";
            }
            if (m3087c(substring) < 0) {
                this.f4831a.append(this.f4831a.size() + i, substring);
            }
        }
        this.f4831a.size();
    }

    /* renamed from: c */
    private int m3087c(String str) {
        int i = 0;
        int i2 = -1;
        while (true) {
            int i3 = i;
            if (i3 >= this.f4831a.size()) {
                return i2;
            }
            if (((String) this.f4831a.get(this.f4831a.keyAt(i3))).equals(str)) {
                i2 = i3;
            }
            i = i3 + 1;
        }
    }

    /* renamed from: a */
    public void mo46888a(String str) {
        if (!this.f4832b.equals(str)) {
            this.f4832b = str;
            this.f4833c = m3086b(str);
            m3085a();
            notifyDataSetChanged();
        }
    }
}
