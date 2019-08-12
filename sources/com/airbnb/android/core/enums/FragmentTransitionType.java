package com.airbnb.android.core.enums;

import com.airbnb.android.core.C0716R;

public enum FragmentTransitionType {
    None(0, 0, 0, 0),
    SlideInFromSide(C0716R.anim.fragment_enter, C0716R.anim.fragment_exit, C0716R.anim.fragment_enter_pop, C0716R.anim.fragment_exit_pop),
    SlideInFromSidePop(C0716R.anim.fragment_enter_pop, C0716R.anim.fragment_exit_pop, 0, 0),
    SlideFromBottom(C0716R.anim.enter_bottom, C0716R.anim.fragment_slide_delay, 0, C0716R.anim.exit_bottom),
    FadeInAndOut(C0716R.anim.n2_fade_in_medium, C0716R.anim.n2_fade_out_medium, C0716R.anim.n2_fade_in_medium, C0716R.anim.n2_fade_out_medium);
    
    public final int enter;
    public final int exit;
    public final int popEnter;
    public final int popExit;

    private FragmentTransitionType(int enter2, int exit2, int popEnter2, int popExit2) {
        this.enter = enter2;
        this.exit = exit2;
        this.popEnter = popEnter2;
        this.popExit = popExit2;
    }
}
