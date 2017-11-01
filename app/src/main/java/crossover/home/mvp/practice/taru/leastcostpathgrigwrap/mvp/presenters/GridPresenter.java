package crossover.home.mvp.practice.taru.leastcostpathgrigwrap.mvp.presenters;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import javax.inject.Inject;

import crossover.home.mvp.practice.taru.leastcostpathgrigwrap.workers.NodeGridrocessor;
import crossover.home.mvp.practice.taru.leastcostpathgrigwrap.mvp.MainActivityMvp;

/**
 * Created by taru on 1/30/2017.
 */

public class GridPresenter implements MainActivityMvp.Presenter {

    MainActivityMvp.Model model;

    @Inject
    MainActivityMvp.View view;

    public void processGridMatrix(String gridStr) {
        int[][] gridMatrix;
        if (isValid(gridStr)) {
            gridMatrix = getGridmatrix(gridStr);
            String result = NodeGridrocessor.main(gridMatrix);
            String results[] = result.split("\\r?\\n");
            StringBuffer isEffecient = new StringBuffer();
            if (Integer.parseInt(results[0]) > 50) {
                isEffecient.append("No\n");
                view.showErrorMsg(isEffecient + result);
                setGridResult(gridStr, results[1], Color.RED);
            } else {
                isEffecient.append("Yes\n");
                view.setResult(isEffecient + result);
                setGridResult(gridStr, results[1], Color.GREEN);
            }
        } else {
            view.showErrorMsg("Invalid grid data!");
        }
    }

    private void setGridResult(String grid, String res, int color) {
        view.setResultGrid("");
        res = res.trim();
        String[] indices = res.split(" ");
        SpannableString spannableString;
        String[] rows = grid.split("\\r?\\n");
        SpannableString[] spannableStrings = new SpannableString[rows.length];
        for (int i = 0; i < rows.length; i++) {
            spannableStrings[i] = new SpannableString(rows[i] + " \n");
        }
        int index;
        for (int i = 0; i < indices.length; i++) {
            int rowIndex = Integer.parseInt(indices[i]);
            String row = rows[rowIndex] + " \n";
            index = i * 2;
            spannableStrings[rowIndex].setSpan(new ForegroundColorSpan(color),
                    index, row.indexOf(" ", index), 0);
            spannableStrings[rowIndex].setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC),
                    index, row.indexOf(" ", index), 0);
        }
        for (int i = 0; i < spannableStrings.length; i++) {
            view.appendToResultGrid(spannableStrings[i]);
        }
    }

    @Override
    public void setView(MainActivityMvp.View v) {
        this.view = v;
    }

    public boolean isValid(String grid) {
        if (TextUtils.isEmpty(grid)) return false;
        return true;
    }

    public int[][] getGridmatrix(String gridStr) {
        String lines[] = gridStr.split("\\r?\\n");
        int lt = lines[0].split(" ").length;
        int[][] grid = new int[lines.length][lt];
        int i = 0;
        for (String line : lines) {
            String[] row = line.split(" ");
            int j = 0;
            for (String s : row) {
                grid[i][j] = Integer.parseInt(s);
                j++;
            }
            i++;
        }
        return grid;
    }
}
