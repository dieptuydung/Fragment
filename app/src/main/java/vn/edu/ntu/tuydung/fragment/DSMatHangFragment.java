package vn.edu.ntu.tuydung.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import vn.edu.ntu.tuydung.controller.ICartcontroller;
import vn.edu.ntu.tuydung.model.Product;

public class DSMatHangFragment extends Fragment {
    RecyclerView rvListMH;
    FloatingActionButton fab;
    NavController controller;

    ArrayList<Product> listProduct;
    ProductAdapter adapter;
    ICartcontroller iCartcontroller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        fab=view.findViewById(R.id.fab);

        rvListMH=view.findViewById(R.id.rvListMH);
        iCartcontroller = (ICartcontroller) getActivity().getApplication();
        listProduct = iCartcontroller.listProduct();
        adapter = new ProductAdapter(listProduct);
        rvListMH.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvListMH.setAdapter(adapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.mnu_cart, menu);
    }
//code tạo menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mncart)
        {
            callShopping();
        }
        return super.onOptionsItemSelected(item);
    }

    private void callShopping()
    {

        controller.navigate(R.id.action_DSMatHangFragment_to_shoppingCartFragment);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = NavHostFragment.findNavController(DSMatHangFragment.this);
        ((MainActivity)getActivity()).controller = controller;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                controller.navigate(R.id.action_DSMatHangFragment_to_productFragment);
            }
        });

    }

    private class ProductViewholder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView txtName, txtPrice, txtDesc;
        ImageView imvCart;
        Product p;
        public ProductViewholder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice=itemView.findViewById(R.id.txtPrice);
            txtDesc=itemView.findViewById(R.id.txtDesc);
            imvCart=itemView.findViewById(R.id.imvCart);
            imvCart.setOnClickListener(this);
        }

        public void bind(Product p)
        {
            this.p = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
            imvCart.setImageResource(R.drawable.ic_action_add_to_cart);
        }
        //Hiển thị sản phẩm
        @Override
        public void onClick(View v)
        {
            iCartcontroller = (ICartcontroller) getActivity().getApplication();
            if(iCartcontroller.addToShoppingCart(p))
            {
                Toast.makeText(getActivity(), "Thêm " + p.getName() + " vào giỏ hàng",
                        Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getActivity(), "Sản phẩm " + p.getName() + " đã có trong giỏ hàng",
                        Toast.LENGTH_SHORT).show();
        }
    }
    private class ProductAdapter extends RecyclerView.Adapter<ProductViewholder>
    {
        ArrayList<Product> listProduct;

        public ProductAdapter(ArrayList<Product> listProduct) {
            this.listProduct = listProduct;
        }

        @NonNull
        @Override
        public ProductViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.product_item, parent, false);
            return new ProductViewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewholder holder, int position) {
            holder.bind(listProduct.get(position));

        }

        @Override
        public int getItemCount() {
            return listProduct.size();
        }
    }
}
