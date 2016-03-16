package com.example.chatviewdemo.sandboxfragment;

import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.db.chart.view.AxisController;
import com.example.chatviewdemo.R;
import com.example.chatviewdemo.SandboxFragment;
import com.example.chatviewdemo.color.ColorPicker;

public class SandBoxAxisFragment extends BaseSandBoxFragment {

	private View mLayout;
	private ViewGroup mViewGroup;
	private AlertDialog.Builder mDialogBuilder;
	private int mLabelXId, mLabelYId;
	private int mAxisXId, mAxisYId;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mLayout = inflater.inflate(R.layout.sandbox_axis, container, false);
		mViewGroup = container;
		mLabelXId = R.id.sandbox_axis_x_outside;
		mAxisXId = R.id.sandbox_axis_x_axis;
		mLabelYId = R.id.sandbox_axis_y_outside;
		mAxisYId = R.id.sandbox_axis_y_axis;
		final EditText editText = (EditText) mLayout
				.findViewById(R.id.sandbox_axis_label_format_value);
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				SandboxFragment.mLabelFormat = editText.getText().toString();
			}
		});

		mDialogBuilder = new AlertDialog.Builder(getActivity())
				.setNegativeButton("Cancel", null);

		return mLayout;
	}

	@Override
	public void onStateRecover() {
		if (mLabelXId != -1)
			mLayout.findViewById(mLabelXId).setSelected(true);
		if (mLabelYId != -1)
			mLayout.findViewById(mLabelYId).setSelected(true);
		if (mAxisXId != -1)
			mLayout.findViewById(mAxisXId).setSelected(true);
		if (mAxisYId != -1)
			mLayout.findViewById(mAxisYId).setSelected(true);
		((GradientDrawable) (mLayout.findViewById(R.id.sandbox_axis_color))
				.getBackground()).setColor(SandboxFragment.mAxisColorId);
		((GradientDrawable) (mLayout.findViewById(R.id.sandbox_label_color))
				.getBackground()).setColor(SandboxFragment.mLabelColorId);
	}

	@Override
	public void onStateChange(int id) {

		switch (id) {

		/** Label Y **/
		case R.id.sandbox_axis_y_outside:
			if (id == mLabelYId)
				SandboxFragment.mYLabelPosition = AxisController.LabelPosition.NONE;
			else
				SandboxFragment.mYLabelPosition = AxisController.LabelPosition.OUTSIDE;
			mLabelYId = swapState(mLayout, mLabelYId, id, false);
			break;
		case R.id.sandbox_axis_y_inside:
			if (id == mLabelYId)
				SandboxFragment.mYLabelPosition = AxisController.LabelPosition.NONE;
			else
				SandboxFragment.mYLabelPosition = AxisController.LabelPosition.INSIDE;
			mLabelYId = swapState(mLayout, mLabelYId, id, false);
			break;

		/** Axis Y **/
		case R.id.sandbox_axis_y_axis:
			SandboxFragment.mHasYAxis = !SandboxFragment.mHasYAxis;
			mAxisYId = swapState(mLayout, mAxisYId, id, false);
			break;

		/** Label X **/
		case R.id.sandbox_axis_x_inside:
			if (id == mLabelXId)
				SandboxFragment.mXLabelPosition = AxisController.LabelPosition.NONE;
			else
				SandboxFragment.mXLabelPosition = AxisController.LabelPosition.INSIDE;
			mLabelXId = swapState(mLayout, mLabelXId, id, false);
			break;
		case R.id.sandbox_axis_x_outside:
			if (id == mLabelXId)
				SandboxFragment.mXLabelPosition = AxisController.LabelPosition.NONE;
			else
				SandboxFragment.mXLabelPosition = AxisController.LabelPosition.OUTSIDE;
			mLabelXId = swapState(mLayout, mLabelXId, id, false);
			break;

		/** Axis X **/
		case R.id.sandbox_axis_x_axis:
			SandboxFragment.mHasXAxis = !SandboxFragment.mHasXAxis;
			mAxisXId = swapState(mLayout, mAxisXId, id, false);
			break;

		case R.id.sandbox_axis_color:
			final View axisColorLayout = getActivity().getLayoutInflater()
					.inflate(R.layout.color_picker, mViewGroup, false);
			configColorPicker(axisColorLayout, SandboxFragment.mAxisColorId);
			mDialogBuilder
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									ColorPicker picker = (ColorPicker) axisColorLayout
											.findViewById(R.id.picker);
									SandboxFragment.mAxisColorId = picker.getColor();
									((GradientDrawable) (mLayout
											.findViewById(R.id.sandbox_axis_color))
											.getBackground())
											.setColor(SandboxFragment.mAxisColorId);

								}
							}).setView(axisColorLayout).create().show();
			break;

		case R.id.sandbox_label_color:
			final View labelColorLayout = getActivity().getLayoutInflater()
					.inflate(R.layout.color_picker, mViewGroup, false);
			configColorPicker(labelColorLayout, SandboxFragment.mLabelColorId);
			mDialogBuilder
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									ColorPicker picker = (ColorPicker) labelColorLayout
											.findViewById(R.id.picker);
									SandboxFragment.mLabelColorId = picker.getColor();
									((GradientDrawable) (mLayout
											.findViewById(R.id.sandbox_label_color))
											.getBackground())
											.setColor(SandboxFragment.mLabelColorId);
								}
							}).setView(labelColorLayout).create().show();
			break;

		default:
		}
	}

}
