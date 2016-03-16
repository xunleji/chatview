package com.example.chatviewdemo.sandboxfragment;

import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.db.chart.Tools;
import com.db.chart.view.ChartView;
import com.example.chatviewdemo.R;
import com.example.chatviewdemo.SandboxFragment;
import com.example.chatviewdemo.color.ColorPicker;

public class SandBoxGridFragment extends BaseSandBoxFragment {
	private View mLayout;
	private ViewGroup mViewGroup;
	private AlertDialog.Builder mDialogBuilder;
	private static int mGridTypeId;
	private static int mGridLineTypeId;
	private static int mGridThicknessId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mViewGroup = container;
		mGridTypeId = -1;
		mGridLineTypeId = R.id.sandbox_grid_solid;
		mGridThicknessId = R.id.sandbox_grid_thickness3;
		mDialogBuilder = new AlertDialog.Builder(getActivity())
				.setNegativeButton("Cancel", null);
		return mLayout = inflater.inflate(R.layout.sandbox_grid, container,
				false);
	}

	@Override
	public void onStateRecover() {
		if (mGridTypeId != -1)
			mLayout.findViewById(mGridTypeId).setSelected(true);
		mLayout.findViewById(mGridLineTypeId).setSelected(true);
		mLayout.findViewById(mGridThicknessId).setSelected(true);
	}

	@Override
	public void onStateChange(int id) {

		switch (id) {

		/** Grid Type **/
		case R.id.sandbox_grid_hor:
			if (mGridTypeId == id)
				SandboxFragment.mGridType = ChartView.GridType.NONE;
			else
				SandboxFragment.mGridType = ChartView.GridType.HORIZONTAL;
			mGridTypeId = swapState(mLayout, mGridTypeId, id, false);
			break;
		case R.id.sandbox_grid_ver:
			if (mGridTypeId == id)
				SandboxFragment.mGridType = ChartView.GridType.NONE;
			else
				SandboxFragment.mGridType = ChartView.GridType.VERTICAL;
			mGridTypeId = swapState(mLayout, mGridTypeId, id, false);
			break;
		case R.id.sandbox_grid_full:
			if (mGridTypeId == id)
				SandboxFragment.mGridType = ChartView.GridType.NONE;
			else
				SandboxFragment.mGridType = ChartView.GridType.FULL;
			mGridTypeId = swapState(mLayout, mGridTypeId, id, false);
			break;

		/** Grid line type **/
		case R.id.sandbox_grid_solid:
			SandboxFragment.mIsGridDashed = false;
			mGridLineTypeId = swapState(mLayout, mGridLineTypeId, id, true);
			break;
		case R.id.sandbox_grid_dashed:
			SandboxFragment.mIsGridDashed = true;
			SandboxFragment.mGridDashType = new float[] { 10, 10 };
			mGridLineTypeId = swapState(mLayout, mGridLineTypeId, id, true);
			break;
		case R.id.sandbox_grid_dashed2:
			SandboxFragment.mIsGridDashed = true;
			SandboxFragment.mGridDashType = new float[] { 5, 5 };
			mGridLineTypeId = swapState(mLayout, mGridLineTypeId, id, true);
			break;

		/** Grid line thickness **/
		case R.id.sandbox_grid_thickness1:
			SandboxFragment.mGridThickness = Tools.fromDpToPx(4);
			mGridThicknessId = swapState(mLayout, mGridThicknessId, id, true);
			break;
		case R.id.sandbox_grid_thickness2:
			SandboxFragment.mGridThickness = Tools.fromDpToPx(3);
			mGridThicknessId = swapState(mLayout, mGridThicknessId, id, true);
			break;
		case R.id.sandbox_grid_thickness3:
			SandboxFragment.mGridThickness = Tools.fromDpToPx(2);
			mGridThicknessId = swapState(mLayout, mGridThicknessId, id, true);
			break;
		case R.id.sandbox_grid_thickness4:
			SandboxFragment.mGridThickness = Tools.fromDpToPx(1);
			mGridThicknessId = swapState(mLayout, mGridThicknessId, id, true);
			break;

		case R.id.sandbox_grid_color:
			final View gridColorLayout = getActivity().getLayoutInflater()
					.inflate(R.layout.color_picker, mViewGroup, false);
			configColorPicker(gridColorLayout, SandboxFragment.mGridColorId);
			mDialogBuilder
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									ColorPicker picker = (ColorPicker) gridColorLayout
											.findViewById(R.id.picker);
									SandboxFragment.mGridColorId = picker.getColor();
									((GradientDrawable) (mLayout
											.findViewById(R.id.sandbox_grid_color))
											.getBackground())
											.setColor(SandboxFragment.mGridColorId);
								}
							}).setView(gridColorLayout).create().show();
			break;

		default:
		}
	}
}
