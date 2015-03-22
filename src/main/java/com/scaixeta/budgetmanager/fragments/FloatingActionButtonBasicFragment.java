/*
 * Copyright 2014, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.scaixeta.budgetmanager.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.scaixeta.budgetmanager.BudgetManager;
import com.scaixeta.budgetmanager.MainActivity;
import com.scaixeta.budgetmanager.R;


public class FloatingActionButtonBasicFragment extends Fragment {

    private BudgetManager budgetManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fab_layout, container, false);

        FrameLayout fab = (FrameLayout) rootView.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (budgetManager.getBudget(getActivity()) != null) {
                    NewExpenseDialogFragment dialog = new NewExpenseDialogFragment();
                    dialog.setNewExpenseListener((MainActivity) getActivity());
                    FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
                    dialog.show(supportFragmentManager, "newExpenseDialog");
                }
            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainActivity){
            this.budgetManager = ((MainActivity) activity).getBudgetManager();
        }
    }
}
