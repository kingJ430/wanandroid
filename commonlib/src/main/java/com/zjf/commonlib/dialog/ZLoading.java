package com.zjf.commonlib.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import com.zjf.commonlib.R;


public class ZLoading extends ProgressDialog{
    private boolean mCancelable;
    private int mMsgId;

    public ZLoading(Context pContext) {
        super(pContext, R.style.loadDialog);
    }

    public ZLoading(Context pContext, int pTheme) {
        super(pContext, R.style.loadDialog);
    }

    public ZLoading(Context pContext, boolean pCancelable) {
        super(pContext, R.style.loadDialog);
        mCancelable = pCancelable;
        mMsgId = R.string.common_default_loading_message;
    }

    public ZLoading(Context pContext, boolean pCancelable, int pMsgId) {
        super(pContext, R.style.loadDialog);
        mCancelable = pCancelable;
        this.mMsgId = pMsgId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog_loading_layout);
        TextView _message = (TextView) findViewById(R.id.tv_message);
        _message.setText(mMsgId);
        setCancelable(mCancelable);
    }
}
