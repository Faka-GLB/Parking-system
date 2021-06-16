package com.onboarding.parkingsystemjava.mvp.view.base;

import android.content.Context;
import androidx.fragment.app.DialogFragment;
import java.lang.ref.WeakReference;

public class FragmentView {
    private final WeakReference<DialogFragment> fragmentRef;

    public FragmentView(DialogFragment dialogFragment) {
        fragmentRef = new WeakReference<>(dialogFragment);
    }

    public DialogFragment getFragment() {
        return fragmentRef.get();
    }

    public Context getContext() {
        return getFragment().getContext();
    }
}
