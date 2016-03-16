package com.example.chatviewdemo.sandboxfragment;

import java.text.DecimalFormat;

import android.content.Intent;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.db.chart.Tools;
import com.db.chart.model.BarSet;
import com.db.chart.model.LineSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.BarChartView;
import com.db.chart.view.BaseBarChartView;
import com.db.chart.view.BaseStackBarChartView;
import com.db.chart.view.ChartView;
import com.db.chart.view.HorizontalBarChartView;
import com.db.chart.view.HorizontalStackBarChartView;
import com.db.chart.view.LineChartView;
import com.db.chart.view.StackBarChartView;
import com.db.chart.view.animation.Animation;
import com.db.chart.view.animation.easing.BaseEasingMethod;
import com.db.chart.view.animation.easing.BounceEase;
import com.db.chart.view.animation.easing.CircEase;
import com.db.chart.view.animation.easing.CubicEase;
import com.db.chart.view.animation.easing.ElasticEase;
import com.db.chart.view.animation.easing.ExpoEase;
import com.db.chart.view.animation.easing.LinearEase;
import com.db.chart.view.animation.easing.QuadEase;
import com.db.chart.view.animation.easing.QuartEase;
import com.db.chart.view.animation.easing.QuintEase;
import com.db.chart.view.animation.easing.SineEase;
import com.example.chatviewdemo.R;
import com.example.chatviewdemo.SandboxFragment;

public class SandBoxPlayFragment extends BaseSandBoxFragment {

	private View mLayout;
	private static ImageButton mPlayBtn;
	/** Labels and values */
	private final String[] mLabels = { "W", "I", "L", "L", "I", "A", "M" };
	private final float[] mValues = { 2.5f, 3.7f, 4f, 8f, 4.5f, 4f, 5f };
	private Paint mGridPaint;
	private BaseEasingMethod mEasing;

	/** Animation end action */
	private static final Runnable mEndAction = new Runnable() {
		@Override
		public void run() {
			mPlayBtn.setEnabled(true);
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mLayout = inflater.inflate(R.layout.sandbox_play, container, false);
		SandboxFragment.mChartId = R.id.sandbox_chart_line;
		mEasing = new CubicEase();
		mPlayBtn = (ImageButton) mLayout.findViewById(R.id.sandbox_play_play);
		return mLayout;
	}

	@Override
	public void onStateRecover() {
		mLayout.findViewById(SandboxFragment.mChartId).setVisibility(
				View.VISIBLE);
	}

	public void onPlay(View view) {
		if (view.getId() == R.id.sandbox_play_play) {
			generateChart();
		} else {
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, buildCode());
			sendIntent.setType("text/plain");
			startActivity(Intent.createChooser(sendIntent, "Send code"));
		}
	}

	private void generateChart() {
		mPlayBtn.setEnabled(false);
		switch (SandboxFragment.mChartId) {
		case R.id.sandbox_chart_line:
			buildChart(buildLineChart((LineChartView) SandboxFragment.mCurrFragment
					.getView().findViewById(SandboxFragment.mChartId)));
			break;
		case R.id.sandbox_chart_bar:
			buildChart(buildBarChart((BarChartView) SandboxFragment.mCurrFragment
					.getView().findViewById(SandboxFragment.mChartId)));
			break;
		case R.id.sandbox_chart_horbar:
			buildChart(buildBarChart((HorizontalBarChartView) SandboxFragment.mCurrFragment
					.getView().findViewById(SandboxFragment.mChartId)));
			break;
		case R.id.sandbox_chart_stacked:
			buildChart(buildStackedChart((StackBarChartView) SandboxFragment.mCurrFragment
					.getView().findViewById(SandboxFragment.mChartId)));
			break;
		case R.id.sandbox_chart_horstacked:
			buildChart(buildStackedChart((HorizontalStackBarChartView) SandboxFragment.mCurrFragment
					.getView().findViewById(SandboxFragment.mChartId)));
			break;
		default:
			buildChart(buildLineChart((LineChartView) SandboxFragment.mCurrFragment
					.getView().findViewById(SandboxFragment.mChartId)));
		}
	}

	private ChartView buildLineChart(LineChartView chart) {
		chart.reset();
		LineSet dataset = new LineSet(mLabels, mValues);
		if (SandboxFragment.mIsLineDashed)
			dataset.setDashed(SandboxFragment.mLineDashType);
		dataset.setSmooth(SandboxFragment.mIsLineSmooth)
				.setThickness(Tools.fromDpToPx(SandboxFragment.mLineThickness))
				.setColor(SandboxFragment.mLineColorId);

		if (SandboxFragment.mPointsSizeId != -1)
			dataset.setDotsRadius(Tools.fromDpToPx(SandboxFragment.mPointsSize))
					.setDotsColor(SandboxFragment.mPointColorId);
		chart.addData(dataset);

		return chart;
	}

	private void buildChart(ChartView chart) {
		mGridPaint = new Paint();
		mGridPaint.setColor(SandboxFragment.mGridColorId);
		mGridPaint.setStyle(Paint.Style.STROKE);
		mGridPaint.setAntiAlias(true);
		mGridPaint.setStrokeWidth(Tools
				.fromDpToPx(SandboxFragment.mGridThickness));
		if (SandboxFragment.mIsGridDashed)
			mGridPaint.setPathEffect(new DashPathEffect(
					SandboxFragment.mGridDashType, 0));

		chart.setXAxis(SandboxFragment.mHasXAxis)
				.setXLabels(SandboxFragment.mXLabelPosition)
				.setYAxis(SandboxFragment.mHasYAxis)
				.setYLabels(SandboxFragment.mYLabelPosition)
				.setLabelsColor(SandboxFragment.mLabelColorId)
				.setAxisColor(SandboxFragment.mAxisColorId);

		if (SandboxFragment.mGridType != null)
			chart.setGrid(SandboxFragment.mGridType, mGridPaint);

		chart.setLabelsFormat(new DecimalFormat("#"
				+ SandboxFragment.mLabelFormat));

		chart.show(buildAnimation());
	}

	private Animation buildAnimation() {
		switch (SandboxFragment.mEasingId) {
		case 0:
			mEasing = new CubicEase();
			break;
		case 1:
			mEasing = new QuartEase();
			break;
		case 2:
			mEasing = new QuintEase();
			break;
		case 3:
			mEasing = new BounceEase();
			break;
		case 4:
			mEasing = new ElasticEase();
			break;
		case 5:
			mEasing = new ExpoEase();
			break;
		case 6:
			mEasing = new CircEase();
			break;
		case 7:
			mEasing = new QuadEase();
			break;
		case 8:
			mEasing = new SineEase();
			break;
		case 9:
			mEasing = new LinearEase();
			break;
		default:
			mEasing = new CubicEase();
		}

		return new Animation(SandboxFragment.mDuration)
				.setAlpha(SandboxFragment.mAlpha)
				.setEasing(mEasing)
				.setOverlap(SandboxFragment.mOverlapFactor,
						SandboxFragment.mOverlapOrder)
				.setStartPoint(SandboxFragment.mStartX, SandboxFragment.mStartY)
				.setEndAction(mEndAction);
	}

	private ChartView buildBarChart(BaseBarChartView chart) {
		chart.reset();
		BarSet dataset = new BarSet(mLabels, mValues);
		dataset.setColor(SandboxFragment.mBarColorId);
		chart.addData(dataset);

		chart.setBarSpacing(SandboxFragment.mBarSpacing);
		if (SandboxFragment.mHasBarBackground)
			chart.setBarBackgroundColor(SandboxFragment.mBarBackgroundColorId);
		chart.setRoundCorners(SandboxFragment.mBarCornersSize);

		return chart;
	}

	private ChartView buildStackedChart(BaseStackBarChartView chart) {
		chart.reset();
		BarSet dataset = new BarSet(mLabels, mValues);
		dataset.setColor(SandboxFragment.mBarColorId);
		chart.addData(dataset);

		chart.setBarSpacing(SandboxFragment.mBarSpacing);
		if (SandboxFragment.mHasBarBackground)
			chart.setBarBackgroundColor(SandboxFragment.mBarBackgroundColorId);
		chart.setRoundCorners(SandboxFragment.mBarCornersSize);

		return chart;
	}

	private String buildCode() {

		StringBuilder code = new StringBuilder();

		code.append("// Do not assume the code below as final. "
				+ "For a complete customization and well behaviour of your "
				+ "chart please check the documentation, wiki, and code examples.\n");

		switch (SandboxFragment.mChartId) {
		case R.id.sandbox_chart_line:
			code.append("LineChartView chart = (LineChartView) <layout>.findViewById(<chart_id>);\n");
			code.append(buildLineCode());
			break;
		case R.id.sandbox_chart_bar:
			code.append("BarChartView chart = (BarChartView) <layout>.findViewById(<chart_id>);\n");
			code.append(buildBarCode());
			break;
		case R.id.sandbox_chart_horbar:
			code.append("HorizontalBarChartView chart = (HorizontalBarChartView) <layout>.findViewById(<chart_id>);\n");
			code.append(buildBarCode());
			break;
		case R.id.sandbox_chart_stacked:
			code.append("StackBarChartView chart = (StackBarChartView) <layout>.findViewById(<chart_id>);\n");
			code.append(buildStackedCode());
			break;
		case R.id.sandbox_chart_horstacked:
			code.append("HorizontalStackChartView chart = (HorizontalStackChartView) <layout>.findViewById(<chart_id>);\n");
			code.append(buildStackedCode());
			break;
		default:
		}

		code.append("\n// Generic chart customization\n");

		if (!SandboxFragment.mHasXAxis)
			code.append("chart.setXAxis(false);\n");
		if (!SandboxFragment.mHasYAxis)
			code.append("chart.setYAxis(false);\n");
		if ((SandboxFragment.mHasXAxis || SandboxFragment.mHasYAxis)
				&& SandboxFragment.mAxisColorId != SandboxFragment.DEFAULT_COLOR)
			code.append("chart.setAxisColor(Color.parseColor('#"
					+ Integer.toHexString(SandboxFragment.mAxisColorId)
							.substring(2) + "'));\n");
		if (SandboxFragment.mXLabelPosition != AxisController.LabelPosition.OUTSIDE)
			code.append("chart.setXLabels(" + SandboxFragment.mXLabelPosition
					+ ");\n");
		if (SandboxFragment.mYLabelPosition != AxisController.LabelPosition.OUTSIDE)
			code.append("chart.setYLabels(" + SandboxFragment.mYLabelPosition
					+ ");\n");
		if ((SandboxFragment.mXLabelPosition != AxisController.LabelPosition.NONE || SandboxFragment.mYLabelPosition != AxisController.LabelPosition.NONE)
				&& SandboxFragment.mLabelColorId != SandboxFragment.DEFAULT_COLOR)
			code.append("chart.setLabelsColor(Color.parseColor('#"
					+ Integer.toHexString(SandboxFragment.mLabelColorId)
							.substring(2) + "'));\n");
		if (SandboxFragment.mGridType != null) {
			code.append("// Paint object used to draw Grid\n"
					+ "Paint gridPaint = new Paint();\n"
					+ "gridPaint.setColor(Color.parseColor('#"
					+ Integer.toHexString(SandboxFragment.mGridColorId)
							.substring(2) + "'));\n"
					+ "gridPaint.setStyle(Paint.Style.STROKE);\n"
					+ "gridPaint.setAntiAlias(true);\n"
					+ "gridPaint.setStrokeWidth(Tools.fromDpToPx("
					+ SandboxFragment.mGridThickness + "));\n");
			if (SandboxFragment.mIsGridDashed)
				code.append("gridPaint.setPathEffect(new DashPathEffect("
						+ SandboxFragment.mGridDashType + ", 0));\n");
			code.append("chart.setGrid(ChartView.GridType."
					+ SandboxFragment.mGridType + ", gridPaint);\n");
		}

		if (SandboxFragment.mLabelFormat != "")
			code.append("chart.setLabelsFormat(new DecimalFormat('#'+"
					+ SandboxFragment.mLabelFormat + "));\n");

		code.append(buildAnimationCode());

		code.append("chart.show(anim);");

		return code.toString();
	}

	private String buildLineCode() {

		StringBuilder code = new StringBuilder();

		code.append("\n// Line chart customization\n");

		code.append("LineSet dataset = new LineSet(<labels>, <values>);\n");

		if (SandboxFragment.mIsLineDashed)
			code.append("dataset.setDashed(true);\n");

		if (SandboxFragment.mIsLineSmooth)
			code.append("dataset.setSmooth(true);\n");

		code.append("dataset.setThickness(Tools.fromDpToPx("
				+ SandboxFragment.mLineThickness + "));\n");
		if (SandboxFragment.mLineColorId != SandboxFragment.DEFAULT_COLOR)
			code.append("dataset.setColor(Color.parseColor('#"
					+ Integer.toHexString(SandboxFragment.mLineColorId)
							.substring(2) + "'));\n");

		if (SandboxFragment.mPointsSizeId != -1)
			code.append("dataset.setDotsRadius(Tools.fromDpToPx("
					+ SandboxFragment.mPointsSize + "));\n");
		if (SandboxFragment.mPointColorId != SandboxFragment.DEFAULT_COLOR)
			code.append("dataset.setDotsColor(Color.parseColor('#"
					+ Integer.toHexString(SandboxFragment.mPointColorId)
							.substring(2) + "'));\n");

		code.append("chart.addData(dataset);\n");

		return code.toString();
	}

	private String buildBarCode() {

		StringBuilder code = new StringBuilder();

		code.append("\n// Bar chart customization\n");

		code.append("BarSet dataset = new BarSet(<labels>, <values>);\n");
		if (SandboxFragment.mBarColorId != SandboxFragment.DEFAULT_COLOR)
			code.append("dataset.setColor(Color.parseColor('#"
					+ Integer.toHexString(SandboxFragment.mBarColorId)
							.substring(2) + "'));\n");
		if (SandboxFragment.mBarSpacing != 0)
			code.append("chart.setBarSpacing(" + SandboxFragment.mBarSpacing
					+ ");\n");
		if (SandboxFragment.mHasBarBackground)
			code.append("chart.setBarBackgroundColor(Color.parseColor('#"
					+ Integer
							.toHexString(SandboxFragment.mBarBackgroundColorId)
							.substring(2) + "'));\n");

		code.append("chart.addData(dataset);\n");

		return code.toString();
	}

	private String buildStackedCode() {

		StringBuilder code = new StringBuilder();

		code.append("\n// Stacked chart customization\n");

		code.append("BarSet dataset = new BarSet(<labels>, <values>);\n");
		if (SandboxFragment.mBarColorId != SandboxFragment.DEFAULT_COLOR)
			code.append("dataset.setColor(Color.parseColor('# "
					+ Integer.toHexString(SandboxFragment.mBarColorId)
							.substring(2) + "'));\n");
		if (SandboxFragment.mBarSpacing != 0)
			code.append("chart.setBarSpacing(" + SandboxFragment.mBarSpacing
					+ ");\n");
		if (SandboxFragment.mHasBarBackground)
			code.append("chart.setBarBackground(Color.parseColor('#"
					+ Integer
							.toHexString(SandboxFragment.mBarBackgroundColorId)
							.substring(2) + "'));\n");

		code.append("chart.addData(dataset);\n");

		return code.toString();
	}

	private static String buildAnimationCode() {

		StringBuilder code = new StringBuilder("\n// Animation customization\n");

		if (SandboxFragment.mDuration != 1000)
			code.append("Animation anim = new Animation("
					+ SandboxFragment.mDuration + ");\n");
		else
			code.append("Animation anim = new Animation();\n");

		switch (SandboxFragment.mEasingId) {
		case 0:
			code.append("anim.setEasing(new CubicEase());\n");
			break;
		case 1:
			code.append("anim.setEasing(new QuartEase());\n");
			break;
		case 2:
			code.append("anim.setEasing(new QuintEase());\n");
			break;
		case 3:
			code.append("anim.setEasing(new BounceEase());\n");
			break;
		case 4:
			code.append("anim.setEasing(new ElasticEase());\n");
			break;
		case 5:
			code.append("anim.setEasing(new ExpoEase());\n");
			break;
		case 6:
			code.append("anim.setEasing(new CircEase());\n");
			break;
		case 7:
			code.append("anim.setEasing(new QuadEase());\n");
			break;
		case 8:
			code.append("anim.setEasing(new SineEase());\n");
			break;
		case 9:
			code.append("anim.setEasing(new LinearEase());\n");
			break;
		default:
		}

		if (SandboxFragment.mAlpha != -1)
			code.append("anim.setAlpha(" + SandboxFragment.mAlpha + ");\n");

		if (SandboxFragment.mOverlapOrder[0] != 0
				|| SandboxFragment.mOverlapFactor != 1) {
			String order = "{ ";
			for (int i = 0; i < SandboxFragment.mOverlapOrder.length; i++)
				order += SandboxFragment.mOverlapOrder[i] + ", ";
			order += "}";
			code.append("anim.setOverlap(" + SandboxFragment.mOverlapFactor
					+ ", " + order + ");\n");
		}

		if (SandboxFragment.mStartX != -1 && SandboxFragment.mStartY != 0)
			code.append("anim.setStartPoint(" + SandboxFragment.mStartX + ", "
					+ SandboxFragment.mStartY + ");\n");

		return code.toString();
	}

}
