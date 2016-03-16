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

public class SandBoxBarFragment extends BaseSandBoxFragment {
	private View mLayout;
	private ViewGroup mViewGroup;
	private AlertDialog.Builder mDialogBuilder;
	private static int mBarCornersSizeId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mViewGroup = container;
		mBarCornersSizeId = R.id.sandbox_bar_corner1;
		mDialogBuilder = new AlertDialog.Builder(getActivity())
				.setNegativeButton("Cancel", null);
		mLayout = inflater.inflate(R.layout.sandbox_bar, container, false);

		return mLayout;
	}

	@Override
	public void onStateRecover() {
		mLayout.findViewById(mBarCornersSizeId).setSelected(true);
		((GradientDrawable) (mLayout.findViewById(R.id.sandbox_bar_color))
				.getBackground()).setColor(SandboxFragment.mBarColorId);
		mLayout.findViewById(SandboxFragment.mBarSpacingId).setSelected(true);
		((GradientDrawable) (mLayout
				.findViewById(R.id.sandbox_bar_background_color))
				.getBackground())
				.setColor(SandboxFragment.mBarBackgroundColorId);
		if (SandboxFragment.mBarBackgroundId != -1)
			mLayout.findViewById(SandboxFragment.mBarSpacingId).setSelected(
					true);
	}

	@Override
	public void onStateChange(int id) {

		switch (id) {
		case R.id.sandbox_bar_color:
			final View barColorLayout = getActivity().getLayoutInflater()
					.inflate(R.layout.color_picker, null, false);
			configColorPicker(barColorLayout, SandboxFragment.mBarColorId);
			mDialogBuilder
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									ColorPicker picker = (ColorPicker) barColorLayout
											.findViewById(R.id.picker);
									SandboxFragment.mBarColorId = picker
											.getColor();
									((GradientDrawable) (mLayout
											.findViewById(R.id.sandbox_bar_color))
											.getBackground())
											.setColor(SandboxFragment.mBarColorId);

								}
							}).setView(barColorLayout).create().show();
			break;

		/** Corners size **/
		case R.id.sandbox_bar_corner1:
			SandboxFragment.mBarCornersSize = Tools.fromDpToPx(0);
			mBarCornersSizeId = swapState(mLayout, mBarCornersSizeId, id, false);
			break;
		case R.id.sandbox_bar_corner2:
			SandboxFragment.mBarCornersSize = Tools.fromDpToPx(4);
			mBarCornersSizeId = swapState(mLayout, mBarCornersSizeId, id, false);
			break;
		case R.id.sandbox_bar_corner3:
			SandboxFragment.mBarCornersSize = Tools.fromDpToPx(8);
			mBarCornersSizeId = swapState(mLayout, mBarCornersSizeId, id, false);
			break;
		case R.id.sandbox_bar_corner4:
			SandboxFragment.mBarCornersSize = Tools.fromDpToPx(12);
			mBarCornersSizeId = swapState(mLayout, mBarCornersSizeId, id, false);
			break;

		/** Bar spacing **/
		case R.id.sandbox_bar_spacing1:
			SandboxFragment.mBarSpacing = Tools.fromDpToPx(10);
			SandboxFragment.mBarSpacingId = swapState(mLayout,
					SandboxFragment.mBarSpacingId, id, true);
			break;
		case R.id.sandbox_bar_spacing2:
			SandboxFragment.mBarSpacing = Tools.fromDpToPx(16);
			SandboxFragment.mBarSpacingId = swapState(mLayout,
					SandboxFragment.mBarSpacingId, id, true);
			break;
		case R.id.sandbox_bar_spacing3:
			SandboxFragment.mBarSpacing = Tools.fromDpToPx(22);
			SandboxFragment.mBarSpacingId = swapState(mLayout,
					SandboxFragment.mBarSpacingId, id, true);
			break;
		case R.id.sandbox_bar_spacing4:
			SandboxFragment.mBarSpacing = Tools.fromDpToPx(28);
			SandboxFragment.mBarSpacingId = swapState(mLayout,
					SandboxFragment.mBarSpacingId, id, true);
			break;

		/** Bar background color */
		case R.id.sandbox_bar_background_toggle:
			SandboxFragment.mHasBarBackground = !SandboxFragment.mHasBarBackground;
			SandboxFragment.mBarBackgroundId = swapState(mLayout,
					SandboxFragment.mBarBackgroundId, id, false);
			break;

		case R.id.sandbox_bar_background_color:
			final View backgroundColorLayout = getActivity()
					.getLayoutInflater().inflate(R.layout.color_picker,
							mViewGroup, false);
			configColorPicker(backgroundColorLayout,
					SandboxFragment.mBarBackgroundColorId);
			mDialogBuilder
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									ColorPicker picker = (ColorPicker) backgroundColorLayout
											.findViewById(R.id.picker);
									SandboxFragment.mBarBackgroundColorId = picker
											.getColor();
									((GradientDrawable) (mLayout
											.findViewById(R.id.sandbox_bar_background_color))
											.getBackground())
											.setColor(SandboxFragment.mBarBackgroundColorId);
								}
							}).setView(backgroundColorLayout).create().show();
			break;

		default:
		}
	}
}
