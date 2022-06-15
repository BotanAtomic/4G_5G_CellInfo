package com.airbus.test5gcell;

import static android.content.Context.TELEPHONY_SERVICE;

import android.os.Bundle;
import android.telephony.CellSignalStrength;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthNr;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.airbus.test5gcell.databinding.FragmentFirstBinding;

import java.util.List;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);

                List<CellSignalStrength> cellInfoList;
                TelephonyManager tm = (TelephonyManager) getContext().getSystemService(TELEPHONY_SERVICE);
                cellInfoList = tm.getSignalStrength().getCellSignalStrengths();
                for (CellSignalStrength cellInfo : cellInfoList) {
                    if (cellInfo instanceof CellSignalStrengthLte) {
                        binding.textviewFirst.setText(cellInfo.toString());
                        Toast.makeText(getContext(), "4G", Toast.LENGTH_LONG).show();
                    } else if (cellInfo instanceof CellSignalStrengthNr) {
                        binding.textviewFirst.setText(cellInfo.toString());
                        Toast.makeText(getContext(), "5G", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "Not Signal", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
