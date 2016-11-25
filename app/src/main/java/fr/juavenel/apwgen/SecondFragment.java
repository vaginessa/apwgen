package fr.juavenel.apwgen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

public class SecondFragment extends Fragment {

    public SecondFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_second, container, false);

        NumberPicker np;

        np = (NumberPicker) view.findViewById(R.id.numberPicker_number);
        np.setMinValue(1);
        np.setMaxValue(100);

        np = (NumberPicker) view.findViewById(R.id.numberPicker_length);
        np.setMinValue(1);
        np.setMaxValue(100);

        Button button = (Button) view.findViewById(R.id.button_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onViewButtonClicked();
            }
        });

        return view;
    }

    private void onViewButtonClicked() {

        EditText editText = (EditText) getView().findViewById(R.id.editText_account);

        String account = editText.getText().toString().trim();

        if (account.isEmpty()) return;

        NumberPicker np;

        np = (NumberPicker) getView().findViewById(R.id.numberPicker_number);
        int number = np.getValue();

        np = (NumberPicker) getView().findViewById(R.id.numberPicker_length);
        int length = np.getValue();

        Bundle args = new Bundle();
        args.putString("uri", getArguments().getString("uri"));
        args.putString("account", account);
        args.putInt("number", number);
        args.putInt("length", length);

        ThirdFragment fragment = new ThirdFragment();
        fragment.setArguments(args);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
