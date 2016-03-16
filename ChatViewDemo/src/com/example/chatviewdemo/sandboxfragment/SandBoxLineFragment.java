package com.example.chatviewdemo.sandboxfragment;

import static com.example.chatviewdemo.sandboxfragment.BaseSandBoxFragment.configColorPicker;
import static com.example.chatviewdemo.sandboxfragment.BaseSandBoxFragment.swapState;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.db.chart.Tools;
import com.example.chatviewdemo.R;
import com.example.chatviewdemo.SandboxFragment;
import com.example.chatviewdemo.color.ColorPicker;

public class SandBoxLineFragment extends BaseSandBoxFragment {
	private View mLayout;
	private ViewGroup mViewGroup;
	private AlertDialog.Builder mDialogBuilder;
	private static int mLineTypeId;
	private static int mLineThicknessTypeId;
	private static int mLineThicknessId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mViewGroup = container;
		mLineTypeId = -1;
		mLineThicknessTypeId = R.id.sandbox_line_solid;
		mLineThicknessId = R.id.sandbox_line_thickness3;
		
		mDialogBuilder = new AlertDialog.Builder(getActivity())
				.setNegativeButton("Cancel", null);
		return mLayout = inflater.inflate(R.layout.sandbox_line, container,
				false);
	}

	@Override
	public void onStateRecover() {

		if (mLineTypeId != -1)
			mLayout.findViewById(mLineTypeId).setSelected(true);
		mLayout.findViewById(mLineThicknessTypeId).setSelected(true);
		mLayout.findViewById(mLineThicknessId).setSelected(true);
		((GradientDrawable) (mLayout.findViewById(R.id.sandbox_line_color))
				.getBackground()).setColor(SandboxFragment.mLineColorId);
		if (SandboxFragment.mPointsSizeId != -1)
			mLayout.findViewById(SandboxFragment.mPointsSizeId).setSelected(true);
		((GradientDrawable) (mLayout.findViewById(R.id.sandbox_point_color))
				.getBackground()).setColor(SandboxFragment.mPointColorId);
	}

	@Override
	public void onStateChange(int id) {

		switch (id) {

		/** Line type **/
		case R.id.sandbox_line_smooth:
			SandboxFragment.mIsLineSmooth = !SandboxFragment.mIsLineSmooth;
			mLineTypeId = swapState(mLayout, mLineTypeId, id, false);
			break;

		/** Line type 2 **/
		case R.id.sandbox_line_solid:
			SandboxFragment.mIsLineDashed = false;
			mLineThicknessTypeId = swapState(mLayout, mLineThicknessTypeId, id,
					true);
			break;
		case R.id.sandbox_line_dashed:
			SandboxFragment.mIsLineDashed = true;
			SandboxFragment.mLineDashType = new float[] { 10, 10 };
			mLineThicknessTypeId = swapState(mLayout, mLineThicknessTypeId, id,
					true);
			break;
		case R.id.sandbox_line_dashed2:
			SandboxFragment.mIsLineDashed = true;
			SandboxFragment.mLineDashType = new float[] { 5, 5 };
			mLineThicknessTypeId = swapState(mLayout, mLineThicknessTypeId, id,
					true);
			break;

		/** Line thickness **/
		case R.id.sandbox_line_thickness1:
			SandboxFragment.mLineThickness = Tools.fromDpToPx(7);
			mLineThicknessId = swapState(mLayout, mLineThicknessId, id, true);
			break;
		case R.id.sandbox_line_thickness2:
			SandboxFragment.mLineThickness = Tools.fromDpToPx(5);
			mLineThicknessId = swapState(mLayout, mLineThicknessId, id, true);
			break;
		case R.id.sandbox_line_thickness3:
			SandboxFragment.mLineThickness = Tools.fromDpToPx(3);
			mLineThicknessId = swapState(mLayout, mLineThicknessId, id, true);
			break;
		case R.id.sandbox_line_thickness4:
			SandboxFragment.mLineThickness = Tools.fromDpToPx(1);
			mLineThicknessId = swapState(mLayout, mLineThicknessId, id, true);
			break;

		case R.id.sandbox_line_color:
			final View lineColorLayout = getActivity().getLayoutInflater()
					.inflate(R.layout.color_picker, mViewGroup, false);
			configColorPicker(lineColorLayout, SandboxFragment.mLineColorId);
			mDialogBuilder
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									ColorPicker picker = (ColorPicker) lineColorLayout
											.findViewById(R.id.picker);
									SandboxFragment.mLineColorId = picker.getColor();
									((GradientDrawable) (mLayout
											.findViewById(R.id.sandbox_line_color))
											.getBackground())
											.setColor(SandboxFragment.mLineColorId);

								}
							}).setView(lineColorLayout).create().show();
			break;

		/** Points size **/
		case R.id.sandbox_point_size1:
			SandboxFragment.mPointsSize = Tools.fromDpToPx(8);
			SandboxFragment.mPointsSizeId = swapState(mLayout, SandboxFragment.mPointsSizeId, id, false);
			break;
		case R.id.sandbox_point_size2:
			SandboxFragment.mPointsSize = Tools.fromDpToPx(6);
			SandboxFragment.mPointsSizeId = swapState(mLayout, SandboxFragment.mPointsSizeId, id, false);
			break;
		case R.id.sandbox_point_size3:
			SandboxFragment.mPointsSize = Tools.fromDpToPx(4);
			SandboxFragment.mPointsSizeId = swapState(mLayout, SandboxFragment.mPointsSizeId, id, false);
			break;
		case R.id.sandbox_point_size4:
			SandboxFragment.mPointsSize = Tools.fromDpToPx(2);
			SandboxFragment.mPointsSizeId = swapState(mLayout, SandboxFragment.mPointsSizeId, id, false);
			break;

		/** Points color **/
		case R.id.sandbox_point_color:
			final View pointColorLayout = getActivity().getLayoutInflater()
					.inflate(R.layout.color_picker, mViewGroup, false);
			configColorPicker(pointColorLayout, SandboxFragment.mPointColorId);
			mDialogBuilder
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									ColorPicker picker = (ColorPicker) pointColorLayout
											.findViewById(R.id.picker);
									SandboxFragment.mPointColorId = picker.getColor();
									((GradientDrawable) (mLayout
											.findViewById(R.id.sandbox_point_color))
											.getBackground())
											.setColor(SandboxFragment.mPointColorId);
								}
							}).setView(pointColorLayout).create().show();
			break;

		default:
		}
	}

}
