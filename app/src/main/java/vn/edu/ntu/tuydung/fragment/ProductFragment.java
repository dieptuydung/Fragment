package vn.edu.ntu.tuydung.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.ntu.tuydung.controller.ICartcontroller;
import vn.edu.ntu.tuydung.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {
    EditText edtName, edtPrice, edtDesc;
    Button btnAdd;

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtName = view.findViewById(R.id.edtName);
        edtPrice = view.findViewById(R.id.edtPrice);
        edtDesc = view.findViewById(R.id.edtDesc);
        btnAdd = view.findViewById(R.id.btnAdd);

        btnAdd. setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ICartcontroller controller = (ICartcontroller) getActivity().getApplication();
                Product  p = new Product(edtName.getText().toString(),
                        edtDesc.getText().toString(), new Integer(edtPrice.getText().toString()));
                controller.listProduct().add(p);

                Toast.makeText(getActivity(), "Đã thêm" + edtName.getText() + " vào giỏ", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
