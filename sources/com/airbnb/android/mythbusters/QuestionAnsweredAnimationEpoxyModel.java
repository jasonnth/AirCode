package com.airbnb.android.mythbusters;

import android.view.View;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieAnimationView.CacheStrategy;
import com.airbnb.p027n2.epoxy.AirEpoxyModelWithHolder;
import com.airbnb.p027n2.epoxy.AirViewHolder;

public abstract class QuestionAnsweredAnimationEpoxyModel extends AirEpoxyModelWithHolder<Holder> {
    private final String LOTTIE_FILE_CORRECT = "correct.json";
    private final String LOTTIE_FILE_INCORRECT = "incorrect.json";
    boolean questionAnsweredCorrectly;

    static final class Holder extends AirViewHolder {
        @BindView
        LottieAnimationView lottieAnimationView;

        Holder() {
        }
    }

    public final class Holder_ViewBinding implements Unbinder {
        private Holder target;

        public Holder_ViewBinding(Holder target2, View source) {
            this.target = target2;
            target2.lottieAnimationView = (LottieAnimationView) Utils.findRequiredViewAsType(source, C7485R.C7487id.lottie_animation, "field 'lottieAnimationView'", LottieAnimationView.class);
        }

        public void unbind() {
            Holder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.lottieAnimationView = null;
        }
    }

    public void bind(Holder viewHolder) {
        super.bind(viewHolder);
        viewHolder.lottieAnimationView.setAnimation(this.questionAnsweredCorrectly ? "correct.json" : "incorrect.json", CacheStrategy.Weak);
        viewHolder.lottieAnimationView.loop(false);
    }

    public void unbind(Holder holder) {
        super.unbind(holder);
        holder.lottieAnimationView.cancelAnimation();
    }
}
