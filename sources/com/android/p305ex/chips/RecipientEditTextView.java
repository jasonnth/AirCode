package com.android.p305ex.chips;

import android.accounts.Account;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.text.method.QwertyKeyListener;
import android.text.style.ImageSpan;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView.Validator;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListPopupWindow;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView.Tokenizer;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.airbnb.android.apprater.AppRaterAnalytics;
import com.android.p305ex.chips.BaseRecipientAdapter.EntriesUpdatedObserver;
import com.android.p305ex.chips.RecipientAlternatesAdapter.RecipientMatchCallback;
import com.android.p305ex.chips.recipientchip.DrawableRecipientChip;
import com.android.p305ex.chips.recipientchip.InvisibleRecipientChip;
import com.android.p305ex.chips.recipientchip.VisibleRecipientChip;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.jmrtd.cbeff.ISO781611;

/* renamed from: com.android.ex.chips.RecipientEditTextView */
public class RecipientEditTextView extends MultiAutoCompleteTextView implements OnDismissListener, Callback, OnGestureListener, OnClickListener, OnItemClickListener, OnEditorActionListener, OnCheckedItemChangedListener {
    /* access modifiers changed from: private */
    public static int DISMISS = AppRaterAnalytics.DISMISS.hashCode();
    private static final Pattern PHONE_PATTERN = Pattern.compile("(\\+[0-9]+[\\- \\.]*)?(1?[ ]*\\([0-9]+\\)[\\- \\.]*)?([0-9][0-9\\- \\.][0-9\\- \\.]+[0-9])");
    private static final String SEPARATOR = (String.valueOf(',') + String.valueOf(' '));
    private static int sExcessTopPadding = -1;
    private static int sSelectedTextColor = -1;
    private ItemSelectedListener itemSelectedListener;
    private int mActionBarHeight;
    private final Runnable mAddTextWatcher = new Runnable() {
        public void run() {
            if (RecipientEditTextView.this.mTextWatcher == null) {
                RecipientEditTextView.this.mTextWatcher = new RecipientTextWatcher();
                RecipientEditTextView.this.addTextChangedListener(RecipientEditTextView.this.mTextWatcher);
            }
        }
    };
    private ListPopupWindow mAddressPopup;
    /* access modifiers changed from: private */
    public OnItemClickListener mAlternatesListener;
    /* access modifiers changed from: private */
    public ListPopupWindow mAlternatesPopup;
    /* access modifiers changed from: private */
    public boolean mAttachedToWindow;
    private int mAvatarPosition;
    /* access modifiers changed from: private */
    public int mCheckedItem;
    private Drawable mChipBackground = null;
    private Drawable mChipBackgroundPressed;
    private Drawable mChipDelete = null;
    private float mChipFontSize;
    /* access modifiers changed from: private */
    public float mChipHeight;
    private int mChipPadding;
    private String mCopyAddress;
    private Dialog mCopyDialog;
    private int mCopyDialogLayoutId;
    private Bitmap mDefaultContactPhoto;
    private Runnable mDelayedShrink = new Runnable() {
        public void run() {
            RecipientEditTextView.this.shrink();
        }
    };
    private boolean mDisableDelete;
    private boolean mDismissPopupOnClick = true;
    private boolean mDragEnabled = false;
    private DropdownChipLayouter mDropdownChipLayouter;
    private GestureDetector mGestureDetector;
    private Runnable mHandlePendingChips = new Runnable() {
        public void run() {
            RecipientEditTextView.this.handlePendingChips();
        }
    };
    /* access modifiers changed from: private */
    public Handler mHandler;
    private int mImageSpanAlignment;
    /* access modifiers changed from: private */
    public IndividualReplacementTask mIndividualReplacements;
    private Drawable mInvalidChipBackground;
    /* access modifiers changed from: private */
    public float mLineSpacingExtra;
    private int mMaxLines;
    /* access modifiers changed from: private */
    public ImageSpan mMoreChip;
    private TextView mMoreItem;
    /* access modifiers changed from: private */
    public boolean mNoChips = false;
    final ArrayList<String> mPendingChips = new ArrayList<>();
    private int mPendingChipsCount = 0;
    /* access modifiers changed from: private */
    public ArrayList<DrawableRecipientChip> mRemovedSpans;
    private ScrollView mScrollView;
    /* access modifiers changed from: private */
    public DrawableRecipientChip mSelectedChip;
    private boolean mShouldShrink = true;
    private boolean mShowActionMode = true;
    ArrayList<DrawableRecipientChip> mTemporaryRecipients;
    /* access modifiers changed from: private */
    public TextWatcher mTextWatcher;
    /* access modifiers changed from: private */
    public Tokenizer mTokenizer;
    private boolean mTriedGettingScrollView;
    /* access modifiers changed from: private */
    public Validator mValidator;

    /* renamed from: com.android.ex.chips.RecipientEditTextView$IndividualReplacementTask */
    private class IndividualReplacementTask extends AsyncTask<ArrayList<DrawableRecipientChip>, Void, Void> {
        private IndividualReplacementTask() {
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(ArrayList<DrawableRecipientChip>... params) {
            Account account;
            final ArrayList<DrawableRecipientChip> originalRecipients = params[0];
            ArrayList<String> addresses = new ArrayList<>();
            for (int i = 0; i < originalRecipients.size(); i++) {
                DrawableRecipientChip chip = (DrawableRecipientChip) originalRecipients.get(i);
                if (chip != null) {
                    addresses.add(RecipientEditTextView.this.createAddressText(chip.getEntry()));
                }
            }
            BaseRecipientAdapter adapter = RecipientEditTextView.this.getAdapter();
            if (adapter != null) {
                account = adapter.getAccount();
            } else {
                account = null;
            }
            RecipientAlternatesAdapter.getMatchingRecipients(RecipientEditTextView.this.getContext(), adapter, addresses, account, new RecipientMatchCallback() {
                public void matchesFound(Map<String, RecipientEntry> entries) {
                    Iterator it = originalRecipients.iterator();
                    while (it.hasNext()) {
                        final DrawableRecipientChip temp = (DrawableRecipientChip) it.next();
                        if (RecipientEntry.isCreatedRecipient(temp.getEntry().getContactId()) && RecipientEditTextView.this.getSpannable().getSpanStart(temp) != -1) {
                            final RecipientEntry entry = RecipientEditTextView.this.createValidatedEntry((RecipientEntry) entries.get(RecipientEditTextView.tokenizeAddress(temp.getEntry().getDestination()).toLowerCase()));
                            if (entry != null) {
                                RecipientEditTextView.this.mHandler.post(new Runnable() {
                                    public void run() {
                                        RecipientEditTextView.this.replaceChip(temp, entry);
                                    }
                                });
                            }
                        }
                    }
                }

                public void matchesNotFound(Set<String> set) {
                }
            });
            return null;
        }
    }

    /* renamed from: com.android.ex.chips.RecipientEditTextView$ItemSelectedListener */
    public interface ItemSelectedListener {
        void onItemSelected();
    }

    /* renamed from: com.android.ex.chips.RecipientEditTextView$MoreImageSpan */
    private class MoreImageSpan extends ImageSpan {
        public MoreImageSpan(Drawable b) {
            super(b);
        }
    }

    /* renamed from: com.android.ex.chips.RecipientEditTextView$RecipientChipShadow */
    private final class RecipientChipShadow extends DragShadowBuilder {
        private final DrawableRecipientChip mChip;

        public RecipientChipShadow(DrawableRecipientChip chip) {
            this.mChip = chip;
        }

        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
            Rect rect = this.mChip.getBounds();
            shadowSize.set(rect.width(), rect.height());
            shadowTouchPoint.set(rect.centerX(), rect.centerY());
        }

        public void onDrawShadow(Canvas canvas) {
            this.mChip.draw(canvas);
        }
    }

    /* renamed from: com.android.ex.chips.RecipientEditTextView$RecipientReplacementTask */
    private class RecipientReplacementTask extends AsyncTask<Void, Void, Void> {
        private RecipientReplacementTask() {
        }

        /* access modifiers changed from: private */
        public DrawableRecipientChip createFreeChip(RecipientEntry entry) {
            try {
                if (RecipientEditTextView.this.mNoChips) {
                    return null;
                }
                return RecipientEditTextView.this.constructChipSpan(entry, false, false);
            } catch (NullPointerException e) {
                Log.e("RecipientEditTextView", e.getMessage(), e);
                return null;
            }
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            List<DrawableRecipientChip> originalRecipients = new ArrayList<>();
            DrawableRecipientChip[] existingChips = RecipientEditTextView.this.getSortedRecipients();
            for (DrawableRecipientChip add : existingChips) {
                originalRecipients.add(add);
            }
            if (RecipientEditTextView.this.mRemovedSpans != null) {
                originalRecipients.addAll(RecipientEditTextView.this.mRemovedSpans);
            }
            List<DrawableRecipientChip> replacements = new ArrayList<>(originalRecipients.size());
            for (DrawableRecipientChip chip : originalRecipients) {
                if (!RecipientEntry.isCreatedRecipient(chip.getEntry().getContactId()) || RecipientEditTextView.this.getSpannable().getSpanStart(chip) == -1) {
                    replacements.add(null);
                } else {
                    replacements.add(createFreeChip(chip.getEntry()));
                }
            }
            processReplacements(originalRecipients, replacements);
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... params) {
            Account account;
            if (RecipientEditTextView.this.mIndividualReplacements != null) {
                RecipientEditTextView.this.mIndividualReplacements.cancel(true);
            }
            final ArrayList<DrawableRecipientChip> recipients = new ArrayList<>();
            DrawableRecipientChip[] existingChips = RecipientEditTextView.this.getSortedRecipients();
            for (DrawableRecipientChip add : existingChips) {
                recipients.add(add);
            }
            if (RecipientEditTextView.this.mRemovedSpans != null) {
                recipients.addAll(RecipientEditTextView.this.mRemovedSpans);
            }
            ArrayList<String> addresses = new ArrayList<>();
            for (int i = 0; i < recipients.size(); i++) {
                DrawableRecipientChip chip = (DrawableRecipientChip) recipients.get(i);
                if (chip != null) {
                    addresses.add(RecipientEditTextView.this.createAddressText(chip.getEntry()));
                }
            }
            BaseRecipientAdapter adapter = RecipientEditTextView.this.getAdapter();
            if (adapter != null) {
                account = adapter.getAccount();
            } else {
                account = null;
            }
            RecipientAlternatesAdapter.getMatchingRecipients(RecipientEditTextView.this.getContext(), adapter, addresses, account, new RecipientMatchCallback() {
                public void matchesFound(Map<String, RecipientEntry> entries) {
                    ArrayList<DrawableRecipientChip> replacements = new ArrayList<>();
                    Iterator it = recipients.iterator();
                    while (it.hasNext()) {
                        DrawableRecipientChip temp = (DrawableRecipientChip) it.next();
                        RecipientEntry entry = null;
                        if (!(temp == null || !RecipientEntry.isCreatedRecipient(temp.getEntry().getContactId()) || RecipientEditTextView.this.getSpannable().getSpanStart(temp) == -1)) {
                            entry = RecipientEditTextView.this.createValidatedEntry((RecipientEntry) entries.get(RecipientEditTextView.tokenizeAddress(temp.getEntry().getDestination())));
                        }
                        if (entry != null) {
                            replacements.add(RecipientReplacementTask.this.createFreeChip(entry));
                        } else {
                            replacements.add(null);
                        }
                    }
                    RecipientReplacementTask.this.processReplacements(recipients, replacements);
                }

                public void matchesNotFound(Set<String> unfoundAddresses) {
                    List<DrawableRecipientChip> replacements = new ArrayList<>(unfoundAddresses.size());
                    Iterator it = recipients.iterator();
                    while (it.hasNext()) {
                        DrawableRecipientChip temp = (DrawableRecipientChip) it.next();
                        if (temp == null || !RecipientEntry.isCreatedRecipient(temp.getEntry().getContactId()) || RecipientEditTextView.this.getSpannable().getSpanStart(temp) == -1) {
                            replacements.add(null);
                        } else if (unfoundAddresses.contains(temp.getEntry().getDestination())) {
                            replacements.add(RecipientReplacementTask.this.createFreeChip(temp.getEntry()));
                        } else {
                            replacements.add(null);
                        }
                    }
                    RecipientReplacementTask.this.processReplacements(recipients, replacements);
                }
            });
            return null;
        }

        /* access modifiers changed from: private */
        public void processReplacements(final List<DrawableRecipientChip> recipients, final List<DrawableRecipientChip> replacements) {
            if (replacements != null && replacements.size() > 0) {
                Runnable runnable = new Runnable() {
                    public void run() {
                        boolean isBetter;
                        Editable text = new SpannableStringBuilder(RecipientEditTextView.this.getText());
                        int i = 0;
                        for (DrawableRecipientChip chip : recipients) {
                            DrawableRecipientChip replacement = (DrawableRecipientChip) replacements.get(i);
                            if (replacement != null) {
                                RecipientEntry oldEntry = chip.getEntry();
                                RecipientEntry newEntry = replacement.getEntry();
                                if (RecipientAlternatesAdapter.getBetterRecipient(oldEntry, newEntry) == newEntry) {
                                    isBetter = true;
                                } else {
                                    isBetter = false;
                                }
                                if (isBetter) {
                                    int start = text.getSpanStart(chip);
                                    if (start != -1) {
                                        int end = Math.min(text.getSpanEnd(chip) + 1, text.length());
                                        text.removeSpan(chip);
                                        SpannableString displayText = new SpannableString(RecipientEditTextView.this.createAddressText(replacement.getEntry()).trim() + " ");
                                        displayText.setSpan(replacement, 0, displayText.length() - 1, 33);
                                        text.replace(start, end, displayText);
                                        replacement.setOriginalText(displayText.toString());
                                        replacements.set(i, null);
                                        recipients.set(i, replacement);
                                    }
                                }
                            }
                            i++;
                        }
                        RecipientEditTextView.this.setText(text);
                    }
                };
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    runnable.run();
                } else {
                    RecipientEditTextView.this.mHandler.post(runnable);
                }
            }
        }
    }

    /* renamed from: com.android.ex.chips.RecipientEditTextView$RecipientTextWatcher */
    private class RecipientTextWatcher implements TextWatcher {
        private RecipientTextWatcher() {
        }

        public void afterTextChanged(Editable s) {
            char last;
            int end = 0;
            if (TextUtils.isEmpty(s)) {
                Spannable spannable = RecipientEditTextView.this.getSpannable();
                DrawableRecipientChip[] chips = (DrawableRecipientChip[]) spannable.getSpans(0, RecipientEditTextView.this.getText().length(), DrawableRecipientChip.class);
                int length = chips.length;
                while (end < length) {
                    spannable.removeSpan(chips[end]);
                    end++;
                }
                if (RecipientEditTextView.this.mMoreChip != null) {
                    spannable.removeSpan(RecipientEditTextView.this.mMoreChip);
                }
                RecipientEditTextView.this.clearSelectedChip();
            } else if (!RecipientEditTextView.this.chipsPending()) {
                if (RecipientEditTextView.this.mSelectedChip != null) {
                    if (!RecipientEditTextView.this.isGeneratedContact(RecipientEditTextView.this.mSelectedChip)) {
                        RecipientEditTextView.this.setCursorVisible(true);
                        RecipientEditTextView.this.setSelection(RecipientEditTextView.this.getText().length());
                        RecipientEditTextView.this.clearSelectedChip();
                    } else {
                        return;
                    }
                }
                if (s.length() <= 1) {
                    return;
                }
                if (RecipientEditTextView.this.lastCharacterIsCommitCharacter(s)) {
                    RecipientEditTextView.this.commitByCharacter();
                    return;
                }
                if (RecipientEditTextView.this.getSelectionEnd() != 0) {
                    end = RecipientEditTextView.this.getSelectionEnd() - 1;
                }
                int len = RecipientEditTextView.this.length() - 1;
                if (end != len) {
                    last = s.charAt(end);
                } else {
                    last = s.charAt(len);
                }
                if (last == ' ' && !RecipientEditTextView.this.isPhoneQuery()) {
                    String text = RecipientEditTextView.this.getText().toString();
                    int tokenStart = RecipientEditTextView.this.mTokenizer.findTokenStart(text, RecipientEditTextView.this.getSelectionEnd());
                    String sub = text.substring(tokenStart, RecipientEditTextView.this.mTokenizer.findTokenEnd(text, tokenStart));
                    if (!TextUtils.isEmpty(sub) && RecipientEditTextView.this.mValidator != null && RecipientEditTextView.this.mValidator.isValid(sub)) {
                        RecipientEditTextView.this.commitByCharacter();
                    }
                }
            }
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (before - count == 1) {
                int selStart = RecipientEditTextView.this.getSelectionStart();
                DrawableRecipientChip[] repl = (DrawableRecipientChip[]) RecipientEditTextView.this.getSpannable().getSpans(selStart, selStart, DrawableRecipientChip.class);
                if (repl.length > 0) {
                    Editable editable = RecipientEditTextView.this.getText();
                    int tokenStart = RecipientEditTextView.this.mTokenizer.findTokenStart(editable, selStart);
                    int tokenEnd = RecipientEditTextView.this.mTokenizer.findTokenEnd(editable, tokenStart) + 1;
                    if (tokenEnd > editable.length()) {
                        tokenEnd = editable.length();
                    }
                    editable.delete(tokenStart, tokenEnd);
                    RecipientEditTextView.this.getSpannable().removeSpan(repl[0]);
                }
            } else if (count > before && RecipientEditTextView.this.mSelectedChip != null && RecipientEditTextView.this.isGeneratedContact(RecipientEditTextView.this.mSelectedChip) && RecipientEditTextView.this.lastCharacterIsCommitCharacter(s)) {
                RecipientEditTextView.this.commitByCharacter();
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
    }

    public RecipientEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setChipDimensions(context, attrs);
        if (sSelectedTextColor == -1) {
            sSelectedTextColor = context.getResources().getColor(17170443);
        }
        this.mAlternatesPopup = new ListPopupWindow(context);
        this.mAddressPopup = new ListPopupWindow(context);
        this.mCopyDialog = new Dialog(context);
        this.mCopyDialogLayoutId = R.layout.copy_chip_dialog_layout;
        this.mAlternatesListener = new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                RecipientEditTextView.this.mAlternatesPopup.setOnItemClickListener(null);
                RecipientEditTextView.this.replaceChip(RecipientEditTextView.this.mSelectedChip, ((RecipientAlternatesAdapter) adapterView.getAdapter()).getRecipientEntry(position));
                Message delayed = Message.obtain(RecipientEditTextView.this.mHandler, RecipientEditTextView.DISMISS);
                delayed.obj = RecipientEditTextView.this.mAlternatesPopup;
                RecipientEditTextView.this.mHandler.sendMessageDelayed(delayed, 300);
                RecipientEditTextView.this.clearComposingText();
            }
        };
        setInputType(getInputType() | 524288);
        setOnItemClickListener(this);
        setCustomSelectionActionModeCallback(this);
        this.mHandler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == RecipientEditTextView.DISMISS) {
                    ((ListPopupWindow) msg.obj).dismiss();
                } else {
                    super.handleMessage(msg);
                }
            }
        };
        this.mTextWatcher = new RecipientTextWatcher();
        addTextChangedListener(this.mTextWatcher);
        this.mGestureDetector = new GestureDetector(context, this);
        setOnEditorActionListener(this);
        setDropdownChipLayouter(new DropdownChipLayouter(LayoutInflater.from(context), context));
    }

    /* access modifiers changed from: protected */
    public void setDropdownChipLayouter(DropdownChipLayouter dropdownChipLayouter) {
        this.mDropdownChipLayouter = dropdownChipLayouter;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
    }

    public boolean onEditorAction(TextView view, int action, KeyEvent keyEvent) {
        if (action == 6) {
            if (commitDefault()) {
                return true;
            }
            if (this.mSelectedChip != null) {
                clearSelectedChip();
                return true;
            } else if (focusNext()) {
                return true;
            }
        }
        return false;
    }

    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        InputConnection connection = super.onCreateInputConnection(outAttrs);
        int imeActions = outAttrs.imeOptions & 255;
        if ((imeActions & 6) != 0) {
            outAttrs.imeOptions ^= imeActions;
            outAttrs.imeOptions |= 6;
        }
        if ((outAttrs.imeOptions & 1073741824) != 0) {
            outAttrs.imeOptions &= -1073741825;
        }
        outAttrs.actionId = 6;
        outAttrs.actionLabel = getContext().getString(R.string.done);
        return connection;
    }

    /* access modifiers changed from: 0000 */
    public DrawableRecipientChip getLastChip() {
        DrawableRecipientChip[] chips = getSortedRecipients();
        if (chips == null || chips.length <= 0) {
            return null;
        }
        return chips[chips.length - 1];
    }

    public void onSelectionChanged(int start, int end) {
        DrawableRecipientChip last = getLastChip();
        if (last != null && start < getSpannable().getSpanEnd(last)) {
            setSelection(Math.min(getSpannable().getSpanEnd(last) + 1, getText().length()));
        }
        super.onSelectionChanged(start, end);
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (!TextUtils.isEmpty(getText())) {
            super.onRestoreInstanceState(null);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    public Parcelable onSaveInstanceState() {
        clearSelectedChip();
        return super.onSaveInstanceState();
    }

    public void append(CharSequence text, int start, int end) {
        if (this.mTextWatcher != null) {
            removeTextChangedListener(this.mTextWatcher);
        }
        super.append(text, start, end);
        if (!TextUtils.isEmpty(text) && TextUtils.getTrimmedLength(text) > 0) {
            String displayString = text.toString();
            if (!displayString.trim().endsWith(String.valueOf(','))) {
                super.append(SEPARATOR, 0, SEPARATOR.length());
                displayString = displayString + SEPARATOR;
            }
            if (!TextUtils.isEmpty(displayString) && TextUtils.getTrimmedLength(displayString) > 0) {
                this.mPendingChipsCount++;
                this.mPendingChips.add(displayString);
            }
        }
        if (this.mPendingChipsCount > 0) {
            postHandlePendingChips();
        }
        this.mHandler.post(this.mAddTextWatcher);
    }

    public void onFocusChanged(boolean hasFocus, int direction, Rect previous) {
        super.onFocusChanged(hasFocus, direction, previous);
        if (!hasFocus) {
            shrink();
        } else {
            expand();
        }
    }

    private int getExcessTopPadding() {
        if (sExcessTopPadding == -1) {
            sExcessTopPadding = (int) (this.mChipHeight + this.mLineSpacingExtra);
        }
        return sExcessTopPadding;
    }

    public <T extends ListAdapter & Filterable> void setAdapter(T adapter) {
        super.setAdapter(adapter);
        BaseRecipientAdapter baseAdapter = (BaseRecipientAdapter) adapter;
        baseAdapter.registerUpdateObserver(new EntriesUpdatedObserver() {
            public void onChanged(List<RecipientEntry> entries) {
                if (entries != null && entries.size() > 0) {
                    RecipientEditTextView.this.scrollBottomIntoView();
                }
            }
        });
        baseAdapter.setDropdownChipLayouter(this.mDropdownChipLayouter);
    }

    /* access modifiers changed from: protected */
    public void scrollBottomIntoView() {
        if (this.mScrollView != null && this.mShouldShrink) {
            int[] location = new int[2];
            getLocationOnScreen(location);
            int currentPos = location[1] + getHeight();
            int desiredPos = ((int) this.mChipHeight) + this.mActionBarHeight + getExcessTopPadding();
            if (currentPos > desiredPos) {
                this.mScrollView.scrollBy(0, currentPos - desiredPos);
            }
        }
    }

    /* access modifiers changed from: protected */
    public ScrollView getScrollView() {
        return this.mScrollView;
    }

    public void performValidation() {
    }

    /* access modifiers changed from: private */
    public void shrink() {
        long contactId;
        if (this.mTokenizer != null) {
            if (this.mSelectedChip != null) {
                contactId = this.mSelectedChip.getEntry().getContactId();
            } else {
                contactId = -1;
            }
            if (this.mSelectedChip != null && contactId != -1 && !isPhoneQuery() && contactId != -2) {
                clearSelectedChip();
            } else if (getWidth() <= 0) {
                this.mHandler.removeCallbacks(this.mDelayedShrink);
                this.mHandler.post(this.mDelayedShrink);
                return;
            } else {
                if (this.mPendingChipsCount > 0) {
                    postHandlePendingChips();
                } else {
                    Editable editable = getText();
                    int end = getSelectionEnd();
                    int start = this.mTokenizer.findTokenStart(editable, end);
                    DrawableRecipientChip[] chips = (DrawableRecipientChip[]) getSpannable().getSpans(start, end, DrawableRecipientChip.class);
                    if (chips == null || chips.length == 0) {
                        Editable text = getText();
                        int whatEnd = this.mTokenizer.findTokenEnd(text, start);
                        if (whatEnd < text.length() && text.charAt(whatEnd) == ',') {
                            whatEnd = movePastTerminators(whatEnd);
                        }
                        if (whatEnd != getSelectionEnd()) {
                            handleEdit(start, whatEnd);
                        } else {
                            commitChip(start, end, editable);
                        }
                    }
                }
                this.mHandler.post(this.mAddTextWatcher);
            }
            createMoreChip();
        }
    }

    private void expand() {
        if (this.mShouldShrink) {
            setMaxLines(Integer.MAX_VALUE);
        }
        removeMoreChip();
        setCursorVisible(true);
        Editable text = getText();
        setSelection((text == null || text.length() <= 0) ? 0 : text.length());
        if (this.mTemporaryRecipients != null && this.mTemporaryRecipients.size() > 0) {
            new RecipientReplacementTask().execute(new Void[0]);
            this.mTemporaryRecipients = null;
        }
    }

    private CharSequence ellipsizeText(CharSequence text, TextPaint paint, float maxWidth) {
        paint.setTextSize(this.mChipFontSize);
        if (maxWidth <= 0.0f && Log.isLoggable("RecipientEditTextView", 3)) {
            Log.d("RecipientEditTextView", "Max width is negative: " + maxWidth);
        }
        return TextUtils.ellipsize(text, paint, maxWidth, TruncateAt.END);
    }

    private Bitmap createSelectedChip(RecipientEntry contact, TextPaint paint) {
        Bitmap photo;
        paint.setColor(sSelectedTextColor);
        if (this.mDisableDelete) {
            photo = getAvatarIcon(contact);
        } else {
            photo = ((BitmapDrawable) this.mChipDelete).getBitmap();
        }
        return createChipBitmap(contact, paint, photo, this.mChipBackgroundPressed);
    }

    private Bitmap createUnselectedChip(RecipientEntry contact, TextPaint paint, boolean leaveBlankIconSpacer) {
        Drawable background = getChipBackground(contact);
        Bitmap photo = getAvatarIcon(contact);
        paint.setColor(getContext().getResources().getColor(17170444));
        return createChipBitmap(contact, paint, photo, background);
    }

    private Bitmap createChipBitmap(RecipientEntry contact, TextPaint paint, Bitmap icon, Drawable background) {
        if (background == null) {
            Log.w("RecipientEditTextView", "Unable to draw a background for the chips as it was never set");
            return Bitmap.createBitmap(((int) this.mChipHeight) * 2, (int) this.mChipHeight, Config.ARGB_8888);
        }
        Rect backgroundPadding = new Rect();
        background.getPadding(backgroundPadding);
        int height = ((int) this.mChipHeight) + getResources().getDimensionPixelSize(R.dimen.extra_chip_height);
        int iconWidth = (height - backgroundPadding.top) - backgroundPadding.bottom;
        float[] widths = new float[1];
        paint.getTextWidths(" ", widths);
        CharSequence ellipsizedText = ellipsizeText(createChipDisplayText(contact), paint, (((calculateAvailableWidth() - ((float) iconWidth)) - widths[0]) - ((float) backgroundPadding.left)) - ((float) backgroundPadding.right));
        int textWidth = (int) paint.measureText(ellipsizedText, 0, ellipsizedText.length());
        int width = Math.max(iconWidth * 2, (this.mChipPadding * 2) + textWidth + iconWidth + backgroundPadding.left + backgroundPadding.right);
        Bitmap tmpBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(tmpBitmap);
        background.setBounds(height / 2, 0, width, height);
        background.draw(canvas);
        int textX = shouldPositionAvatarOnRight() ? this.mChipPadding + backgroundPadding.left : ((width - backgroundPadding.right) - this.mChipPadding) - textWidth;
        paint.setColor(-10724260);
        paint.setAntiAlias(true);
        canvas.drawText(ellipsizedText, 0, ellipsizedText.length(), (float) textX, getTextYOffset(ellipsizedText.toString(), paint, height), paint);
        if (icon == null) {
            return tmpBitmap;
        }
        Bitmap icon2 = ChipsUtil.getClip(icon);
        if (shouldPositionAvatarOnRight()) {
            int i = (width - backgroundPadding.right) - iconWidth;
        } else {
            int i2 = backgroundPadding.left;
        }
        drawIconOnCanvas(icon2, canvas, paint, new RectF(0.0f, 0.0f, (float) icon2.getWidth(), (float) icon2.getHeight()), new RectF(0.0f, 0.0f, (float) height, (float) height));
        return tmpBitmap;
    }

    private boolean shouldPositionAvatarOnRight() {
        boolean assignedPosition;
        boolean isRtl = VERSION.SDK_INT >= 17 ? getLayoutDirection() == 1 : false;
        if (this.mAvatarPosition == 0) {
            assignedPosition = true;
        } else {
            assignedPosition = false;
        }
        if (!isRtl) {
            return assignedPosition;
        }
        if (!assignedPosition) {
            return true;
        }
        return false;
    }

    private Bitmap getAvatarIcon(RecipientEntry contact) {
        long contactId = contact.getContactId();
        byte[] photoBytes = contact.getPhotoBytes();
        if (photoBytes == null && contact.getPhotoThumbnailUri() != null) {
            getAdapter();
            BaseRecipientAdapter.fetchPhoto(contact, contact.getPhotoThumbnailUri(), getContext().getContentResolver());
            photoBytes = contact.getPhotoBytes();
        }
        if (photoBytes != null) {
            return BitmapFactory.decodeByteArray(photoBytes, 0, photoBytes.length);
        }
        return this.mDefaultContactPhoto;
    }

    /* access modifiers changed from: 0000 */
    public Drawable getChipBackground(RecipientEntry contact) {
        return contact.isValid() ? this.mChipBackground : this.mInvalidChipBackground;
    }

    /* access modifiers changed from: protected */
    public float getTextYOffset(String text, TextPaint paint, int height) {
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        return (float) ((height - ((height - (bounds.bottom - bounds.top)) / 2)) - (((int) paint.descent()) / 2));
    }

    /* access modifiers changed from: protected */
    public void drawIconOnCanvas(Bitmap icon, Canvas canvas, Paint paint, RectF src, RectF dst) {
        Matrix matrix = new Matrix();
        matrix.setRectToRect(src, dst, ScaleToFit.FILL);
        canvas.drawBitmap(icon, matrix, paint);
    }

    /* access modifiers changed from: private */
    public DrawableRecipientChip constructChipSpan(RecipientEntry contact, boolean pressed, boolean leaveIconSpace) throws NullPointerException {
        Bitmap tmpBitmap;
        if (this.mChipBackground == null) {
            throw new NullPointerException("Unable to render any chips as setChipDimensions was not called.");
        }
        TextPaint paint = getPaint();
        float defaultSize = paint.getTextSize();
        int defaultColor = paint.getColor();
        if (pressed) {
            tmpBitmap = createSelectedChip(contact, paint);
        } else {
            tmpBitmap = createUnselectedChip(contact, paint, leaveIconSpace);
        }
        Drawable result = new BitmapDrawable(getResources(), tmpBitmap);
        result.setBounds(0, 0, tmpBitmap.getWidth(), tmpBitmap.getHeight());
        DrawableRecipientChip recipientChip = new VisibleRecipientChip(result, contact, getImageSpanAlignment());
        paint.setTextSize(defaultSize);
        paint.setColor(defaultColor);
        return recipientChip;
    }

    private int getImageSpanAlignment() {
        switch (this.mImageSpanAlignment) {
            case 1:
                return 1;
            default:
                return 0;
        }
    }

    private int calculateOffsetFromBottom(int line) {
        return (-((((int) this.mChipHeight) * (getLineCount() - (line + 1))) + getPaddingBottom() + getPaddingTop())) + getDropDownVerticalOffset();
    }

    private float calculateAvailableWidth() {
        return (float) (((getWidth() - getPaddingLeft()) - getPaddingRight()) - (this.mChipPadding * 2));
    }

    private void setChipDimensions(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RecipientEditTextView, 0, 0);
        Resources r = getContext().getResources();
        this.mChipBackground = a.getDrawable(R.styleable.RecipientEditTextView_chipBackground);
        if (this.mChipBackground == null) {
            this.mChipBackground = r.getDrawable(R.drawable.chip_background);
        }
        this.mChipBackgroundPressed = a.getDrawable(R.styleable.RecipientEditTextView_chipBackgroundPressed);
        if (this.mChipBackgroundPressed == null) {
            this.mChipBackgroundPressed = r.getDrawable(R.drawable.chip_background_selected);
        }
        this.mChipDelete = a.getDrawable(R.styleable.RecipientEditTextView_chipDelete);
        if (this.mChipDelete == null) {
            this.mChipDelete = r.getDrawable(R.drawable.chip_delete);
        }
        this.mChipPadding = a.getDimensionPixelSize(R.styleable.RecipientEditTextView_chipPadding, -1);
        if (this.mChipPadding == -1) {
            this.mChipPadding = (int) r.getDimension(R.dimen.chip_padding);
        }
        this.mDefaultContactPhoto = BitmapFactory.decodeResource(r, R.drawable.ic_contact_picture);
        this.mMoreItem = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.more_item, null);
        this.mChipHeight = (float) a.getDimensionPixelSize(R.styleable.RecipientEditTextView_chipHeight, -1);
        if (this.mChipHeight == -1.0f) {
            this.mChipHeight = r.getDimension(R.dimen.chip_height);
        }
        this.mChipFontSize = (float) a.getDimensionPixelSize(R.styleable.RecipientEditTextView_chipFontSize, -1);
        if (this.mChipFontSize == -1.0f) {
            this.mChipFontSize = r.getDimension(R.dimen.chip_text_size);
        }
        this.mInvalidChipBackground = a.getDrawable(R.styleable.RecipientEditTextView_invalidChipBackground);
        if (this.mInvalidChipBackground == null) {
            this.mInvalidChipBackground = r.getDrawable(R.drawable.chip_background_invalid);
        }
        this.mAvatarPosition = a.getInt(R.styleable.RecipientEditTextView_avatarPosition, 1);
        this.mImageSpanAlignment = a.getInt(R.styleable.RecipientEditTextView_imageSpanAlignment, 0);
        this.mDisableDelete = a.getBoolean(R.styleable.RecipientEditTextView_disableDelete, false);
        this.mLineSpacingExtra = r.getDimension(R.dimen.line_spacing_extra);
        this.mMaxLines = r.getInteger(R.integer.chips_max_lines);
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(16843499, tv, true)) {
            this.mActionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        a.recycle();
    }

    /* access modifiers changed from: 0000 */
    public void setMoreItem(TextView moreItem) {
        this.mMoreItem = moreItem;
    }

    /* access modifiers changed from: 0000 */
    public void setChipBackground(Drawable chipBackground) {
        this.mChipBackground = chipBackground;
    }

    /* access modifiers changed from: 0000 */
    public void setChipHeight(int height) {
        this.mChipHeight = (float) height;
    }

    public float getChipHeight() {
        return this.mChipHeight;
    }

    public void setOnFocusListShrinkRecipients(boolean shrink) {
        this.mShouldShrink = shrink;
    }

    public void onSizeChanged(int width, int height, int oldw, int oldh) {
        super.onSizeChanged(width, height, oldw, oldh);
        if (!(width == 0 || height == 0)) {
            if (this.mPendingChipsCount > 0) {
                postHandlePendingChips();
            } else {
                checkChipWidths();
            }
        }
        if (this.mScrollView == null && !this.mTriedGettingScrollView) {
            ViewParent parent = getParent();
            while (parent != null && !(parent instanceof ScrollView)) {
                parent = parent.getParent();
            }
            if (parent != null) {
                this.mScrollView = (ScrollView) parent;
            }
            this.mTriedGettingScrollView = true;
        }
    }

    private void postHandlePendingChips() {
        this.mHandler.removeCallbacks(this.mHandlePendingChips);
        this.mHandler.post(this.mHandlePendingChips);
    }

    private void checkChipWidths() {
        DrawableRecipientChip[] chips = getSortedRecipients();
        if (chips != null) {
            for (DrawableRecipientChip chip : chips) {
                Rect bounds = chip.getBounds();
                if (getWidth() > 0 && bounds.right - bounds.left > (getWidth() - getPaddingLeft()) - getPaddingRight()) {
                    replaceChip(chip, chip.getEntry());
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void handlePendingChips() {
        if (getViewWidth() > 0 && this.mPendingChipsCount > 0) {
            synchronized (this.mPendingChips) {
                Editable editable = getText();
                if (this.mPendingChipsCount <= 50) {
                    int i = 0;
                    while (i < this.mPendingChips.size()) {
                        String current = (String) this.mPendingChips.get(i);
                        int tokenStart = editable.toString().indexOf(current);
                        int tokenEnd = (current.length() + tokenStart) - 1;
                        if (tokenStart >= 0) {
                            if (tokenEnd < editable.length() - 2 && editable.charAt(tokenEnd) == ',') {
                                tokenEnd++;
                            }
                            createReplacementChip(tokenStart, tokenEnd, editable, i < 2 || !this.mShouldShrink);
                        }
                        this.mPendingChipsCount--;
                        i++;
                    }
                    sanitizeEnd();
                } else {
                    this.mNoChips = true;
                }
                if (this.mTemporaryRecipients == null || this.mTemporaryRecipients.size() <= 0 || this.mTemporaryRecipients.size() > 50) {
                    this.mTemporaryRecipients = null;
                    createMoreChip();
                } else if (hasFocus() || this.mTemporaryRecipients.size() < 2) {
                    new RecipientReplacementTask().execute(new Void[0]);
                    this.mTemporaryRecipients = null;
                } else {
                    this.mIndividualReplacements = new IndividualReplacementTask();
                    this.mIndividualReplacements.execute(new ArrayList[]{new ArrayList(this.mTemporaryRecipients.subList(0, 2))});
                    if (this.mTemporaryRecipients.size() > 2) {
                        this.mTemporaryRecipients = new ArrayList<>(this.mTemporaryRecipients.subList(2, this.mTemporaryRecipients.size()));
                    } else {
                        this.mTemporaryRecipients = null;
                    }
                    createMoreChip();
                }
                this.mPendingChipsCount = 0;
                this.mPendingChips.clear();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public int getViewWidth() {
        return getWidth();
    }

    /* access modifiers changed from: 0000 */
    public void sanitizeEnd() {
        int end;
        if (this.mPendingChipsCount <= 0) {
            DrawableRecipientChip[] chips = getSortedRecipients();
            Spannable spannable = getSpannable();
            if (chips != null && chips.length > 0) {
                this.mMoreChip = getMoreChip();
                if (this.mMoreChip != null) {
                    end = spannable.getSpanEnd(this.mMoreChip);
                } else {
                    end = getSpannable().getSpanEnd(getLastChip());
                }
                Editable editable = getText();
                int length = editable.length();
                if (length > end) {
                    if (Log.isLoggable("RecipientEditTextView", 3)) {
                        Log.d("RecipientEditTextView", "There were extra characters after the last tokenizable entry." + editable);
                    }
                    editable.delete(end + 1, length);
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void createReplacementChip(int tokenStart, int tokenEnd, Editable editable, boolean visible) {
        boolean leaveSpace = false;
        if (!alreadyHasChip(tokenStart, tokenEnd)) {
            String token = editable.toString().substring(tokenStart, tokenEnd);
            String trimmedToken = token.trim();
            int commitCharIndex = trimmedToken.lastIndexOf(44);
            if (commitCharIndex != -1 && commitCharIndex == trimmedToken.length() - 1) {
                token = trimmedToken.substring(0, trimmedToken.length() - 1);
            }
            RecipientEntry entry = createTokenizedEntry(token);
            if (entry != null) {
                DrawableRecipientChip chip = null;
                try {
                    if (!this.mNoChips) {
                        if (TextUtils.isEmpty(entry.getDisplayName()) || TextUtils.equals(entry.getDisplayName(), entry.getDestination())) {
                            leaveSpace = true;
                        }
                        chip = visible ? constructChipSpan(entry, false, leaveSpace) : new InvisibleRecipientChip(entry);
                    }
                } catch (NullPointerException e) {
                    Log.e("RecipientEditTextView", e.getMessage(), e);
                }
                editable.setSpan(chip, tokenStart, tokenEnd, 33);
                if (chip != null) {
                    if (this.mTemporaryRecipients == null) {
                        this.mTemporaryRecipients = new ArrayList<>();
                    }
                    chip.setOriginalText(token);
                    this.mTemporaryRecipients.add(chip);
                }
            }
        }
    }

    private static boolean isPhoneNumber(String number) {
        if (TextUtils.isEmpty(number)) {
            return false;
        }
        return PHONE_PATTERN.matcher(number).matches();
    }

    /* access modifiers changed from: 0000 */
    public RecipientEntry createTokenizedEntry(String token) {
        if (TextUtils.isEmpty(token)) {
            return null;
        }
        if (isPhoneQuery() && isPhoneNumber(token)) {
            return RecipientEntry.constructFakePhoneEntry(token, true);
        }
        Rfc822Token[] tokens = Rfc822Tokenizer.tokenize(token);
        boolean isValid = isValid(token);
        if (isValid && tokens != null && tokens.length > 0) {
            String display = tokens[0].getName();
            if (!TextUtils.isEmpty(display)) {
                return RecipientEntry.constructGeneratedEntry(display, tokens[0].getAddress(), isValid);
            }
            String display2 = tokens[0].getAddress();
            if (!TextUtils.isEmpty(display2)) {
                return RecipientEntry.constructFakeEntry(display2, isValid);
            }
        }
        String validatedToken = null;
        if (this.mValidator != null && !isValid) {
            validatedToken = this.mValidator.fixText(token).toString();
            if (!TextUtils.isEmpty(validatedToken)) {
                if (validatedToken.contains(token)) {
                    Rfc822Token[] tokenized = Rfc822Tokenizer.tokenize(validatedToken);
                    if (tokenized.length > 0) {
                        validatedToken = tokenized[0].getAddress();
                        isValid = true;
                    }
                } else {
                    validatedToken = null;
                    isValid = false;
                }
            }
        }
        if (TextUtils.isEmpty(validatedToken)) {
            validatedToken = token;
        }
        return RecipientEntry.constructFakeEntry(validatedToken, isValid);
    }

    private boolean isValid(String text) {
        if (this.mValidator == null) {
            return true;
        }
        return this.mValidator.isValid(text);
    }

    /* access modifiers changed from: private */
    public static String tokenizeAddress(String destination) {
        Rfc822Token[] tokens = Rfc822Tokenizer.tokenize(destination);
        if (tokens == null || tokens.length <= 0) {
            return destination;
        }
        return tokens[0].getAddress();
    }

    public void setTokenizer(Tokenizer tokenizer) {
        this.mTokenizer = tokenizer;
        super.setTokenizer(this.mTokenizer);
    }

    public void setValidator(Validator validator) {
        this.mValidator = validator;
        super.setValidator(validator);
    }

    /* access modifiers changed from: protected */
    public void replaceText(CharSequence text) {
    }

    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode != 4 || this.mSelectedChip == null) {
            return super.onKeyPreIme(keyCode, event);
        }
        clearSelectedChip();
        return true;
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 61:
                if (event.hasNoModifiers()) {
                    if (this.mSelectedChip == null) {
                        commitDefault();
                        break;
                    } else {
                        clearSelectedChip();
                        break;
                    }
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    private boolean focusNext() {
        View next = focusSearch(ISO781611.BIOMETRIC_SUBTYPE_TAG);
        if (next == null) {
            return false;
        }
        next.requestFocus();
        return true;
    }

    private boolean commitDefault() {
        if (this.mTokenizer == null) {
            return false;
        }
        Editable editable = getText();
        int end = getSelectionEnd();
        int start = this.mTokenizer.findTokenStart(editable, end);
        if (!shouldCreateChip(start, end)) {
            return false;
        }
        int whatEnd = movePastTerminators(this.mTokenizer.findTokenEnd(getText(), start));
        if (whatEnd == getSelectionEnd()) {
            return commitChip(start, end, editable);
        }
        handleEdit(start, whatEnd);
        return true;
    }

    /* access modifiers changed from: private */
    public void commitByCharacter() {
        if (this.mTokenizer != null) {
            Editable editable = getText();
            int end = getSelectionEnd();
            int start = this.mTokenizer.findTokenStart(editable, end);
            if (shouldCreateChip(start, end)) {
                commitChip(start, end, editable);
            }
            setSelection(getText().length());
        }
    }

    private boolean commitChip(int start, int end, Editable editable) {
        ListAdapter adapter = getAdapter();
        if (adapter == null || adapter.getCount() <= 0 || !enoughToFilter() || end != getSelectionEnd() || isPhoneQuery()) {
            int tokenEnd = this.mTokenizer.findTokenEnd(editable, start);
            if (editable.length() > tokenEnd + 1) {
                char charAt = editable.charAt(tokenEnd + 1);
                if (charAt == ',' || charAt == ';') {
                    tokenEnd++;
                }
            }
            String text = editable.toString().substring(start, tokenEnd).trim();
            clearComposingText();
            if (text == null || text.length() <= 0 || text.equals(" ")) {
                return false;
            }
            RecipientEntry entry = createTokenizedEntry(text);
            if (entry != null) {
                QwertyKeyListener.markAsReplaced(editable, start, end, "");
                CharSequence chipText = createChip(entry, false);
                if (chipText != null && start > -1 && end > -1) {
                    editable.replace(start, end, chipText);
                }
            }
            if (end == getSelectionEnd()) {
                dismissDropDown();
            }
            sanitizeBetween();
            return true;
        }
        submitItemAtPosition(0);
        dismissDropDown();
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void sanitizeBetween() {
        if (this.mPendingChipsCount <= 0) {
            DrawableRecipientChip[] recips = getSortedRecipients();
            if (recips != null && recips.length > 0) {
                DrawableRecipientChip last = recips[recips.length - 1];
                DrawableRecipientChip beforeLast = null;
                if (recips.length > 1) {
                    beforeLast = recips[recips.length - 2];
                }
                int startLooking = 0;
                int end = getSpannable().getSpanStart(last);
                if (beforeLast != null) {
                    startLooking = getSpannable().getSpanEnd(beforeLast);
                    Editable text = getText();
                    if (startLooking != -1 && startLooking <= text.length() - 1) {
                        if (text.charAt(startLooking) == ' ') {
                            startLooking++;
                        }
                    } else {
                        return;
                    }
                }
                if (startLooking >= 0 && end >= 0 && startLooking < end) {
                    getText().delete(startLooking, end);
                }
            }
        }
    }

    private boolean shouldCreateChip(int start, int end) {
        return !this.mNoChips && hasFocus() && enoughToFilter() && !alreadyHasChip(start, end);
    }

    private boolean alreadyHasChip(int start, int end) {
        if (this.mNoChips) {
            return true;
        }
        DrawableRecipientChip[] chips = (DrawableRecipientChip[]) getSpannable().getSpans(start, end, DrawableRecipientChip.class);
        if (chips == null || chips.length == 0) {
            return false;
        }
        return true;
    }

    private void handleEdit(int start, int end) {
        if (start == -1 || end == -1) {
            dismissDropDown();
            return;
        }
        Editable editable = getText();
        setSelection(end);
        String text = getText().toString().substring(start, end);
        if (!TextUtils.isEmpty(text)) {
            RecipientEntry entry = RecipientEntry.constructFakeEntry(text, isValid(text));
            QwertyKeyListener.markAsReplaced(editable, start, end, "");
            CharSequence chipText = createChip(entry, false);
            int selEnd = getSelectionEnd();
            if (chipText != null && start > -1 && selEnd > -1) {
                editable.replace(start, selEnd, chipText);
            }
        }
        dismissDropDown();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (this.mSelectedChip != null && keyCode == 67) {
            if (this.mAlternatesPopup != null && this.mAlternatesPopup.isShowing()) {
                this.mAlternatesPopup.dismiss();
            }
            removeChip(this.mSelectedChip);
        }
        switch (keyCode) {
            case 23:
            case 66:
                if (event.hasNoModifiers()) {
                    if (commitDefault()) {
                        return true;
                    }
                    if (this.mSelectedChip != null) {
                        clearSelectedChip();
                        return true;
                    } else if (focusNext()) {
                        return true;
                    }
                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    /* access modifiers changed from: 0000 */
    public Spannable getSpannable() {
        return getText();
    }

    /* access modifiers changed from: private */
    public int getChipStart(DrawableRecipientChip chip) {
        return getSpannable().getSpanStart(chip);
    }

    private int getChipEnd(DrawableRecipientChip chip) {
        return getSpannable().getSpanEnd(chip);
    }

    /* access modifiers changed from: protected */
    public void performFiltering(CharSequence text, int keyCode) {
        if (TextUtils.isEmpty(text)) {
            getFilter().filter("", this);
            return;
        }
        boolean isCompletedToken = isCompletedToken(text);
        if (enoughToFilter() && !isCompletedToken) {
            int end = getSelectionEnd();
            DrawableRecipientChip[] chips = (DrawableRecipientChip[]) getSpannable().getSpans(this.mTokenizer.findTokenStart(text, end), end, DrawableRecipientChip.class);
            if (chips != null && chips.length > 0) {
                dismissDropDown();
                return;
            }
        } else if (isCompletedToken) {
            dismissDropDown();
            return;
        }
        super.performFiltering(text, keyCode);
    }

    /* access modifiers changed from: 0000 */
    public boolean isCompletedToken(CharSequence text) {
        if (TextUtils.isEmpty(text)) {
            return false;
        }
        int end = text.length();
        String token = text.toString().substring(this.mTokenizer.findTokenStart(text, end), end).trim();
        if (TextUtils.isEmpty(token)) {
            return false;
        }
        char atEnd = token.charAt(token.length() - 1);
        if (atEnd == ',' || atEnd == ';') {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void clearSelectedChip() {
        if (this.mSelectedChip != null) {
            unselectChip(this.mSelectedChip);
            this.mSelectedChip = null;
        }
        setCursorVisible(true);
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (!isFocused()) {
            return super.onTouchEvent(event);
        }
        boolean handled = super.onTouchEvent(event);
        int action = event.getAction();
        boolean chipWasSelected = false;
        this.mGestureDetector.onTouchEvent(event);
        if (this.mCopyAddress == null && action == 1) {
            float x = event.getX();
            float y = event.getY();
            int offset = putOffsetInRange(x, y);
            DrawableRecipientChip currentChip = findChip(offset);
            if (currentChip != null) {
                if (action == 1) {
                    if (this.mSelectedChip != null && this.mSelectedChip != currentChip) {
                        clearSelectedChip();
                        this.mSelectedChip = selectChip(currentChip);
                    } else if (this.mSelectedChip == null) {
                        setSelection(getText().length());
                        commitDefault();
                        this.mSelectedChip = selectChip(currentChip);
                    } else {
                        onClick(this.mSelectedChip, offset, x, y);
                    }
                }
                chipWasSelected = true;
                handled = true;
            } else if (this.mSelectedChip != null && shouldShowEditableText(this.mSelectedChip)) {
                chipWasSelected = true;
            }
        }
        if (action != 1 || chipWasSelected) {
            return handled;
        }
        clearSelectedChip();
        return handled;
    }

    private void scrollLineIntoView(int line) {
        if (this.mScrollView != null) {
            this.mScrollView.smoothScrollBy(0, calculateOffsetFromBottom(line));
        }
    }

    private void showAlternates(final DrawableRecipientChip currentChip, final ListPopupWindow alternatesPopup, final int width) {
        new AsyncTask<Void, Void, ListAdapter>() {
            /* access modifiers changed from: protected */
            public ListAdapter doInBackground(Void... params) {
                return RecipientEditTextView.this.createAlternatesAdapter(currentChip);
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(ListAdapter result) {
                int bottom;
                if (RecipientEditTextView.this.mAttachedToWindow) {
                    int line = RecipientEditTextView.this.getLayout().getLineForOffset(RecipientEditTextView.this.getChipStart(currentChip));
                    if (line == RecipientEditTextView.this.getLineCount() - 1) {
                        bottom = 0;
                    } else {
                        bottom = -((int) ((RecipientEditTextView.this.mChipHeight + (2.0f * RecipientEditTextView.this.mLineSpacingExtra)) * ((float) Math.abs((RecipientEditTextView.this.getLineCount() - 1) - line))));
                    }
                    alternatesPopup.setWidth(width);
                    alternatesPopup.setAnchorView(RecipientEditTextView.this);
                    alternatesPopup.setVerticalOffset(bottom);
                    alternatesPopup.setAdapter(result);
                    alternatesPopup.setOnItemClickListener(RecipientEditTextView.this.mAlternatesListener);
                    RecipientEditTextView.this.mCheckedItem = -1;
                    alternatesPopup.show();
                    ListView listView = alternatesPopup.getListView();
                    listView.setChoiceMode(1);
                    if (RecipientEditTextView.this.mCheckedItem != -1) {
                        listView.setItemChecked(RecipientEditTextView.this.mCheckedItem, true);
                        RecipientEditTextView.this.mCheckedItem = -1;
                    }
                }
            }
        }.execute(null);
    }

    /* access modifiers changed from: private */
    public ListAdapter createAlternatesAdapter(DrawableRecipientChip chip) {
        return new RecipientAlternatesAdapter(getContext(), chip.getContactId(), chip.getDirectoryId(), chip.getLookupKey(), chip.getDataId(), getAdapter().getQueryType(), this, this.mDropdownChipLayouter);
    }

    private ListAdapter createSingleAddressAdapter(DrawableRecipientChip currentChip) {
        return new SingleRecipientArrayAdapter(getContext(), currentChip.getEntry(), this.mDropdownChipLayouter);
    }

    public void onCheckedItemChanged(int position) {
        ListView listView = this.mAlternatesPopup.getListView();
        if (listView != null && listView.getCheckedItemCount() == 0) {
            listView.setItemChecked(position, true);
        }
        this.mCheckedItem = position;
    }

    private int putOffsetInRange(float x, float y) {
        int offset;
        if (VERSION.SDK_INT >= 14) {
            offset = getOffsetForPosition(x, y);
        } else {
            offset = supportGetOffsetForPosition(x, y);
        }
        return putOffsetInRange(offset);
    }

    private int putOffsetInRange(int o) {
        int offset = o;
        Editable text = getText();
        int length = text.length();
        int realLength = length;
        int i = length - 1;
        while (i >= 0 && text.charAt(i) == ' ') {
            realLength--;
            i--;
        }
        if (offset >= realLength) {
            return offset;
        }
        Editable editable = getText();
        while (offset >= 0 && findText(editable, offset) == -1 && findChip(offset) == null) {
            offset--;
        }
        return offset;
    }

    private static int findText(Editable text, int offset) {
        if (text.charAt(offset) != ' ') {
            return offset;
        }
        return -1;
    }

    private DrawableRecipientChip findChip(int offset) {
        DrawableRecipientChip[] chips = (DrawableRecipientChip[]) getSpannable().getSpans(0, getText().length(), DrawableRecipientChip.class);
        for (DrawableRecipientChip chip : chips) {
            int start = getChipStart(chip);
            int end = getChipEnd(chip);
            if (offset >= start && offset <= end) {
                return chip;
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public String createAddressText(RecipientEntry entry) {
        String trimmedDisplayText;
        String display = entry.getDisplayName();
        String address = entry.getDestination();
        if (TextUtils.isEmpty(display) || TextUtils.equals(display, address)) {
            display = null;
        }
        if (!isPhoneQuery() || !isPhoneNumber(address)) {
            if (address != null) {
                Rfc822Token[] tokenized = Rfc822Tokenizer.tokenize(address);
                if (tokenized != null && tokenized.length > 0) {
                    address = tokenized[0].getAddress();
                }
            }
            trimmedDisplayText = new Rfc822Token(display, address, null).toString().trim();
        } else {
            trimmedDisplayText = address.trim();
        }
        int index = trimmedDisplayText.indexOf(",");
        if (this.mTokenizer == null || TextUtils.isEmpty(trimmedDisplayText) || index >= trimmedDisplayText.length() - 1) {
            return trimmedDisplayText;
        }
        return (String) this.mTokenizer.terminateToken(trimmedDisplayText);
    }

    /* access modifiers changed from: 0000 */
    public String createChipDisplayText(RecipientEntry entry) {
        String display = entry.getDisplayName();
        String address = entry.getDestination();
        if (TextUtils.isEmpty(display) || TextUtils.equals(display, address)) {
            display = null;
        }
        if (!TextUtils.isEmpty(display)) {
            return display;
        }
        if (!TextUtils.isEmpty(address)) {
            return address;
        }
        return new Rfc822Token(display, address, null).toString();
    }

    private CharSequence createChip(RecipientEntry entry, boolean pressed) {
        String displayText = createAddressText(entry);
        if (TextUtils.isEmpty(displayText)) {
            return null;
        }
        int textLength = displayText.length() - 1;
        SpannableString chipText = new SpannableString(displayText);
        if (this.mNoChips) {
            return chipText;
        }
        try {
            DrawableRecipientChip chip = constructChipSpan(entry, pressed, false);
            chipText.setSpan(chip, 0, textLength, 33);
            chip.setOriginalText(chipText.toString());
            return chipText;
        } catch (NullPointerException e) {
            Log.e("RecipientEditTextView", e.getMessage(), e);
            return null;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        if (position >= 0) {
            submitItemAtPosition(position);
            if (this.itemSelectedListener != null) {
                this.itemSelectedListener.onItemSelected();
            }
        }
    }

    private void submitItemAtPosition(int position) {
        submitItem(createValidatedEntry(getAdapter().getItem(position)));
    }

    private void submitItem(RecipientEntry entry) {
        if (entry != null) {
            clearComposingText();
            int end = getSelectionEnd();
            int start = this.mTokenizer.findTokenStart(getText(), end);
            Editable editable = getText();
            QwertyKeyListener.markAsReplaced(editable, start, end, "");
            CharSequence chip = createChip(entry, false);
            if (chip != null && start >= 0 && end >= 0) {
                editable.replace(start, end, chip);
            }
            sanitizeBetween();
        }
    }

    /* access modifiers changed from: private */
    public RecipientEntry createValidatedEntry(RecipientEntry item) {
        if (item == null) {
            return null;
        }
        String destination = item.getDestination();
        if (!isPhoneQuery() && item.getContactId() == -2) {
            return RecipientEntry.constructGeneratedEntry(item.getDisplayName(), destination, item.isValid());
        }
        if (!RecipientEntry.isCreatedRecipient(item.getContactId()) || (!TextUtils.isEmpty(item.getDisplayName()) && !TextUtils.equals(item.getDisplayName(), destination) && (this.mValidator == null || this.mValidator.isValid(destination)))) {
            return item;
        }
        return RecipientEntry.constructFakeEntry(destination, item.isValid());
    }

    /* access modifiers changed from: 0000 */
    public Collection<Long> getContactIds() {
        Set<Long> result = new HashSet<>();
        DrawableRecipientChip[] chips = getSortedRecipients();
        if (chips != null) {
            for (DrawableRecipientChip chip : chips) {
                result.add(Long.valueOf(chip.getContactId()));
            }
        }
        return result;
    }

    /* access modifiers changed from: 0000 */
    public Collection<Long> getDataIds() {
        Set<Long> result = new HashSet<>();
        DrawableRecipientChip[] chips = getSortedRecipients();
        if (chips != null) {
            for (DrawableRecipientChip chip : chips) {
                result.add(Long.valueOf(chip.getDataId()));
            }
        }
        return result;
    }

    public DrawableRecipientChip[] getRecipients() {
        ArrayList<DrawableRecipientChip> recipientsList = new ArrayList<>(Arrays.asList((DrawableRecipientChip[]) getSpannable().getSpans(0, getText().length(), DrawableRecipientChip.class)));
        try {
            recipientsList.addAll(this.mRemovedSpans);
        } catch (Exception e) {
        }
        return (DrawableRecipientChip[]) recipientsList.toArray(new DrawableRecipientChip[recipientsList.size()]);
    }

    public DrawableRecipientChip[] getSortedRecipients() {
        ArrayList<DrawableRecipientChip> recipientsList = new ArrayList<>(Arrays.asList((DrawableRecipientChip[]) getSpannable().getSpans(0, getText().length(), DrawableRecipientChip.class)));
        final Spannable spannable = getSpannable();
        Collections.sort(recipientsList, new Comparator<DrawableRecipientChip>() {
            public int compare(DrawableRecipientChip first, DrawableRecipientChip second) {
                int firstStart = spannable.getSpanStart(first);
                int secondStart = spannable.getSpanStart(second);
                if (firstStart < secondStart) {
                    return -1;
                }
                if (firstStart > secondStart) {
                    return 1;
                }
                return 0;
            }
        });
        return (DrawableRecipientChip[]) recipientsList.toArray(new DrawableRecipientChip[recipientsList.size()]);
    }

    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        return false;
    }

    public void onDestroyActionMode(ActionMode mode) {
    }

    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        return false;
    }

    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        return this.mShowActionMode;
    }

    /* access modifiers changed from: 0000 */
    public ImageSpan getMoreChip() {
        MoreImageSpan[] moreSpans = (MoreImageSpan[]) getSpannable().getSpans(0, getText().length(), MoreImageSpan.class);
        if (moreSpans == null || moreSpans.length <= 0) {
            return null;
        }
        return moreSpans[0];
    }

    private MoreImageSpan createMoreSpan(int count) {
        String moreText = String.format(this.mMoreItem.getText().toString(), new Object[]{Integer.valueOf(count)});
        TextPaint morePaint = new TextPaint(getPaint());
        morePaint.setTextSize(this.mMoreItem.getTextSize());
        morePaint.setColor(this.mMoreItem.getCurrentTextColor());
        int width = ((int) morePaint.measureText(moreText)) + this.mMoreItem.getPaddingLeft() + this.mMoreItem.getPaddingRight();
        int height = getLineHeight();
        Bitmap drawable = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(drawable);
        int adjustedHeight = height;
        Layout layout = getLayout();
        if (layout != null) {
            adjustedHeight -= layout.getLineDescent(0);
        }
        canvas.drawText(moreText, 0, moreText.length(), 0.0f, (float) adjustedHeight, morePaint);
        Drawable result = new BitmapDrawable(getResources(), drawable);
        result.setBounds(0, 0, width, height);
        return new MoreImageSpan(result);
    }

    /* access modifiers changed from: 0000 */
    public void createMoreChipPlainText() {
        Editable text = getText();
        int start = 0;
        int end = 0;
        for (int i = 0; i < 2; i++) {
            end = movePastTerminators(this.mTokenizer.findTokenEnd(text, start));
            start = end;
        }
        MoreImageSpan moreSpan = createMoreSpan(countTokens(text) - 2);
        SpannableString chipText = new SpannableString(text.subSequence(end, text.length()));
        chipText.setSpan(moreSpan, 0, chipText.length(), 33);
        text.replace(end, text.length(), chipText);
        this.mMoreChip = moreSpan;
    }

    /* access modifiers changed from: 0000 */
    public int countTokens(Editable text) {
        int tokenCount = 0;
        int start = 0;
        while (start < text.length()) {
            start = movePastTerminators(this.mTokenizer.findTokenEnd(text, start));
            tokenCount++;
            if (start >= text.length()) {
                break;
            }
        }
        return tokenCount;
    }

    /* access modifiers changed from: 0000 */
    public void createMoreChip() {
        if (this.mNoChips) {
            createMoreChipPlainText();
        } else if (this.mShouldShrink) {
            ImageSpan[] tempMore = (ImageSpan[]) getSpannable().getSpans(0, getText().length(), MoreImageSpan.class);
            if (tempMore.length > 0) {
                getSpannable().removeSpan(tempMore[0]);
            }
            DrawableRecipientChip[] recipients = getSortedRecipients();
            if (recipients == null || recipients.length <= 2) {
                this.mMoreChip = null;
                return;
            }
            Spannable spannable = getSpannable();
            int numRecipients = recipients.length;
            int overage = numRecipients - 2;
            MoreImageSpan moreSpan = createMoreSpan(overage);
            this.mRemovedSpans = new ArrayList();
            int totalReplaceStart = 0;
            int totalReplaceEnd = 0;
            Editable text = getText();
            for (int i = numRecipients - overage; i < recipients.length; i++) {
                this.mRemovedSpans.add(recipients[i]);
                if (i == numRecipients - overage) {
                    totalReplaceStart = spannable.getSpanStart(recipients[i]);
                }
                if (i == recipients.length - 1) {
                    totalReplaceEnd = spannable.getSpanEnd(recipients[i]);
                }
                if (this.mTemporaryRecipients == null || !this.mTemporaryRecipients.contains(recipients[i])) {
                    recipients[i].setOriginalText(text.toString().substring(spannable.getSpanStart(recipients[i]), spannable.getSpanEnd(recipients[i])));
                }
                spannable.removeSpan(recipients[i]);
            }
            if (totalReplaceEnd < text.length()) {
                totalReplaceEnd = text.length();
            }
            int end = Math.max(totalReplaceStart, totalReplaceEnd);
            int start = Math.min(totalReplaceStart, totalReplaceEnd);
            SpannableString chipText = new SpannableString(text.subSequence(start, end));
            chipText.setSpan(moreSpan, 0, chipText.length(), 33);
            text.replace(start, end, chipText);
            this.mMoreChip = moreSpan;
            if (!isPhoneQuery() && getLineCount() > this.mMaxLines) {
                setMaxLines(getLineCount());
            }
        }
    }

    public void removeMoreChip() {
        if (this.mMoreChip != null) {
            Spannable span = getSpannable();
            span.removeSpan(this.mMoreChip);
            this.mMoreChip = null;
            if (this.mRemovedSpans != null && this.mRemovedSpans.size() > 0) {
                DrawableRecipientChip[] recipients = getSortedRecipients();
                if (recipients != null && recipients.length != 0) {
                    int end = span.getSpanEnd(recipients[recipients.length - 1]);
                    Editable editable = getText();
                    Iterator it = this.mRemovedSpans.iterator();
                    while (it.hasNext()) {
                        DrawableRecipientChip chip = (DrawableRecipientChip) it.next();
                        String token = (String) chip.getOriginalText();
                        int chipStart = editable.toString().indexOf(token, end);
                        int chipEnd = Math.min(editable.length(), token.length() + chipStart);
                        end = chipEnd;
                        if (chipStart != -1) {
                            editable.setSpan(chip, chipStart, chipEnd, 33);
                        }
                    }
                    this.mRemovedSpans.clear();
                }
            }
        }
    }

    private DrawableRecipientChip selectChip(DrawableRecipientChip currentChip) {
        if (shouldShowEditableText(currentChip)) {
            CharSequence text = currentChip.getValue();
            Editable editable = getText();
            Spannable spannable = getSpannable();
            int spanStart = spannable.getSpanStart(currentChip);
            int spanEnd = spannable.getSpanEnd(currentChip);
            spannable.removeSpan(currentChip);
            editable.delete(spanStart, spanEnd + 1);
            setCursorVisible(true);
            setSelection(editable.length());
            editable.append(text);
            return constructChipSpan(RecipientEntry.constructFakeEntry((String) text, isValid(text.toString())), true, false);
        } else if (currentChip.getContactId() == -2) {
            int start = getChipStart(currentChip);
            int end = getChipEnd(currentChip);
            getSpannable().removeSpan(currentChip);
            try {
                if (this.mNoChips) {
                    return null;
                }
                DrawableRecipientChip newChip = constructChipSpan(currentChip.getEntry(), true, false);
                Editable editable2 = getText();
                QwertyKeyListener.markAsReplaced(editable2, start, end, "");
                if (start == -1 || end == -1) {
                    Log.d("RecipientEditTextView", "The chip being selected no longer exists but should.");
                } else {
                    editable2.setSpan(newChip, start, end, 33);
                }
                newChip.setSelected(true);
                if (shouldShowEditableText(newChip)) {
                    scrollLineIntoView(getLayout().getLineForOffset(getChipStart(newChip)));
                }
                showAddress(newChip, this.mAddressPopup, getWidth());
                setCursorVisible(false);
                return newChip;
            } catch (NullPointerException e) {
                Log.e("RecipientEditTextView", e.getMessage(), e);
                return null;
            }
        } else {
            int start2 = getChipStart(currentChip);
            int end2 = getChipEnd(currentChip);
            getSpannable().removeSpan(currentChip);
            try {
                DrawableRecipientChip newChip2 = constructChipSpan(currentChip.getEntry(), true, false);
                Editable editable3 = getText();
                QwertyKeyListener.markAsReplaced(editable3, start2, end2, "");
                if (start2 == -1 || end2 == -1) {
                    Log.d("RecipientEditTextView", "The chip being selected no longer exists but should.");
                } else {
                    editable3.setSpan(newChip2, start2, end2, 33);
                }
                newChip2.setSelected(true);
                if (shouldShowEditableText(newChip2)) {
                    scrollLineIntoView(getLayout().getLineForOffset(getChipStart(newChip2)));
                }
                showAlternates(newChip2, this.mAlternatesPopup, getWidth());
                setCursorVisible(false);
                return newChip2;
            } catch (NullPointerException e2) {
                Log.e("RecipientEditTextView", e2.getMessage(), e2);
                return null;
            }
        }
    }

    private boolean shouldShowEditableText(DrawableRecipientChip currentChip) {
        long contactId = currentChip.getContactId();
        return contactId == -1 || (!isPhoneQuery() && contactId == -2);
    }

    private void showAddress(final DrawableRecipientChip currentChip, final ListPopupWindow popup, int width) {
        if (this.mAttachedToWindow) {
            int bottom = calculateOffsetFromBottom(getLayout().getLineForOffset(getChipStart(currentChip)));
            popup.setWidth(width);
            popup.setAnchorView(this);
            popup.setVerticalOffset(bottom);
            popup.setAdapter(createSingleAddressAdapter(currentChip));
            popup.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    RecipientEditTextView.this.unselectChip(currentChip);
                    popup.dismiss();
                }
            });
            popup.show();
            ListView listView = popup.getListView();
            listView.setChoiceMode(1);
            listView.setItemChecked(0, true);
        }
    }

    /* access modifiers changed from: private */
    public void unselectChip(DrawableRecipientChip chip) {
        int start = getChipStart(chip);
        int end = getChipEnd(chip);
        Editable editable = getText();
        this.mSelectedChip = null;
        if (start == -1 || end == -1) {
            Log.w("RecipientEditTextView", "The chip doesn't exist or may be a chip a user was editing");
            setSelection(editable.length());
            commitDefault();
        } else {
            getSpannable().removeSpan(chip);
            QwertyKeyListener.markAsReplaced(editable, start, end, "");
            editable.removeSpan(chip);
            try {
                if (!this.mNoChips) {
                    editable.setSpan(constructChipSpan(chip.getEntry(), false, false), start, end, 33);
                }
            } catch (NullPointerException e) {
                Log.e("RecipientEditTextView", e.getMessage(), e);
            }
        }
        setCursorVisible(true);
        setSelection(editable.length());
        if (this.mAlternatesPopup != null && this.mAlternatesPopup.isShowing()) {
            this.mAlternatesPopup.dismiss();
        }
    }

    private boolean isInDelete(DrawableRecipientChip chip, int offset, float x, float y) {
        if (this.mDisableDelete || !chip.isSelected()) {
            return false;
        }
        if ((this.mAvatarPosition != 0 || offset != getChipEnd(chip)) && (this.mAvatarPosition == 0 || offset != getChipStart(chip))) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public void removeChip(DrawableRecipientChip chip) {
        Spannable spannable = getSpannable();
        int spanStart = spannable.getSpanStart(chip);
        int spanEnd = spannable.getSpanEnd(chip);
        Editable text = getText();
        int toDelete = spanEnd;
        boolean wasSelected = chip == this.mSelectedChip;
        if (wasSelected) {
            this.mSelectedChip = null;
        }
        while (toDelete >= 0 && toDelete < text.length() && text.charAt(toDelete) == ' ') {
            toDelete++;
        }
        spannable.removeSpan(chip);
        if (spanStart >= 0 && toDelete > 0) {
            text.delete(spanStart, toDelete);
        }
        if (wasSelected) {
            clearSelectedChip();
        }
    }

    /* access modifiers changed from: 0000 */
    public void replaceChip(DrawableRecipientChip chip, RecipientEntry entry) {
        boolean wasSelected;
        if (chip == this.mSelectedChip) {
            wasSelected = true;
        } else {
            wasSelected = false;
        }
        if (wasSelected) {
            this.mSelectedChip = null;
        }
        int start = getChipStart(chip);
        int end = getChipEnd(chip);
        getSpannable().removeSpan(chip);
        Editable editable = getText();
        CharSequence chipText = createChip(entry, false);
        if (chipText != null) {
            if (start == -1 || end == -1) {
                Log.e("RecipientEditTextView", "The chip to replace does not exist but should.");
                editable.insert(0, chipText);
            } else if (!TextUtils.isEmpty(chipText)) {
                int toReplace = end;
                while (toReplace >= 0 && toReplace < editable.length() && editable.charAt(toReplace) == ' ') {
                    toReplace++;
                }
                editable.replace(start, toReplace, chipText);
            }
        }
        setCursorVisible(true);
        if (wasSelected) {
            clearSelectedChip();
        }
    }

    public void onClick(DrawableRecipientChip chip, int offset, float x, float y) {
        if (!chip.isSelected()) {
            return;
        }
        if (isInDelete(chip, offset, x, y)) {
            removeChip(chip);
        } else {
            clearSelectedChip();
        }
    }

    /* access modifiers changed from: private */
    public boolean chipsPending() {
        return this.mPendingChipsCount > 0 || (this.mRemovedSpans != null && this.mRemovedSpans.size() > 0);
    }

    public void removeTextChangedListener(TextWatcher watcher) {
        this.mTextWatcher = null;
        super.removeTextChangedListener(watcher);
    }

    public boolean lastCharacterIsCommitCharacter(CharSequence s) {
        char last;
        int end = getSelectionEnd() == 0 ? 0 : getSelectionEnd() - 1;
        int len = length() - 1;
        if (end != len) {
            last = s.charAt(end);
        } else {
            last = s.charAt(len);
        }
        return last == ',' || last == ';';
    }

    public boolean isGeneratedContact(DrawableRecipientChip chip) {
        long contactId = chip.getContactId();
        return contactId == -1 || (!isPhoneQuery() && contactId == -2);
    }

    private void handlePasteClip(ClipData clip) {
        removeTextChangedListener(this.mTextWatcher);
        if (clip != null && clip.getDescription().hasMimeType("text/plain")) {
            for (int i = 0; i < clip.getItemCount(); i++) {
                CharSequence paste = clip.getItemAt(i).getText();
                if (paste != null) {
                    int start = getSelectionStart();
                    int end = getSelectionEnd();
                    Editable editable = getText();
                    if (start < 0 || end < 0 || start == end) {
                        editable.insert(end, paste);
                    } else {
                        editable.replace(start, end, paste);
                    }
                    handlePasteAndReplace();
                }
            }
        }
        this.mHandler.post(this.mAddTextWatcher);
    }

    public boolean onTextContextMenuItem(int id) {
        if (id != 16908322) {
            return super.onTextContextMenuItem(id);
        }
        handlePasteClip(((ClipboardManager) getContext().getSystemService("clipboard")).getPrimaryClip());
        return true;
    }

    private void handlePasteAndReplace() {
        ArrayList<DrawableRecipientChip> created = handlePaste();
        if (created != null && created.size() > 0) {
            new IndividualReplacementTask().execute(new ArrayList[]{created});
        }
    }

    /* access modifiers changed from: 0000 */
    public ArrayList<DrawableRecipientChip> handlePaste() {
        String text = getText().toString();
        int originalTokenStart = this.mTokenizer.findTokenStart(text, getSelectionEnd());
        String lastAddress = text.substring(originalTokenStart);
        int tokenStart = originalTokenStart;
        int prevTokenStart = 0;
        DrawableRecipientChip findChip = null;
        ArrayList<DrawableRecipientChip> created = new ArrayList<>();
        if (tokenStart != 0) {
            while (tokenStart != 0 && findChip == null && tokenStart != prevTokenStart) {
                prevTokenStart = tokenStart;
                tokenStart = this.mTokenizer.findTokenStart(text, tokenStart);
                findChip = findChip(tokenStart);
                if (tokenStart == originalTokenStart && findChip == null) {
                    break;
                }
            }
            if (tokenStart != originalTokenStart) {
                if (findChip != null) {
                    tokenStart = prevTokenStart;
                }
                while (tokenStart < originalTokenStart) {
                    commitChip(tokenStart, movePastTerminators(this.mTokenizer.findTokenEnd(getText().toString(), tokenStart)), getText());
                    DrawableRecipientChip createdChip = findChip(tokenStart);
                    if (createdChip == null) {
                        break;
                    }
                    tokenStart = getSpannable().getSpanEnd(createdChip) + 1;
                    created.add(createdChip);
                }
            }
        }
        if (isCompletedToken(lastAddress)) {
            Editable editable = getText();
            int tokenStart2 = editable.toString().indexOf(lastAddress, originalTokenStart);
            commitChip(tokenStart2, editable.length(), editable);
            created.add(findChip(tokenStart2));
        }
        return created;
    }

    /* access modifiers changed from: 0000 */
    public int movePastTerminators(int tokenEnd) {
        if (tokenEnd >= length()) {
            return tokenEnd;
        }
        char atEnd = getText().toString().charAt(tokenEnd);
        if (atEnd == ',' || atEnd == ';') {
            tokenEnd++;
        }
        if (tokenEnd < length() && getText().toString().charAt(tokenEnd) == ' ') {
            tokenEnd++;
        }
        return tokenEnd;
    }

    public boolean onDown(MotionEvent e) {
        return false;
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    public void onLongPress(MotionEvent event) {
        DrawableRecipientChip currentChip = findChip(putOffsetInRange(event.getX(), event.getY()));
        if (currentChip == null) {
            this.mShowActionMode = true;
        } else if (this.mDragEnabled) {
            this.mShowActionMode = false;
            startDrag(currentChip);
        } else {
            this.mShowActionMode = false;
            showCopyDialog(currentChip.getEntry().getDestination());
        }
    }

    private int supportGetOffsetForPosition(float x, float y) {
        if (getLayout() == null) {
            return -1;
        }
        return supportGetOffsetAtCoordinate(supportGetLineAtCoordinate(y), x);
    }

    private float supportConvertToLocalHorizontalCoordinate(float x) {
        return Math.min((float) ((getWidth() - getTotalPaddingRight()) - 1), Math.max(0.0f, x - ((float) getTotalPaddingLeft()))) + ((float) getScrollX());
    }

    private int supportGetLineAtCoordinate(float y) {
        return getLayout().getLineForVertical((int) (Math.min((float) ((getHeight() - getTotalPaddingBottom()) - 1), Math.max(0.0f, y - ((float) getTotalPaddingLeft()))) + ((float) getScrollY())));
    }

    private int supportGetOffsetAtCoordinate(int line, float x) {
        return getLayout().getOffsetForHorizontal(line, supportConvertToLocalHorizontalCoordinate(x));
    }

    private void startDrag(DrawableRecipientChip currentChip) {
        String address = currentChip.getEntry().getDestination();
        startDrag(ClipData.newPlainText(address, address + ','), new RecipientChipShadow(currentChip), null, 0);
        removeChip(currentChip);
    }

    public boolean onDragEvent(DragEvent event) {
        switch (event.getAction()) {
            case 1:
                return event.getClipDescription().hasMimeType("text/plain");
            case 3:
                handlePasteClip(event.getClipData());
                return true;
            case 5:
                requestFocus();
                return true;
            default:
                return false;
        }
    }

    private void showCopyDialog(String address) {
        int btnTitleId;
        if (this.mAttachedToWindow) {
            this.mCopyAddress = address;
            this.mCopyDialog.setTitle(address);
            this.mCopyDialog.setContentView(this.mCopyDialogLayoutId);
            this.mCopyDialog.setCancelable(true);
            this.mCopyDialog.setCanceledOnTouchOutside(true);
            Button button = (Button) this.mCopyDialog.findViewById(16908313);
            button.setOnClickListener(this);
            if (isPhoneQuery()) {
                btnTitleId = R.string.copy_number;
            } else {
                btnTitleId = R.string.copy_email;
            }
            button.setText(getContext().getResources().getString(btnTitleId));
            this.mCopyDialog.setOnDismissListener(this);
            this.mCopyDialog.show();
        }
    }

    public void setCopyDialogLayout(int layoutResId) {
        this.mCopyDialogLayoutId = layoutResId;
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    public void onShowPress(MotionEvent e) {
    }

    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    public void onDismiss(DialogInterface dialog) {
        this.mCopyAddress = null;
    }

    public void onClick(View v) {
        ((ClipboardManager) getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("", this.mCopyAddress));
        this.mCopyDialog.dismiss();
    }

    /* access modifiers changed from: protected */
    public boolean isPhoneQuery() {
        if (getAdapter() == null || getAdapter().getQueryType() != 1) {
            return false;
        }
        return true;
    }

    public BaseRecipientAdapter getAdapter() {
        return (BaseRecipientAdapter) super.getAdapter();
    }

    public void dismissDropDown() {
        if (this.mDismissPopupOnClick) {
            super.dismissDropDown();
        }
    }

    public void setPostSelectedAction(ItemSelectedListener listener) {
        this.itemSelectedListener = listener;
    }
}
