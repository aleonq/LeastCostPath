package crossover.home.mvp.practice.taru.leastcostpathgrigwrap.mvp.views;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import crossover.home.mvp.practice.taru.leastcostpathgrigwrap.GridApp;
import crossover.home.mvp.practice.taru.leastcostpathgrigwrap.R;
import crossover.home.mvp.practice.taru.leastcostpathgrigwrap.mvp.MainActivityMvp;

public class MainActivity extends AppCompatActivity implements MainActivityMvp.View, View.OnClickListener {

    @BindView(R.id.et_grid)
    EditText etGrid;

    @BindView(R.id.tv_result)
    TextView tvResult;

    @BindView(R.id.btn_solve)
    Button btnSolve;

    @BindView(R.id.tv_grid)
    TextView tvGrid;

    @Inject
    MainActivityMvp.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((GridApp) getApplication()).getComponent().inject(this);
        btnSolve.setOnClickListener(this);
        presenter.setView(this);
    }

    @Override
    public void setResultGrid(CharSequence res) {
        tvGrid.setText(res);
    }

    @Override
    public void appendToResultGrid(SpannableString row) {
        tvGrid.append(row);
    }

    @Override
    public void setResult(String s) {
        tvResult.setTextColor(Color.GREEN);
        tvResult.setText(s);
    }

//    @OnClick(R.id.btn_solve)
//    void onClick() {
//        presenter.processGridMatrix(etGrid.getText().toString());
//    }

    @Override
    public void showErrorMsg(String msg) {
        tvResult.setTextColor(Color.RED);
        tvResult.setText(msg);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_solve) {
            presenter.processGridMatrix(etGrid.getText().toString());
        }
    }
}
