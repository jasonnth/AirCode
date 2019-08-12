package com.facebook.react.devsupport;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.SpannedString;
import android.text.method.LinkMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.C3704R;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.RedBoxHandler.ReportCompletedListener;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import org.json.JSONObject;

class RedBoxDialog extends Dialog implements OnItemClickListener {
    /* access modifiers changed from: private */
    public boolean isReporting = false;
    private Button mCopyToClipboardButton;
    /* access modifiers changed from: private */
    public final DevSupportManager mDevSupportManager;
    private Button mDismissButton;
    private final DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    /* access modifiers changed from: private */
    public View mLineSeparator;
    /* access modifiers changed from: private */
    public ProgressBar mLoadingIndicator;
    /* access modifiers changed from: private */
    public final RedBoxHandler mRedBoxHandler;
    private Button mReloadJsButton;
    /* access modifiers changed from: private */
    public Button mReportButton;
    private OnClickListener mReportButtonOnClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (RedBoxDialog.this.mRedBoxHandler != null && RedBoxDialog.this.mRedBoxHandler.isReportEnabled() && !RedBoxDialog.this.isReporting) {
                RedBoxDialog.this.isReporting = true;
                ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setText("Reporting...");
                ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setVisibility(0);
                ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.this.mLoadingIndicator)).setVisibility(0);
                ((View) Assertions.assertNotNull(RedBoxDialog.this.mLineSeparator)).setVisibility(0);
                ((Button) Assertions.assertNotNull(RedBoxDialog.this.mReportButton)).setEnabled(false);
                RedBoxDialog.this.mRedBoxHandler.reportRedbox((String) Assertions.assertNotNull(RedBoxDialog.this.mDevSupportManager.getLastErrorTitle()), (StackFrame[]) Assertions.assertNotNull(RedBoxDialog.this.mDevSupportManager.getLastErrorStack()), RedBoxDialog.this.mDevSupportManager.getSourceUrl(), (ReportCompletedListener) Assertions.assertNotNull(RedBoxDialog.this.mReportCompletedListener));
            }
        }
    };
    /* access modifiers changed from: private */
    public ReportCompletedListener mReportCompletedListener = new ReportCompletedListener() {
        public void onReportSuccess(SpannedString spannedString) {
            RedBoxDialog.this.isReporting = false;
            ((Button) Assertions.assertNotNull(RedBoxDialog.this.mReportButton)).setEnabled(true);
            ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.this.mLoadingIndicator)).setVisibility(8);
            ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setText(spannedString);
        }

        public void onReportError(SpannedString spannedString) {
            RedBoxDialog.this.isReporting = false;
            ((Button) Assertions.assertNotNull(RedBoxDialog.this.mReportButton)).setEnabled(true);
            ((ProgressBar) Assertions.assertNotNull(RedBoxDialog.this.mLoadingIndicator)).setVisibility(8);
            ((TextView) Assertions.assertNotNull(RedBoxDialog.this.mReportTextView)).setText(spannedString);
        }
    };
    /* access modifiers changed from: private */
    public TextView mReportTextView;
    private ListView mStackView;

    private static class CopyToHostClipBoardTask extends AsyncTask<String, Void, Void> {
        private final DevSupportManager mDevSupportManager;

        private CopyToHostClipBoardTask(DevSupportManager devSupportManager) {
            this.mDevSupportManager = devSupportManager;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(String... clipBoardString) {
            try {
                String sendClipBoardUrl = Uri.parse(this.mDevSupportManager.getSourceUrl()).buildUpon().path("/copy-to-clipboard").query(null).build().toString();
                for (String string : clipBoardString) {
                    new OkHttpClient().newCall(new Builder().url(sendClipBoardUrl).post(RequestBody.create((MediaType) null, string)).build()).execute();
                }
            } catch (Exception e) {
                FLog.m1808e(ReactConstants.TAG, "Could not copy to the host clipboard", (Throwable) e);
            }
            return null;
        }
    }

    private static class OpenStackFrameTask extends AsyncTask<StackFrame, Void, Void> {
        private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        private final DevSupportManager mDevSupportManager;

        private OpenStackFrameTask(DevSupportManager devSupportManager) {
            this.mDevSupportManager = devSupportManager;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(StackFrame... stackFrames) {
            try {
                String openStackFrameUrl = Uri.parse(this.mDevSupportManager.getSourceUrl()).buildUpon().path("/open-stack-frame").query(null).build().toString();
                OkHttpClient client = new OkHttpClient();
                for (StackFrame frame : stackFrames) {
                    client.newCall(new Builder().url(openStackFrameUrl).post(RequestBody.create(JSON, stackFrameToJson(frame).toString())).build()).execute();
                }
            } catch (Exception e) {
                FLog.m1808e(ReactConstants.TAG, "Could not open stack frame", (Throwable) e);
            }
            return null;
        }

        private static JSONObject stackFrameToJson(StackFrame frame) {
            return new JSONObject(MapBuilder.m1885of(UriUtil.LOCAL_FILE_SCHEME, frame.getFile(), "methodName", frame.getMethod(), "lineNumber", Integer.valueOf(frame.getLine()), "column", Integer.valueOf(frame.getColumn())));
        }
    }

    private static class StackAdapter extends BaseAdapter {
        private static final int VIEW_TYPE_COUNT = 2;
        private static final int VIEW_TYPE_STACKFRAME = 1;
        private static final int VIEW_TYPE_TITLE = 0;
        private final StackFrame[] mStack;
        private final String mTitle;

        private static class FrameViewHolder {
            /* access modifiers changed from: private */
            public final TextView mFileView;
            /* access modifiers changed from: private */
            public final TextView mMethodView;

            private FrameViewHolder(View v) {
                this.mMethodView = (TextView) v.findViewById(C3704R.C3706id.rn_frame_method);
                this.mFileView = (TextView) v.findViewById(C3704R.C3706id.rn_frame_file);
            }
        }

        public StackAdapter(String title, StackFrame[] stack) {
            this.mTitle = title;
            this.mStack = stack;
        }

        public boolean areAllItemsEnabled() {
            return false;
        }

        public boolean isEnabled(int position) {
            return position > 0;
        }

        public int getCount() {
            return this.mStack.length + 1;
        }

        public Object getItem(int position) {
            return position == 0 ? this.mTitle : this.mStack[position - 1];
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public int getViewTypeCount() {
            return 2;
        }

        public int getItemViewType(int position) {
            return position == 0 ? 0 : 1;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TextView title;
            if (position == 0) {
                if (convertView != null) {
                    title = (TextView) convertView;
                } else {
                    title = (TextView) LayoutInflater.from(parent.getContext()).inflate(C3704R.layout.redbox_item_title, parent, false);
                }
                title.setText(this.mTitle);
                return title;
            }
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(C3704R.layout.redbox_item_frame, parent, false);
                convertView.setTag(new FrameViewHolder(convertView));
            }
            StackFrame frame = this.mStack[position - 1];
            FrameViewHolder holder = (FrameViewHolder) convertView.getTag();
            holder.mMethodView.setText(frame.getMethod());
            holder.mFileView.setText(StackTraceHelper.formatFrameSource(frame));
            return convertView;
        }
    }

    protected RedBoxDialog(Context context, DevSupportManager devSupportManager, RedBoxHandler redBoxHandler) {
        super(context, C3704R.C3707style.Theme_Catalyst_RedBox);
        requestWindowFeature(1);
        setContentView(C3704R.layout.redbox_view);
        this.mDevSupportManager = devSupportManager;
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
        this.mRedBoxHandler = redBoxHandler;
        this.mStackView = (ListView) findViewById(C3704R.C3706id.rn_redbox_stack);
        this.mStackView.setOnItemClickListener(this);
        this.mReloadJsButton = (Button) findViewById(C3704R.C3706id.rn_redbox_reload_button);
        this.mReloadJsButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                RedBoxDialog.this.mDevSupportManager.handleReloadJS();
            }
        });
        this.mDismissButton = (Button) findViewById(C3704R.C3706id.rn_redbox_dismiss_button);
        this.mDismissButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                RedBoxDialog.this.dismiss();
            }
        });
        this.mCopyToClipboardButton = (Button) findViewById(C3704R.C3706id.rn_redbox_copy_button);
        this.mCopyToClipboardButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String title = RedBoxDialog.this.mDevSupportManager.getLastErrorTitle();
                StackFrame[] stack = RedBoxDialog.this.mDevSupportManager.getLastErrorStack();
                Assertions.assertNotNull(title);
                Assertions.assertNotNull(stack);
                new CopyToHostClipBoardTask(RedBoxDialog.this.mDevSupportManager).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{StackTraceHelper.formatStackTrace(title, stack)});
            }
        });
        if (this.mRedBoxHandler != null && this.mRedBoxHandler.isReportEnabled()) {
            this.mLoadingIndicator = (ProgressBar) findViewById(C3704R.C3706id.rn_redbox_loading_indicator);
            this.mLineSeparator = findViewById(C3704R.C3706id.rn_redbox_line_separator);
            this.mReportTextView = (TextView) findViewById(C3704R.C3706id.rn_redbox_report_label);
            this.mReportTextView.setMovementMethod(LinkMovementMethod.getInstance());
            this.mReportTextView.setHighlightColor(0);
            this.mReportButton = (Button) findViewById(C3704R.C3706id.rn_redbox_report_button);
            this.mReportButton.setOnClickListener(this.mReportButtonOnClickListener);
        }
    }

    public void setExceptionDetails(String title, StackFrame[] stack) {
        this.mStackView.setAdapter(new StackAdapter(title, stack));
    }

    public void resetReporting(boolean enabled) {
        int i = 0;
        if (this.mRedBoxHandler != null && this.mRedBoxHandler.isReportEnabled()) {
            this.isReporting = false;
            ((TextView) Assertions.assertNotNull(this.mReportTextView)).setVisibility(8);
            ((ProgressBar) Assertions.assertNotNull(this.mLoadingIndicator)).setVisibility(8);
            ((View) Assertions.assertNotNull(this.mLineSeparator)).setVisibility(8);
            Button button = (Button) Assertions.assertNotNull(this.mReportButton);
            if (!enabled) {
                i = 8;
            }
            button.setVisibility(i);
            ((Button) Assertions.assertNotNull(this.mReportButton)).setEnabled(true);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        new OpenStackFrameTask(this.mDevSupportManager).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new StackFrame[]{(StackFrame) this.mStackView.getAdapter().getItem(position)});
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == 82) {
            this.mDevSupportManager.showDevOptionsDialog();
            return true;
        }
        if (this.mDoubleTapReloadRecognizer.didDoubleTapR(keyCode, getCurrentFocus())) {
            this.mDevSupportManager.handleReloadJS();
        }
        return super.onKeyUp(keyCode, event);
    }
}
