package fr.juavenel.apwgen;

import android.support.v4.app.Fragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ThirdFragment extends Fragment {

    private ArrayList<String> mListPasswords;

    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_third, container, false);

        PasswordGenerator pwgen = new PasswordGenerator(getActivity());

        try {
            mListPasswords = pwgen.generate(Uri.parse(getArguments().getString("uri")),
                    getArguments().getString("account"),
                    getArguments().getInt("number"),
                    getArguments().getInt("length"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mListPasswords);

        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pass = (String) parent.getItemAtPosition(position);

                ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("apwgen", pass);
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getActivity(), "Password is copy to clipboard", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton closeButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);

        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

}
