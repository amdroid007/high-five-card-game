package com.iliaskomp.highfivecardgame.activities;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.iliaskomp.highfivecardgame.R;

public class RuleDialogFragment extends DialogFragment {
    private static final String ARGS_RULE_DESCRIPTION = "ruleDescription";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my_dialog, null);

        String ruleDescription = getArguments().getString(ARGS_RULE_DESCRIPTION);

        TextView textViewRule = (TextView) v.findViewById(R.id.text_view_rule);
        textViewRule.setText(ruleDescription);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Rule")
                .setPositiveButton(android.R.string.ok, null)
                .setView(v)
                .create();
    }

    public static RuleDialogFragment newInstance(String ruleDescription) {
        Bundle args = new Bundle();
        args.putString(ARGS_RULE_DESCRIPTION, ruleDescription);

        RuleDialogFragment fragment = new RuleDialogFragment();
        fragment.setArguments(args);

        return fragment;
    }

}
