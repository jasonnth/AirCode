package com.airbnb.android.lib.utils.erf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.erf.ErfExperiment;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.events.ErfExperimentsUpdatedEvent;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.erf.Experiment;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.google.common.collect.Collections2;
import com.squareup.otto.Subscribe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ErfOverrideActivity extends AirActivity {
    private static final Comparator<Experiment> EXPERIMENT_ORDER = ErfOverrideActivity$$Lambda$4.lambdaFactory$();
    private static final String EXTRA_ASSIGNED_TREAMENT_NAME = "treatment_name";
    private static final String EXTRA_EXPERIMENT_NAME = "experiment_name";
    private static final String EXTRA_TREATMENTS = "treatments";
    private static final int REQUEST_ADD_EXPERIMENT = 88001;
    private static final int REQUEST_EDIT_EXPERIMENT = 88000;
    private ErfExperimentsAdapter erfExperimentsAdapter;
    @BindView
    EditText erfFilter;
    /* access modifiers changed from: private */
    public String erfFilterString;
    /* access modifiers changed from: private */
    public List<Experiment> experiments;
    ExperimentsProvider experimentsProvider;
    @BindView
    ListView mExperimentList;
    @BindView
    FloatingActionButton mFab;
    @BindView
    StickyButton mRefreshExperiments;
    private Unbinder unbinder;

    public static class AddExperimentDialog extends ZenDialog {
        @BindView
        EditText mExperimentField;
        @BindView
        EditText mTreatmentField;

        public static AddExperimentDialog newInstance() {
            return (AddExperimentDialog) new ZenBuilder(new AddExperimentDialog()).withTitle(C0880R.string.erf_add_experiment).withCustomLayout().withDualButton(C0880R.string.cancel, 0, C0880R.string.okay, (int) ErfOverrideActivity.REQUEST_ADD_EXPERIMENT, (Fragment) null).create();
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = super.onCreateView(inflater, container, savedInstanceState);
            setCustomView(inflater.inflate(C0880R.layout.dialog_fragment_add_experiment, container, false));
            ButterKnife.bind(this, view);
            return view;
        }

        /* access modifiers changed from: protected */
        public void clickRightButton(int requestCodeRight) {
            Experiment experiment = new ErfExperiment(this.mExperimentField.getText().toString(), this.mTreatmentField.getText().toString());
            sendActivityResult(ErfOverrideActivity.REQUEST_ADD_EXPERIMENT, -1, new Intent().putExtra(ErfOverrideActivity.EXTRA_EXPERIMENT_NAME, experiment.getName()).putExtra(ErfOverrideActivity.EXTRA_ASSIGNED_TREAMENT_NAME, experiment.getAssignedTreatment()).putStringArrayListExtra("treatments", new ArrayList(experiment.getTreatments())));
        }
    }

    public class AddExperimentDialog_ViewBinding implements Unbinder {
        private AddExperimentDialog target;

        public AddExperimentDialog_ViewBinding(AddExperimentDialog target2, View source) {
            this.target = target2;
            target2.mTreatmentField = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.treatment_name, "field 'mTreatmentField'", EditText.class);
            target2.mExperimentField = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.experiment_name, "field 'mExperimentField'", EditText.class);
        }

        public void unbind() {
            AddExperimentDialog target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.mTreatmentField = null;
            target2.mExperimentField = null;
        }
    }

    public static class EditExperimentDialog extends ZenDialog {
        private Experiment mExperiment;
        @BindView
        ListView mList;
        @BindView
        EditText mNewTreatment;

        public static EditExperimentDialog newInstance(Experiment experiment) {
            EditExperimentDialog dialog = (EditExperimentDialog) new ZenBuilder(new EditExperimentDialog()).withTitle(C0880R.string.erf_set_treatment).withCustomLayout().withDualButton(C0880R.string.cancel, 0, C0880R.string.okay, (int) ErfOverrideActivity.REQUEST_EDIT_EXPERIMENT, (Fragment) null).create();
            Bundle arguments = dialog.getArguments();
            arguments.putString(ErfOverrideActivity.EXTRA_EXPERIMENT_NAME, experiment.getName());
            arguments.putString(ErfOverrideActivity.EXTRA_ASSIGNED_TREAMENT_NAME, experiment.getAssignedTreatment());
            arguments.putStringArrayList("treatments", new ArrayList(experiment.getTreatments()));
            return dialog;
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = super.onCreateView(inflater, container, savedInstanceState);
            setCustomView(inflater.inflate(C0880R.layout.dialog_fragment_edit_experiment, container, false));
            Bundle arguments = getArguments();
            this.mExperiment = new ErfExperiment(arguments.getString(ErfOverrideActivity.EXTRA_EXPERIMENT_NAME), arguments.getString(ErfOverrideActivity.EXTRA_ASSIGNED_TREAMENT_NAME), arguments.getStringArrayList("treatments"));
            ButterKnife.bind(this, view);
            this.mList.setAdapter(new ArrayAdapter<>(getActivity(), C0880R.layout.list_item_erf_treatment, this.mExperiment.getTreatments()));
            this.mList.setOnItemClickListener(ErfOverrideActivity$EditExperimentDialog$$Lambda$1.lambdaFactory$(this));
            return view;
        }

        /* access modifiers changed from: private */
        public void setTreatment(String treatment) {
            this.mExperiment.setAssignedTreatment(treatment);
            saveExperiment();
            dismissAllowingStateLoss();
        }

        /* access modifiers changed from: protected */
        public void clickRightButton(int requestCodeRight) {
            String newTreatment = this.mNewTreatment.getText().toString().toLowerCase();
            if (!TextUtils.isEmpty(newTreatment)) {
                this.mExperiment.getTreatments().add(newTreatment);
                this.mExperiment.setAssignedTreatment(newTreatment);
            }
            saveExperiment();
        }

        private void saveExperiment() {
            sendActivityResult(ErfOverrideActivity.REQUEST_EDIT_EXPERIMENT, -1, new Intent().putExtra(ErfOverrideActivity.EXTRA_EXPERIMENT_NAME, this.mExperiment.getName()).putExtra(ErfOverrideActivity.EXTRA_ASSIGNED_TREAMENT_NAME, this.mExperiment.getAssignedTreatment()).putStringArrayListExtra("treatments", new ArrayList(this.mExperiment.getTreatments())));
        }
    }

    public class EditExperimentDialog_ViewBinding implements Unbinder {
        private EditExperimentDialog target;

        public EditExperimentDialog_ViewBinding(EditExperimentDialog target2, View source) {
            this.target = target2;
            target2.mNewTreatment = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.new_treatment, "field 'mNewTreatment'", EditText.class);
            target2.mList = (ListView) Utils.findRequiredViewAsType(source, C0880R.C0882id.list, "field 'mList'", ListView.class);
        }

        public void unbind() {
            EditExperimentDialog target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.mNewTreatment = null;
            target2.mList = null;
        }
    }

    public class ErfExperimentsAdapter extends BaseAdapter implements Filterable {
        /* access modifiers changed from: private */
        public final ArrayList<Experiment> mAllExperiments;
        private final Filter mFilter = newErfExperimentFilter();

        public class ViewHolder {
            @BindView
            TextView assignedTreatment;
            @BindView
            TextView name;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }

        public class ViewHolder_ViewBinding implements Unbinder {
            private ViewHolder target;

            public ViewHolder_ViewBinding(ViewHolder target2, View source) {
                this.target = target2;
                target2.name = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.name, "field 'name'", TextView.class);
                target2.assignedTreatment = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.assigned_treatment, "field 'assignedTreatment'", TextView.class);
            }

            public void unbind() {
                ViewHolder target2 = this.target;
                if (target2 == null) {
                    throw new IllegalStateException("Bindings already cleared.");
                }
                this.target = null;
                target2.name = null;
                target2.assignedTreatment = null;
            }
        }

        public ErfExperimentsAdapter(List<Experiment> experiments) {
            Check.notNull(experiments);
            this.mAllExperiments = new ArrayList<>(experiments);
            if (!TextUtils.isEmpty(ErfOverrideActivity.this.erfFilterString)) {
                this.mFilter.filter(ErfOverrideActivity.this.erfFilterString);
            }
        }

        public int getCount() {
            return ErfOverrideActivity.this.experiments.size();
        }

        public Experiment getItem(int position) {
            return (Experiment) ErfOverrideActivity.this.experiments.get(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(ErfOverrideActivity.this).inflate(C0880R.layout.list_item_erf_experiment, parent, false);
                convertView.setTag(new ViewHolder(convertView));
            }
            Experiment e = getItem(position);
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.name.setText(e.getName());
            holder.assignedTreatment.setText(e.getAssignedTreatment());
            return convertView;
        }

        public Filter getFilter() {
            return this.mFilter;
        }

        private Filter newErfExperimentFilter() {
            return new Filter() {
                /* access modifiers changed from: protected */
                public FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults filterResults = new FilterResults();
                    ArrayList<Experiment> filteredExperiments = new ArrayList<>(Collections2.filter(ErfExperimentsAdapter.this.mAllExperiments, ErfOverrideActivity$ErfExperimentsAdapter$1$$Lambda$1.lambdaFactory$(charSequence)));
                    filterResults.values = filteredExperiments;
                    filterResults.count = filteredExperiments.size();
                    return filterResults;
                }

                /* access modifiers changed from: protected */
                public void publishResults(CharSequence charSequence, FilterResults filterResults) {
                    ErfOverrideActivity.this.experiments = (ArrayList) filterResults.values;
                    ErfExperimentsAdapter.this.notifyDataSetChanged();
                }
            };
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, ErfOverrideActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        if (isDeepLinkToEditExperiment(getIntent())) {
            assignTreatmentFromEditIntent(getIntent());
            finish();
        } else {
            setContentView(C0880R.layout.activity_erf_override);
            setupActionBar(C0880R.string.debug_menu_override_erf, new Object[0]);
            this.unbinder = ButterKnife.bind((Activity) this);
            setupFab();
            setupExperimentList();
            this.mRefreshExperiments.setOnClickListener(ErfOverrideActivity$$Lambda$1.lambdaFactory$(this));
        }
        this.bus.register(this);
    }

    public void assignTreatmentFromEditIntent(Intent intent) {
        String experimentName = intent.getStringExtra(EXTRA_EXPERIMENT_NAME);
        String treatmentName = intent.getStringExtra(EXTRA_ASSIGNED_TREAMENT_NAME);
        if (TextUtils.isEmpty(experimentName) || TextUtils.isEmpty(treatmentName)) {
            Toast.makeText(this, "To edit an experiment you must supply the experiment name and treatment name", 0).show();
            return;
        }
        ErfExperiment experiment = this.experimentsProvider.getExperiment(experimentName);
        if (experiment == null) {
            experiment = new ErfExperiment(experimentName, treatmentName);
        } else {
            experiment.addTreatment(treatmentName);
            experiment.setAssignedTreatment(treatmentName);
        }
        addExperiment(experiment);
        Toast.makeText(this, "The experiment '" + experimentName + "' was assigned the treatment '" + treatmentName + "'", 0).show();
    }

    private boolean isDeepLinkToEditExperiment(Intent intent) {
        return DeepLinkUtils.isDeepLink(intent) && "android.intent.action.EDIT".equals(intent.getAction());
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.bus.unregister(this);
        if (this.unbinder != null) {
            this.unbinder.unbind();
            this.unbinder = null;
        }
    }

    /* access modifiers changed from: private */
    public void refreshExperiments() {
        this.experimentsProvider.refreshExperimentsFromServer(true);
    }

    @Subscribe
    public void experimentsUpdated(ErfExperimentsUpdatedEvent event) {
        setupExperimentList();
    }

    private void setupExperimentList() {
        this.experiments = new ArrayList(this.experimentsProvider.getExperiments().values());
        Collections.sort(this.experiments, EXPERIMENT_ORDER);
        this.erfExperimentsAdapter = new ErfExperimentsAdapter(this.experiments);
        this.mExperimentList.setAdapter(this.erfExperimentsAdapter);
        this.mExperimentList.setOnItemClickListener(ErfOverrideActivity$$Lambda$2.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void editExperiment(Experiment experiment) {
        EditExperimentDialog.newInstance(experiment).showAllowingStateLoss(getSupportFragmentManager(), null);
    }

    private void setupFab() {
        this.mFab.setImageDrawable(ColorizedDrawable.forIdWithColor(this, C0880R.C0881drawable.icon_plus, C0880R.color.white));
        this.mFab.setOnClickListener(ErfOverrideActivity$$Lambda$3.lambdaFactory$(this));
    }

    private void addExperiment(ErfExperiment experiment) {
        this.experimentsProvider.addExperimentWithOverriddenAssignmentForTesting(experiment);
        this.bus.post(new ErfExperimentsUpdatedEvent());
    }

    /* access modifiers changed from: 0000 */
    @OnTextChanged
    public void filterExperiments(CharSequence filter) {
        this.erfFilterString = filter.toString();
        this.erfExperimentsAdapter.getFilter().filter(this.erfFilterString);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_EDIT_EXPERIMENT /*88000*/:
            case REQUEST_ADD_EXPERIMENT /*88001*/:
                if (resultCode == -1) {
                    addExperiment(new ErfExperiment(data.getStringExtra(EXTRA_EXPERIMENT_NAME), data.getStringExtra(EXTRA_ASSIGNED_TREAMENT_NAME), data.getStringArrayListExtra("treatments")));
                    return;
                }
                return;
            default:
                return;
        }
    }
}
