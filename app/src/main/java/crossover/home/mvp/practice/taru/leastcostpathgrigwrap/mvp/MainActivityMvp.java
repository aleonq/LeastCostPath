package crossover.home.mvp.practice.taru.leastcostpathgrigwrap.mvp;

import android.text.SpannableString;

/**
 * Created by taru on 1/30/2017.
 */

public interface MainActivityMvp {
    interface Model {
        //nothing here
    }

    interface View {
        void setResultGrid(CharSequence res);
        void appendToResultGrid(SpannableString row);

        void setResult(String s);

        void showErrorMsg(String msg);
    }

    interface Presenter {
        void processGridMatrix(String gridStr);

        void setView(View v);

    }
}
