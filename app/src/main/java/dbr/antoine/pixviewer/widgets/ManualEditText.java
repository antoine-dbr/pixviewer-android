package dbr.antoine.pixviewer.widgets;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * Inhibit the TextWatcher when setting the text programmatically (to avoid an infinite loop with Redux).
 * Created by antoine on 7/8/17.
 */

public class ManualEditText extends AppCompatEditText {

    private boolean blockCallbacks;
    private Handler handler;

    public ManualEditText(Context context) {
        super(context);
    }

    public ManualEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ManualEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        blockCallbacks = true;
        super.setText(text, type);

        initHandler();
        handler.post(() -> {
            setSelection(getText().length());
            blockCallbacks = false;
        });
    }

    private void initHandler() {
        if (handler == null) {
            handler = new Handler();
        }
    }

    @Override
    public void addTextChangedListener(final TextWatcher watcher) {
        super.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (allow()) {
                    watcher.beforeTextChanged(charSequence, i, i1, i2);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (allow()) {
                    watcher.onTextChanged(charSequence, i, i1, i2);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (allow()) {
                    watcher.afterTextChanged(editable);
                }
            }

            private boolean allow() {
                return !(blockCallbacks || watcher == null);
            }
        });
    }
}
