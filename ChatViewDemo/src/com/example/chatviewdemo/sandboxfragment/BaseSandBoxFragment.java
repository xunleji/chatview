package com.example.chatviewdemo.sandboxfragment;

import com.example.chatviewdemo.R;
import com.example.chatviewdemo.color.ColorPicker;
import com.example.chatviewdemo.color.ValueBar;

import android.support.v4.app.Fragment;
import android.view.View;

public class BaseSandBoxFragment extends Fragment {

	public void onStart() {
		super.onStart();
		onStateRecover();
	}

	public void onStateRecover() {
	}

	public void onStateChange(int id) {
	}

	/**
	 * Swap the state of Attr's Views
	 * 
	 * @param parentView
	 *            - Parent View of views to be changed
	 * @param oldId
	 *            - ID of last view selected
	 * @param newId
	 *            - ID of new view selected
	 * @param mandatoryAttr
	 *            - If at least one view is required to be selected
	 * @return The ID of the new selected View
	 */
	static int swapState(View parentView, int oldId, int newId,
			boolean mandatoryAttr) {

		if (oldId == newId && !mandatoryAttr) {
			parentView.findViewById(newId).setSelected(
					!parentView.findViewById(newId).isSelected());
			newId = -1;

		} else if (oldId == -1) {
			parentView.findViewById(newId).setSelected(
					!parentView.findViewById(newId).isSelected());

		} else if (oldId != newId) {
			parentView.findViewById(oldId).setSelected(false);
			parentView.findViewById(newId).setSelected(
					!parentView.findViewById(newId).isSelected());
		}

		return newId;
	}

	static void configColorPicker(View view, int colorId) {
		ColorPicker picker = (ColorPicker) view.findViewById(R.id.picker);
		ValueBar valueBar = (ValueBar) view.findViewById(R.id.valuebar);
		picker.addValueBar(valueBar);
		picker.setOldCenterColor(colorId);
		picker.setColor(colorId);
	}
}
